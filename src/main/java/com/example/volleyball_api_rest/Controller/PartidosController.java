package com.example.volleyball_api_rest.Controller;

import com.example.volleyball_api_rest.Partidos;
import com.example.volleyball_api_rest.Repository.PartidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/partidos")
public class PartidosController {

    @Autowired
    private PartidosRepository partidosRepository;

    @GetMapping
    public List<Partidos> getAllPartidos() {
        return partidosRepository.findAll();
    }

    // Obtener partidos por categoría
    @GetMapping("/categoria/{categoria_id}")
    public ResponseEntity<List<Partidos>> getPartidosByCategoriaId(@PathVariable Integer categoria_id) {
        List<Partidos> partidos = partidosRepository.findByCategoria_id(categoria_id);
            return ResponseEntity.ok().body(partidos);
    }

    // Obtener un partido por ID
    @GetMapping("/{id}")
    public ResponseEntity<Partidos> getPartidoById(@PathVariable Integer id) {
        Optional<Partidos> partido = partidosRepository.findById(id);
        if (partido.isPresent()) {
            return ResponseEntity.ok().body(partido.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo partido
    @PostMapping
    public ResponseEntity<Partidos> createPartido(@RequestBody Partidos partido) {
        Partidos newPartido = partidosRepository.save(partido);
        return ResponseEntity.ok(newPartido);
    }

    // Actualizar un partido existente
    @PutMapping("/{id}")
    public ResponseEntity<Partidos> updatePartido(@PathVariable Integer id, @RequestBody Partidos partidoDetails) {
        Optional<Partidos> optionalPartido = partidosRepository.findById(id);
        if (optionalPartido.isPresent()) {
            Partidos partido = optionalPartido.get();
            partido.setCategoria_id(partidoDetails.getCategoria_id());
            partido.setClub_local_id(partidoDetails.getClub_local_id());
            partido.setClub_visitante_id(partidoDetails.getClub_visitante_id());
            partido.setFecha(partidoDetails.getFecha());
            partido.setResultado_local(partidoDetails.getResultado_local());
            partido.setResultado_visitante(partidoDetails.getResultado_visitante());
            final Partidos updatedPartido = partidosRepository.save(partido);
            return ResponseEntity.ok(updatedPartido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Eliminar un partido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartido(@PathVariable Integer id) {
        Optional<Partidos> partido = partidosRepository.findById(id);
        if (partido.isPresent()) {
            partidosRepository.delete(partido.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
