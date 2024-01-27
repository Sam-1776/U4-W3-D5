package samuelesimeone;

import com.github.javafaker.Faker;
import samuelesimeone.classi.Libri;
import samuelesimeone.classi.Prestito;
import samuelesimeone.classi.Riviste;
import samuelesimeone.classi.Utente;
import samuelesimeone.dao.BibliotecaDao;
import samuelesimeone.dao.PrestitoDAO;
import samuelesimeone.dao.UtenteDAO;
import samuelesimeone.enumeratori.Periodo;
import samuelesimeone.superclassi.Biblioteca;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;

public class Application {

    static Faker faker = new Faker(Locale.ITALY);
    static Random rdm = new Random();
    static Scanner input = new Scanner(System.in);
    static Integer cmd;

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogo_bibliografico");

    static Supplier<Libri> genrateBook = () ->{
      return new Libri(faker.book().title(), generateData(), rdm.nextInt(1000), faker.book().author(), faker.book().genre() );
    };

    static Supplier<Riviste> generateMagazine = () ->{

        Integer x = rdm.nextInt(3);
        Riviste magazine;
        if (x == 1) {
            magazine = new Riviste(faker.book().title(), generateData(), rdm.nextInt(12,30), Periodo.Settimanale);
        } else if (x == 2) {
            magazine = new Riviste(faker.book().title(), generateData(), rdm.nextInt(12,30), Periodo.Mensile);
        }else{
            magazine = new Riviste(faker.book().title(), generateData(), rdm.nextInt(12,30), Periodo.Semestrale);
        }

        return magazine;
    };

    static Supplier<Utente> generatoreUser = () ->{
        return new Utente(faker.name().firstName(), faker.name().lastName(),generateData());
    };


    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        BibliotecaDao ed = new BibliotecaDao(em);
        UtenteDAO ud = new UtenteDAO(em);
        PrestitoDAO pd = new PrestitoDAO(em);

        System.out.println("Progetto W3");

        List<Biblioteca> bibliotecaList = new ArrayList<>();
        List<Utente> utenteList = new ArrayList<>();


//        for (int i = 0; i < 100; i++) {
//            generateCatalogo(bibliotecaList);
//        }
//        for (int i = 0; i < 50; i++) {
//            utenteList.add(generatoreUser.get());
//        }
//
////        for (Biblioteca element: bibliotecaList){
////            ed.saveDb(element);
////        }
//        for (Utente user : utenteList){
//            ud.saveDb(user);
//        }




        System.out.println("********************************** Benvenuto ************************************");
            System.out.println("Che azioni vuoi fare nel catalogo? "+ "\n" + "(1-Aggiungi, 2-Cancellazione, 3-Ricerca ISBN, 4-Ricerca Data, 5-Ricerca Autore, 6-Ricerca Titolo, 7-Crea Prestito, 8-Cerca Prestiti, 9-Prestiti Scaduti, 10-Vedi Catalogo, 11-Restituzione, 0-Esci)");
            cmd = input.nextInt();
            switch (cmd){
                case 1: {
                    printCatalogo(ed);
                    newElement(ed);
                    break;
                }
                case 2: {
                    removeElement(ed);
                    break;
                }
                case 3: {
                    searchElementByISBN(ed);
                    break;
                }
                case 4:{
                    searchElementByDate(ed);
                    break;
                }
                case 5:{
                    searchElementByAuthor(ed);
                    break;
                }
                case 6:{
                    searchElementByPartOfTitle(ed);
                    break;
                }
                case 7:{
                    generatePrestito(pd,ud,ed);
                    break;
                }
                case 8:{
                    checkPrestito(pd);
                    break;
                }
                case 9:{
                    searchExpired(pd);
                    break;
                }
                case 10:{
                    printCatalogo(ed);
                    break;
                }
                case 11:{
                    restitutionPrestito(pd);
                    break;
                }
                default:{
                    System.out.println("********************************** Arrivederci ************************************");
                    break;
                }
            }
            input.close();

            em.close();
            emf.close();


    }

    public static List<Biblioteca> generateCatalogo(List<Biblioteca> x){
        Integer y = rdm.nextInt(2);
        if (y == 1) {
            x.add(genrateBook.get());
        }else {
            x.add(generateMagazine.get());
        }
        return x;
    }

    public static LocalDate generateData (){

        int year = rdm.nextInt(1600,2023);
        int month = rdm.nextInt(12) + 1;
        int maxDay = LocalDate.of(year, month, 1).lengthOfMonth();
        int day = rdm.nextInt(maxDay) + 1;

        LocalDate randomDate = LocalDate.of(year, month, day);

        return randomDate;

    }

    public static void newElement(BibliotecaDao x){
        String str = "";
        System.out.println("Cosa vuoi aggiungere? (libro o rivista)");
        Scanner scanner = new Scanner(System.in);
        str = scanner.nextLine();
        if (str.equals("libro")){
            Libri newBook = genrateBook.get();
            System.out.println("Libro aggiunto: " + newBook.toString());
            x.saveDb(newBook);
        }else if (str.equals("rivista")){
            Riviste newMegazine = generateMagazine.get();
            System.out.println("Rivista aggiunta: " + newMegazine.toString());
            x.saveDb(newMegazine);
        }else {
            System.out.println("Non hai aggiunto nessun elemento in catalogo");
        }
    }

    public static void removeElement(BibliotecaDao x){
        System.out.println("Codici ISBN");
        x.findAllISBN().forEach(System.out::println);
        Long y = null;
        System.out.println("Quale elemento vuoi eliminare? (Inserire il suo codice ISBN)");
        y = input.nextLong();
        x.delete(y);
    }

    public static void searchElementByISBN(BibliotecaDao x){
        System.out.println("Codici ISBN");
        x.findAllISBN().forEach(System.out::println);
        System.out.println("Quale elemento vuoi cercare?");
        Long y;
        y = input.nextLong();
        Biblioteca element = x.getById(y);
        System.out.println("Elemento cercato: " + element);
    }

    public static void searchElementByDate(BibliotecaDao x){
        System.out.println("Date di pubblicazione");
        x.findAllDate().forEach(System.out::println);
        try{;
            System.out.println("Di che anno è l'elemento?");
            Scanner scanner = new Scanner(System.in);
            String test = scanner.nextLine();
            LocalDate g = LocalDate.parse(test);
            x.getByDate(g).forEach(System.out::println);
                scanner.close();
        }catch (DateTimeException e){
            System.out.println(e.getMessage());
        }
    }

    public static void searchElementByAuthor(BibliotecaDao x){
        System.out.println("Autori");
        x.findAllAuthor().forEach(System.out::println);
        try{
            System.out.println("Inserire il nome dell'autore");
            Scanner scanner = new Scanner(System.in);
            String b = scanner.nextLine();
            x.getByAuthor(b).forEach(System.out::println);
            scanner.close();
        }catch (Exception e){
            e.getMessage();
        }
    }

    public static void searchElementByPartOfTitle(BibliotecaDao x){
        System.out.println("Inserire il titolo o parte di esso");
        Scanner scanner = new Scanner(System.in);
        String y = scanner.nextLine();
        x.getByTitle(y).forEach(System.out::println);
        scanner.close();
    }

    public static void generatePrestito(PrestitoDAO x, UtenteDAO y, BibliotecaDao z){
        try{
            System.out.println("Inserire numero tessera");
            Scanner scanner = new Scanner(System.in);
            long input = scanner.nextLong();
            Utente u = y.getById(input);
            if (u != null){
                System.out.println("Inserire elemento");
                input = scanner.nextLong();
                Biblioteca b = z.getById(input);
                if (b != null ){
                    if (x.getElement(input).size() == 0){
                        LocalDate data = LocalDate.now();
                        Prestito p = new Prestito(u,b,data,data.plusDays(30));
                        x.saveDb(p);
                    }else {
                        System.out.println("Non puoi prendere questo elemento");
                    }
                }
            }
            scanner.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void checkPrestito(PrestitoDAO x){
        System.out.println("Inserire numero tessera");
        Scanner scanner = new Scanner(System.in);
        long input = scanner.nextLong();
        List<Biblioteca> list = x.getAllBorrowsToUser(input);
        if (list.size() == 0){
            System.out.println("Non ci sono prestiti");
        }else {
            list.forEach(System.out::println);
        }

        scanner.close();
    }

    public static void searchExpired(PrestitoDAO x){
        LocalDate today = LocalDate.now();
        List<Prestito> lista = x.getAllBorrowsExpired(today);
        if (lista.size() == 0){
            System.out.println("Tutti gli elementi sono stati restituiti");
        }else {
            lista.forEach(System.out::println);
        }
    }

    public static void restitutionPrestito(PrestitoDAO x){
        System.out.println("Inserire Id prestito");
        Scanner scanner = new Scanner(System.in);
        long input = scanner.nextLong();
        Prestito p = x.getById(input);
        if (p != null){
            p.setData_restituzione_effettiva(LocalDate.now());
            x.saveDb(p);
        }else {
            System.out.println("Non è stato trovato nessun prestito");
        }
    }


    public static void printCatalogo(BibliotecaDao x){
        System.out.println("********************* Catalogo Completo ********************");
        x.printCatalog().forEach(System.out::println);
    }

}
