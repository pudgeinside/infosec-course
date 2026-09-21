package edu.itmo.infosec_lab1.controller;

import edu.itmo.infosec_lab1.dto.UserDto;
import edu.itmo.infosec_lab1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/data")
    public List<UserDto> getUsers() {
        return userService.getUsers()
                .stream()
                .map(UserDto::new)
                .toList();
    }
}