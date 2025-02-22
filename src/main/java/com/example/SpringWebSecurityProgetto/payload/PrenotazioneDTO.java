package com.example.SpringWebSecurityProgetto.payload;

import com.example.SpringWebSecurityProgetto.model.Evento;
import com.example.SpringWebSecurityProgetto.model.Utente;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrenotazioneDTO {

    @NotBlank(message = "Il campo è obbligatorio")
    private Utente utentePrenotato;

    @NotBlank(message = "Il campo è obbligatorio")
    private Evento eventoPrenotato;

    private LocalDate dataPrenotazione;

}
