package commandes;

import caretaker.ConcreteEnregistreur;

public class Arreter implements Commande {

    private ConcreteEnregistreur concreteEnregistreur;

    /**
     * Constructeur
     * @param concreteEnregistreur ConcreteEnregistreur
     */
    public Arreter( ConcreteEnregistreur concreteEnregistreur){
        this.concreteEnregistreur = concreteEnregistreur;
    }

    /**
     * Exécute la commande Arreter
     */
    @Override
    public void execute() {

        this.concreteEnregistreur.arreter();
    }
}
