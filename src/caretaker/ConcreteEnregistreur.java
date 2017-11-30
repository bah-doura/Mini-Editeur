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

    public ConcreteEnregistreur()
    {
        this.saveCommandes = new ArrayList<>();
        this.recording = false;
        this.replay = false;
        this.index = 0;
    }

    public void addMemento(Memento memento, Commande commande) {
        if(memento != null && memento != null)
        {

            this.saveCommandes.add(new Pair(memento,commande));
        }
        else {
            System.out.println(this.getClass().getName()+" : the command is nul");
        }
    }

    @Override
    public void arreter() {
        this.recording = false;
    }

    @Override
    public void demarrerEnregistrement() {
        this.recording = true;
    }

    @Override
    public void rejouer() {
        Iterator<Pair<Memento, Commande>> it = this.saveCommandes.iterator();
        while(it.hasNext()){
            this.index++;
            it.next().getValue().execute();
        }
        index = 0;
    }

    @Override
    public Memento getCurrentMemento(){
        return this.saveCommandes.get(index).getKey();
    }

    public boolean isRecording() {
        return recording;
    }

    public boolean isReplay() {
        return replay;
    }


}
