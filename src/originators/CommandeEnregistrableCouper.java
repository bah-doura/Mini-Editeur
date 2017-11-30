package originators;

import commandes.Commande;
import mementos.Memento;
import mementos.MementoCouper;

public class CommandeEnregistrableCouper implements Enregistrable, Commande {

    @Override
    public void execute() {

    }

    @Override
    public Memento storInMemento() {
        return new MementoCouper();
    }

    @Override
    public void restoreFromMemento(Memento memento) {

    }
}
