package com.example.volleyball_api_rest.Controller;

import com.example.volleyball_api_rest.JwtTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/social-login")
public class SocialLoginController {

    private final JwtTokenService jwtTokenService = new JwtTokenService();

    @PostMapping
    public ResponseEntity<?> socialLogin(@RequestBody Map<String, String> authRequest) {
        String tokenProveedor = authRequest.get("token");
        String proveedor = authRequest.get("provider"); // 'google', 'facebook', 'apple'

        // Valida el token con el proveedor correspondiente
        boolean valid = validateProviderToken(proveedor, tokenProveedor);

        if (valid) {
            // Crea un JWT basado en el correo electrónico autenticado
            String jwtToken = jwtTokenService.generateToken("usuario@example.com");
            return ResponseEntity.ok(Collections.singletonMap("token", jwtToken));
        }

        return ResponseEntity.status(401).body("Invalid credentials");
    }

    // Implementa un método que valide los tokens según cada proveedor
    private boolean validateProviderToken(String provider, String token) {
        // Implementa validación para cada proveedor según la documentación
        return true;
    }
}
