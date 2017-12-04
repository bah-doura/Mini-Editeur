package caretaker;

import commandes.Commande;
import javafx.util.Pair;
import mementos.Memento;

import java.util.ArrayList;
import java.util.Iterator;

public class ConcreteEnregistreur implements Enregistreur {

    private boolean recording;

    private boolean replay;

    private ArrayList<Pair<Memento, Commande>> saveCommandes;

    private int index;

    /**
     * Constructeur
     */
    public ConcreteEnregistreur()
    {
        this.saveCommandes = new ArrayList<>();
        this.recording = false;
        this.replay = false;
        this.index = 0;
    }

    /**
     * Ajoute un nouveau Memento
     * @param memento memento à ajouter
     * @param commande commande associée
     */
    public void addMemento(Memento memento, Commande commande) {
        if(memento != null && memento != null)
        {

            this.saveCommandes.add(new Pair(memento,commande));
        }
        else {
            System.out.println(this.getClass().getName()+" : the command is nul");
        }
    }

    /**
     * Arrête l'enregistrement
     */
    @Override
    public void arreter() {
        this.recording = false;
    }

    /**
     * Demarre l'enregistrement
     */
    @Override
    public void demarrerEnregistrement() {
        this.recording = true;
    }

    /**
     * Rejoue les commandes
     */
    @Override
    public void rejouer() {
        index = 0;
        this.replay = true;
        System.out.println("rejouer !!!");
        Iterator<Pair<Memento, Commande>> it = this.saveCommandes.iterator();
        while(it.hasNext()){
            System.out.println("rejouer commande");
            it.next().getValue().execute();
            this.index++;
        }
        this.saveCommandes.clear();
        index = 0;
    }

    /**
     * Retourne le Memento situé à l'index i
     * @return un Memento
     */
    @Override
    public Memento getCurrentMemento(){
        return this.saveCommandes.get(index).getKey();
    }

    /**
     * Vérifie si c'est en mode enregistrement
     * @return boolean
     */
    public boolean isRecording() {
        return recording;
    }

    /**
     * Vérifie si c'est en mode rejouer
     * @return boolean
     */
    public boolean isReplay() {
        return replay;
    }

    /**
     * Met le mode enregistrement true ou false
     * @param bool true ou false
     */
    public void setReplay(boolean bool){
        this.replay = bool;
    }

    /**
     * Fonction utilisée pour les tests
     * @return
     */
    public int getSize()
    {return this.saveCommandes.size();}
}
