package originators;

import commandes.Commande;
import mementos.Memento;
import mementos.MementoSelectionner;
import moteurEdition.Selection;

public class CommandeEnregistrableSelectionner implements Enregistrable, Commande {

    Selection selection;

    public CommandeEnregistrableSelectionner()
    {
        this.selection = new Selection();
    }

    @Override
    public void execute() {

    }

    @Override
    public Memento storInMemento() {
        return new MementoSelectionner(this.selection);
    }

    @Override
    public void restoreFromMemento(Memento memento) {
        MementoSelectionner mementoSelectionner = (MementoSelectionner)memento;
        this.selection = mementoSelectionner.getSelection();
    }

    public void setSelection(int debu, int fin)
    {
        this.selection.setDebutSelection(debu);
        this.selection.setFinSelection(fin);
    }
}
