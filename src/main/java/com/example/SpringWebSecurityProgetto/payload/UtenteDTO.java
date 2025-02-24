package com.example.SpringWebSecurityProgetto.payload;


import com.example.SpringWebSecurityProgetto.enumeration.RuoliUtente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UtenteDTO {

    @NotBlank(message = "Il campo è obbligatorio")
    private String nome;

    @NotBlank(message = "Il campo è obbligatorio")
    private String cognome;

    @NotBlank(message = "Il campo è obbligatorio")
    private String username;

    @NotBlank(message = "il campo è obbligatorio")
    @Email(message = "Email già esistente")
    private String email;

    @NotBlank(message = "Il campo è obbligatorio")
    private String password;

    private RuoliUtente ruoloUtente;


}
