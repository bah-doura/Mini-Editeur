package originators;

import commandes.Commande;
import mementos.Memento;
import mementos.MementoInsererTexte;

public class CommandeEnregistrableInsererText implements Enregistrable, Commande {

    private String texte;

    public CommandeEnregistrableInsererText()
    {
        this.texte = "";
    }

    @Override
    public void execute() {

    }

    @Override
    public Memento storInMemento() {
        return new MementoInsererTexte(this.texte);
    }

    @Override
    public void restoreFromMemento(Memento memento) {

        MementoInsererTexte mementoInsererTexte = (MementoInsererTexte)memento;
        this.texte = mementoInsererTexte.getTexte();
    }

    public void setTexte(String texte)
    {
        this.texte = texte;
    }
}
