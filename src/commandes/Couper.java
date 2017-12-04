package commandes;

import moteurEdition.MoteurEdition;

public class Couper implements Commande {

    private MoteurEdition moteurEdition;

    /**
     * Constructeur
     * @param moteurEdition MoteurEdition
     */
    public Couper(MoteurEdition moteurEdition) {
        this.moteurEdition = moteurEdition;
    }

    /**
     *    Exécute la commande couper
     */
    @Override
    public void execute() {
        this.moteurEdition.couper();
    }
}
