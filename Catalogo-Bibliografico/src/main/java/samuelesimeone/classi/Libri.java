package samuelesimeone.classi;

import samuelesimeone.superclassi.Biblioteca;

import javax.persistence.Entity;
import java.time.LocalDate;


@Entity
public class Libri extends Biblioteca {
    private String autore;
    private String genere;

    public Libri() {
    }

    public Libri(String titolo, LocalDate anno_pubblicazione, Integer nPagine, String autore, String genere) {
        super(titolo, anno_pubblicazione, nPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public String getGenere() {
        return genere;
    }

    @Override
    public String toString() {
        return "Libri{" +
                "autore='" + autore + '\'' +
                ", genere='" + genere + '\'' +
                ", codice_ISBN=" + codice_ISBN +
                ", titolo='" + titolo + '\'' +
                ", anno_pubblicazione=" + anno_pubblicazione +
                ", nPagine=" + nPagine +
                '}';
    }
}
