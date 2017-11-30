package moteurEdition;

public class Buffer {

    private String zoneTexte;
    private int curseur;

    public Buffer()
    {
        this.setZoneTexte("");
        this.setCurseur(0);
    }

    public int getCurseur(){
        return curseur;
    }

    public void setCurseur(int curseur) {
        this.curseur = curseur;
    }

    public String getZoneTexte() {
        return zoneTexte;
    }

    public void setZoneTexte(String zoneTexte) {
        this.zoneTexte = zoneTexte;
    }
}
