package commandes;

import IHM.Controleur;
import moteurEdition.MoteurEdition;

public class InsererTexte implements Commande {

    private MoteurEdition moteurEdition;
    private Controleur controller;

    /**
     * Constructeur
     * @param moteurEdition MoteurEdition
     * @param controleur Controleur
     */
    public InsererTexte(MoteurEdition moteurEdition, Controleur controleur) {
        this.moteurEdition = moteurEdition;
        this.controller = controleur;
    }

    /**
     *    Exécute la commande insererTexte
     */
    @Override
    public void execute() {

        this.moteurEdition.insererTexte(controller.getText());
    }
}
