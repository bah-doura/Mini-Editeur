package mementos;

import moteurEdition.Selection;

public class MementoSelectionner implements Memento {

    private Selection selection;

    public MementoSelectionner(Selection selection) {
        this.selection = selection;
    }

    public Selection getSelection() {
        return this.selection;
    }
}
