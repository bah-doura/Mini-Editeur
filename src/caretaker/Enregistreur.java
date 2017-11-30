package caretaker;

import commandes.Commande;
import javafx.util.Pair;
import mementos.Memento;

public interface Enregistreur {

    public void arreter();
    public void demarrerEnregistrement();
    public Pair<Memento, Commande> rejouer(int index);
}
