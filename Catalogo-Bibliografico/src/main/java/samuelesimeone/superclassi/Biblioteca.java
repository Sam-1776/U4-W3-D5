package samuelesimeone.superclassi;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "biblioteca")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQuery(name = "find_author", query = "SELECT b.autore FROM Biblioteca b WHERE b.autore IS NOT NULL")
@NamedQuery(name = "find_date", query = "SELECT b.anno_pubblicazione FROM Biblioteca b")
@NamedQuery(name = "find_ISBN", query = "SELECT b.codice_ISBN FROM Biblioteca b")
@NamedQuery(name = "find_catalog", query = "SELECT b FROM Biblioteca b")
public abstract class Biblioteca {
    @Id
    @GeneratedValue
    protected long codice_ISBN;
    protected String titolo;
    protected LocalDate anno_pubblicazione;
    protected Integer nPagine;

    public Biblioteca() {
    }

    public Biblioteca(String titolo, LocalDate anno_pubblicazione, Integer nPagine) {
        this.titolo = titolo;
        this.anno_pubblicazione = anno_pubblicazione;
        this.nPagine = nPagine;
    }

    public long getCodice_ISBN() {
        return codice_ISBN;
    }

    public String getTitolo() {
        return titolo;
    }

    public LocalDate getAnno_pubblicazione() {
        return anno_pubblicazione;
    }

    public Integer getnPagine() {
        return nPagine;
    }
}
