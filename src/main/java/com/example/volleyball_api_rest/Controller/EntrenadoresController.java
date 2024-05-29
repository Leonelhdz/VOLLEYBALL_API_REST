package com.example.volleyball_api_rest.Controller;

import com.example.volleyball_api_rest.AuthRequest;
import com.example.volleyball_api_rest.Categoria;
import com.example.volleyball_api_rest.Entrenadores;
import com.example.volleyball_api_rest.Repository.EntrenadoresRepository;
import com.example.volleyball_api_rest.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/entrenadores")
public class EntrenadoresController {
    @Autowired
    private EntrenadoresRepository entrenadoresRepository;

    @GetMapping("/entrenadores")
    public List<Entrenadores> getAllEntrenadores() {
        return entrenadoresRepository.findAll();
    }

    @GetMapping("/entrenadores/{id}")
    public ResponseEntity<Entrenadores> getEntrenadoresById(@PathVariable(value = "id") Integer id){
        Optional<Entrenadores> entrenadores = entrenadoresRepository.findById(id);
        if (entrenadores.isPresent()) {
            return ResponseEntity.ok().body(entrenadores.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/entrenadores/nombre/{nombre}")
    public List<Entrenadores> getEntrenadoresByName(@PathVariable(value = "nombre") String nombre){
        return entrenadoresRepository.findByNombre(nombre);
    }

    @GetMapping("/{categoria_id}/entrenador")
    public ResponseEntity<Entrenadores> getEntrenadorByCategoriaId(@PathVariable Integer categoria_id) {
        Entrenadores entrenador = entrenadoresRepository.findEntrenadoresByCategoria_id(categoria_id);
        if (entrenador != null) {
            return ResponseEntity.ok(entrenador);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/crearentrenadores")
    public ResponseEntity<?> createEntrenador(@RequestBody Entrenadores entrenadores) {
        try {
            Entrenadores newEntrenador = entrenadoresRepository.save(entrenadores);
            return new ResponseEntity<>(newEntrenador, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/entrenadores/{id}")
    public ResponseEntity<Entrenadores> updateEntrenador(@PathVariable(value = "id") Integer id, @RequestBody Entrenadores entrenadoresDetails) {
        Optional<Entrenadores> optionalEntrenadores = entrenadoresRepository.findById(id);
        if (optionalEntrenadores.isPresent()) {
            Entrenadores entrenadores = optionalEntrenadores.get();
            entrenadores.setNombre(entrenadoresDetails.getNombre());
            // Actualiza los demás campos según sea necesario
            final Entrenadores updatedEntrenador = entrenadoresRepository.save(entrenadores);
            return ResponseEntity.ok(updatedEntrenador);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginEntrenador(@RequestBody AuthRequest request) {
        Entrenadores entrenadores = entrenadoresRepository.findByEmail(request.getEmail());
        if (entrenadores != null && entrenadores.getPassword().equals(request.getPassword())) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("id", entrenadores.getId());
            response.put("role", entrenadores.getRole());
            response.put("club_id", entrenadores.getClub_id());
            response.put("nombre", entrenadores.getNombre());
            response.put("email", entrenadores.getEmail());
            response.put("apellidos", entrenadores.getApellidos());
            response.put("telefono", entrenadores.getTelefono());
            response.put("certificacion", entrenadores.getCertificacion());
            response.put("categoria_id", entrenadores.getCategoria_id());
            return ResponseEntity.ok(response);
        }
//        Map<String, Object> response = new HashMap<>();
//        response.put("error", "Invalid credentials");
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @DeleteMapping("/entrenadores/{id}")
    public ResponseEntity<?> deleteEntrenadores(@PathVariable(value = "id") Integer id, @RequestParam(value = "token") String token) {
        Optional<Entrenadores> entrenadores = entrenadoresRepository.findById(id);
        if (entrenadores.isPresent()) {
            entrenadoresRepository.delete(entrenadores.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/entrenadores/club/{clubId}")
    public ResponseEntity<List<Entrenadores>> getEntrenadoresByClubId(@PathVariable(value = "clubId") Integer club_id) {
        List<Entrenadores> entrenadores = entrenadoresRepository.findByClubId(club_id);
        if (!entrenadores.isEmpty()) {
            return ResponseEntity.ok().body(entrenadores);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
