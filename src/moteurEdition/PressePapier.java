package moteurEdition;

public class PressePapier {

    private String contenu;

    /**
     * Constructeur
     */
    public PressePapier()
    {
        this.copier("");

    }

    /**
     * Copie dans le presse papier
     * @param contenu
     */
    public void copier(String contenu) {
        this.contenu = contenu;
    }

    /**
     * Retourne le contenu du presse papier
     * @return
     */
    public String coller()
    {
        return this.contenu;
    }
}
