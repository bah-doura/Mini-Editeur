package commandes;

import moteurEdition.MoteurEdition;

import java.awt.event.ComponentAdapter;

public class Effacer implements Commande {


    private MoteurEdition moteurEdition;

    public Effacer(MoteurEdition moteurEdition) {
        this.moteurEdition = moteurEdition;
    }

    /**
     *    execute the command effacer
     */
    @Override
    public void execute() {
        this.moteurEdition.effacer();
    }
}
