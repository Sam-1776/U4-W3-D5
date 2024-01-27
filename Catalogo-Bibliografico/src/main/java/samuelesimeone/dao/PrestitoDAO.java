package samuelesimeone.dao;

import samuelesimeone.classi.Prestito;
import samuelesimeone.superclassi.Biblioteca;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class PrestitoDAO {
    private EntityManager em;

    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    public void saveDb(Prestito x){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(x);
        transaction.commit();
        System.out.println("Prestito salvato con successo!");
    }

    public Prestito getById (long id){
        Prestito found = em.find(Prestito.class, id);
        if (found != null) {
            return found;
        }else {
            System.out.println("Prestito non trovato");
            return null;
        }
    }

    public void delete (long id){
        Prestito found = em.find(Prestito.class, id);

        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            em.remove(found);
            transaction.commit();
        }else {
            System.out.println("Prestito non trovato");
        }
    }

    public List<Biblioteca> getAllBorrowsToUser(long x) {
        Query getAll = em.createQuery("SELECT b FROM Prestito p JOIN Biblioteca b ON p.elemento_prestato = b.codice_ISBN WHERE p.utente.numero_di_tessera = :x AND p.data_restituzione_effettiva IS NULL");
        getAll.setParameter("x", x);
        if (getAll != null){
            return getAll.getResultList();
        }else {
            return null;
        }
    }
    public List<Prestito> getAllBorrowsExpired(LocalDate x){
        TypedQuery<Prestito> getAll = em.createQuery("SELECT p FROM Prestito p WHERE p.data_restituzione_prevista < :x AND p.data_restituzione_effettiva IS NULL", Prestito.class);
        getAll.setParameter("x", x);
        if (getAll != null){
            return getAll.getResultList();
        }else {
            return null;
        }
    }

    public List<Prestito> getElement(long x){
        TypedQuery<Prestito> getIt = em.createQuery("SELECT p FROM Prestito p WHERE p.elemento_prestato.codice_ISBN =:x AND p.data_restituzione_effettiva IS NULL", Prestito.class);
        getIt.setParameter("x",x);
        if (getIt != null){
            return getIt.getResultList();
        }else {
            return null;
        }
    }
}
