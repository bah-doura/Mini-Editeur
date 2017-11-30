package originators;

import mementos.Memento;

public interface Enregistrable {

    public Memento storInMemento();
    public void restoreFromMemento(Memento memento);
}
