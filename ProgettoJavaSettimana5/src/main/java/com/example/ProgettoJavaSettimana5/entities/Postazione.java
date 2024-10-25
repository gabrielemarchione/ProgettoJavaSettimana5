package com.example.ProgettoJavaSettimana5.entities;


import com.example.ProgettoJavaSettimana5.enums.TipoPostazione;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "postazioni")
public class Postazione {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID postazione_id;
    private String descrizione;
    @Column(nullable = false, name = "tipo_postazione")
    @Enumerated(EnumType.STRING)
    private TipoPostazione tipoPostazione;
    @ManyToOne
    @Setter(AccessLevel.NONE)
    @JoinColumn(name = "edificio_id")
    private Edificio edificio;
    @OneToMany(mappedBy = "postazione")
    @Setter(AccessLevel.NONE)
    private List<Prenotazione> prenotazioniList;

    public Postazione(String descrizione, TipoPostazione tipoPostazione, Edificio edificio) {
        this.descrizione = descrizione;
        this.tipoPostazione = tipoPostazione;
        this.edificio = edificio;
    }


    @Override
    public String toString() {
        return "Postazione = edificio: " + edificio +
                ", descrizione: " + descrizione  +
                ", tipo di postazione: " + tipoPostazione ;
    }
}
