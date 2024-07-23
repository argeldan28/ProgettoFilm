package com.generation.progettofilm.progetto_film.auth;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.progettofilm.progetto_film.auth.dto.AuthResponseDto;
import com.generation.progettofilm.progetto_film.auth.dto.CredentialsDto;
import com.generation.progettofilm.progetto_film.auth.model.Role;
import com.generation.progettofilm.progetto_film.auth.model.UserEntity;
import com.generation.progettofilm.progetto_film.auth.repository.RoleRepository;
import com.generation.progettofilm.progetto_film.auth.repository.UserRepository;
import com.generation.progettofilm.progetto_film.auth.security.JWTGenerator;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTGenerator jwtGenerator;


    @PostMapping("login")
    public AuthResponseDto login(@RequestBody CredentialsDto loginDto)
    {
        Authentication user = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()));

        String token = jwtGenerator.generateToken(user);
        Optional<String> role =   jwtGenerator.getRolesFromJWT(token)
                        .stream()
                        .filter(r -> !r.equals("ROLE_USER"))
                        .findFirst();

        if(role.isPresent())
            return new AuthResponseDto(token, role.get().replace("ROLE_", ""));
        else
            return new AuthResponseDto(token, "");
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody CredentialsDto registerDto) 
    {
        if (userRepository.existsByUsername(registerDto.getUsername())) 
        {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }
}
