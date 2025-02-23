package com.example.SpringWebSecurityProgetto.service;

import com.example.SpringWebSecurityProgetto.exception.EmailDuplicataException;
import com.example.SpringWebSecurityProgetto.exception.UsernameDuplicatoException;
import com.example.SpringWebSecurityProgetto.model.Utente;
import com.example.SpringWebSecurityProgetto.payload.UtenteDTO;
import com.example.SpringWebSecurityProgetto.repository.UtenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UtenteService {

    @Autowired
    UtenteRepository utenteRepository;

    //  crea Utente
    public String creaUtente(UtenteDTO utenteDTO){

        Utente user = dto_entity(utenteDTO);
        user = utenteRepository.save(user);

     return "Utente inserito: " + user;
    }

    // Trova Utente tramite ID
    public UtenteDTO trovaUtente(long id){
        Optional<Utente> userTrovato = utenteRepository.findById(id);
        if(userTrovato.isPresent()){
        return entity_dto(userTrovato.get());
        }else{
            throw new RuntimeException("Utente non trovato");
        }

    }

    // Trova tutti gli Utenti
    public Page<UtenteDTO> trovaTuttiUtenti(Pageable page){
        Page<Utente> listaUtenti = utenteRepository.findAll(page);
        List<UtenteDTO> listaUtentiDTO = new ArrayList<>();
        // verifico e ciclo l elemento di destra tramite l appartenenza alla classe di sinistra
        for(Utente utente : listaUtenti.getContent()){
            // travaso e aggiungo la lista di utenti
            UtenteDTO utenteDTO = entity_dto(utente);
            listaUtentiDTO.add(utenteDTO);
        }
        return new PageImpl<>(listaUtentiDTO);
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
        user.setRuoloUtente(utenteDto.getRuoloUtente());
        return user;
    }

    // travaso ENTITY ---> DTO
    public UtenteDTO entity_dto(Utente utente) {
        UtenteDTO userDto = new UtenteDTO();
        userDto.setNome(utente.getNome());
        userDto.setCognome(utente.getCognome());
        userDto.setUsername(utente.getUsername());
        userDto.setEmail(utente.getEmail());
        userDto.setRuoloUtente(utente.getRuoloUtente());
        return userDto;
    }
}