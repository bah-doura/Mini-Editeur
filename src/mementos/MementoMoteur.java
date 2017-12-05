package mementos;

import moteurEdition.Buffer;
import moteurEdition.PressePapier;
import moteurEdition.Selection;

public class MementoMoteur implements Memento{

    private Buffer buffer;
    private Selection selection;
    private PressePapier pressePapier;

    /**
     * Constructeur, copie l'etat actuel de moteurEdition
     * @param buffer clone du dernier etat du buffer
     * @param selection clone du dernier etat de la selection
     * @param pressePapier clone du dernier etat du presse papier
     */
    public MementoMoteur(Buffer buffer, Selection selection, PressePapier pressePapier){
        this.buffer = buffer;
        this.selection = selection;
        this.pressePapier = pressePapier;
    }

    /**
     * retourne le buffer conservé dans le memento
     * @return
     */
    public Buffer getBuffer() {
        return buffer;
    }

    /**
     * modifie le buffer du memento
     * @param buffer nouvelle valeur du buffer
     */
    public void setBuffer(Buffer buffer) {
        this.buffer = buffer;
    }

    /**
     * retourne la selection conservé dans le memento
     * @return
     */
    public Selection getSelection() {
        return selection;
    }

    /**
     * modifie la selection du memento
     * @param selection nouvelle valeur de la selection
     */
    public void setSelection(Selection selection) {
        this.selection = selection;
    }

    /**
     * retourne le presse papier conservé dans le memento
     * @return
     */
    public PressePapier getPressePapier() {
        return pressePapier;
    }

    /**
     * modfie le presse papier du memento
     * @param pressePapier nouvelle valeur du presse papier
     */
    public void setPressePapier(PressePapier pressePapier) {
        this.pressePapier = pressePapier;
    }
}
