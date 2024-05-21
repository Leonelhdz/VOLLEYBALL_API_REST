package com.example.volleyball_api_rest;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="entrenadores")
public class Entrenadores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String certificacion;
    private Integer club_id;
    private String password;
    private String role;
    private Integer categoria_id;
}
