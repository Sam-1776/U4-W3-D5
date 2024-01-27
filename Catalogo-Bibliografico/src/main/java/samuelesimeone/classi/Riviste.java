package samuelesimeone.classi;

import samuelesimeone.enumeratori.Periodo;
import samuelesimeone.superclassi.Biblioteca;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Entity
public class Riviste extends Biblioteca {

    @Enumerated(EnumType.STRING)
    private Periodo periodicità;

    public Riviste() {
    }

    public Riviste( String titolo, LocalDate anno_pubblicazione, Integer nPagine, Periodo periodicità) {
        super(titolo, anno_pubblicazione, nPagine);
        this.periodicità = periodicità;
    }

    public Periodo getPeriodicità() {
        return periodicità;
    }

    @Override
    public String toString() {
        return "Riviste{" +
                "titolo='" + titolo + '\'' +
                ", codice_ISBN=" + codice_ISBN +
                ", periodicità=" + periodicità +
                ", anno_pubblicazione=" + anno_pubblicazione +
                ", nPagine=" + nPagine +
                '}';
    }
}
