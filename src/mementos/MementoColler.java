package mementos;

public class MementoColler implements Memento {

    private String texte;

    public MementoColler(String texte) {
        this.texte = texte;
    }

    public String getTexte()
    {
        return this.texte;
    }
}
