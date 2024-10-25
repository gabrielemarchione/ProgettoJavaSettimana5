package com.example.ProgettoJavaSettimana5.repositories;

import com.example.ProgettoJavaSettimana5.entities.Postazione;
import com.example.ProgettoJavaSettimana5.entities.Prenotazione;
import com.example.ProgettoJavaSettimana5.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface PrenotazioniRepository extends JpaRepository<Prenotazione, UUID> {
    List<Prenotazione> findByUtente(Utente utente);
    boolean existsByUtenteAndDataPrenotazione(Utente utente, LocalDate dataPrenotazione);
    boolean existsByPostazioneAndDataPrenotazione(Postazione postazione, LocalDate dataPrenotazione);
}
