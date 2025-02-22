package com.example.SpringWebSecurityProgetto.model;

import com.example.SpringWebSecurityProgetto.enumeration.RuoliUtente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "utenti")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long utente_id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private RuoliUtente ruoliUtente;

    @OneToMany(mappedBy = "evento_id")
    private List<Evento> listaEventi = new ArrayList<>();

}
