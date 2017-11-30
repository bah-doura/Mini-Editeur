package commandes;

import caretaker.ConcreteEnregistreur;

public class Enregistrer implements Commande {

    private ConcreteEnregistreur concreteEnregistreur;
    public Enregistrer( ConcreteEnregistreur concreteEnregistreur)
    {
        this.concreteEnregistreur = concreteEnregistreur;
    }
    @Override
    public void execute() {
        this.concreteEnregistreur.demarrerEnregistrement();
    }
}
