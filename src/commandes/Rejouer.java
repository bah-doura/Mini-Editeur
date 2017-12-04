package commandes;

import caretaker.ConcreteEnregistreur;

public class Rejouer implements Commande {

    private ConcreteEnregistreur concreteEnregistreur;

    /**
     * Constructeur
     * @param concreteEnregistreur ConcreteEnregistreur
     */
    public Rejouer(ConcreteEnregistreur concreteEnregistreur)
    {
        this.concreteEnregistreur = concreteEnregistreur;
    }

    /**
     *    Exécute la commande Rejouer
     */
    @Override
    public void execute() {
        concreteEnregistreur.rejouer();
    }
}
