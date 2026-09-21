package edu.itmo.infosec_lab1.service;


import edu.itmo.infosec_lab1.dto.LoginRequest;
import edu.itmo.infosec_lab1.dto.RegisterRequest;
import edu.itmo.infosec_lab1.entity.User;
import edu.itmo.infosec_lab1.exception.DuplicateEmailException;
import edu.itmo.infosec_lab1.exception.DuplicateUsernameException;
import edu.itmo.infosec_lab1.exception.UserNotFoundException;
import edu.itmo.infosec_lab1.exception.WrongPasswordException;
import edu.itmo.infosec_lab1.repository.UserRepository;
import edu.itmo.infosec_lab1.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String login(LoginRequest request) {
        String username = request.username();
        String password = request.password();
        User user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new WrongPasswordException();
        }
        return jwtService.createToken(username);
    }

    public String register(RegisterRequest request) {
        if (userRepository.findByUsername(request.username()).isPresent()) {
            throw new DuplicateUsernameException();
        }
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new DuplicateEmailException();
        }
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setEmail(request.email());
        userRepository.save(user);
        return jwtService.createToken(user.getUsername());
    }
}