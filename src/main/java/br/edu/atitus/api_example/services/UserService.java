package br.edu.atitus.api_example.services;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.atitus.api_example.entities.UserEntity;
import br.edu.atitus.api_example.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
    
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    // concise and efficient compiled pattern
    private static final Pattern REGEX_EMAIL = Pattern.compile(
        "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    public UserService(UserRepository repository, PasswordEncoder encoder) {
        super();
        this.repository = repository;
        this.encoder = encoder;
    }

    public UserEntity save(UserEntity user) throws Exception {
        if (user == null)
            throw new Exception("Objeto Nulo");
        
        if (user.getName() == null || user.getName().isEmpty())
            throw new Exception("Nome inválido");
        user.setName(user.getName().trim());
        
        if (user.getEmail() == null || user.getEmail().isEmpty())
            throw new Exception("E-mail inválido");

        String email = user.getEmail().trim().toLowerCase();
        user.setEmail(email);

        Matcher m = REGEX_EMAIL.matcher(email);
        if (!m.matches()) {
            throw new Exception("E-mail com formato inválido");
        }
        
        if (user.getPassword() == null 
                || user.getPassword().isEmpty()
                || user.getPassword().length() < 8)
            throw new Exception("Password inválido");
        
        user.setPassword(encoder.encode(user.getPassword()));
        
        if (repository.existsByEmail(user.getEmail()))
            throw new Exception("Já existe usuário cadastrado com este e-mail");
        
        repository.save(user);
        
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Não existe usuário com este email"));
                
        return user;
    }

}
