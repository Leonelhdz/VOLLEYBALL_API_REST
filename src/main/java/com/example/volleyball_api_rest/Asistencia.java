package com.example.volleyball_api_rest;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="asistencia")
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer event_id;
    private Integer user_id;
    private Boolean asistencia;
    private LocalDateTime created_at;
}

//public class Attendance {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @ManyToOne
//    @JoinColumn(name = "event_id", nullable = false)
//    private Events event;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    private boolean attending;
//
//    private LocalDateTime createdAt;
//
//    public Attendance() {
//        this.createdAt = LocalDateTime.now();
//    }