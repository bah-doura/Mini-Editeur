package commandes;

import moteurEdition.GestDefRefaire;

public class Defaire implements Commande{

    private GestDefRefaire gestDefRefaire;

    public Defaire(GestDefRefaire gestDefRefaire){
        this.gestDefRefaire = gestDefRefaire;
    }

    @Override
    public void execute() {
        this.gestDefRefaire.defaireMoteur();
    }
}
