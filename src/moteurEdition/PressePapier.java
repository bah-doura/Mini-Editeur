package moteurEdition;

public class PressePapier {

    private String contenu;

    public PressePapier()
    {
        this.copier("");

    }

    public void copier(String contenu) {
        this.contenu = contenu;
    }

    public String coller()
    {
        return this.contenu;
    }

    public PressePapier getClone() {
        PressePapier pressePapier = new PressePapier();
        pressePapier.copier(this.coller());
        return pressePapier;
    }
}
