package com.example.SpringWebSecurityProgetto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long prenotazione_id;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utentePrenotato;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento eventoPrenotato;

    private LocalDate dataPrenotazione;
}
