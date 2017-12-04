package originators;

import IHM.Controleur;
import caretaker.Enregistreur;
import commandes.Commande;
import mementos.Memento;
import mementos.MementoInsererTexte;
import moteurEdition.MoteurEdition;

public class CommandeEnregistrableInsererText implements Enregistrable, Commande {

    private String texte;
    private MoteurEdition moteurEdition;
    private Enregistreur enregistreur;
    private Controleur controleur;

    /**
     * Constructeur
     * @param moteurEdition
     * @param enregistreur
     * @param controleur
     */
    public CommandeEnregistrableInsererText(MoteurEdition moteurEdition, Enregistreur enregistreur, Controleur controleur)
    {
        this.controleur = controleur;
        this.moteurEdition = moteurEdition;
        this.enregistreur = enregistreur;
        this.texte = "";
    }

    /**
     * Exécution de la commande CommandeEnregistrableInsererText
     */
    @Override
    public void execute() {
        this.setTexte(controleur.getText());
        if(this.enregistreur.isRecording()){
            this.enregistreur.addMemento(this.storInMemento(), this);
        }
        else if(this.enregistreur.isReplay()){
            this.restoreFromMemento(this.enregistreur.getCurrentMemento());
        }
        this.moteurEdition.insererTexte(this.texte);

        //enre
        restoreFromMemento(enregistreur.getCurrentMemento());
    }

    /**
     * Crée un nouveau Memento à partir du texte courant
     * @return
     */
    @Override
    public Memento storInMemento() {
        return new MementoInsererTexte(this.texte);
    }

    /**
     * Récupère le texte contenu dans le Memento
     * @param memento
     */
    @Override
    public void restoreFromMemento(Memento memento) {

        MementoInsererTexte mementoInsererTexte = (MementoInsererTexte)memento;
        this.texte = mementoInsererTexte.getTexte();
    }

    /**
     * Défini le texte Courant
     * @param texte
     */
    public void setTexte(String texte)
    {
        this.texte = texte;
    }
}
