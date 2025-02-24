package com.example.SpringWebSecurityProgetto.model;

import com.example.SpringWebSecurityProgetto.enumeration.RuoliUtente;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Ruolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ruolo_id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RuoliUtente nome;

}
