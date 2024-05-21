package com.example.volleyball_api_rest.Repository;

import com.example.volleyball_api_rest.Entrenadores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EntrenadoresRepository extends JpaRepository<Entrenadores, Integer> {
    List<Entrenadores> findByNombre(String nombre);
    Entrenadores findByEmail(String email);
    Entrenadores save(Entrenadores entrenadores);

    void delete(Entrenadores entrenadores);
    @Query("SELECT e FROM Entrenadores e JOIN Categoria c ON e.categoria_id = c.id WHERE c.id = ?1")
    Entrenadores findEntrenadoresByCategoria_id(Integer categoria_id);
}
