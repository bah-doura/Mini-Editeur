package caretaker;

import commandes.Commande;
import javafx.util.Pair;
import mementos.Memento;

public interface Enregistreur {

    public void arreter();
    public void demarrerEnregistrement();
    public boolean isReplay();
    public boolean isRecording();
    public void addMemento(Memento memento, Commande commande);
    public Memento getCurrentMemento();
    public void rejouer();
}
