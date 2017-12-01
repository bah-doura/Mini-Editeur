package mementos;

import moteurEdition.Buffer;
import moteurEdition.PressePapier;
import moteurEdition.Selection;

public class MementoMoteur implements Memento{

    private Buffer buffer;
    private Selection selection;
    private PressePapier pressePapier;

    public MementoMoteur(Buffer buffer, Selection selection, PressePapier pressePapier){
        this.buffer = buffer;
        this.selection = selection;
        this.pressePapier = pressePapier;
    }

    public Buffer getBuffer() {
        return buffer;
    }

    public void setBuffer(Buffer buffer) {
        this.buffer = buffer;
    }

    public Selection getSelection() {
        return selection;
    }

    public void setSelection(Selection selection) {
        this.selection = selection;
    }

    public PressePapier getPressePapier() {
        return pressePapier;
    }

    public void setPressePapier(PressePapier pressePapier) {
        this.pressePapier = pressePapier;
    }
}
