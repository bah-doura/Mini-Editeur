package commandes;

import moteurEdition.GestDefRefaire;

public class Refaire implements Commande{

    private GestDefRefaire gestDefRefaire;

    public Refaire(GestDefRefaire gestDefRefaire){
        this.gestDefRefaire = gestDefRefaire;
    }

    @Override
    public void execute() {
        this.gestDefRefaire.refaireMoteur();
    }
}
