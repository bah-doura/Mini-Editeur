package originators;

import caretaker.Enregistreur;
import commandes.Commande;
import mementos.Memento;
import mementos.MementoCopier;
import moteurEdition.MoteurEdition;

public class CommandeEnregistrableCopier implements Enregistrable, Commande {


    private MoteurEdition moteurEdition;
    private Enregistreur enregistreur;

    /**
     * Constructeur
     * @param enregistreur
     * @param moteurEdition
     */
    public CommandeEnregistrableCopier(Enregistreur enregistreur, MoteurEdition moteurEdition){
        this.moteurEdition = moteurEdition;
        this.enregistreur = enregistreur;
    }

    /**
     * Crée un nouveau Memento
     * @return
     */
    @Override
    public Memento storInMemento() {
        return new MementoCopier();
    }

    @Override
    public void restoreFromMemento(Memento memento) {

    }

    /**
     * Exécution de la commande CommandeEnregistrableCopier
     */
    @Override
    public void execute() {
        try{
            if(this.enregistreur.isRecording()){
                this.enregistreur.addMemento(this.storInMemento(), this);
            }
            else if(this.enregistreur.isReplay()){
                this.restoreFromMemento(this.enregistreur.getCurrentMemento());
            }
            this.moteurEdition.copier();
        }
        catch(StringIndexOutOfBoundsException e){
            System.out.println("impossible de récuprérer la selection");
        };

    }
}
