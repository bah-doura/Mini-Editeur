package commandes;

import moteurEdition.MoteurEdition;

public class Couper implements Commande {

    private MoteurEdition moteurEdition;

    public Couper(MoteurEdition moteurEdition) {
        this.moteurEdition = moteurEdition;
    }

    /**
     *    execute the command couper
     */
    @Override
    public void execute() {
        this.moteurEdition.couper();
    }
}
