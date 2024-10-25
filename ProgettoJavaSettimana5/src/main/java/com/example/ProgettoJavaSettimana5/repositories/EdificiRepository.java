package com.example.ProgettoJavaSettimana5.repositories;

import com.example.ProgettoJavaSettimana5.entities.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EdificiRepository extends JpaRepository<Edificio, UUID> {
    boolean existsByIndirizzoAndCitta(String indirizzo, String citta);
}
