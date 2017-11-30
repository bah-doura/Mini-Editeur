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

    public CommandeEnregistrableInsererText(MoteurEdition moteurEdition, Enregistreur enregistreur, Controleur controleur)
    {
        this.controleur = controleur;
        this.moteurEdition = moteurEdition;
        this.enregistreur = enregistreur;
        this.texte = "";
    }

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
    }

    @Override
    public Memento storInMemento() {
        return new MementoInsererTexte(this.texte);
    }

    @Override
    public void restoreFromMemento(Memento memento) {

        MementoInsererTexte mementoInsererTexte = (MementoInsererTexte)memento;
        this.texte = mementoInsererTexte.getTexte();
    }

    public void setTexte(String texte)
    {
        this.texte = texte;
    }
}
