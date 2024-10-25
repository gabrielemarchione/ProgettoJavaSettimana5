package com.example.ProgettoJavaSettimana5.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "prenotazioni")
public class Prenotazione {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID prenotazione_id;
    @Column(nullable = false, name = "data_prenotazione")
    private LocalDate dataPrenotazione;
    @Column(name = "n_partecipanti")
    private int nPartecipanti;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    @Setter(AccessLevel.NONE)
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "postazione_id")
    private Postazione postazione;


    public Prenotazione(LocalDate dataPrenotazione, Postazione postazione, Utente utente) {
        this.dataPrenotazione = dataPrenotazione;
        this.postazione = postazione;
        this.utente = utente;
    }

    @Override
    public String toString() {
        return "Prenotazione numero " + prenotazione_id +
                ", data di prenotazione: " + dataPrenotazione +
                ", numero partecipanti: " + (nPartecipanti != 0 ? nPartecipanti : "n/a") +
                ", postazione: " + postazione +
                ", prenotazione effettuata da: " + utente;
    }
}
