package commandes;

import IHM.Controleur;
import moteurEdition.MoteurEdition;

public class SelectionnerTexte implements Commande {

    private MoteurEdition moteurEdition;
    private Controleur controleur;

    public SelectionnerTexte(MoteurEdition moteurEdition, Controleur controleur) {
        this.moteurEdition = moteurEdition;
        this.controleur = controleur;
    }

    /**
     *    execute the command selectionnerTexte
     */
    @Override
    public void execute() {
        this.moteurEdition.selectionnerTexte(controleur.getDebutSelection(),controleur.getFinSelection());
    }


}
