package samuelesimeone.dao;

import samuelesimeone.superclassi.Biblioteca;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class BibliotecaDao {
    private EntityManager em;

    public BibliotecaDao(EntityManager em) {
        this.em = em;
    }

    public void saveDb(Biblioteca x){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(x);
        transaction.commit();
        System.out.println("Elemento salvato con successo!");
    }

    public Biblioteca getById (long id){
        Biblioteca found = em.find(Biblioteca.class, id);
        if (found != null) {
            return found;
        }else {
            System.out.println("Elemento non trovato");
            return null;
        }
    }

    public void delete (long id){
        Biblioteca found = em.find(Biblioteca.class, id);

        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            System.out.println("Elemento " + found + " rimosso con successo");
            em.remove(found);
            transaction.commit();
        }else {
            System.out.println("Elemento non trovato");
        }
    }

    public List<Long> findAllISBN (){
        TypedQuery<Long> getAll = em.createNamedQuery("find_ISBN", Long.class);
        return getAll.getResultList();
    }

    public List<LocalDate> findAllDate(){
        TypedQuery<LocalDate> getAll = em.createNamedQuery("find_date", LocalDate.class);
        return getAll.getResultList();
    }

    public List<Biblioteca> getByDate (LocalDate x){
        TypedQuery<Biblioteca> found = em.createQuery("SELECT b FROM Biblioteca b WHERE b.anno_pubblicazione = :x", Biblioteca.class);
        found.setParameter("x" , x);
        if (found != null) {
            return found.getResultList();
        }else {
            System.out.println("Elemento non trovato");
            return null;
        }
    }

    public List<String> findAllAuthor(){
        TypedQuery<String> getAll = em.createNamedQuery("find_author", String.class);
        return getAll.getResultList();
    }

    public List<Biblioteca> getByAuthor (String x){
        TypedQuery<Biblioteca> found = em.createQuery("SELECT b FROM Biblioteca b WHERE b.autore = :x", Biblioteca.class);
        found.setParameter("x" , x);
        if (found != null) {
            return found.getResultList();
        }else {
            System.out.println("Elemento non trovato");
            return null;
        }
    }

    public List<Biblioteca> getByTitle(String str){
        TypedQuery<Biblioteca> found = em.createQuery("SELECT b FROM Biblioteca b WHERE LOWER (b.titolo) LIKE LOWER (CONCAT('%' ,:str, '%')) ", Biblioteca.class);
        found.setParameter("str", str);
        if (found != null) {
            return found.getResultList();
        }else {
            System.out.println("Elemento non trovato");
            return null;
        }
    }

    public List<Biblioteca> printCatalog (){
        TypedQuery<Biblioteca> getAll = em.createNamedQuery("find_catalog", Biblioteca.class);
        return getAll.getResultList();
    }
}
