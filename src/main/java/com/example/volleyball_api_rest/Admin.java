package com.example.volleyball_api_rest;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String contrasena;
    private String email;
    private String direccion;
    private String telefono;
    private String codigo_acceso;
    private String role;
    private Integer club_id;
}
