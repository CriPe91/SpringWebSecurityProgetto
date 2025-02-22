package com.example.SpringWebSecurityProgetto.service;

import com.example.SpringWebSecurityProgetto.model.Prenotazione;
import com.example.SpringWebSecurityProgetto.payload.PrenotazioneDTO;
import com.example.SpringWebSecurityProgetto.repository.PrenotazioneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class PrenotazioneService {

    @Autowired
    PrenotazioneRepository prenotazioneRepository;

    //Crea Prenotazione

    public String creaPrenotazione(PrenotazioneDTO prenotazioneDTO){

        Prenotazione prenotazione = dto_entity(prenotazioneDTO);
        prenotazione = prenotazioneRepository.save(prenotazione);
        return "Prenotazione creata con successo : " + prenotazione;

    }

    //Trova Prenotazione

    public String trovaPrenotazione(long id){
        Optional<Prenotazione> prenotazioneTrovata = prenotazioneRepository.findById(id);

        if(prenotazioneTrovata.isPresent()){
            return "Prenotazione trovata con id: "+id + " " + prenotazioneTrovata;
        }else{
            throw new RuntimeException("Prenotazione non trovata");
        }
    }

    // Modifica Prenotazione
    public String modificaPrenotazione(PrenotazioneDTO prenotazioneDTO , long id){

        Optional<Prenotazione> prenotazioneTrovata = prenotazioneRepository.findById(id);

        if(prenotazioneTrovata.isPresent()){
            Prenotazione prenotazione = prenotazioneTrovata.get();
            prenotazione.setUtentePrenotato(prenotazioneDTO.getUtentePrenotato());
            prenotazione.setEventoPrenotato(prenotazioneDTO.getEventoPrenotato());
            prenotazione.setDataPrenotazione(prenotazioneDTO.getDataPrenotazione());

            return "Prenotazione modificata correttamente :" + prenotazione;
        }else{
            throw new RuntimeException("Errore! Non è possibile modificare la prenotazione. La prenotazione non è presente");
        }

    }

    // Elimina Prenotazione
    public String eliminaPrenotazione(long id){
        Optional<Prenotazione> prenotazioneTrovata = prenotazioneRepository.findById(id);
        if(prenotazioneTrovata.isPresent()){
            prenotazioneRepository.delete(prenotazioneTrovata.get());
            return "Prenotazione rimossa con successo :" + id;
        }else{
            throw new RuntimeException("Rimozione non riuscita, prenotazione non trovata con id: " + id);
        }
    }




    //Travaso DTO--->ENTITY
    public Prenotazione dto_entity(PrenotazioneDTO prenotazioneDTO){
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setUtentePrenotato(prenotazioneDTO.getUtentePrenotato());
        prenotazione.setEventoPrenotato(prenotazioneDTO.getEventoPrenotato());
        prenotazione.setDataPrenotazione(prenotazioneDTO.getDataPrenotazione());
        return prenotazione;
    }

    //Travaso ENTITY--->DTO
    public PrenotazioneDTO entity_dto(Prenotazione prenotazione){
        PrenotazioneDTO prenotazioneDTO = new PrenotazioneDTO();
        prenotazioneDTO.setUtentePrenotato(prenotazione.getUtentePrenotato());
        prenotazioneDTO.setEventoPrenotato(prenotazione.getEventoPrenotato());
        prenotazioneDTO.setDataPrenotazione(prenotazione.getDataPrenotazione());
        return prenotazioneDTO;
    }

}
