package com.example.SpringWebSecurityProgetto.payload;

import com.example.SpringWebSecurityProgetto.model.Utente;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventoDTO {

    @NotBlank(message = "Il campo è obbligatorio")
    private String titolo;

    private String descrizione;

    @NotBlank(message = "Il campo è obbligatorio")
    private LocalDate dataEvento;

    @NotBlank(message = "Il campo è obbligatorio")
    private String luogo;

    private int numeroPosti;

    private Utente utentePerEvento;

    @NotBlank(message = "Il campo è obbligatorio")
    private Utente creatoreEvento;
}
