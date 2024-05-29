package com.example.volleyball_api_rest;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellidos;
    private String email;
    private String password_hash;
    private Date fecha_nac;
    private String nivel_juego;
    private String direccion;
    private String telefono;
    private String tel_responsable;
    private String email_responsable;
    private Integer categoria_id;
    @Column(name = "club_id")
    private Integer clubId;
    private String foto_perfil;
    private String role;
    private String sexo;
}
