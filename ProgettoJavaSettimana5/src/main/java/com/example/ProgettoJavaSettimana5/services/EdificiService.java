package com.example.ProgettoJavaSettimana5.services;

import com.example.ProgettoJavaSettimana5.entities.Edificio;
import com.example.ProgettoJavaSettimana5.repositories.EdificiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ProgettoJavaSettimana5.exceptions.ValidationException;
import java.util.List;

@Service
@Slf4j
public class EdificiService {
    @Autowired
    private EdificiRepository edificiRepository;

    public void salvaEdificio(Edificio edificio){
        if(edificiRepository.existsByIndirizzoAndCitta(edificio.getIndirizzo(), edificio.getCitta())) throw new ValidationException("Impossibile salvare l'edificio/indirizzo, già in uso");
        edificiRepository.save(edificio);
        log.info("Edificio " + edificio + " salvato");
    }

    public void salvaMoltiEdifici(List<Edificio> edifici){
        for (Edificio utente : edifici) {
            try {
                this.salvaEdificio(utente);
            } catch (ValidationException e){
                log.error(e.getMessage());
            }
        }
    }

    public List<Edificio> trovaTutti(){
        return edificiRepository.findAll();
    }
}
