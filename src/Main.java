import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        final int MAX_GIOCATORI = 11;
        Giocatore[] squadra = new Giocatore[MAX_GIOCATORI];
        int scelta;
        String nome;
        boolean capitano;
        int goal;
        int numeroGiocatori = 0;
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("1 - Aggiungi giocatore");
            System.out.println("2 - Visualizza giocatori");
            System.out.println("3 - Modifica giocatore");
            System.out.println("4 - Elimina giocatore");
            System.out.println("5 - Visualizza giocatori con 5+ goal");
            System.out.println("6 - Visualizza capitano");
            System.out.println("7 - Randomizza capitano");
            System.out.println(">>> ");
            scelta = input.nextInt();
            input.nextLine();

            switch (scelta) {
                case 1:
                    if (numeroGiocatori < MAX_GIOCATORI) {
                        System.out.print("Scrivi il nome del giocatore >> ");
                        nome = input.nextLine();
                        System.out.print("Scrivi il numero di goal del giocatore >> ");
                        goal = input.nextInt();
                        System.out.print("Il giocatore è anche il capitano? >> ");
                        capitano = input.nextBoolean();
                        input.nextLine();  // Consuma la newline rimanente

                        if (checkCapitano(squadra, numeroGiocatori) && capitano) {
                            System.out.println("C'è già un capitano, riprova.");
                            break;
                        }
                        squadra[numeroGiocatori] = new Giocatore(nome, goal, capitano);
                        numeroGiocatori++;
                    } else {
                        System.out.println("La squadra è piena, non puoi aggiungere altri giocatori.");
                    }
                    break;

                case 2:
                    System.out.println("Tutti i giocatori: ");
                    for (int i = 0; i < numeroGiocatori; i++) {
                        System.out.format("Nome: %s\nGoal: %d\nCapitano: %b\n",
                                squadra[i].getNome(), squadra[i].getGoal(), squadra[i].isCapitano());
                    }
                    break;

                case 3:
                    System.out.print("Scrivi il nome del giocatore da modificare >> ");
                    nome = input.nextLine();
                    boolean trovato = false;
                    for (int i = 0; i < numeroGiocatori; i++) {
                        if (squadra[i].getNome().equalsIgnoreCase(nome)) {
                            trovato = true;
                            System.out.print("Scrivi il nuovo nome del giocatore >> ");
                            nome = input.nextLine();
                            System.out.print("Scrivi il nuovo numero di goal del giocatore >> ");
                            goal = input.nextInt();
                            System.out.print("Il giocatore è il capitano? >> ");
                            capitano = input.nextBoolean();
                            input.nextLine();  // Consuma la newline rimanente

                            if (checkCapitano(squadra, numeroGiocatori) && capitano) {
                                System.out.println("C'è già un capitano, riprova.");
                                break;
                            }
                            squadra[i].setNome(nome);
                            squadra[i].setGoal(goal);
                            squadra[i].setCapitano(capitano);
                            System.out.println("Giocatore modificato con successo");
                            break;
                        }
                    }
                    if (!trovato) {
                        System.out.println("Giocatore non trovato.");
                    }
                    break;

                case 4:
                    System.out.print("Scrivi il nome del giocatore da eliminare >> ");
                    nome = input.nextLine();
                    trovato = false;
                    for (int i = 0; i < numeroGiocatori; i++) {
                        if (squadra[i].getNome().equalsIgnoreCase(nome)) {
                            trovato = true;

                            for (int j = i; j < numeroGiocatori - 1; j++) {
                                squadra[j] = squadra[j + 1];
                            }
                            squadra[numeroGiocatori - 1] = null; // Pulisce l'ultimo elemento
                            numeroGiocatori--;
                            System.out.println("Giocatore eliminato con successo");
                            break;
                        }
                    }
                    if (!trovato) {
                        System.out.println("Giocatore non trovato.");
                    }
                    break;

                case 5:
                    System.out.println("Ecco i giocatori che hanno realizzato più di 5 goal:");
                    boolean trovatoGoal = false;
                    for (int i = 0; i < numeroGiocatori; i++) {
                        if (squadra[i].getGoal() > 5) {
                            System.out.format("Nome: %s\nGoal: %d\nCapitano: %b\n",
                                    squadra[i].getNome(), squadra[i].getGoal(), squadra[i].isCapitano());
                            trovatoGoal = true;
                        }
                    }
                    if (!trovatoGoal) {
                        System.out.println("Nessun giocatore ha realizzato più di 5 goal.");
                    }
                    break;

                case 6:
                    System.out.println("Ecco il capitano: ");
                    boolean capitanoTrovato = false;
                    for (int i = 0; i < numeroGiocatori; i++) {
                        if (squadra[i].isCapitano()) {
                            System.out.println(squadra[i].getNome());
                            capitanoTrovato = true;
                            break;
                        }
                    }
                    if (!capitanoTrovato) {
                        System.out.println("Non c'è un capitano.");
                    }
                    break;

                case 7:
                    Random rng = new Random();
                    int indiceCapitano = rng.nextInt(numeroGiocatori);
                    for (int i = 0; i < numeroGiocatori; i++) {
                        squadra[i].setCapitano(false);
                    }
                    squadra[indiceCapitano].setCapitano(true);
                    System.out.println("Capitano scelto casualmente.");
                    break;

                case 8:
                    System.out.println("Uscita dal programma...");
                    break;

                default:
                    System.out.println("Scelta non valida. Riprova.");
                    break;
            }
        } while (scelta != 8);
    }

    private static boolean checkCapitano(Giocatore[] squadra, int numeroGiocatori) {
        for (int i = 0; i < numeroGiocatori; i++) {
            if (squadra[i].isCapitano()) {
                return true;
            }
        }
        return false;
    }
}
/* //main con squadra
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Squadra squadra = new Squadra();
        int scelta;

        do {
            System.out.print(">>> ");
            scelta = input.nextInt();
            input.nextLine();  // Consuma la newline

            switch (scelta) {
                case 1:
                    System.out.print("Scrivi il nome del giocatore >> ");
                    String nome = input.nextLine();
                    System.out.print("Scrivi il numero di goal del giocatore >> ");
                    int goal = input.nextInt();
                    System.out.print("Il giocatore è anche il capitano? >> ");
                    boolean capitano = input.nextBoolean();
                    squadra.aggiungiGiocatore(nome, goal, capitano);
                    break;

                case 2:
                    squadra.stampaGiocatori();
                    break;

                case 3:
                    System.out.print("Scrivi il nome del giocatore da modificare >> ");
                    nome = input.nextLine();
                    System.out.print("Scrivi il nuovo nome del giocatore >> ");
                    String nuovoNome = input.nextLine();
                    System.out.print("Scrivi il nuovo numero di goal del giocatore >> ");
                    goal = input.nextInt();
                    System.out.print("Il giocatore è il capitano? >> ");
                    capitano = input.nextBoolean();
                    squadra.modificaGiocatore(nome, nuovoNome, goal, capitano);
                    break;

                case 4:
                    System.out.print("Scrivi il nome del giocatore da eliminare >> ");
                    nome = input.nextLine();
                    squadra.rimuoviGiocatore(nome);
                    break;

                case 5:
                    squadra.stampaGiocatoriConPiuDi5Goal();
                    break;

                case 6:
                    squadra.stampaCapitano();
                    break;
*/

