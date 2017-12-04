package moteurEdition;

public class Selection {

    private int debutSelection;
    private int finSelection;

    /**
     * Constructeur
     */
    public Selection()
    {
       this.setDebutSelection(0);
       this.setFinSelection(0);
    }

    /**
     * Retourne le début de la sélection
     * @return
     */
    public int getDebutSelection() {
        return debutSelection;
    }

    /**
     * Défini le début de la sélection
     * @param debutSelection
     */
    public void setDebutSelection(int debutSelection) {
        this.debutSelection = debutSelection;
    }

    /**
     * Retourne la fin de la sélection
     * @return
     */
    public int getFinSelection() {
        return finSelection;
    }

    /**
     * Défini la fin de la Sélection
     * @param finSelection
     */
    public void setFinSelection(int finSelection) {
        this.finSelection = finSelection;
    }


}
