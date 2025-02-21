package com.example.SpringWebSecurityProgetto.repository;

import com.example.SpringWebSecurityProgetto.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository<Evento,Long> {

    public Optional<Evento> findByLuogo(String luogo);

    public Optional<Evento> findByDataEvento(LocalDate dataEvento);

}
