package mementos;

import moteurEdition.Selection;

public class MementoSelectionner implements Memento {

    private Selection selection;

    /**
     * Constructeur
     * @param selection La sélection
     */
    public MementoSelectionner(Selection selection) {
        this.selection = selection;
    }

    /**
     * Retourne la Sélection
     * @return
     */
    public Selection getSelection() {
        return this.selection;
    }
}
