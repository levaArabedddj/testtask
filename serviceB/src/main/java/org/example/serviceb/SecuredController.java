package org.example.serviceb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class SecuredController {

    @Value("${INTERNAL_TOKEN}")
    private String internalToken;

    @PostMapping("/transform")
    public ResponseEntity<?> transform(
            @RequestHeader("X-Internal-Token") String token,
            @RequestBody Map<String, String> body) {


        if (!internalToken.equals(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).
                    body("Invalid token");
        }

        String text = body.get("text");
        String transformed = new StringBuilder(text).
                reverse().toString().
                toUpperCase();

        return ResponseEntity.ok(
                Map.of("result", transformed));
    }
}
