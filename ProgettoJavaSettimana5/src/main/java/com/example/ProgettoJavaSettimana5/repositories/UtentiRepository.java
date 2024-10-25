package com.example.ProgettoJavaSettimana5.repositories;

import com.example.ProgettoJavaSettimana5.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UtentiRepository extends JpaRepository<Utente, UUID> {
    boolean existsByEmailOrUsername(String email, String username);
}
