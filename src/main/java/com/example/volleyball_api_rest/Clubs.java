package com.example.volleyball_api_rest;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="clubs")
public class Clubs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String direccion;
    private String descripcion;
    private String logo_url;
    private String email;
    private Double latitud;
    private Double longitud;
    private Integer likes;
    private String telefono;
    private String codigo_acceso;
}
