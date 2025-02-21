package com.example.SpringWebSecurityProgetto.service;

import com.example.SpringWebSecurityProgetto.enumeration.RuoliUtente;
import com.example.SpringWebSecurityProgetto.exception.EmailDuplicataException;
import com.example.SpringWebSecurityProgetto.exception.UsernameDuplicatoException;
import com.example.SpringWebSecurityProgetto.model.Utente;
import com.example.SpringWebSecurityProgetto.payload.UtenteDTO;
import com.example.SpringWebSecurityProgetto.payload.request.RegistrazioneRequest;
import com.example.SpringWebSecurityProgetto.repository.UtenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UtenteService {

    @Autowired
    UtenteRepository utenteRepository;

    //  crea Utente
    public String insertUtente(RegistrazioneRequest userDTO) throws UsernameDuplicatoException, EmailDuplicataException {
        // Verifica se username ed email sono già esistenti
        controlloDuplicati(userDTO.getUsername(), userDTO.getEmail());

        // Gestione dei ruoli
        Set<RuoliUtente> ruoliUtente = new HashSet<>();

        // Se i ruoli sono null o vuoti, impostiamo il ruolo di default come ROLE_USER
        if (userDTO.getRuoloUtente() == null || userDTO.getRuoloUtente().describeConstable().isEmpty()) {
            ruoliUtente.add(RuoliUtente.UTENTE_NORMALE);  // Ruolo di default
        } else {
            // Altrimenti, mappiamo i ruoli passati nella richiesta e verifichiamo che siano validi
            for (String ruoloNome : userDTO.getRuoloUtente()) {
                try {
                    RuoliUtente ruolo = RuoliUtente.valueOf(ruoloNome);  // Verifica se il ruolo esiste nell'enum
                    ruoliUtente.add(ruolo);
                } catch (IllegalArgumentException e) {
                    // Se il ruolo non è valido, puoi lanciare un'eccezione o gestirlo come vuoi
                    throw new IllegalArgumentException("Ruolo non valido: " + ruoloNome);
                }
            }
        }
        // Salvataggio dell'utente nel database
        Long id = utenteRepository.save(user).getId();

        // Restituisci il messaggio di conferma
        return "L'utente " + user.getUsername() + " con id " + id + " è stato inserito correttamente";
    }

    // controllo duplicato Username e Password
    public void controlloDuplicati(String username, String email) throws UsernameDuplicatoException, EmailDuplicataException {

        if(utenteRepository.existsByUsername(username)){
            throw new UsernameDuplicatoException("Username gia esistente nel sistema");
        }

        if(utenteRepository.existsByEmail(email)){
            throw new EmailDuplicataException("Email gia presente nel sistema");
        }

    }



    // travaso DTO ----> ENTITY

    public Utente dto_entity(UtenteDTO utenteDto) {
        Utente user = new Utente();
        user.setNome(utenteDto.getNome());
        user.setCognome(utenteDto.getCognome());
        user.setUsername(utenteDto.getUsername());
        user.setEmail(utenteDto.getEmail());
        user.setPassword(utenteDto.getPassword());
        user.setRuoliUtente(Collections.singleton(utenteDto.getRuoloUtente()));
        return user;
    }

    // travaso ENTITY ---> DTO
    public UtenteDTO entity_dto(Utente utente) {
        UtenteDTO userDto = new UtenteDTO();
        userDto.setNome(utente.getNome());
        userDto.setCognome(utente.getCognome());
        userDto.setUsername(utente.getUsername());
        userDto.setEmail(utente.getEmail());
//        userDto.setPassword(utente.getPassword());
        userDto.setRuoloUtente(utente.getRuoloUtente());
        return userDto;
    }
}