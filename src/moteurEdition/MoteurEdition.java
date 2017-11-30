package moteurEdition;

public interface MoteurEdition {
    public void couper();
    public void copier();
    public void coller();
    public void insererTexte(String text);
    public void selectionnerTexte(int debutStelection, int finSelection);
    public void effacer();
}
