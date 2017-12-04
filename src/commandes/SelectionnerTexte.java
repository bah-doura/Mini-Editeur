package commandes;

import IHM.Controleur;
import moteurEdition.MoteurEdition;

public class SelectionnerTexte implements Commande {

    private MoteurEdition moteurEdition;
    private Controleur controleur;

    /**
     * Constructeur
     * @param moteurEdition MoteurEdition
     * @param controleur Controleur
     */
    public SelectionnerTexte(MoteurEdition moteurEdition, Controleur controleur) {
        this.moteurEdition = moteurEdition;
        this.controleur = controleur;
    }

    /**
     *    Exécute la commande SelectionnerTexte
     */
    @Override
    public void execute() {
        this.moteurEdition.selectionnerTexte(controleur.getDebutSelection(),controleur.getFinSelection());
    }


}
