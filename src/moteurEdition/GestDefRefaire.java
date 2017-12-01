package moteurEdition;

import mementos.MementoMoteur;
import moteurEdition.MoteurEdition;
import moteurEdition.MoteurEditionImplementation;

import java.util.Stack;

public class GestDefRefaire {

    private Stack<MementoMoteur> faitStack;
    private Stack<MementoMoteur> refaitStack;
    private MoteurEdition moteurEdition;

    public GestDefRefaire(MoteurEdition moteurEdition){
        this.faitStack = new Stack<MementoMoteur>();
        this.refaitStack = new Stack<MementoMoteur>();
        this.moteurEdition = moteurEdition;
    }

    public void addFaitMoteur(){
        this.faitStack.push(this.moteurEdition.getEtat());
        this.refaitStack.removeAllElements();
    }

    public void defaireMoteur(){
        if(!this.faitStack.isEmpty()){
            MementoMoteur memento = this.faitStack.pop();
            this.moteurEdition.setEtat(memento);
            this.refaitStack.push(memento);
        }
    }

    public void refaireMoteur(){
        System.out.println("refaire");
        if(!this.refaitStack.isEmpty()){
            MementoMoteur memento = this.refaitStack.pop();
            this.moteurEdition.setEtat(memento);
            this.faitStack.push(memento);
        }
    }
}
