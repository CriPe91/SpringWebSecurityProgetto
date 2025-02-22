package com.example.SpringWebSecurityProgetto.controller;

import com.example.SpringWebSecurityProgetto.payload.UtenteDTO;
import com.example.SpringWebSecurityProgetto.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/utente")
public class UtenteController {

    @Autowired
    UtenteService utenteService;

    @PostMapping("newUtente")
    public ResponseEntity<?> nuovoUtente(@RequestBody @Validated UtenteDTO utenteDTO, BindingResult validation) {

        if (validation.hasErrors()) {
            String errorMessage = "ERRORE DI VALIDAZIONE \n";

            for (ObjectError errore : validation.getAllErrors()) {
                errorMessage += errore.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        String NuovoUtente = utenteService.creaUtente(utenteDTO);
        return new ResponseEntity<>("Dipendente inserito nel DB: " + NuovoUtente, HttpStatus.CREATED);
    }


    // Trova tutti gli utenti
    @GetMapping(value = "/allUtenti", produces = "application/json")
    public ResponseEntity<Page<UtenteDTO>> getAllDipendenti(Pageable page) {
        Page<UtenteDTO> utenti = utenteService.trovaTuttiUtenti(page);
        return new ResponseEntity<>(utenti, HttpStatus.OK);
    }

    // Trova utente tramite ID
    @GetMapping("/{id}")
    public UtenteDTO trovaUtenteConId(@PathVariable long id) {
        return utenteService.trovaUtente(id);
    }


}
