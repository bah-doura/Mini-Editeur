package IHM;

import commandes.Commande;

public class InvokerImplementation implements Invoker {

    private Commande commande;

    public InvokerImplementation(Commande commande)
    {
        this.commande = commande;
    }


    /**
     *  Invoke the commands
     */

    public void InvokeCommande() {
        this.commande.execute();
    }
}
