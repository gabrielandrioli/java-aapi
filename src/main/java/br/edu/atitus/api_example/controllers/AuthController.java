package br.edu.atitus.api_example.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.api_example.configs.JwtService;
import br.edu.atitus.api_example.dtos.SigninDTO;
import br.edu.atitus.api_example.dtos.SignupDTO;
import br.edu.atitus.api_example.entities.TypeUser;
import br.edu.atitus.api_example.entities.UserEntity;
import br.edu.atitus.api_example.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;
    private final AuthenticationConfiguration authConfig;
    private final JwtService jwtService;

    public AuthController(UserService service, 
                          AuthenticationConfiguration authConfig,
                          JwtService jwtService) {
        this.service = service;
        this.authConfig = authConfig;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> postSignup(@RequestBody SignupDTO dto) throws Exception {
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(dto, user);
        user.setType(TypeUser.Common);

        service.save(user);

        return ResponseEntity.status(201).body(user);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> postSignin(@RequestBody SigninDTO dto)
            throws AuthenticationException, Exception {

        Authentication auth = authConfig.getAuthenticationManager()
                .authenticate(
                    new UsernamePasswordAuthenticationToken(
                        dto.email(),
                        dto.password()
                    )
                );

        var user = (UserEntity) auth.getPrincipal();

        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(token);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e) {
        return ResponseEntity.badRequest()
                .body(e.getMessage().replaceAll("\r\n", ""));
    }
}
