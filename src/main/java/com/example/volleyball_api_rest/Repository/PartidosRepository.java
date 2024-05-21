package com.example.volleyball_api_rest.Repository;

import com.example.volleyball_api_rest.Partidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidosRepository extends JpaRepository<Partidos, Integer> {
    @Query("SELECT p FROM Partidos p JOIN Categoria c ON p.categoria_id = c.id WHERE c.id = ?1")
    List<Partidos> findByCategoria_id(Integer categoria_id);


}
