package moteurEdition;

import mementos.MementoMoteur;
import moteurEdition.MoteurEdition;
import moteurEdition.MoteurEditionImplementation;

import java.util.Stack;

public class GestDefRefaire {

    //faitStack : Pile de MementoMoteur contenant les anciennes états du moteurEdition afin de les rétablir en cas de Defaire
    private Stack<MementoMoteur> faitStack;
    //refaitStack : Pile de MementoMoteur contenant les états du moteurEdition ayant été défait afin de pouvoir les rétablir en cas de Refaire
    private Stack<MementoMoteur> refaitStack;
    //moteurEdition : référence au moteur d'édition actif sur l'IHM
    private MoteurEdition moteurEdition;

    /**
     * Constructeur de GestDefRef, initialise les attributs d'instances
     * @param moteurEdition instance du moteurEdition permetant à la classe de récupérer et rétablir les états de ce derniers
     */
    public GestDefRefaire(MoteurEdition moteurEdition){
        this.faitStack = new Stack<MementoMoteur>();
        this.refaitStack = new Stack<MementoMoteur>();
        this.moteurEdition = moteurEdition;
    }

    /**
     * Ajout un etat de moteurEdition dans la pile faitStack
     * Supprime tout les elements de la pile refaitStack
     */
    public void addFaitMoteur(){
        this.faitStack.push(this.moteurEdition.getEtat());
        this.refaitStack.removeAllElements();
    }

    /**
     * Permet de defaire
     * Si faitStack n'est pas vide, on récupère le dernier état enregistrer dans faitStack,
     * on le retire de la pile et on l'assigne au moteur.
     * On ajout le memento contenant l'etat actuel du moteur dans la pile refaitStack,
     * afin de concerver cette etat pour un futur refaire
     */
    public void defaireMoteur(){
        if(!this.faitStack.isEmpty()){
            MementoMoteur memento = this.faitStack.pop();
            this.refaitStack.push( this.moteurEdition.getEtat());
            this.moteurEdition.setEtat(memento);
        }
    }

    /**
     * Permet de refaire
     * Si refaitStack n'est pas vide, on récupère le dernier état enregistrer dans refaitStack,
     * on le retire de la pile et on l'assigne au moteur.
     * On ajout le memento contenant l'etat actuel du moteur dans la pile faitStack,
     * afin de concerver cette etat pour un futur defaire
     */
    public void refaireMoteur(){
        if(!this.refaitStack.isEmpty()){
            MementoMoteur memento = this.refaitStack.pop();
            this.faitStack.push(this.moteurEdition.getEtat());
            this.moteurEdition.setEtat(memento);
        }
    }
}
