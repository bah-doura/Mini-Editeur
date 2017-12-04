package mementos;

public class MementoInsererTexte implements Memento {

    private String texte;

    /**
     * Constructeur
     * @param texte Texte inséré
     */
    public MementoInsererTexte(String texte) {
        this.texte = texte;
    }

    /**
     * Retourne le texte inséré
     * @return
     */
    public String getTexte()
    {
        return this.texte;
    }
}
