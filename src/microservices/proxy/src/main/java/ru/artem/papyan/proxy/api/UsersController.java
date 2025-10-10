package ru.artem.papyan.proxy.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.artem.papyan.proxy.integration.MonolithClient;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UsersController {

    private final MonolithClient monolithClient;

    @GetMapping
    public ResponseEntity<?> getUsers(
            @RequestParam(required = false, name = "id") Long id
    ) {
        if (id != null) {
            return ResponseEntity.ofNullable(monolithClient.getUser(id));
        } else {
            return ResponseEntity.ofNullable(monolithClient.getUsers());
        }
    }
}
