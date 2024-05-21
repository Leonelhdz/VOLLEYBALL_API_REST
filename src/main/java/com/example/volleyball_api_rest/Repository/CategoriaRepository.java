package com.example.volleyball_api_rest.Repository;

import com.example.volleyball_api_rest.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    @Query("SELECT c FROM Categoria c WHERE c.nombre_categoria = ?1")
    List<Categoria> findByNombre_categoria(String nombre_categoria);

    @Query("SELECT c FROM Categoria c WHERE c.club_id = ?1")
    List<Categoria> findByClubId(Integer club_id);

    Categoria save(Categoria categoria);

    void delete(Categoria categoria);
}
