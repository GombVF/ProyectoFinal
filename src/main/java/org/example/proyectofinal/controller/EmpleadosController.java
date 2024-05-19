package org.example.proyectofinal.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.proyectofinal.models.clientes.dtos.ClienteLoginDto;
import org.example.proyectofinal.models.empleados.Empleado;
import org.example.proyectofinal.models.personasFisicas.PersonaFisica;
import org.example.proyectofinal.models.personasMorales.PersonaMoral;
//import org.example.proyectofinal.security.jwt.JWTTokenProvider;
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

@Controller
@RequestMapping("/empleados")
public class EmpleadosController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private PersonaFisicaService personaFisicaService;
    @Autowired
    private PersonaMoralService personaMoralService;
    //@Autowired
    //private JWTTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/inicio")
    public String inicio(Model model, HttpSession session,
                         @ModelAttribute ClienteLoginDto authRequestDTO, HttpServletResponse response) {
        return "/empleados/inicio";
    }

}
