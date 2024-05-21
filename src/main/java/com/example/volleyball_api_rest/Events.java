package com.example.volleyball_api_rest;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
@Entity
@Table(name="events")
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer club_id;
    private String event_name;
    private Date event_date;
    private Time event_time;
    private String event_description;
    private String foto_evento;
    private String event_public;
}
