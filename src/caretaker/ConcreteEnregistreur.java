package caretaker;

import commandes.Commande;
import javafx.util.Pair;
import mementos.Memento;

import java.util.ArrayList;

public class ConcreteEnregistreur implements Enregistreur {

    private boolean startLoading;

    private ArrayList<Pair<Memento, Commande>> saveCommandes;

    public ConcreteEnregistreur()
    {
        this.saveCommandes = new ArrayList<>();
        this.startLoading = false;
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
        this.startLoading = false;
    }

    @Override
    public void demarrerEnregistrement() {
        this.startLoading = true;
    }

    @Override
    public Pair<Memento, Commande> rejouer(int index) {

        return this.saveCommandes.get(index);
    }

    public boolean isStartLoading() {
        return startLoading;
    }


}
