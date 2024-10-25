package com.example.ProgettoJavaSettimana5.services;

import com.example.ProgettoJavaSettimana5.entities.Prenotazione;
import com.example.ProgettoJavaSettimana5.entities.Utente;
import com.example.ProgettoJavaSettimana5.exceptions.EmptyListException;
import com.example.ProgettoJavaSettimana5.exceptions.ValidationException;
import com.example.ProgettoJavaSettimana5.repositories.PrenotazioniRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class PrenotazioniService {
    @Autowired
    private PrenotazioniRepository prenotazioniRepository;

    public void salvaPrenotazione(Prenotazione prenotazione){
        if(prenotazioniRepository.existsByPostazioneAndDataPrenotazione(prenotazione.getPostazione(), prenotazione.getDataPrenotazione()))
            throw new ValidationException("Impossibile salvare la prenotazione. Postazione occupata per la data selezionata");
        if(prenotazioniRepository.existsByUtenteAndDataPrenotazione(prenotazione.getUtente(), prenotazione.getDataPrenotazione()))
            throw new ValidationException("Impossibile salvare la prenotazione. Hai già una prenotazione per la data selezionata");;
        if(prenotazione.getDataPrenotazione().isBefore(LocalDate.now()))
            throw new ValidationException("Impossibile salvare la prenotazione.La data deve essere successivaalla data di oggi");
        prenotazioniRepository.save(prenotazione);
        log.info("Prenotazione " + prenotazione + "effettuata con successo ");
    }

    public void salvaVariePrenotazioni(List<Prenotazione> prenotazioni){
        for (Prenotazione prenotazione : prenotazioni) {
            try {
                this.salvaPrenotazione(prenotazione);
            } catch (ValidationException e){
                log.error(e.getMessage());
            }
        }
    }

    public List<Prenotazione> trovaTutte(){
        return prenotazioniRepository.findAll();
    }

}