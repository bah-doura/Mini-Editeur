package commandes;

import moteurEdition.MoteurEdition;

public class Copier implements Commande {

    private MoteurEdition moteurEdition;

    /**
     * Constructeur
     * @param moteurEdition MoteurEdition
     */
    public Copier(MoteurEdition moteurEdition)
    {
        this.moteurEdition = moteurEdition;
    }

    /**
     *    Exécute la commande copier
     */
    @Override
    public void execute() {
      this.moteurEdition.copier();
    }
}
