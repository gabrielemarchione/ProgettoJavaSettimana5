package com.example.ProgettoJavaSettimana5.services;

import com.example.ProgettoJavaSettimana5.entities.Postazione;
import com.example.ProgettoJavaSettimana5.repositories.PostazioniRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class PostazioniService {

        @Autowired
        private PostazioniRepository postazioniRepository;

        public void salvaPostazione(Postazione postazione){
            postazioniRepository.save(postazione);
            log.info("Postazione " + postazione + " salvata con successo");

        }

        public void salvaVariePostazioni(List<Postazione> postazioni){
            for (Postazione postazione : postazioni) this.salvaPostazione(postazione);
        }

        public List<Postazione> trovaTutti(){
            return postazioniRepository.findAll();
        }
    }




