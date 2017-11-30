package commandes;

import moteurEdition.MoteurEdition;

public class Coller implements Commande {

    private MoteurEdition moteurEdition;

    public Coller(MoteurEdition moteurEdition)
    {
        this.moteurEdition = moteurEdition;
    }

    /**
     *   execute the command coller
     */
    @Override
    public void execute() {
       this.moteurEdition.coller();
    }
}
