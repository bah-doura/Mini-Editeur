package originators;

import IHM.Controleur;
import caretaker.Enregistreur;
import commandes.Commande;
import mementos.Memento;
import mementos.MementoSelectionner;
import moteurEdition.MoteurEdition;
import moteurEdition.Selection;

public class CommandeEnregistrableSelectionner implements Enregistrable, Commande {

    private MoteurEdition moteurEdition;
    private Enregistreur enregistreur;
    private Controleur controleur;
    Selection selection;

    /**
     * Constructeur
     * @param moteurEdition
     * @param enregistreur
     * @param controleur
     */
    public CommandeEnregistrableSelectionner(MoteurEdition moteurEdition, Enregistreur enregistreur, Controleur controleur)
    {
        this.moteurEdition = moteurEdition;
        this.enregistreur = enregistreur;
        this.controleur = controleur;
        this.selection = new Selection();
    }

    /**
     * Exécute la commande CommandeEnregistrableSelectionner
     */
    @Override
    public void execute() {
        this.setSelection(controleur.getDebutSelection(),controleur.getFinSelection());
        if(this.enregistreur.isRecording()){
            this.enregistreur.addMemento(this.storInMemento(), this);
        }
        else if(this.enregistreur.isReplay()){
            this.restoreFromMemento(this.enregistreur.getCurrentMemento());
        }
        this.moteurEdition.selectionnerTexte(controleur.getDebutSelection(),controleur.getFinSelection());
    }

    /**
     * Crée un nouveau Memento à partir de la sélection courante
     * @return
     */
    @Override
    public Memento storInMemento() {
        return new MementoSelectionner(this.selection);
    }

    /**
     * Récupère la sélection  contenu dans le Memento
     * @param memento
     */
    @Override
    public void restoreFromMemento(Memento memento) {
        MementoSelectionner mementoSelectionner = (MementoSelectionner)memento;
        this.selection = mementoSelectionner.getSelection();
    }

    /**
     * Défini la Sélection
     * @param debu
     * @param fin
     */
    public void setSelection(int debu, int fin)
    {
        this.selection.setDebutSelection(debu);
        this.selection.setFinSelection(fin);
    }
}
