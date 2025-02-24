package com.example.SpringWebSecurityProgetto.service;

import com.example.SpringWebSecurityProgetto.model.Evento;
import com.example.SpringWebSecurityProgetto.payload.EventoDTO;
import com.example.SpringWebSecurityProgetto.repository.EventoRepository;
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
public class EventoService {

    @Autowired
    EventoRepository eventoRepository;

    //Crea Evento
    public String creaEvento(EventoDTO eventoDTO){

        Evento evento = dto_entity(eventoDTO);
        evento = eventoRepository.save(evento);
        return "Evento creato con successo : " + evento;

    }

    //Trova Evento tramite ID
    public EventoDTO trovaEvento(long id){
        Optional<Evento> eventoTrovato = eventoRepository.findById(id);

        if(eventoTrovato.isPresent()){
            // travaso da entity a DTO per tornarci l intero oggetto eventoTrovato
            return entity_dto(eventoTrovato.get());
        }else{
            throw new RuntimeException("Evento non trovato");
        }
    }

    // Modifica Evento
    public String modificaEvento(EventoDTO eventoDTO , long id){

        Optional<Evento> eventoTrovato = eventoRepository.findById(id);

        if(eventoTrovato.isPresent()){
            Evento evento = eventoTrovato.get();
            evento.setTitolo(eventoDTO.getTitolo());
            evento.setDescrizione(eventoDTO.getDescrizione());
            evento.setLuogo(eventoDTO.getLuogo());
            evento.setNumeroPosti(eventoDTO.getNumeroPosti());
            evento.setDataEvento(eventoDTO.getDataEvento());
            evento.setCreatoreEvento(eventoDTO.getCreatoreEvento());
            return "Evento modificato correttamente :" + evento;
        }else{
            throw new RuntimeException("Errore! Non è possibile modificare l evento. Evento non presente");
        }

    }

    // Elimina Evento
    public String eliminaEvento(long id){
        Optional<Evento> eventoTrovato = eventoRepository.findById(id);
        if(eventoTrovato.isPresent()){
            eventoRepository.delete(eventoTrovato.get());
            return "Evento rimosso con successo :" + id;
        }else{
            throw new RuntimeException("Rimozione non riuscita, evento non trovato con id: " + id);
        }
    }


    // Trova tutti gli Eventi
    public Page<EventoDTO> trovaTuttiEventi(Pageable page){
        Page<Evento> listaEventi = eventoRepository.findAll(page);
        List<EventoDTO> listaEventiDTO = new ArrayList<>();
        // verifico e ciclo l elemento di destra tramite l appartenenza alla classe di sinistra
        for(Evento evento : listaEventi.getContent()){
            // travaso e aggiungo la lista di utenti
            EventoDTO eventoDTO = entity_dto(evento);
            listaEventiDTO.add(eventoDTO);
        }
        return new PageImpl<>(listaEventiDTO);
    }


    //Travaso DTO--->ENTITY
    public Evento dto_entity(EventoDTO eventoDTO){
        Evento evento = new Evento();
        evento.setTitolo(eventoDTO.getTitolo());
        evento.setDescrizione(eventoDTO.getDescrizione());
        evento.setLuogo(eventoDTO.getLuogo());
        evento.setNumeroPosti(eventoDTO.getNumeroPosti());
        evento.setDataEvento(eventoDTO.getDataEvento());
        evento.setCreatoreEvento(eventoDTO.getCreatoreEvento());
        return evento;
    }

    //Travaso ENTITY--->DTO
    public EventoDTO entity_dto(Evento evento){
        EventoDTO eventoDTO = new EventoDTO();
        eventoDTO.setTitolo(evento.getTitolo());
        eventoDTO.setDescrizione(evento.getDescrizione());
        eventoDTO.setLuogo(evento.getLuogo());
        eventoDTO.setNumeroPosti(evento.getNumeroPosti());
        eventoDTO.setDataEvento(evento.getDataEvento());
        eventoDTO.setCreatoreEvento(evento.getCreatoreEvento());
        return eventoDTO;
    }
}
