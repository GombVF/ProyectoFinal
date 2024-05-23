package org.example.proyectofinal.controller.clientes;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.proyectofinal.models.clientes.Cliente;
import org.example.proyectofinal.models.clientes.dtos.ClienteLoginDto;
import org.example.proyectofinal.models.codigosPostales.CodigoPostal;
import org.example.proyectofinal.models.envios.Envio;
import org.example.proyectofinal.models.envios.dto.EnvioCarritoDto;
import org.example.proyectofinal.models.envios.dto.EnvioClaveDto;
import org.example.proyectofinal.models.maquinas.Maquina;
import org.example.proyectofinal.models.modelosMaquinas.ModeloMaquina;
import org.example.proyectofinal.models.productos.Producto;
import org.example.proyectofinal.models.servicios.Servicio;
import org.example.proyectofinal.models.tiposServicios.TipoServicio;
import org.example.proyectofinal.models.ubicaciones.Ubicacion;
import org.example.proyectofinal.models.envios.dto.EnvioConsulta;
import org.example.proyectofinal.security.jwt.service.JwtService;
import org.example.proyectofinal.service.*;
import org.example.proyectofinal.util.enums.EstatusEnvio;
import org.example.proyectofinal.util.pageable.RenderPagina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private EnvioServiceImp envioServiceImp;
    @Autowired
    private UbicacionService ubicacionService;
    @Autowired
    private MaquinaService maquinaService;
    @Autowired
    private CodigoPostalService codigoPostalService;
    @Autowired
    private ModeloMaquinaService modeloMaquinaService;
    @Autowired
    private TipoServicioService tipoServicioService;
    @Autowired
    private ServicioService servicioService;
    @Autowired
    private EnvioService envioService;

    @GetMapping("/inicio")
    public String inicio(Model model, HttpSession session,
                         @ModelAttribute ClienteLoginDto authRequestDTO, HttpServletResponse response) {
        return "/clientes/inicio";
    }

    @GetMapping("/pedidos/catalogo")
    public String solicitarPedido(@RequestParam(name="page",defaultValue = "0")int page,
                                  Model model,
                                  HttpSession session,
                                  HttpServletRequest request,
                                  RedirectAttributes flash,
                                  HttpServletResponse response) {
        String token = null;
        if(request.getCookies() != null){
            for(Cookie cookie: request.getCookies()){
                if(cookie.getName().equals("accessToken")){
                    token = cookie.getValue();
                    break;
                }
            }
        }
        String username = jwtService.extractUsername(token);
        boolean hasAddress = clienteService.hasRegisteredAddress(username);
        if (!hasAddress) {
            flash.addFlashAttribute("hasNotAddress", hasAddress);
            return "redirect:/clientes/pedidos/inicio";
        }
        Pageable pagReq = PageRequest.of(page,10);
        Page<Producto> productos = productoService.findAll(pagReq);
        RenderPagina<Producto> render = new RenderPagina<>("catalogo",productos);
        model.addAttribute("productosPage",productos);
        model.addAttribute("page",render);
        return "/clientes/pedidos/catalogo";
    }

    @PostMapping("/pedidos/carrito")
    public String carrito(@RequestParam(name="page",defaultValue = "0") int page,
                          Model model,
                          @RequestParam("selectedProducts") List<Integer> selectedProducts,
                          HttpSession session,
                          HttpServletRequest request,
                          RedirectAttributes flash,
                          HttpServletResponse response) {
        Pageable pagReq = PageRequest.of(page,10);
        List<Producto> selectedProductos = new ArrayList<>();
        Page<Producto> productos;
        do{
            productos = productoService.findAll(pagReq);
            selectedProductos.addAll(productos.stream().filter(
                producto -> selectedProducts.contains(producto.getId())).toList());
            pagReq = productos.nextPageable();
        }while(productos.hasNext());

        List<EnvioCarritoDto> envioCarritoDtoList = new ArrayList<>();
        for(Producto p: selectedProductos){
            envioCarritoDtoList.add(new EnvioCarritoDto(1,p));
        }
        Page<EnvioCarritoDto> selectedProductosPage = new PageImpl<>(envioCarritoDtoList, pagReq, envioCarritoDtoList.size());
        RenderPagina<Producto> render = new RenderPagina<>("catalogo",productos);
        model.addAttribute("selectedProductos",selectedProductosPage);
        model.addAttribute("page",render);
        return "/clientes/pedidos/carrito";
    }

    @PostMapping("/pedidos/resumen")
    public String resumen(Model model,
                          @RequestParam("productIds") List<Integer> productIds,
                          @RequestParam("quantities") List<Integer> quantities,
                          HttpSession session,
                          HttpServletRequest request,
                          RedirectAttributes flash,
                          HttpServletResponse response) throws NoSuchAlgorithmException {
        String token = null;
        if(request.getCookies() != null){
            for(Cookie cookie: request.getCookies()){
                if(cookie.getName().equals("accessToken")){
                    token = cookie.getValue();
                    break;
                }
            }
        }
        String username = jwtService.extractUsername(token);
        Ubicacion ubicacion = clienteService.getClienteByTipoPersonaRfc(username).getDomicilio();
        List<Envio> envios = new ArrayList<>();
        Date fechasolicitud = new Date();
        EstatusEnvio estatusEnvio = EstatusEnvio.SOLICITUD;
        List<Producto> productos = new ArrayList<>();
        for (int i = 0; i < productIds.size(); i++){
            productos.add(productoService.getProducto(productIds.get(i)));
        }
        EnvioClaveDto dto = new EnvioClaveDto(username,ubicacion,productos,quantities,estatusEnvio,fechasolicitud);
        String clave = String.valueOf(dto.hashCode());
        for (int i = 0; i < productos.size(); i++){
            envioServiceImp.addEnvio(new Envio(clave,quantities.get(i),fechasolicitud,estatusEnvio,productos.get(i),
                ubicacion));
        }

        return "/clientes/pedidos/inicio";
    }

    @GetMapping("pedidos/consultarPendientes")
    public String pedidosPendientes(@RequestParam(name="page",defaultValue = "0") int page,
                                    Model model,
                                    HttpSession session,
                                    HttpServletRequest request,
                                    RedirectAttributes flash,
                                    HttpServletResponse response){
        Pageable pagReq = PageRequest.of(page,10);
        String token = null;
        if(request.getCookies() != null){
            for(Cookie cookie: request.getCookies()){
                if(cookie.getName().equals("accessToken")){
                    token = cookie.getValue();
                    break;
                }
            }
        }
        String username = jwtService.extractUsername(token);
        Cliente cliente = clienteService.getClienteByTipoPersonaRfc(username);

        List<Envio> enviosPendientes = envioService.getEnviosUsuario(cliente.getDomicilio().getId());
        enviosPendientes = enviosPendientes.stream().filter(envio -> {
            List<EstatusEnvio> estatusPendientes = List.of(EstatusEnvio.SOLICITUD, EstatusEnvio.ATENCION,
                EstatusEnvio.ENVIADO);
            return estatusPendientes.contains(envio.getStatus());
        }).toList();
        Set<EnvioConsulta> envioConsultas =
            enviosPendientes.stream().map(envio -> new EnvioConsulta(envio.getClaveProducto(), envio.getUbicacion(),
                envio.getEmpleado(), envio.getFechaSolicitud(), envio.getFechaEnvio(), envio.getFechaEntrega(), envio.getStatus())).collect(Collectors.toSet());
        List<EnvioConsulta> envioConsultaList = envioConsultas.stream().toList();
        Page<EnvioConsulta> enviosPendientesPage = new PageImpl<>(envioConsultaList, pagReq,
            envioConsultaList.size());
        RenderPagina<EnvioConsulta> render = new RenderPagina<>("pedidos/consultarPendientes", enviosPendientesPage);
        model.addAttribute("enviosPendientes",envioConsultaList);
        model.addAttribute("page",render);
        return "/clientes/pedidos/consultarPendientes";
    }

    @GetMapping("pedidos/consultarCompletados")
    public String pedidosCompletados(@RequestParam(name="page",defaultValue = "0") int page,
                                    Model model,
                                    HttpSession session,
                                    HttpServletRequest request,
                                    RedirectAttributes flash,
                                    HttpServletResponse response){
        Pageable pagReq = PageRequest.of(page,10);
        String token = null;
        if(request.getCookies() != null){
            for(Cookie cookie: request.getCookies()){
                if(cookie.getName().equals("accessToken")){
                    token = cookie.getValue();
                    break;
                }
            }
        }
        String username = jwtService.extractUsername(token);
        Cliente cliente = clienteService.getClienteByTipoPersonaRfc(username);

        List<Envio> enviosCompletados = envioService.getEnviosUsuario(cliente.getDomicilio().getId());
        enviosCompletados =
            enviosCompletados.stream().filter(envio -> envio.getStatus().equals(EstatusEnvio.ENTREGADO)).toList();
        Set<EnvioConsulta> envioConsultas =
            enviosCompletados.stream().map(envio -> new EnvioConsulta(envio.getClaveProducto(), envio.getUbicacion(),
                envio.getEmpleado(), envio.getFechaSolicitud(), envio.getFechaEnvio(), envio.getFechaEntrega(),
                envio.getStatus())).collect(Collectors.toSet());
        List<EnvioConsulta> envioConsultaList = envioConsultas.stream().toList();
        Page<EnvioConsulta> enviosCompletadosPage = new PageImpl<>(envioConsultaList, pagReq,
            envioConsultaList.size());
        RenderPagina<EnvioConsulta> render = new RenderPagina<>("pedidos/consultarCompletados", enviosCompletadosPage);
        model.addAttribute("enviosCompletados",envioConsultaList);
        model.addAttribute("page",render);
        return "/clientes/pedidos/consultarCompletados";
    }

    @GetMapping("/detallePedidoPendiente/{claveProducto}")
    public String detallePedidoPendiente(@RequestParam(name="page",defaultValue = "0") int page,
                                @PathVariable("claveProducto") String clavePedido,
                                Model model,
                                HttpSession session,
                                HttpServletRequest request,
                                RedirectAttributes flash,
                                HttpServletResponse response){
        Pageable pagReq = PageRequest.of(page,10);
        String token = null;
        if(request.getCookies() != null){
            for(Cookie cookie: request.getCookies()){
                if(cookie.getName().equals("accessToken")){
                    token = cookie.getValue();
                    break;
                }
            }
        }
        String username = jwtService.extractUsername(token);
        Cliente cliente = clienteService.getClienteByTipoPersonaRfc(username);

        List<Envio> enviosPendientes = envioService.getEnviosUsuario(cliente.getDomicilio().getId());
        enviosPendientes = enviosPendientes.stream().filter(envio -> {
            List<EstatusEnvio> estatusPendientes = List.of(EstatusEnvio.SOLICITUD, EstatusEnvio.ATENCION,
                EstatusEnvio.ENVIADO);
            return estatusPendientes.contains(envio.getStatus());
        }).toList();
        Page<Envio> enviosPendientesPage = new PageImpl<>(enviosPendientes, pagReq,
            enviosPendientes.size());
        RenderPagina<Envio> render = new RenderPagina<>("detallePedidoPendiente/" + clavePedido, enviosPendientesPage);
        model.addAttribute("enviosPendientes",enviosPendientesPage);
        model.addAttribute("page",render);
        return "/clientes/pedidos/detallePendientes";
    }

    @GetMapping("/detallePedidoCompletados/{claveProducto}")
    public String detallePedidoCompletado(@RequestParam(name="page",defaultValue = "0") int page,
                                         @PathVariable("claveProducto") String clavePedido,
                                         Model model,
                                         HttpSession session,
                                         HttpServletRequest request,
                                         RedirectAttributes flash,
                                         HttpServletResponse response){
        Pageable pagReq = PageRequest.of(page,10);
        String token = null;
        if(request.getCookies() != null){
            for(Cookie cookie: request.getCookies()){
                if(cookie.getName().equals("accessToken")){
                    token = cookie.getValue();
                    break;
                }
            }
        }
        String username = jwtService.extractUsername(token);
        Cliente cliente = clienteService.getClienteByTipoPersonaRfc(username);

        List<Envio> enviosCompletados = envioService.getEnviosUsuario(cliente.getDomicilio().getId());
        enviosCompletados = enviosCompletados.stream().filter(envio -> envio.getStatus() == EstatusEnvio.ENTREGADO).toList();
        Page<Envio> enviosCompletadosPage = new PageImpl<>(enviosCompletados, pagReq,
            enviosCompletados.size());
        RenderPagina<Envio> render = new RenderPagina<>("detallePedidoCompletados/" + clavePedido, enviosCompletadosPage);
        model.addAttribute("enviosCompletados",enviosCompletadosPage);
        model.addAttribute("page",render);
        return "/clientes/pedidos/detallesCompletado";
    }



    @GetMapping("domicilio")
    public String validaDomicilio(Model model,
                                  HttpSession session,
                                  HttpServletRequest request,
                                  RedirectAttributes flash,
                                  HttpServletResponse response){
        String token = null;
        if(request.getCookies() != null){
            for(Cookie cookie: request.getCookies()){
                if(cookie.getName().equals("accessToken")){
                    token = cookie.getValue();
                    break;
                }
            }
        }
        String username = jwtService.extractUsername(token);
        Ubicacion ubicacion = clienteService.getClienteByTipoPersonaRfc(username).getDomicilio();
        if (ubicacion != null){
            return "redirect:/clientes/consultarDomicilio";
        }
        else{
            return "redirect:/clientes/solicitudRegistroDomicilio";
        }

    }

    @GetMapping("solicitudRegistroDomicilio")
    public String solicitudRegistroDomicilio(Model model) {
        Ubicacion ubicacion = new Ubicacion();
        List<CodigoPostal> cps = codigoPostalService.findAll();
        model.addAttribute("codigosPostales",cps);
        model.addAttribute("domicilio",ubicacion);
        return "/clientes/domicilio/registrarDomicilio";
    }

    @PostMapping("regitrarDomicilio")
    public String registrarDomicilio(Model model,
                                     @ModelAttribute Ubicacion ubicacion,
                                     HttpSession session,
                                     HttpServletRequest request,
                                     RedirectAttributes flash,
                                     HttpServletResponse response){
        String token = null;
        if(request.getCookies() != null){
            for(Cookie cookie: request.getCookies()){
                if(cookie.getName().equals("accessToken")){
                    token = cookie.getValue();
                    break;
                }
            }
        }
        String username = jwtService.extractUsername(token);
        //Ubicacion ubicacion = ubicacionClienteMapper.UbicacionClienteDtoToUbicacion(dto);
        Ubicacion newUbicacion = ubicacionService.addUbicacion(ubicacion);
        Cliente cliente = clienteService.getClienteByTipoPersonaRfc(username);
        cliente.setDomicilio(newUbicacion);
        clienteService.updateCliente(cliente);

        return "redirect:/clientes/consultarDomicilio";
    }

    @GetMapping("consultarDomicilio")
    public String consultardomicilio(Model model,
                                     HttpSession session,
                                     HttpServletRequest request,
                                     RedirectAttributes flash,
                                     HttpServletResponse response){
        String token = null;
        if(request.getCookies() != null){
            for(Cookie cookie: request.getCookies()){
                if(cookie.getName().equals("accessToken")){
                    token = cookie.getValue();
                    break;
                }
            }
        }
        String username = jwtService.extractUsername(token);
        Ubicacion ubicacion = clienteService.getClienteByTipoPersonaRfc(username).getDomicilio();
        //UbicacionClienteDto dto = ubicacionClienteMapper.UbicacionToUbicacionClienteDto(ubicacion);
        model.addAttribute("domicilios",ubicacion);
        return "/clientes/domicilio/consultarDomicilio";

    }

    @GetMapping("consultarMaquinas")
    public String consultarMaquinas(@RequestParam(name="page",defaultValue = "0") int page,
                                    Model model,
                                    HttpSession session,
                                    HttpServletRequest request,
                                    RedirectAttributes flash,
                                    HttpServletResponse response){
        Pageable pagReq = PageRequest.of(page,10);
        String token = null;
        if(request.getCookies() != null){
            for(Cookie cookie: request.getCookies()){
                if(cookie.getName().equals("accessToken")){
                    token = cookie.getValue();
                    break;
                }
            }
        }

        String username = jwtService.extractUsername(token);
        Cliente cliente = clienteService.getClienteByTipoPersonaRfc(username);
        List<Maquina> maquinas = maquinaService.getMaquinasUsuario(cliente.getId());
        Page<Maquina> maquinasPage = new PageImpl<>(maquinas, pagReq, maquinas.size());
        RenderPagina<Maquina> render = new RenderPagina<>("consultarMaquinas",maquinasPage);
        model.addAttribute("maquinas",maquinasPage);
        model.addAttribute("page",render);
        return "/clientes/maquinas/consultarMaquinas";
    }

    @GetMapping("solicitudRegistro")
    public String solicitudRegistro(Model model,
                                   HttpSession session,
                                   HttpServletRequest request,
                                   RedirectAttributes flash,
                                   HttpServletResponse response){
        Maquina maquina = new Maquina();
        List<ModeloMaquina> mm = modeloMaquinaService.getModelosMaquinas();
        model.addAttribute("modeloMaquina",mm);
        model.addAttribute("maquina",maquina);
        return "/clientes/maquinas/registrar";
    }

    @PostMapping("/registrarMaquina")
    public String registrarMaquina(Model model,
                                   @ModelAttribute Maquina maquina,
                                   HttpSession session,
                                   HttpServletRequest request,
                                   RedirectAttributes flash,
                                   HttpServletResponse response){
        String token = null;
        if(request.getCookies() != null){
            for(Cookie cookie: request.getCookies()){
                if(cookie.getName().equals("accessToken")){
                    token = cookie.getValue();
                    break;
                }
            }
        }
        String username = jwtService.extractUsername(token);
        Cliente cliente = clienteService.getClienteByTipoPersonaRfc(username);
        //Maquina maquina = maquinaMapper.MaquinaRegistoDtoToMaquina(dto);
        maquina.setCliente(cliente);
        maquinaService.addMaquina(maquina);

        return "redirect:/clientes/consultarMaquinas";

    }

    @GetMapping("/eliminarMaquina/{id}")
    public String eliminarMaquina(@PathVariable("id") Long id,
                                  RedirectAttributes flash){
        maquinaService.deleteMaquinaById(id);
        return "redirect:/clientes/consultarMaquinas";
    }

    @GetMapping("/eliminarDomicilio/{id}")
    public String eliminarDomicilio(@PathVariable("id") Integer id,
                                    HttpServletRequest request,
                                    RedirectAttributes flash){
        String token = null;
        if(request.getCookies() != null){
            for(Cookie cookie: request.getCookies()){
                if(cookie.getName().equals("accessToken")){
                    token = cookie.getValue();
                    break;
                }
            }
        }
        String username = jwtService.extractUsername(token);
        Cliente cliente = clienteService.getClienteByTipoPersonaRfc(username);
        cliente.setDomicilio(null);
        clienteService.updateCliente(cliente);
        try{
            ubicacionService.deleteUbicacionById(id);
        }catch (Exception ignored){

        }

        return "redirect:/clientes/inicio";
    }

    @GetMapping("servicio")
    public String validaServicio(Model model,
                                 HttpSession session,
                                 HttpServletRequest request,
                                 RedirectAttributes flash,
                                 HttpServletResponse response){
        String token = null;
        if(request.getCookies() != null){
            for(Cookie cookie: request.getCookies()){
                if(cookie.getName().equals("accessToken")){
                    token = cookie.getValue();
                    break;
                }
            }
        }
        String username = jwtService.extractUsername(token);
        Cliente cliente = clienteService.getClienteByTipoPersonaRfc(username);
        if (maquinaService.clienteHasMaquina(cliente.getId())){
            return "redirect:/clientes/solicitudServicio";
        }
        else{
            //todo: poner atributos de modelo de que no se encuentra disponible ya que no hay maquina registrada
            return "redirect:/clientes/inicio";
        }

    }

    @GetMapping("solicitudServicio")
    public String crearServicio(Model model,
                                HttpSession session,
                                HttpServletRequest request,
                                RedirectAttributes flash,
                                HttpServletResponse response){
        String token = null;
        if(request.getCookies() != null){
            for(Cookie cookie: request.getCookies()){
                if(cookie.getName().equals("accessToken")){
                    token = cookie.getValue();
                    break;
                }
            }
        }
        String username = jwtService.extractUsername(token);
        Cliente cliente = clienteService.getClienteByTipoPersonaRfc(username);
        List<Maquina> maquinas = maquinaService.getMaquinasUsuario(cliente.getId());
        List<TipoServicio> tipoServicios = tipoServicioService.getTiposServicios();
        Servicio servicio = new Servicio();
        model.addAttribute("servicio",servicio);
        model.addAttribute("tipoServicio",tipoServicios);
        model.addAttribute("maquina",maquinas);
        return "/clientes/servicios/registrar";
    }

    @PostMapping("registrarServicio")
    public String registrarServicio(Model model,
                                    @ModelAttribute Servicio servicio,
                                    HttpSession session,
                                    HttpServletRequest request,
                                    RedirectAttributes flash,
                                    HttpServletResponse response){

        servicio.setFechaInicio(new Date());
        servicioService.addServicio(servicio);
        return "redirect:/clientes/misServiciosPendientes";
    }

    @GetMapping("misServiciosPendientes")
    public String consultarServiciosPendientes(@RequestParam(name="page",defaultValue = "0") int page,
                                     Model model,
                                     HttpSession session,
                                     HttpServletRequest request,
                                     RedirectAttributes flash,
                                     HttpServletResponse response){
        Pageable pagReq = PageRequest.of(page,10);
        String token = null;
        if(request.getCookies() != null){
            for(Cookie cookie: request.getCookies()){
                if(cookie.getName().equals("accessToken")){
                    token = cookie.getValue();
                    break;
                }
            }
        }
        String username = jwtService.extractUsername(token);
        Cliente cliente = clienteService.getClienteByTipoPersonaRfc(username);
        List<Servicio> serviciosPendientes = servicioService.getServiciosPendientesCliente(cliente.getId());
        Page<Servicio> servicioPendientePage = new PageImpl<>(serviciosPendientes, pagReq, serviciosPendientes.size());
        RenderPagina<Servicio> renderPendiente = new RenderPagina<>("misServiciosPendientes", servicioPendientePage);
        model.addAttribute("serviciosPendientes",servicioPendientePage);
        model.addAttribute("page",renderPendiente);
        return "/clientes/servicios/consultarServiciosPendientes";
    }

    @GetMapping("misServiciosCompletados")
    public String consultarServiciosCompletados(@RequestParam(name="page",defaultValue = "0") int page,
                                     Model model,
                                     HttpSession session,
                                     HttpServletRequest request,
                                     RedirectAttributes flash,
                                     HttpServletResponse response){
        Pageable pagReq = PageRequest.of(page,10);
        String token = null;
        if(request.getCookies() != null){
            for(Cookie cookie: request.getCookies()){
                if(cookie.getName().equals("accessToken")){
                    token = cookie.getValue();
                    break;
                }
            }
        }
        String username = jwtService.extractUsername(token);
        Cliente cliente = clienteService.getClienteByTipoPersonaRfc(username);
        List<Servicio> serviciosCompletados = servicioService.getServiciosCompletadosCliente(cliente.getId());
        Page<Servicio> serviciosCompletadosePage = new PageImpl<>(serviciosCompletados, pagReq,
            serviciosCompletados.size());
        RenderPagina<Servicio> renderCompletos = new RenderPagina<>("misServiciosCompletados", serviciosCompletadosePage);
        model.addAttribute("serviciosCompletos",serviciosCompletadosePage);
        model.addAttribute("page",renderCompletos);
        return "/clientes/servicios/consultarServiciosCompletados";
    }






}
