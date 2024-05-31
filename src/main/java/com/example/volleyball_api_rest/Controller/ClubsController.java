package com.example.volleyball_api_rest.Controller;

import com.example.volleyball_api_rest.Clubs;
import com.example.volleyball_api_rest.Entrenadores;
import com.example.volleyball_api_rest.Repository.ClubsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clubs")
public class ClubsController {
    @Autowired
    private ClubsRepository clubsRepository;


    @GetMapping("/club")
    public List<Clubs> getAllClubs() {
        return clubsRepository.findAll();
    }

    @GetMapping("/club/{id}")
    public ResponseEntity<Clubs> getClubById(@PathVariable(value = "id") Integer id){
            Optional<Clubs> clubs = clubsRepository.findById(id);
            if (clubs.isPresent()) {
                return ResponseEntity.ok().body(clubs.get());
            } else {
                return ResponseEntity.notFound().build();
            }
    }

    @GetMapping("/club/nombre/{nombre}")
    public List<Clubs> getHotelByName(@PathVariable(value = "nombre") String nombre){
        return clubsRepository.findByNombre(nombre);
    }


    @GetMapping("/validate-access-code/{codigo_acceso}")
    public ResponseEntity<Clubs> validateAccessCode(@PathVariable String codigo_acceso) {
        Clubs club = clubsRepository.findByCodigoAcceso(codigo_acceso);
        if (club != null) {
            return ResponseEntity.ok(club);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/club/like/{id}")
    public ResponseEntity<Clubs> likeClub(@PathVariable(value = "id") Integer id) {
        Optional<Clubs> optionalClub = clubsRepository.findById(id);
        if (optionalClub.isPresent()) {
            Clubs club = optionalClub.get();
            club.setLikes(club.getLikes() + 1); // Incrementa el contador de "Me gusta"
            final Clubs updatedClub = clubsRepository.save(club);
            return ResponseEntity.ok(updatedClub);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/Crearclub")
    public ResponseEntity<?> createClub(@RequestBody Clubs clubs) {
        try {
            Clubs newClub = clubsRepository.save(clubs);
            return new ResponseEntity<>(newClub, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/club/{id}")
    public ResponseEntity<Clubs> updateClub(@PathVariable(value = "id") Integer id, @RequestBody Clubs clubsDetails) {
        Optional<Clubs> optionalClub = clubsRepository.findById(id);
        if (optionalClub.isPresent()) {
            Clubs clubs = optionalClub.get();
            clubs.setNombre(clubsDetails.getNombre());
            // Actualiza los demás campos según sea necesario
            final Clubs club = clubsRepository.save(clubs);
            return ResponseEntity.ok(club);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/club/{id}")
    public ResponseEntity<?> deleteClub(@PathVariable(value = "id") Integer id) {
        Optional<Clubs> club = clubsRepository.findById(id);
        if (club.isPresent()) {
            clubsRepository.delete(club.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}