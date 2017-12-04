package commandes;

import moteurEdition.MoteurEdition;

public class Effacer implements Commande {


    private MoteurEdition moteurEdition;

    /**
     * Constructeur
     * @param moteurEdition MoteurEdition
     */
    public Effacer(MoteurEdition moteurEdition) {
        this.moteurEdition = moteurEdition;
    }

    /**
     *    Exécute la commande effacer
     */
    @Override
    public void execute() {
        this.moteurEdition.effacer();
    }
}
