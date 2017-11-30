package originators;

import commandes.Commande;
import mementos.Memento;
import mementos.MementoColler;

public class CommandeEnregistrableColler implements Enregistrable, Commande {

    private String texte;

    public CommandeEnregistrableColler()
    {
        this.texte ="";
    }
    @Override
    public void execute() {

    }

    @Override
    public Memento storInMemento() {
        return new MementoColler(this.texte);

    }

    @Override
    public void restoreFromMemento(Memento memento) {
        MementoColler mementoColler = (MementoColler)memento;
        this.texte = mementoColler.getTexte();
    }

    public void setTexte(String texte)
    {
        this.texte = texte;
    }
}
