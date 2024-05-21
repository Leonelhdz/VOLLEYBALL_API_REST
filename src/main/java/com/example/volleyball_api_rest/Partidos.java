package com.example.volleyball_api_rest;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="partidos")
public class Partidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer categoria_id;
    private Integer club_local_id;
    private Integer club_visitante_id;
    private Date fecha;
    private Integer resultado_local;
    private Integer resultado_visitante;
}
