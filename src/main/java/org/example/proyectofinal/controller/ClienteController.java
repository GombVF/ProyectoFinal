package org.example.proyectofinal.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.proyectofinal.models.clientes.Cliente;
import org.example.proyectofinal.models.clientes.dtos.ClienteLoginDto;
import org.example.proyectofinal.models.clientes.dtos.ClienteRegisterDto;
import org.example.proyectofinal.models.personasFisicas.PersonaFisica;
import org.example.proyectofinal.models.personasMorales.PersonaMoral;
import org.example.proyectofinal.security.jwt.JWTTokenProvider;
import org.example.proyectofinal.security.jwt.request.JwtRequest;
import org.example.proyectofinal.service.ClienteService;
import org.example.proyectofinal.service.PersonaFisicaService;
import org.example.proyectofinal.service.PersonaMoralService;
import org.example.proyectofinal.util.enums.TipoPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private PersonaFisicaService personaFisicaService;
    @Autowired
    private PersonaMoralService personaMoralService;
    @Autowired
    private JWTTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/registrar")
    public String registrar(Model model) {
        ClienteRegisterDto cliente = new ClienteRegisterDto();
        model.addAttribute("cliente",cliente);
        model.addAttribute("contenido","Alta de Barco");
        return "clientes/alta";
    }

    @PostMapping("/registroExitoso")
    public String registroExitoso(@ModelAttribute("cliente") ClienteRegisterDto clienteRegisterDto, Model model) {
        if (clienteRegisterDto.getTipoPersona() == "Persona Física") {
            PersonaFisica clienteFisica = personaFisicaService.addPersonaFisica(
                PersonaFisica.builder().nombre(clienteRegisterDto.getNombre()).paterno(clienteRegisterDto.getPaterno())
                .materno(clienteRegisterDto.getMaterno()).rfc(clienteRegisterDto.getRfc()).build());
            Cliente cliente = new Cliente(clienteRegisterDto.getTipoPersona(), clienteFisica);
            clienteService.addCliente(cliente);
        }
        else if (clienteRegisterDto.getTipoPersona() == "Persona Moral"){
            PersonaMoral clienteMoral = personaMoralService.addPersonaMoral(
                PersonaMoral.builder().razonSocial(clienteRegisterDto.getRazonSocial()).rfc(clienteRegisterDto.getRfc()).build());
            Cliente cliente = new Cliente(clienteRegisterDto.getTipoPersona(), clienteMoral);
            clienteService.addCliente(cliente);
        }

        return "redirect:login";
    }


    @PostMapping("/inicio")
    public String inicio(Model model) {
        return "clientes/inicio";
    }
    @GetMapping("/login")
    public String login(Model model) {
        return "clientes/login";
    }

    @PostMapping("/login_success_handler")
    public String loginSuccessHandler() {
        System.out.println("Logging user login success...");
        return "clientes/inicio";
    }

    @PostMapping("/login_failure_handler")
    public String loginFailureHandler() {
        System.out.println("Login failure handler....");
        return "clientes/login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Cliente());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(Cliente user){
        return "register_success";
    }

    @PostMapping("/token")
    public String createAuthenticationToken(Model model, HttpSession session,
                                            @ModelAttribute ClienteLoginDto loginUserRequest,
                                            HttpServletResponse res) throws Exception {
        try {
            Cliente user = clienteService.getClienteByTipoPersonaRfc(loginUserRequest.getRfc());
            Authentication authentication = authenticate(loginUserRequest.getRfc(),
                loginUserRequest.getPassword());
            String jwtToken = jwtTokenProvider.generateJwtTokenClientes(authentication, user);
            JwtRequest jwtRequest;
            if (user.getTipoPersona() == TipoPersona.FISICA){
                jwtRequest = new JwtRequest(jwtToken, (long)user.getId(), user.getPersonaFisica().getRfc(),
                    jwtTokenProvider.getExpiryDuration(), authentication.getAuthorities());
            }
            else{
                jwtRequest = new JwtRequest(jwtToken, (long)user.getId(), user.getPersonaFisica().getRfc(),
                    jwtTokenProvider.getExpiryDuration(), authentication.getAuthorities());
            }

            Cookie cookie = new Cookie("token",jwtToken);
            cookie.setMaxAge(Integer.MAX_VALUE);
            res.addCookie(cookie);
            session.setAttribute("msg","Login OK!");
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            session.setAttribute("msg","Bad Credentials");
            return "redirect:/login";
        }
        return "redirect:/inicio";
    }

    private Authentication authenticate(String username, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }




}
