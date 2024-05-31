package com.example.volleyball_api_rest.Controller;
import com.example.volleyball_api_rest.Categoria;
import com.example.volleyball_api_rest.Repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/categorias")
    public List<Categoria> getAllClubs() {
        return categoriaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Integer id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            return ResponseEntity.ok().body(categoria.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/categorias/nombre_categoria/{nombre_categoria}")
    public List<Categoria> getByNombre_categoria(@PathVariable(value = "nombre_categoria") String nombre_categoria){
        return categoriaRepository.findByNombre_categoria(nombre_categoria);
    }

    @GetMapping("/categorias/club/{clubId}")
    public ResponseEntity<List<Categoria>> getCategoriasByClubId(@PathVariable(value = "clubId") Integer club_id) {
        List<Categoria> categorias = categoriaRepository.findByClubId(club_id);
        if (!categorias.isEmpty()) {
            return ResponseEntity.ok().body(categorias);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/categorias/sinentrenador/club/{clubId}")
    public ResponseEntity<List<Categoria>> getCategoriasSinEntrenador(@PathVariable(value = "clubId") Integer clubId) {
        List<Categoria> categorias = categoriaRepository.findCategoriasSinEntrenador(clubId);
        if (!categorias.isEmpty()) {
            return ResponseEntity.ok().body(categorias);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/Crearcategorias")
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
{
            return new ResponseEntity<>(categoriaRepository.save(categoria), HttpStatus.OK);

        }
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable(value = "id") Integer id, @RequestBody Categoria categoriaDetails) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        if (optionalCategoria.isPresent()) {
            Categoria categoria = optionalCategoria.get();
            categoria.setNombre_categoria(categoriaDetails.getNombre_categoria());
            categoria.setDescripcion(categoria.getDescripcion());
            categoria.setEdad_min(categoria.getEdad_min());
            categoria.setEdad_max(categoria.getEdad_max());
            categoria.setClub_id(categoria.getClub_id());
            // Actualiza los demás campos según sea necesario
            final Categoria updateCategoria = categoriaRepository.save(categoria);
            return ResponseEntity.ok(updateCategoria);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable(value = "id") Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            categoriaRepository.delete(categoria.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
