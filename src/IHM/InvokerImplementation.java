package IHM;

import commandes.Commande;

public class InvokerImplementation implements Invoker {

    private Commande commande;

    /**
     * Constructeur
     * @param commande la commande à invoquer
     */
    public InvokerImplementation(Commande commande)
    {
        this.commande = commande;
    }


    /**
     *  Invoque une Commande
     */

    public void InvokeCommande() {
        this.commande.execute();
    }
}
