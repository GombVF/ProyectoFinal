package org.example.proyectofinal.controller.login;


import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Cookie;
import org.example.proyectofinal.models.clientes.Cliente;
import org.example.proyectofinal.models.clientes.dtos.ClienteLoginDto;
import org.example.proyectofinal.models.empleados.Empleado;
//import org.example.proyectofinal.security.jwt.JWTTokenProvider;
import org.example.proyectofinal.security.jwt.dto.JwtResponseDTO;
import org.example.proyectofinal.security.jwt.request.JwtRequest;
import org.example.proyectofinal.security.jwt.service.JwtService;
import org.example.proyectofinal.service.ClienteService;
import org.example.proyectofinal.service.PersonaFisicaService;
import org.example.proyectofinal.service.PersonaMoralService;
import org.example.proyectofinal.service.RolesEmpleadoService;
import org.example.proyectofinal.util.enums.TipoPersona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private RolesEmpleadoService rolesEmpleadoService;
    //@Autowired
    //private JWTTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;


    @GetMapping("/login")
    public String login(Model model) {

        return "/login/login";
    }

    //@PostMapping("/login_success_handler")
    //public String loginSuccessHandler() {
    //    System.out.println("Logging user login success...");
    //    return "clientes/inicio";
    //}
    //
    //@PostMapping("/login_failure_handler")
    //public String loginFailureHandler() {
    //    System.out.println("Login failure handler....");
    //    return "clientes/login";
    //}

    //@GetMapping("/register")
    //public String showRegistrationForm(Model model) {
    //    model.addAttribute("user", new Cliente());
    //    return "signup_form";
    //}
    //
    //@PostMapping("/process_register")
    //public String processRegister(Cliente user){
    //    return "register_success";
    //}

    //@PostMapping("/token")
    //public String createAuthenticationToken(Model model, HttpSession session,
    //                                        @ModelAttribute ClienteLoginDto loginUserRequest,
    //                                        HttpServletResponse res) throws Exception {
    //    try {
    //        String jwtToken;
    //        if (rolesEmpleadoService.existsRolEmpleadoByEmpleadoPersonaFisicaRfc(loginUserRequest.getRfc())){
    //            Empleado user = rolesEmpleadoService.getEmpleadoByPersonaFisicaRfc(loginUserRequest.getRfc());
    //            Authentication authentication = authenticate(loginUserRequest.getRfc(),
    //                loginUserRequest.getPassword());
    //            jwtToken = jwtTokenProvider.generateJwtTokenEmpleados(authentication, user);
    //            JwtRequest jwtRequest = new JwtRequest(jwtToken, (long)user.getId(), user.getPersonaFisica().getRfc(),
    //                jwtTokenProvider.getExpiryDuration(), authentication.getAuthorities());
    //            Cookie cookie = new Cookie("token",jwtToken);
    //            cookie.setMaxAge(Integer.MAX_VALUE);
    //            res.addCookie(cookie);
    //            session.setAttribute("msg","Login OK!");
    //            //return "/empleados/inicio";
    //            res.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    //            return "redirect:/empleados/inicio";
    //        }
    //        else if (clienteService.existsClienteByTipoPersonaRfc(loginUserRequest.getRfc())){
    //            Cliente user = clienteService.getClienteByTipoPersonaRfc(loginUserRequest.getRfc());
    //            Authentication authentication = authenticate(loginUserRequest.getRfc(),
    //                loginUserRequest.getPassword());
    //            jwtToken = jwtTokenProvider.generateJwtTokenClientes(authentication, user);
    //            JwtRequest jwtRequest;
    //            if (user.getTipoPersona() == TipoPersona.FISICA){
    //                jwtRequest = new JwtRequest(jwtToken, (long)user.getId(), user.getPersonaFisica().getRfc(),
    //                    jwtTokenProvider.getExpiryDuration(), authentication.getAuthorities());
    //            }
    //            else{
    //                jwtRequest = new JwtRequest(jwtToken, (long)user.getId(), user.getPersonaFisica().getRfc(),
    //                    jwtTokenProvider.getExpiryDuration(), authentication.getAuthorities());
    //            }
    //            Cookie cookie = new Cookie("token",jwtToken);
    //            cookie.setMaxAge(Integer.MAX_VALUE);
    //            res.addCookie(cookie);
    //            session.setAttribute("msg","Login OK!");
    //            return "redirect:/clientes/inicio";
    //        }
    //        else {
    //            model.addAttribute("error", true);
    //            return "/login/login";
    //        }
    //
    //    } catch (UsernameNotFoundException | BadCredentialsException e) {
    //        session.setAttribute("msg","Bad Credentials");
    //        return "redirect:/login";
    //    }
    //}

    private Authentication authenticate(String username, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping("/token")
    public String AuthenticateAndGetToken(Model model, HttpSession session,
                                          @ModelAttribute ClienteLoginDto authRequestDTO, HttpServletResponse response) {
        Authentication authentication;
        if (rolesEmpleadoService.existsRolEmpleadoByEmpleadoPersonaFisicaRfc(authRequestDTO.getRfc())) {
            authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getRfc(),
                    authRequestDTO.getPassword()));
            if (authentication.isAuthenticated()) {
                //RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getRfc());
                String accessToken = jwtService.GenerateToken(authRequestDTO.getRfc());
                // set accessToken to cookie header
                //ResponseCookie cookie = ResponseCookie.from("accessToken", accessToken)
                //    .httpOnly(true)
                //    .secure(false)
                //    .path("/")
                //    .maxAge(Integer.MAX_VALUE)
                //    .build();
                //response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
                Cookie galleta = new Cookie("accessToken",accessToken);
                galleta.setMaxAge(Integer.MAX_VALUE);
                galleta.setPath("/");
                response.addCookie(galleta);

                //return JwtResponseDTO.builder()
                //    .accessToken(accessToken).token(accessToken).build();
                //.token(refreshToken.getToken()).build();
                return "redirect:/empleados/inicio";
            }
            else{
                session.setAttribute("msg","Bad Credentials");
                return "redirect:/login";
            }
        } else if (clienteService.existsClienteByTipoPersonaRfc(authRequestDTO.getRfc())) {
            authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getRfc(),
                    authRequestDTO.getPassword()));
            if (authentication.isAuthenticated()) {
                //RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getRfc());
                String accessToken = jwtService.GenerateToken(authRequestDTO.getRfc());
                // set accessToken to cookie header
                //ResponseCookie cookie = ResponseCookie.from("accessToken", accessToken)
                //    .httpOnly(true)
                //    .secure(false)
                //    .path("/")
                //    .maxAge(Integer.MAX_VALUE)
                //    .build();
                //response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
                Cookie galleta = new Cookie("accessToken",accessToken);
                galleta.setMaxAge(Integer.MAX_VALUE);
                galleta.setPath("/");
                response.addCookie(galleta);

                //return JwtResponseDTO.builder()
                //    .accessToken(accessToken).token(accessToken).build();
                //.token(refreshToken.getToken()).build();
                return "redirect:/clientes/inicio";
            }
            else{
                session.setAttribute("msg","Bad Credentials");
                return "redirect:/login";
            }
        }
        session.setAttribute("msg","Bad Credentials");
        return "redirect:/login";
    }
}
