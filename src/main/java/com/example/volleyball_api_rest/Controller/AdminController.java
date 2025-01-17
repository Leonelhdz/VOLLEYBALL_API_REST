package com.example.volleyball_api_rest.Controller;

import com.example.volleyball_api_rest.Admin;
import com.example.volleyball_api_rest.AuthRequest;
import com.example.volleyball_api_rest.Entrenadores;
import com.example.volleyball_api_rest.Events;
import com.example.volleyball_api_rest.Repository.AdminRepository;
import com.example.volleyball_api_rest.Repository.EntrenadoresRepository;
import com.example.volleyball_api_rest.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EntrenadoresRepository entrenadoresRepository;

    @Autowired
    private EventRepository eventRepository;

    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@RequestBody AuthRequest request) {
        Admin admin = adminRepository.findByEmail(request.getEmail());
        if (admin != null && admin.getContrasena().equals(request.getPassword())) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("role", admin.getRole());
            response.put("id", admin.getId());
            response.put("club_id", admin.getClub_id());
            response.put("nombre", admin.getNombre());
            response.put("email", admin.getEmail());
            response.put("direccion", admin.getDireccion());
            response.put("telefono", admin.getTelefono());
            return ResponseEntity.ok(response);
        }
//        Map<String, Object> response = new HashMap<>();
//        response.put("error", "Invalid credentials");
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");

    }

    @PostMapping("/registrarentrenador")
    public ResponseEntity<?> createEntrenador(@RequestBody Entrenadores entrenador) {
        try {
            Entrenadores newEntrenador = entrenadoresRepository.save(entrenador);
            return new ResponseEntity<>(newEntrenador, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/crearevento")
    public ResponseEntity<?> createEvent(@RequestBody Events event) {
        try {
            Events newEvent = eventRepository.save(event);
            return new ResponseEntity<>(newEvent, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
