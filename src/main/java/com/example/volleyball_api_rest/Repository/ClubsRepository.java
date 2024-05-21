package com.example.volleyball_api_rest.Repository;
import com.example.volleyball_api_rest.Clubs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClubsRepository extends JpaRepository<Clubs, Integer> {

     List<Clubs> findByNombre(String nombre);
     Clubs save(Clubs clubs);

     void delete(Clubs clubs);
     @Query("SELECT c FROM Clubs c WHERE c.codigo_acceso = :codigo_acceso")
     Clubs findByCodigoAcceso(String codigo_acceso);


}
