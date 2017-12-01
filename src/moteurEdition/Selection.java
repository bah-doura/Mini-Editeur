package moteurEdition;

public class Selection {

    private int debutSelection;
    private int finSelection;

    public Selection()
    {
       this.setDebutSelection(0);
       this.setFinSelection(0);
    }

    public int getDebutSelection() {
        return debutSelection;
    }

    public void setDebutSelection(int debutSelection) {
        this.debutSelection = debutSelection;
    }

    public int getFinSelection() {
        return finSelection;
    }

    public void setFinSelection(int finSelection) {
        this.finSelection = finSelection;
    }

    @Override
    public String toString() {
        return "Selection{" +
                "debutSelection=" + debutSelection +
                ", finSelection=" + finSelection +
                '}';
    }

    public Selection getClone() {
        Selection selection = new Selection();
        selection.setDebutSelection(this.getDebutSelection());
        selection.setFinSelection(this.getFinSelection());
        return selection;
    }
}
