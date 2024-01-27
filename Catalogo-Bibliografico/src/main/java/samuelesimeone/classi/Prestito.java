package samuelesimeone.classi;

import samuelesimeone.superclassi.Biblioteca;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "borrows")
public class Prestito {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name = "tessera_utente")
    private Utente utente;
    @OneToOne
    @JoinColumn(name = "codice_ISBN")
    private Biblioteca elemento_prestato;
    private LocalDate data_inizio_prestito;
    private LocalDate data_restituzione_prevista;
    private LocalDate data_restituzione_effettiva;

    public Prestito(Utente utente, Biblioteca elemento_prestato, LocalDate data_inizio_prestito, LocalDate data_restituzione_prevista) {
        this.utente = utente;
        this.elemento_prestato = elemento_prestato;
        this.data_inizio_prestito = data_inizio_prestito;
        this.data_restituzione_prevista = data_restituzione_prevista;
    }

    public Prestito() {
    }

    public long getId() {
        return id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Biblioteca getElemento_prestato() {
        return elemento_prestato;
    }

    public void setElemento_prestato(Biblioteca elemento_prestato) {
        this.elemento_prestato = elemento_prestato;
    }

    public LocalDate getData_inizio_prestito() {
        return data_inizio_prestito;
    }

    public void setData_inizio_prestito(LocalDate data_inizio_prestito) {
        this.data_inizio_prestito = data_inizio_prestito;
    }

    public LocalDate getData_restituzione_prevista() {
        return data_restituzione_prevista;
    }

    public void setData_restituzione_prevista(LocalDate data_restituzione_prevista) {
        this.data_restituzione_prevista = data_restituzione_prevista;
    }

    public LocalDate getData_restituzione_effettiva() {
        return data_restituzione_effettiva;
    }

    public void setData_restituzione_effettiva(LocalDate data_restituzione_effettiva) {
        this.data_restituzione_effettiva = data_restituzione_effettiva;
    }

    @Override
    public String toString() {
        return "Prestito{" +
                "id=" + id +
                ", utente=" + utente +
                ", elemento_prestato=" + elemento_prestato +
                ", data_inizio_prestito=" + data_inizio_prestito +
                ", data_restituzione_prevista=" + data_restituzione_prevista +
                ", data_restituzione_effettiva=" + data_restituzione_effettiva +
                '}';
    }
}
