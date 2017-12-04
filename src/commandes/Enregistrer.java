package commandes;

import caretaker.ConcreteEnregistreur;

public class Enregistrer implements Commande {

    private ConcreteEnregistreur concreteEnregistreur;

    /**
     * Constructeur
     * @param concreteEnregistreur ConcreteEnregistreur
     */
    public Enregistrer( ConcreteEnregistreur concreteEnregistreur)
    {
        this.concreteEnregistreur = concreteEnregistreur;
    }

    /**
     *    Exécute la commande Enregistrer
     */
    @Override
    public void execute() {
        this.concreteEnregistreur.demarrerEnregistrement();
    }
}
