package com.example.SpringWebSecurityProgetto.repository;

import com.example.SpringWebSecurityProgetto.enumeration.RuoliUtente;
import com.example.SpringWebSecurityProgetto.model.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RuoloRepository extends JpaRepository<Ruolo,Long> {
    Optional<Ruolo> findByNome(RuoliUtente String);
}
