package edu.itmo.infosec_lab1.dto;

import edu.itmo.infosec_lab1.entity.User;
import org.springframework.web.util.HtmlUtils;

public record UserDto(
        String username,
        String email
) {
    public UserDto(User user) {
        this(
                HtmlUtils.htmlEscape(user.getUsername()),
                HtmlUtils.htmlEscape(user.getEmail())
        );
    }
}