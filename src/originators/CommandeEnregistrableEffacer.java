package originators;

import commandes.Commande;
import mementos.Memento;
import mementos.MementoEffacer;

public class CommandeEnregistrableEffacer implements Enregistrable, Commande {

    @Override
    public void execute() {

    }

    @Override
    public Memento storInMemento() {
        return new MementoEffacer();
    }

    @Override
    public void restoreFromMemento(Memento memento) {

    }
}
