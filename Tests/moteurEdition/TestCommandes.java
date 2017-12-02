package moteurEdition;

import IHM.Invoker;
import commandes.Commande;
import org.junit.Before;

public class TestCommandes {

    private MoteurEdition moteurEdition;
    private Invoker invoker;
    private Commande commande;

    @Before
    public void setUp() throws Exception {
        moteurEdition = new MoteurEditionImplementation();

    }
}
