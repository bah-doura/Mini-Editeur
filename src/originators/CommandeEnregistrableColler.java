package originators;

import caretaker.ConcreteEnregistreur;
import caretaker.Enregistreur;
import commandes.Commande;
import mementos.Memento;
import mementos.MementoColler;
import moteurEdition.MoteurEdition;

public class CommandeEnregistrableColler implements Enregistrable, Commande {

    private MoteurEdition moteurEdition;
    private Enregistreur enregistreur;

    public CommandeEnregistrableColler( MoteurEdition moteurEdition, Enregistreur enregistreur)
    {
        this.moteurEdition = moteurEdition;
        this.enregistreur = enregistreur;
    }
    @Override
    public void execute() {
        if(this.enregistreur.isRecording()){
            this.enregistreur.addMemento(storInMemento(), this);
        }
        else if(this.enregistreur.isReplay()){
            this.restoreFromMemento(enregistreur.getCurrentMemento());
        }
        this.moteurEdition.coller();
    }

    @Override
    public Memento storInMemento() {
        return new MementoColler();

    }

    @Override
    public void restoreFromMemento(Memento memento) {
        MementoColler mementoColler = (MementoColler)memento;
    }

}
