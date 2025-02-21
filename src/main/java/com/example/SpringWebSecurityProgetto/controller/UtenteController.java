package com.example.SpringWebSecurityProgetto.controller;

import com.example.SpringWebSecurityProgetto.exception.EmailDuplicataException;
import com.example.SpringWebSecurityProgetto.exception.UsernameDuplicatoException;
import com.example.SpringWebSecurityProgetto.payload.UtenteDTO;
import com.example.SpringWebSecurityProgetto.payload.request.RegistrazioneRequest;
import com.example.SpringWebSecurityProgetto.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utente")
public class UtenteController {

    @Autowired
    UtenteService utenteService;

    @PostMapping("/new")
    public ResponseEntity<String> inserisciUtente(@Validated @RequestBody RegistrazioneRequest nuovoUtente, BindingResult validazione){

        try {

            if(validazione.hasErrors()){
                StringBuilder errori = new StringBuilder("Problemi nella validazione dati :\n");

                for(ObjectError errore : validazione.getAllErrors()){
                    errori.append(errore.getDefaultMessage()).append("\n");
                }

                return new ResponseEntity<>(errori.toString(), HttpStatus.BAD_REQUEST);

            }

            String messaggio =utenteService.creaUtente(nuovoUtente);
            return new ResponseEntity<>(messaggio, HttpStatus.OK);
        } catch (UsernameDuplicatoException e) {
            return new ResponseEntity<>("Username già utilizzato", HttpStatus.BAD_REQUEST);
        } catch (EmailDuplicataException e) {
            return new ResponseEntity<>("Email non disponibile", HttpStatus.BAD_REQUEST);
        }
    }
}
