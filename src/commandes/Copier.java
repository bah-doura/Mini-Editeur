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
        try{
            this.moteurEdition.copier();
        }
        catch(StringIndexOutOfBoundsException e){
            System.out.println("impossible de récuprérer la selection");
        };
    }
}
