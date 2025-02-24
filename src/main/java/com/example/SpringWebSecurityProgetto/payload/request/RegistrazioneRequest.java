package com.example.SpringWebSecurityProgetto.payload.request;

import com.example.SpringWebSecurityProgetto.enumeration.RuoliUtente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


    @Data
    public class RegistrazioneRequest {

        @NotBlank(message = "Il campo è obbligatorio")
        @Size(min = 3, max = 15)
        private String username;

        @NotBlank(message = "Il campo è obbligatorio")
        @Size(min = 3, max = 20)
        private String password;

        @NotBlank(message = "Il campo è obbligatorio")
        private String nome;

        @NotBlank(message = "Il campo è obbligatorio")
        private String cognome;

        @NotBlank(message = "Il campo è obbligatorio")
        @Email
        private String email;

        private RuoliUtente ruolo;
    }
