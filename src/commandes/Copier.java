package commandes;

import moteurEdition.MoteurEdition;

public class Copier implements Commande {

    private MoteurEdition moteurEdition;

    public Copier(MoteurEdition moteurEdition)
    {
        this.moteurEdition = moteurEdition;
    }

    /**
     *    execute the command copier
     */
    @Override
    public void execute() {
      this.moteurEdition.copier();
    }
}
