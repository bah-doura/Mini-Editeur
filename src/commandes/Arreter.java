package commandes;

import caretaker.ConcreteEnregistreur;

public class Arreter implements Commande {

    private ConcreteEnregistreur concreteEnregistreur;
    public Arreter( ConcreteEnregistreur concreteEnregistreur){
        this.concreteEnregistreur = concreteEnregistreur;
    }

    @Override
    public void execute() {

        this.concreteEnregistreur.arreter();
    }
}
