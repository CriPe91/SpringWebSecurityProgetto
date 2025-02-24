package com.example.SpringWebSecurityProgetto.controller;


import com.example.SpringWebSecurityProgetto.payload.EventoDTO;
import com.example.SpringWebSecurityProgetto.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evento")
public class EventoController {
    @Autowired
    EventoService eventoService;


    @PostMapping("newEvento")
    public ResponseEntity<?> nuovoEvento(@RequestBody @Validated EventoDTO eventoDTO, BindingResult validation) {

        if (validation.hasErrors()) {
            String errorMessage = "ERRORE DI VALIDAZIONE \n";

            for (ObjectError errore : validation.getAllErrors()) {
                errorMessage += errore.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        String NuovoEvento = eventoService.creaEvento(eventoDTO);
        return new ResponseEntity<>("Dipendente inserito nel DB: " +NuovoEvento, HttpStatus.CREATED);
    }


    // Trova tutti gli Eventi
    @GetMapping(value = "/allEventi", produces = "application/json")
    public ResponseEntity<Page<EventoDTO>> getAllEventi(Pageable page) {
        Page<EventoDTO> eventi = eventoService.trovaTuttiEventi(page);
        return new ResponseEntity<>(eventi, HttpStatus.OK);
    }

    // Trova Evento tramite ID
    @GetMapping("/{id}")
    public EventoDTO trovaEventoConId(@PathVariable long id) {
        return eventoService.trovaEvento(id);
    }

    // Modifica Evento tramite ID





    // Elimina Evento tramite ID



}
