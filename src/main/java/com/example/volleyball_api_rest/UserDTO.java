package com.example.volleyball_api_rest;
import lombok.Data;
import java.util.Date;
@Data
public class UserDTO {
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
    private Integer categoriaId;
    private Integer clubId;
    private String foto_perfil;
    private String role;
    private String sexo;
}
