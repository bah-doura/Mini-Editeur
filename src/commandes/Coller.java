package commandes;

import moteurEdition.MoteurEdition;

public class Coller implements Commande {

    private MoteurEdition moteurEdition;

    /**
     *  Constructeur
     * @param moteurEdition MoteurEdition
     */
    public Coller(MoteurEdition moteurEdition)
    {
        this.moteurEdition = moteurEdition;
    }

    /**
     * Exécute la commande Coller
     */
    @Override
    public void execute() {

        try{
            this.moteurEdition.coller();
        }
        catch(StringIndexOutOfBoundsException e){
            System.out.println("impossible de récuprérer la selection");
        };
    }
}
