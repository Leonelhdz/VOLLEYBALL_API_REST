package com.example.volleyball_api_rest.Repository;

import com.example.volleyball_api_rest.Categoria;

import com.example.volleyball_api_rest.Clubs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    @Query("SELECT c FROM Categoria c WHERE c.nombre_categoria = ?1")
    List<Categoria> findByNombre_categoria(String nombre_categoria);

    @Query("SELECT c FROM Categoria c WHERE c.club_id = ?1")
    List<Categoria> findByClubId(Integer club_id);

    @Query("SELECT c FROM Categoria c WHERE c.club_id = :clubId AND c.id NOT IN (SELECT e.categoria_id FROM Entrenadores e WHERE e.categoria_id IS NOT NULL)")
    List<Categoria> findCategoriasSinEntrenador(@Param("clubId") Integer clubId);

    Categoria save(Categoria categoria);

    void delete(Categoria categoria);
    @Query("SELECT c FROM Categoria c WHERE c.codigo_secreto = :codigo_secreto")
    Categoria findByCodigo(String codigo_secreto);
}
