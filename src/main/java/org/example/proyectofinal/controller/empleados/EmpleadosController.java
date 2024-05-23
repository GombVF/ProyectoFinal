package org.example.proyectofinal.controller.empleados;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.proyectofinal.models.areas.Area;
import org.example.proyectofinal.models.clientes.Cliente;
import org.example.proyectofinal.models.clientes.dtos.ClienteLoginDto;
//import org.example.proyectofinal.security.jwt.JWTTokenProvider;
import org.example.proyectofinal.models.clientes.dtos.ClienteRegisterDto;
import org.example.proyectofinal.models.clientes.mappers.ClienteMapper;
import org.example.proyectofinal.models.empleados.Empleado;
import org.example.proyectofinal.models.envios.Envio;
import org.example.proyectofinal.models.envios.dto.EnvioConsulta;
import org.example.proyectofinal.models.personasFisicas.PersonaFisica;
import org.example.proyectofinal.models.personasMorales.PersonaMoral;
import org.example.proyectofinal.models.servicios.Servicio;
import org.example.proyectofinal.models.ubicaciones.Ubicacion;
import org.example.proyectofinal.security.jwt.service.JwtService;
import org.example.proyectofinal.service.*;
import org.example.proyectofinal.util.enums.EstatusEnvio;
import org.example.proyectofinal.util.enums.TipoPersona;
import org.example.proyectofinal.util.pageable.RenderPagina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/empleados")
public class EmpleadosController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private PersonaFisicaService personaFisicaService;
    @Autowired
    private PersonaMoralService personaMoralService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RolService rolService;
    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private EnvioService envioService;
    @Autowired
    private ServicioService servicioService;

    @GetMapping("/inicio")
    public String inicio(Model model, HttpSession session,
                         @ModelAttribute ClienteLoginDto authRequestDTO, HttpServletResponse response) {
        return "/empleados/inicio";
    }

    @GetMapping("rrhh/solicitudRegistroCliente")
    public String solicitudCliente(Model model){
        ClienteRegisterDto dto = new ClienteRegisterDto();
        model.addAttribute("cliente",dto);
        return "/empleados/rrhh/alta";
    }

    @PostMapping("rrhh/registroCliente")
    public String registroCliente(Model model,
                                  @ModelAttribute ClienteRegisterDto dto,
                                  HttpSession session,
                                  HttpServletRequest request,
                                  RedirectAttributes flash,
                                  HttpServletResponse response){
        Cliente cliente = clienteService.getClienteByTipoPersonaRfc(dto.getRfc());
        if (cliente != null){
            // todo regresar el registro y decir que ya existe
            flash.addFlashAttribute("usuarioRegistrado",true);
            return "redirect:/empleados/rrhh/solicitudRegistroCliente";
        } else if (Objects.equals(dto.getTipoPersona(), TipoPersona.FISICA.getTipoPersona())){
            PersonaFisica pf = new PersonaFisica(dto.getNombre(),dto.getPaterno(),dto.getMaterno(),dto.getRfc());
            pf = personaFisicaService.addPersonaFisica(pf);
            cliente = new Cliente(dto.getTipoPersona(), pf, passwordEncoder.encode("contraseña"),
                rolService.geRolByRol("Cliente"));
            clienteService.addCliente(cliente);
        } else{
            PersonaMoral pm = new PersonaMoral(dto.getRazonSocial(),dto.getRfc());
            pm = personaMoralService.addPersonaMoral(pm);
            cliente = new Cliente(dto.getTipoPersona(), pm, passwordEncoder.encode("contraseña"),
                rolService.geRolByRol("Cliente"));
            clienteService.addCliente(cliente);
        }
        return "redirect:/empleados/rrhh/clientes";
    }

    @GetMapping("rrhh/clientes")
    public String consultarClientes(@RequestParam(name="page",defaultValue = "0") int page,
                                    Model model,
                                    HttpSession session,
                                    HttpServletRequest request,
                                    RedirectAttributes flash,
                                    HttpServletResponse response){
        Pageable pagReq = PageRequest.of(page,10);
        List<Cliente> clientes = clienteService.getClientes();
        Page<Cliente> clientesPage = new PageImpl<>(clientes, pagReq,clientes.size());
        RenderPagina<Cliente> render = new RenderPagina<>("clientes",clientesPage);
        model.addAttribute("clientes",clientesPage);
        model.addAttribute("page",render);
        return "/empleados/rrhh/clientes";
    }
    // todo: ver si da tiempo de hacer el actualizar
    //@GetMapping("actualizarCliente/{id}")
    //public String actualizarCliente(@PathVariable("id") Integer id, Model model){
    //    Optional<Cliente> cliente = clienteService.getClienteById(id);
    //    ClienteRegisterDto dto = clienteMapper.
    //    model.addAttribute("cliente",cliente);
    //    return "empleados/alta";
    //}

    @GetMapping("rrhh/eliminarCliente/{id}")
    public String eliminarCliente(@PathVariable("id") Integer id,RedirectAttributes flash){
        clienteService.deleteClienteById(id);
        return "redirect:/empleados/rrhh/clientes";
    }


    @GetMapping("rrhh/solicitudRegistroEmpleado")
    public String solicitudEmpleado(Model model){
        ClienteRegisterDto dto = new ClienteRegisterDto();
        model.addAttribute("empleado",dto);
        return "/empleados/rrhh/alta_empleado";
    }

    @PostMapping("rrhh/registroEmpleado")
    public String registroEmpleado(Model model,
                                  @ModelAttribute ClienteRegisterDto dto,
                                  HttpSession session,
                                  HttpServletRequest request,
                                  RedirectAttributes flash,
                                  HttpServletResponse response){
        Empleado empleado = empleadoService.getEmpleadoByTipoPersonaRfc(dto.getRfc());
        if (empleado != null){
            // todo regresar el registro y decir que ya existe
            flash.addFlashAttribute("empleadoRegistrado",true);
            return "redirect:/empleados/rrhh/solicitudRegistroEmpleado";
        }
        PersonaFisica pf = new PersonaFisica(dto.getNombre(),dto.getPaterno(),dto.getMaterno(),dto.getRfc());
        pf = personaFisicaService.addPersonaFisica(pf);
        Area area = areaService.getAreaByNombreArea("Sin Area");
        empleado = new Empleado(pf,area,true,passwordEncoder.encode("contraseña"));
        empleadoService.addEmpleado(empleado);
        return "redirect:/empleados/rrhh/empleados";
    }

    @GetMapping("rrhh/empleados")
    public String consultarEmpleados(@RequestParam(name="page",defaultValue = "0") int page,
                                    Model model,
                                    HttpSession session,
                                    HttpServletRequest request,
                                    RedirectAttributes flash,
                                    HttpServletResponse response){
        Pageable pagReq = PageRequest.of(page,10);
        List<Empleado> empleados = empleadoService.getEmpleados();
        Page<Empleado> empleadoPage = new PageImpl<>(empleados, pagReq,empleados.size());
        RenderPagina<Empleado> render = new RenderPagina<>("empleados",empleadoPage);
        model.addAttribute("empleados",empleadoPage);
        model.addAttribute("page",render);
        return "/empleados/rrhh/empleados";
    }
    // todo: ver si da tiempo de hacer el actualizar
    //@GetMapping("actualizarCliente/{id}")
    //public String actualizarCliente(@PathVariable("id") Integer id, Model model){
    //    Optional<Cliente> cliente = clienteService.getClienteById(id);
    //    ClienteRegisterDto dto = clienteMapper.
    //    model.addAttribute("cliente",cliente);
    //    return "empleados/alta";
    //}

    @GetMapping("rrhh/eliminarEmpleado/{id}")
    public String eliminarEmpleado(@PathVariable("id") Integer id,RedirectAttributes flash){
        empleadoService.deleteEmpleado(id);
        return "redirect:/empleados/rrhh/empleados";
    }

    @GetMapping("pedidos/consultarSinAtender")
    public String consultarPedidosSinAntender(@RequestParam(name="page",defaultValue = "0") int page,
                                     Model model,
                                     HttpSession session,
                                     HttpServletRequest request,
                                     RedirectAttributes flash,
                                     HttpServletResponse response){
        Pageable pagReq = PageRequest.of(page,10);
        List<Envio> envios = envioService.getEnviosSinAtender();
        Set<EnvioConsulta> envioConsultas =
            envios.stream().map(envio -> new EnvioConsulta(envio.getClaveProducto(), envio.getUbicacion(),
                envio.getEmpleado(), envio.getFechaSolicitud(), envio.getFechaEnvio(), envio.getFechaEntrega(),
                envio.getStatus())).collect(Collectors.toSet());
        List<EnvioConsulta> envioConsultaList = envioConsultas.stream().toList();
        Page<EnvioConsulta> emviosPage = new PageImpl<>(envioConsultaList, pagReq,envioConsultaList.size());
        RenderPagina<EnvioConsulta> render = new RenderPagina<>("pedidos/consultarSinAtender",emviosPage);
        model.addAttribute("envios",emviosPage);
        model.addAttribute("page",render);
        return "/empleados/pedidos/pedidosSinAtender";
    }

    @GetMapping("pedidos/atenderPedido/{claveProducto}")
    public String atenderPedidoPendiente(@PathVariable("claveProducto") String claveProducto,
                                Model model,
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
        Empleado empleado = empleadoService.getEmpleadoByTipoPersonaRfc(username);
        List<Envio> envios = envioService.getEnvioByClaveEnvio(claveProducto);
        for(Envio e: envios){
            e.setEmpleado(empleado);
            envioService.updateEnvio(e);
        }
        return "redirect:/empleados/pedidos/misPedidosPendientes";
    }

    @GetMapping("pedidos/misPedidosPendientes")
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
        Empleado empleado = empleadoService.getEmpleadoByTipoPersonaRfc(username);
        List<Envio> envios = envioService.getEnviosPendientesByEmpleadoId(empleado.getId());
        Set<EnvioConsulta> envioConsultas =
            envios.stream().map(envio -> new EnvioConsulta(envio.getClaveProducto(), envio.getUbicacion(),
                envio.getEmpleado(), envio.getFechaSolicitud(), envio.getFechaEnvio(), envio.getFechaEntrega(),
                envio.getStatus())).collect(Collectors.toSet());
        List<EnvioConsulta> envioConsultaList = envioConsultas.stream().toList();
        Page<EnvioConsulta> emviosPage = new PageImpl<>(envioConsultaList, pagReq,envioConsultaList.size());
        RenderPagina<EnvioConsulta> render = new RenderPagina<>("pedidos/consultarSinAtender",emviosPage);
        model.addAttribute("envios",emviosPage);
        model.addAttribute("page",render);
        return "empleados/pedidos/pedidosPendientes";
    }

    @GetMapping("pedidos/atender/{claveProducto}")
    public String atenderPedido(@PathVariable("claveProducto") String claveProducto,
                                Model model,
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
        Empleado empleado = empleadoService.getEmpleadoByTipoPersonaRfc(username);
        Date fechaEnvioEntrega = new Date();
        EstatusEnvio enviado = EstatusEnvio.ENVIADO;
        EstatusEnvio entregado = EstatusEnvio.ENTREGADO;
        List<Envio> envios = envioService.getEnvioByClaveEnvio(claveProducto);
        for(Envio e: envios){
            if (e.getFechaEnvio()==null){
                e.setFechaEnvio(fechaEnvioEntrega);
                e.setStatus(enviado);
                envioService.updateEnvio(e);
            } else if (e.getFechaEnvio() != null && e.getFechaEntrega() == null){
                e.setFechaEntrega(fechaEnvioEntrega);
                e.setStatus(entregado);
                envioService.updateEnvio(e);
            }
        }
        return "redirect:/empleados/pedidos/misPedidosPendientes";

    }
    @GetMapping("pedidos/detallePedidoPendiente/{claveProducto}")
    public String detallePedidoPendiente(@RequestParam(name="page",defaultValue = "0") int page,
                                         @PathVariable("claveProducto") String clavePedido,
                                         Model model,
                                         HttpSession session,
                                         HttpServletRequest request,
                                         RedirectAttributes flash,
                                         HttpServletResponse response){
        Pageable pagReq = PageRequest.of(page,10);

        List<Envio> enviosPendientes = envioService.getEnvioByClaveEnvio(clavePedido);
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
        return "/empleados/pedidos/detallePendientes";
    }

    @GetMapping("pedidos/misPedidosCompletados")
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
        Empleado empleado = empleadoService.getEmpleadoByTipoPersonaRfc(username);
        List<Envio> envios = envioService.getEnviosCompletadosByEmpleadoId(empleado.getId());
        Set<EnvioConsulta> envioConsultas =
            envios.stream().map(envio -> new EnvioConsulta(envio.getClaveProducto(), envio.getUbicacion(),
                envio.getEmpleado(), envio.getFechaSolicitud(), envio.getFechaEnvio(), envio.getFechaEntrega(),
                envio.getStatus())).collect(Collectors.toSet());
        List<EnvioConsulta> envioConsultaList = envioConsultas.stream().toList();
        Page<EnvioConsulta> enviosCompletadosPage = new PageImpl<>(envioConsultaList, pagReq,envioConsultaList.size());
        RenderPagina<EnvioConsulta> render = new RenderPagina<>("pedidos/misPedidosCompletados",enviosCompletadosPage);
        model.addAttribute("envios",enviosCompletadosPage);
        model.addAttribute("page",render);
        return "empleados/pedidos/pedidosCompletados";
    }


    @GetMapping("servicios/consultarSinAtender")
    public String consultarServiciosSinAntender(@RequestParam(name="page",defaultValue = "0") int page,
                                              Model model,
                                              HttpSession session,
                                              HttpServletRequest request,
                                              RedirectAttributes flash,
                                              HttpServletResponse response){
        Pageable pagReq = PageRequest.of(page,10);
        List<Servicio> servicios = servicioService.getServiciosSinAtender();
        Page<Servicio> servicioPage = new PageImpl<>(servicios, pagReq,servicios.size());
        RenderPagina<Servicio> render = new RenderPagina<>("servicios/consultarSinAtender",servicioPage);
        model.addAttribute("servicios",servicioPage);
        model.addAttribute("page",render);
        return "/empleados/servicios/serviciosSinAtender";
    }

    @GetMapping("servicios/atenderServicio/{id}")
    public String atenderServicioPendiente(@PathVariable("id") Integer id,
                                         Model model,
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
        Empleado empleado = empleadoService.getEmpleadoByTipoPersonaRfc(username);
        Optional<Servicio> servicio = servicioService.getServicioById(id);
        servicio.get().setEmpleado(empleado);
        servicioService.updateServicio(servicio.get());
        return "redirect:/empleados/servicios/misServiciosPendientes";
    }

    @GetMapping("servicios/misServiciosPendientes")
    public String serviciosPendientes(@RequestParam(name="page",defaultValue = "0") int page,
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
        Empleado empleado = empleadoService.getEmpleadoByTipoPersonaRfc(username);
        List<Servicio> serviciosPendientes = servicioService.getServiciosPendientesEmpleado(empleado.getId());
        Page<Servicio> serviciosPage = new PageImpl<>(serviciosPendientes, pagReq,serviciosPendientes.size());
        RenderPagina<Servicio> render = new RenderPagina<>("servicios/misServiciosPendientes",serviciosPage);
        model.addAttribute("servicios",serviciosPage);
        model.addAttribute("page",render);
        return "empleados/servicios/serviciosPendientes";
    }

    @GetMapping("servicios/atender/{id}")
    public String atenderServicio(@PathVariable("id") Integer id,
                                Model model,
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
        Empleado empleado = empleadoService.getEmpleadoByTipoPersonaRfc(username);
        Date fechaInicioFin = new Date();
        Optional<Servicio> servicio = servicioService.getServicioById(id);
        if (servicio.get().getFechaInicio() == null){
            servicio.get().setFechaInicio(fechaInicioFin);
            servicioService.updateServicio(servicio.get());
        } else if(servicio.get().getFechaInicio() != null && servicio.get().getFechaFin() == null){
            servicio.get().setFechaFin(fechaInicioFin);
            servicioService.updateServicio(servicio.get());
        }
        return "redirect:/empleados/servicios/misServiciosPendientes";

    }

    @GetMapping("servicios/misServiciosCompletados")
    public String serviciosCompletados(@RequestParam(name="page",defaultValue = "0") int page,
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
        Empleado empleado = empleadoService.getEmpleadoByTipoPersonaRfc(username);
        List<Servicio> servicios = servicioService.getServiciosCompletadosEmpleado(empleado.getId());
        Page<Servicio> serviciosCompletadosPage = new PageImpl<>(servicios, pagReq,servicios.size());
        RenderPagina<Servicio> render = new RenderPagina<>("servicios/misServiciosCompletados",
            serviciosCompletadosPage);
        model.addAttribute("servicios",serviciosCompletadosPage);
        model.addAttribute("page",render);
        return "empleados/servicios/serviciosCompletados";
    }




}
