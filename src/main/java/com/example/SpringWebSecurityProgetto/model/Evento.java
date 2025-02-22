package com.example.SpringWebSecurityProgetto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "eventi")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long evento_id;
    @Column(nullable = false)
    private String titolo;

    private String descrizione;

    @Column(nullable = false)
    private LocalDate dataEvento;

    @Column(nullable = false)
    private String luogo;

    private int numeroPosti;

    @ManyToOne
    private Utente utentePerEvento;

    @ManyToOne
    @JoinColumn(name = "creatoreEvento_id")
    private Utente creatoreEvento;
}
