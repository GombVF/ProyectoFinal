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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


    @PostMapping("/token")
    public String AuthenticateAndGetToken(RedirectAttributes model,
                                          HttpSession session,
                                          RedirectAttributes flash,
                                          @ModelAttribute ClienteLoginDto authRequestDTO,
                                          HttpServletResponse response) {
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
                flash.addFlashAttribute("error",true);
                return "redirect:/login/login";
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
                flash.addFlashAttribute("error",true);
                return "redirect:/login/login";
            }
        }
        flash.addFlashAttribute("error",true);
        return "redirect:/login/login";
    }
}
