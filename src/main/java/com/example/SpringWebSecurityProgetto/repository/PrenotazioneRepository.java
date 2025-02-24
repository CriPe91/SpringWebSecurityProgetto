package com.example.SpringWebSecurityProgetto.repository;

import com.example.SpringWebSecurityProgetto.model.Evento;
import com.example.SpringWebSecurityProgetto.model.Prenotazione;
import com.example.SpringWebSecurityProgetto.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione,Long> {

     Optional<Prenotazione> findByUtentePrenotato(Utente utentePrenotato);

     Optional<Prenotazione> findByEventoPrenotato(Evento eventoPrenotato);

     Optional<Prenotazione> findByDataPrenotazione(LocalDate dataPrenotazione);
}
