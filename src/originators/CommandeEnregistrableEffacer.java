package originators;

import caretaker.Enregistreur;
import commandes.Commande;
import mementos.Memento;
import mementos.MementoEffacer;
import moteurEdition.MoteurEdition;

public class CommandeEnregistrableEffacer implements Enregistrable, Commande {

    private MoteurEdition moteurEdition;
    private Enregistreur enregistreur;

    public CommandeEnregistrableEffacer(MoteurEdition moteurEdition, Enregistreur enregistreur){
        this.moteurEdition = moteurEdition;
        this.enregistreur = enregistreur;
    }

    @Override
    public void execute() {
        if(this.enregistreur.isRecording()){
            this.enregistreur.addMemento(this.storInMemento(), this);
        }
        else if(this.enregistreur.isReplay()){
            this.restoreFromMemento(this.enregistreur.getCurrentMemento());
        }
        this.moteurEdition.effacer();
    }

    @Override
    public Memento storInMemento() {
        return new MementoEffacer();
    }

    @Override
    public void restoreFromMemento(Memento memento) {

    }
}
