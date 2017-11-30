package commandes;

import IHM.Controleur;
import moteurEdition.MoteurEdition;

public class InsererTexte implements Commande {

    private MoteurEdition moteurEdition;
    private Controleur controller;

    public InsererTexte(MoteurEdition moteurEdition, Controleur controleur) {
        this.moteurEdition = moteurEdition;
        this.controller = controleur;
    }

    /**
     *    execute the command insererTexte
     */
    @Override
    public void execute() {

        this.moteurEdition.insererTexte(controller.getText());
    }
}
