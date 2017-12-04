package moteurEdition;

public class Buffer {

    private String zoneTexte;
    private int curseur;

    /**
     * Constructeur
     */
    public Buffer()
    {
        this.setZoneTexte("");
        this.setCurseur(0);
    }

    /**
     * Retourne la position du Curseur
     * @return
     */
    public int getCurseur(){
        return curseur;
    }

    /**
     * Défini la position du curseur
     * @param curseur
     */
    public void setCurseur(int curseur) {
        this.curseur = curseur;
    }

    /**
     * Retourne le texte du Buffeur
     * @return
     */
    public String getZoneTexte() {
        return zoneTexte;
    }

    /**
     * Défini le texte du Buffeur
     * @param zoneTexte
     */
    public void setZoneTexte(String zoneTexte) {
        this.zoneTexte = zoneTexte;
    }
}
