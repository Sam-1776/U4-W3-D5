package samuelesimeone.classi;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class Utente {
    @Id
    @GeneratedValue
    private long numero_di_tessera;

    private String nome;
    private String cognome;
    private LocalDate data_di_nascita;

    public Utente(String nome, String cognome, LocalDate data_di_nascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.data_di_nascita = data_di_nascita;
    }

    public Utente() {
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getData_di_nascita() {
        return data_di_nascita;
    }

    public void setData_di_nascita(LocalDate data_di_nascita) {
        this.data_di_nascita = data_di_nascita;
    }

    public long getNumero_di_tessera() {
        return numero_di_tessera;
    }

    public void setNumero_di_tessera(long numero_di_tessera) {
        this.numero_di_tessera = numero_di_tessera;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", data_di_nascita=" + data_di_nascita +
                ", numero_di_tessera=" + numero_di_tessera +
                '}';
    }
}
