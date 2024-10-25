package com.example.ProgettoJavaSettimana5.services;


import com.example.ProgettoJavaSettimana5.entities.Utente;
import com.example.ProgettoJavaSettimana5.exceptions.ValidationException;
import com.example.ProgettoJavaSettimana5.repositories.UtentiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UtentiService {
    @Autowired
    private UtentiRepository utentiRepository;

    public void salvaUtente(Utente utente){
        if(utentiRepository.existsByEmailOrUsername(utente.getEmail(), utente.getUsername())) throw new ValidationException("Impossibile salvare utente. Username o email già in uso");
        if(utente.getNominativo().length() <= 2) throw new ValidationException("Impossibile salvare utente. Username non valido");
        utentiRepository.save(utente);
        log.info("Utente " + utente + " salvato ");
    }

    public void salvaVariUtenti(List<Utente> utenti){
        for (Utente utente : utenti) {
            try {
                this.salvaUtente(utente);
            } catch (ValidationException e){
                log.error(e.getMessage());
            }
        }
    }

    public List<Utente> trovaTutti(){
        return utentiRepository.findAll();
    }
}
