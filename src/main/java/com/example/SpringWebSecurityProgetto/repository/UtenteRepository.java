package com.example.SpringWebSecurityProgetto.repository;

import com.example.SpringWebSecurityProgetto.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente,Long> {

    public Optional<Utente> findByUsername(String username);

    public Utente findById(long id);

    // Controllo per i duplicati Username e Password

    public boolean existsByUsername(String username);

    public boolean existsByEmail(String email);

}
