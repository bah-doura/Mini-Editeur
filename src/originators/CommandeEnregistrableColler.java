package originators;

import caretaker.Enregistreur;
import commandes.Commande;
import mementos.Memento;
import mementos.MementoColler;
import moteurEdition.MoteurEdition;

public class CommandeEnregistrableColler implements Enregistrable, Commande {

    private MoteurEdition moteurEdition;
    private Enregistreur enregistreur;

    /**
     * Constructeur
     * @param moteurEdition
     * @param enregistreur
     */
    public CommandeEnregistrableColler( MoteurEdition moteurEdition, Enregistreur enregistreur)
    {
        this.moteurEdition = moteurEdition;
        this.enregistreur = enregistreur;
    }

    /**
     * Exécution de la Commande CommandeEnregistrableColler
     */
    @Override
    public void execute() {
        try{
            if(this.enregistreur.isRecording()){
                this.enregistreur.addMemento(storInMemento(), this);
            }
            else if(this.enregistreur.isReplay()){
                this.restoreFromMemento(enregistreur.getCurrentMemento());
            }
            this.moteurEdition.coller();
        }
        catch(StringIndexOutOfBoundsException e){
            System.out.println("impossible de récuprérer la selection");
        };

    }

    /**
     * Crée un nouveau Memento
     * @return
     */
    @Override
    public Memento storInMemento() {
        return new MementoColler();
    }

    /**
     * Récupère le MementoColler à partir du memento en paramètre
     * @param memento
     */
    @Override
    public void restoreFromMemento(Memento memento) {
        MementoColler mementoColler = (MementoColler)memento;
    }

}
