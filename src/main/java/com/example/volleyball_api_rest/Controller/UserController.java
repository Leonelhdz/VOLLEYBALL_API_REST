package com.example.volleyball_api_rest.Controller;
import com.example.volleyball_api_rest.*;
import com.example.volleyball_api_rest.Repository.CategoriaRepository;
import com.example.volleyball_api_rest.Repository.ClubsRepository;
import com.example.volleyball_api_rest.Repository.UserRepository;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClubsRepository clubsRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @GetMapping("/user")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Integer id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/nombre/{nombre}")
    public List<User> getUserByName(@PathVariable(value = "nombre") String nombre){
        return userRepository.findByNombre(nombre);
    }

    @GetMapping("/club/userBy/{club_id}")
    public ResponseEntity<List<User>> getUsersByClubId(@PathVariable Integer club_id) {
        List<User> users = userRepository.findUsersByClubId(club_id);
        if (users != null && !users.isEmpty()) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/categoria/{categoria_id}/users")
    public ResponseEntity<List<User>> getUsersByCategory(@PathVariable Integer categoria_id) {
        List<User> users = userRepository.findUsersByCategoria_id(categoria_id);
        if (users != null && !users.isEmpty()) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/crearuser")
    public ResponseEntity<User> createUser(@RequestBody User user, @RequestParam String token) {
        {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(value = "id") Integer id, @RequestBody User userToUpdate) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            // Copiar solo los campos no nulos del usuario a actualizar al usuario existente
            BeanUtils.copyProperties(userToUpdate, existingUser, getNullPropertyNames(userToUpdate));

            // Guardar los cambios
            userRepository.save(existingUser);

            // Devolver un objeto JSON en lugar de una cadena simple
            Map<String, String> response = new HashMap<>();
            response.put("message", "Usuario actualizado exitosamente.");
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private String[] getNullPropertyNames(User user) {
        final BeanWrapper src = new BeanWrapperImpl(user);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            // Para cada propiedad, si el valor es nulo, agregar el nombre de la propiedad a la lista
            if (src.getPropertyValue(pd.getName()) == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteClub(@PathVariable(value = "id") Integer id, @RequestParam(value = "token") String token) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user != null && user.getPassword_hash().equals(request.getPassword())) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("id", user.getId());
            response.put("nombre", user.getNombre());
            response.put("apellidos", user.getApellidos());
            response.put("email", user.getEmail());
            response.put("password_hash", user.getPassword_hash());
            response.put("fecha_nac", user.getFecha_nac());
            response.put("nivel_juego", user.getNivel_juego());
            response.put("direccion", user.getDireccion());
            response.put("telefono", user.getTelefono());
            response.put("tel_responsable", user.getTel_responsable());
            response.put("email_responsable", user.getEmail_responsable());
            response.put("foto_perfil", user.getFoto_perfil());
            response.put("role", "jugador");
            response.put("categoria_id", user.getCategoria_id());
            response.put("club_id", user.getClubId());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user, @RequestParam String codigo_acceso, @RequestParam String codigo_secreto) {
        Clubs clubs = clubsRepository.findByCodigoAcceso(codigo_acceso);
        if (clubs == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Código de acceso del club inválido"));
        }

        Categoria categoria = categoriaRepository.findByCodigo(codigo_secreto);
        if (categoria == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Código de acceso de la categoría inválido"));
        }

        user.setClubId(clubs.getId());
        user.setCategoria_id(categoria.getId());
        userRepository.save(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Registro exitoso");
        response.put("id", String.valueOf(user.getId()));

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestParam String email, @RequestParam String newPassword) {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword_hash(newPassword); // Aquí podrías aplicar un hash a la nueva contraseña
            userRepository.save(user);
            return ResponseEntity.ok(Collections.singletonMap("message", "Contraseña actualizada con éxito"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Usuario no encontrado"));
        }
    }
}
