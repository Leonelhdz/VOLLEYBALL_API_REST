package com.example.volleyball_api_rest.Controller;

import com.example.volleyball_api_rest.Asistencia;
import com.example.volleyball_api_rest.Repository.AsistenciaRepository;
import com.example.volleyball_api_rest.Repository.EventRepository;
import com.example.volleyball_api_rest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/asistencia")
public class AsistenciaController {
    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

//    @PostMapping("/{eventId}/user/{userId}")
//    public ResponseEntity<Asistencia> attendEvent(
//            @PathVariable Integer eventId,
//            @PathVariable Integer userId,
//            @RequestBody boolean asistencia) {
//        return eventRepository.findById(eventId).map(event -> {
//            return userRepository.findById(userId).map(user -> {
//                Asistencia nuevaAsistencia = new Asistencia();
//                nuevaAsistencia.setEvent_id(eventId);
//                nuevaAsistencia.setUser_id(userId);
//                nuevaAsistencia.setAsistencia(asistencia);
//                nuevaAsistencia.setCreated_at(LocalDateTime.now());
//                return ResponseEntity.ok(asistenciaRepository.save(nuevaAsistencia));
//            }).orElse(ResponseEntity.notFound().build());
//        }).orElse(ResponseEntity.notFound().build());
//    }

    @PostMapping("/{eventId}/user/{userId}")
    public ResponseEntity<Asistencia> saveAttendance(@PathVariable Integer eventId, @PathVariable Integer userId, @RequestBody Asistencia asistencia) {
        asistencia.setEvent_id(eventId);
        asistencia.setUser_id(userId);
        asistencia.setCreated_at(LocalDateTime.now());
        Asistencia savedAsistencia = asistenciaRepository.save(asistencia);
        return new ResponseEntity<>(savedAsistencia, HttpStatus.OK);
    }
}
