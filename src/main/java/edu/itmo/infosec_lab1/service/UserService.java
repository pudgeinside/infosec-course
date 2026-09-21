package edu.itmo.infosec_lab1.service;

import edu.itmo.infosec_lab1.entity.User;
import edu.itmo.infosec_lab1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}