package mementos;

public class MementoInsererTexte implements Memento {

    private String texte;

    public MementoInsererTexte(String texte) {
        this.texte = texte;
    }

    public String getTexte()
    {
        return this.texte;
    }
}
