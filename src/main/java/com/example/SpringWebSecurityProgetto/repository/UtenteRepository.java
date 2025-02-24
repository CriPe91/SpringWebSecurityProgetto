package com.example.SpringWebSecurityProgetto.repository;

import com.example.SpringWebSecurityProgetto.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente,Long> {

     Optional<Utente> findByUsername(String username);


    // Controllo per i duplicati Username e Password

   boolean existsByUsername(String username);

   boolean existsByEmail(String email);

}
