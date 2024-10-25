package com.example.ProgettoJavaSettimana5.runners;

import com.example.ProgettoJavaSettimana5.entities.Edificio;
import com.example.ProgettoJavaSettimana5.entities.Postazione;
import com.example.ProgettoJavaSettimana5.entities.Utente;
import com.example.ProgettoJavaSettimana5.enums.TipoPostazione;
import com.example.ProgettoJavaSettimana5.exceptions.ValidationException;
import com.example.ProgettoJavaSettimana5.services.EdificiService;
import com.example.ProgettoJavaSettimana5.services.PostazioniService;
import com.example.ProgettoJavaSettimana5.services.UtentiService;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class PopolaDbRunner implements CommandLineRunner {
    @Autowired
    private UtentiService utentiService;
    @Autowired
    private EdificiService edificiService;
    @Autowired
    private PostazioniService postazioniService;
    @Autowired
    private Faker faker;
    @Override
    public void run(String... args) throws Exception {
        List<Utente> utentiList = utentiService.trovaTutti();
        if(utentiList.isEmpty()){
            List<Utente> utentiPerDb = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                String name = faker.name().firstName();
                String surname = faker.name().lastName();
                String nomeCognome = name.toLowerCase() + "." + surname.toLowerCase();
                String[] dominio = new String[]{"@gmail.com"};
                Utente utente = new Utente(nomeCognome, name + " " + surname, nomeCognome + dominio[faker.random().nextInt(dominio.length)]);
                utentiPerDb.add(utente);
            }
            try {
                utentiService.salvaVariUtenti(utentiPerDb);
            } catch (ValidationException e){
                log.error(e.getMessage());
            }

        }
        List<Edificio> edificiList = edificiService.trovaTutti();
        if(edificiList.isEmpty()){
            List<Edificio> edificiPerDb = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                String[] citta = new String[]{"Milano", "Roma", "Napoli", "Palermo"};
                Edificio edificio = new Edificio(faker.company().name(), faker.address().streetName(), citta[faker.random().nextInt(citta.length)]);
                edificiPerDb.add(edificio);
            }
            try {
                edificiService.salvaMoltiEdifici(edificiPerDb);
            } catch (ValidationException e){
                log.error(e.getMessage());
            }

        }
        List<Postazione> postazioniList = postazioniService.trovaTutti();
        if(postazioniList.isEmpty()){
            List<Postazione> postazioniPerDb = new ArrayList<>();
            edificiList.forEach(edificio -> {
                for (int i = 0; i < faker.random().nextInt(1, 4); i++) {
                    String description = faker.harryPotter().quote();
                    if (description.length() > 100) description = description.substring(0,100) + "...";
                    TipoPostazione tipoPostazioneRandom = TipoPostazione.values()[faker.random().nextInt(TipoPostazione.values().length)];
                    Postazione postazione = new Postazione(description, tipoPostazioneRandom, edificio);
                    postazioniPerDb.add(postazione);
                }
            });
            postazioniService.salvaVariePostazioni(postazioniPerDb);
        }

    }
}
