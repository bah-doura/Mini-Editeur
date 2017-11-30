package commandes;

import caretaker.ConcreteEnregistreur;

public class Rejouer implements Commande {

    private ConcreteEnregistreur concreteEnregistreur;

    public Rejouer(ConcreteEnregistreur concreteEnregistreur)
    {
        this.concreteEnregistreur = concreteEnregistreur;
    }
    @Override
    public void execute() {
        concreteEnregistreur.rejouer(0);
    }
}
