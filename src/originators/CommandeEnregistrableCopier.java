package originators;

import commandes.Commande;
import mementos.Memento;
import mementos.MementoCopier;

public class CommandeEnregistrableCopier implements Enregistrable, Commande {


    @Override
    public Memento storInMemento() {
        return new MementoCopier();
    }

    @Override
    public void restoreFromMemento(Memento memento) {

    }

    @Override
    public void execute() {

    }
}
