package com.example.volleyball_api_rest;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer club_id;
    private String descripcion;
    private Integer edad_min;
    private Integer edad_max;
    private String nombre_categoria;
    private String sexo;
}
