import java.util.Random;
import java.util.Scanner;

public class Squadra {
    private Giocatore[] squadra;
    private int numeroGiocatori;
    private final int MAX_GIOCATORI = 10;

    // Costruttore
    public Squadra() {
        squadra = new Giocatore[MAX_GIOCATORI];
        numeroGiocatori = 0;
    }

    public boolean aggiungiGiocatore(String nome, int goal, boolean capitano) {
        if (numeroGiocatori < MAX_GIOCATORI) {
            if (checkCapitano() && capitano) {
                System.out.println("C'è già un capitano, riprova.");
                return false;
            }
            squadra[numeroGiocatori] = new Giocatore(nome, goal, capitano);
            numeroGiocatori++;
            return true;
        } else {
            System.out.println("La squadra è piena, non puoi aggiungere altri giocatori.");
            return false;
        }
    }

    public boolean rimuoviGiocatore(String nome) {
        for (int i = 0; i < numeroGiocatori; i++) {
            if (squadra[i].getNome().equalsIgnoreCase(nome)) {
                // Spostamento degli altri giocatori per eliminare l'elemento
                for (int j = i; j < numeroGiocatori - 1; j++) {
                    squadra[j] = squadra[j + 1];
                }
                squadra[numeroGiocatori - 1] = null; // Pulisce l'ultimo elemento
                numeroGiocatori--;
                return true;
            }
        }
        return false;  // Giocatore non trovato
    }

    public boolean modificaGiocatore(String nome, String nuovoNome, int nuovoGoal, boolean nuovoCapitano) {
        for (int i = 0; i < numeroGiocatori; i++) {
            if (squadra[i].getNome().equalsIgnoreCase(nome)) {
                if (checkCapitano() && nuovoCapitano) {
                    System.out.println("C'è già un capitano, riprova.");
                    return false;
                }
                squadra[i].setNome(nuovoNome);
                squadra[i].setGoal(nuovoGoal);
                squadra[i].setCapitano(nuovoCapitano);
                return true;
            }
        }
        return false;  // Giocatore non trovato
    }

    private boolean checkCapitano() {
        for (int i = 0; i < numeroGiocatori; i++) {
            if (squadra[i].isCapitano()) {
                return true;
            }
        }
        return false;
    }

    public void stampaGiocatori() {
        if (numeroGiocatori == 0) {
            System.out.println("La squadra non ha giocatori.");
        } else {
            for (int i = 0; i < numeroGiocatori; i++) {
                System.out.println(squadra[i].toString());  // Stampa ogni giocatore
            }
        }
    }

    public void stampaGiocatoriConPiuDi5Goal() {
        boolean trovato = false;
        for (int i = 0; i < numeroGiocatori; i++) {
            if (squadra[i].getGoal() > 5) {
                System.out.println(squadra[i].toString());  // Stampa ogni giocatore
                trovato = true;
            }
        }
        if (!trovato) {
            System.out.println("Nessun giocatore ha realizzato più di 5 goal.");
        }
    }

    public void stampaCapitano() {
        boolean capitanoTrovato = false;
        for (int i = 0; i < numeroGiocatori; i++) {
            if (squadra[i].isCapitano()) {
                System.out.println("Il capitano è: " + squadra[i].getNome());
                capitanoTrovato = true;
                break;
            }
        }
        if (!capitanoTrovato) {
            System.out.println("Non c'è un capitano.");
        }
    }

    public void scegliCapitanoCasualmente() {
        if (numeroGiocatori > 0) {
            Random rng = new Random();
            int indiceCapitano = rng.nextInt(numeroGiocatori);
            for (int i = 0; i < numeroGiocatori; i++) {
                squadra[i].setCapitano(false);  // Rimuovi la carica da tutti i giocatori
            }
            squadra[indiceCapitano].setCapitano(true);  // Assegna il capitano casualmente
            System.out.println("Capitano scelto casualmente.");
        } else {
            System.out.println("Non ci sono giocatori nella squadra.");
        }
    }

    public int getNumeroGiocatori() {
        return numeroGiocatori;
    }
}
