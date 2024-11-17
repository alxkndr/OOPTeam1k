public class Giocatore {

    private String nome;
    private int goal;
    private boolean capitano;

    public Giocatore(String nome, int goal, boolean capitano) {
        this.nome = nome;
        this.goal = goal;
        this.capitano = capitano;
    }

    // Getter e Setter
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public boolean isCapitano() {
        return capitano;
    }

    public void setCapitano(boolean capitano) {
        this.capitano = capitano;
    }

    // Metodo equals per confrontare due oggetti Giocatore
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Giocatore giocatore = (Giocatore) obj;
        return goal == giocatore.goal && capitano == giocatore.capitano && nome.equals(giocatore.nome);
    }

    // Metodo toString per una rappresentazione in formato stringa
    @Override
    public String toString() {
        return "Giocatore{" +
                "nome='" + nome + '\'' +
                ", goal=" + goal +
                ", capitano=" + capitano +
                '}';
    }
}
