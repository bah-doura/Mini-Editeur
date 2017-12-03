package Test_V2;

import IHM.Controleur;
import IHM.InvokerImplementation;
import caretaker.ConcreteEnregistreur;
import commandes.*;
import moteurEdition.MoteurEditionImplementation;
import org.junit.Before;
import org.junit.Test;
import originators.*;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestCommandes {

    private MoteurEditionImplementation moteurEdition;
    private InvokerImplementation invoker;
    Commande commande;
    private CommandeEnregistrableInsererText insererTexte;
    private SelectionnerTexte selectionnerTexte;
    Controleur controleur;
    ConcreteEnregistreur concreteEnregistreur;


    /**
     * S'exécute après chaque cas de test pour initialiser les variables
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        moteurEdition = new MoteurEditionImplementation();
        controleur = new Controleur();
        selectionnerTexte = new SelectionnerTexte(moteurEdition, controleur);
        concreteEnregistreur = new ConcreteEnregistreur();
    }

    /**
     * Test de la commande Enregistrer
     * @throws Exception
     */

    @Test
    public void testDemarrer() throws Exception
    {
        //appel de la commande Enregistrer pou démarrer l'enrégistrement
        commande = new Enregistrer(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        assertTrue("Erreur de la  Commande Enregistrer", concreteEnregistreur.isRecording());
    }

    /**
     * Test de la commande Arreter
     * @throws Exception
     */

    @Test
    public void testArreter() throws Exception
    {
        //Appel de la commande Enregistrer pou démarrer l'enrégistrement
        commande = new Enregistrer(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        // Insertion de texte
        String texte1 =" holla";
        controleur.setText(texte1);
        insererTexte = new CommandeEnregistrableInsererText(moteurEdition,concreteEnregistreur, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();

        //Arrêt de l'enrégistrement (appel de la commande arreter)
        commande = new Arreter(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        assertFalse("Erreur de la  Commande Arreter", concreteEnregistreur.isRecording());
    }

    /**
     * Test enregistrement de la commande insererTexte
     * @throws Exception
     */
    @Test
    public void testEnregistrementCommandeInsertionTexte() throws Exception
    {
        String texte1 = "Bonjour 1";

        //Début de l'enregistrement
        commande = new Enregistrer(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Insertion de texte1
        controleur.setText(texte1);
        insererTexte = new CommandeEnregistrableInsererText(moteurEdition,concreteEnregistreur, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();

        //Appel de la commander Arreter
        commande = new Arreter(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        assertTrue("Erreur Enregistrement de la commande InsererTexte", concreteEnregistreur.getSize()== 1 );
    }


    /**
     * Test rejouer de la commande insererTexte
     * @throws Exception
     */
    @Test
    public void testCommandeRejouerInsertionTexte() throws Exception
    {
        String texte1 = "Bonjour 1";

        //Début de l'enregistrement
        commande = new Enregistrer(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Insertion de texte1
        controleur.setText(texte1);
        insererTexte = new CommandeEnregistrableInsererText(moteurEdition,concreteEnregistreur, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();

        //Appel de la commander Arreter
        commande = new Arreter(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Appel de commande Rejouer
        commande = new Rejouer(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        assertTrue("Erreur Rejouer de la commande InsererTexte ", moteurEdition.getBuffer().getZoneTexte().equals(texte1+texte1) );
    }

    /**
     * Test enregistrement de la commande SelectionnerTexte
     * @throws Exception
     */
    @Test
    public void testEnregistrementCommandeSelectionnerTexte() throws Exception
    {
        String texte1 = "Bonjour 1";

        //Début de l'enregistrement
        commande = new Enregistrer(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Insertion de texte1
        controleur.setText(texte1);
        insererTexte = new CommandeEnregistrableInsererText(moteurEdition,concreteEnregistreur, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();

        //Appel de la commande selectionner
        controleur.setDebutSelection(0);
        controleur.setFinSelection(5);
        commande = new CommandeEnregistrableSelectionner(moteurEdition,concreteEnregistreur,controleur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Appel de la commander Arreter
        commande = new Arreter(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();


        assertTrue("Erreur Enregitrement de la Commande SelectionnerTexte ", concreteEnregistreur.getSize()== 2 );
    }

    /**
     * Test rejouer de la commande SelectionnerTexte
     * @throws Exception
     */
    @Test
    public void testRejouerCommandeSelectionnerTexte() throws Exception
    {
        String texte1 = "Bonjour 1";

        //Début de l'enregistrement
        commande = new Enregistrer(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Insertion de texte1
        controleur.setText(texte1);
        insererTexte = new CommandeEnregistrableInsererText(moteurEdition,concreteEnregistreur, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();

        //Appel de la commande selectionner
        controleur.setDebutSelection(0);
        controleur.setFinSelection(5);
        commande = new CommandeEnregistrableSelectionner(moteurEdition,concreteEnregistreur,controleur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Appel de la commander Arreter
        commande = new Arreter(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Appel de la commande Rejouer
        commande = new Rejouer(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        assertTrue("Erreur Rejouer de la commande SelectionnerTexte ", moteurEdition.getSelection().getDebutSelection()==0
        && moteurEdition.getSelection().getFinSelection()== 5);
    }

    /**
     * Test enregistrement de la commande Copier
     * @throws Exception
     */
    @Test
    public void testEnregistrementCommandeCopier() throws Exception
    {
        String texte1 = "Bonjour 1";

        //Début de l'enregistrement
        commande = new Enregistrer(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Insertion de texte1
        controleur.setText(texte1);
        insererTexte = new CommandeEnregistrableInsererText(moteurEdition,concreteEnregistreur, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();

        //Appel de la commande selectionner
        controleur.setDebutSelection(0);
        controleur.setFinSelection(5);
        commande = new CommandeEnregistrableSelectionner(moteurEdition,concreteEnregistreur,controleur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Appel de la commande Copier
        commande = new CommandeEnregistrableCopier(concreteEnregistreur,moteurEdition);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Appel de la commander Arreter
        commande = new Arreter(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        assertTrue("Erreur Enregitrement de la commande copier", concreteEnregistreur.getSize()== 3 );
    }

    /**
     * Test Rejouer de la commande Copier
     * @throws Exception
     */
    @Test
    public void testRejouerCommandeCopier() throws Exception
    {
        String texte1 = "Bonjour 1";

        //Début de l'enregistrement
        commande = new Enregistrer(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Insertion de texte1
        controleur.setText(texte1);
        insererTexte = new CommandeEnregistrableInsererText(moteurEdition,concreteEnregistreur, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();

        //Appel de la commande selectionner
        controleur.setDebutSelection(0);
        controleur.setFinSelection(5);
        commande = new CommandeEnregistrableSelectionner(moteurEdition,concreteEnregistreur,controleur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Appel de la commande Copier
        commande = new CommandeEnregistrableCopier(concreteEnregistreur,moteurEdition);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();


        //Appel de la commander Arreter
        commande = new Arreter(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Appel de la commander Rejouer
        commande = new Rejouer(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        assertTrue("Erreur Rejouer de la commande copier ", moteurEdition.getPressePapier().coller().equals("Bonjo"));
    }


    /**
     * Test enregistrement de la commande Couper
     * @throws Exception
     */
    @Test
    public void testEnregistrementCommandeCouper() throws Exception
    {
        String texte1 = "Bonjour 1";

        //Début de l'enregistrement
        commande = new Enregistrer(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Insertion de texte1
        controleur.setText(texte1);
        insererTexte = new CommandeEnregistrableInsererText(moteurEdition,concreteEnregistreur, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();

        //Appel de la commande selectionner
        controleur.setDebutSelection(0);
        controleur.setFinSelection(5);
        commande = new CommandeEnregistrableSelectionner(moteurEdition,concreteEnregistreur, controleur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Appel de la commande Copier
        commande = new CommandeEnregistrableCouper(moteurEdition, concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Appel de la commander Arreter
        commande = new Arreter(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();


        assertTrue("Erreur Enregitrement de la commande Couper ", concreteEnregistreur.getSize()== 3 );
    }


    /**
     * Test enregistrement de la commande Coller
     * @throws Exception
     */
    @Test
    public void testEnregistrementCommandeColler() throws Exception
    {
        String texte1 = "Bonjour 1";

        //Début de l'enregistrement
        commande = new Enregistrer(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Insertion de texte1
        controleur.setText(texte1);
        insererTexte = new CommandeEnregistrableInsererText(moteurEdition,concreteEnregistreur, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();

        //Appel de la commande selectionner
        controleur.setDebutSelection(0);
        controleur.setFinSelection(5);
        commande = new CommandeEnregistrableSelectionner(moteurEdition, concreteEnregistreur, controleur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Appel de la commande Copier
        commande = new CommandeEnregistrableCopier(concreteEnregistreur, moteurEdition);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Appel de la commande coller
        commande = new CommandeEnregistrableColler(moteurEdition, concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Appel de la commander Arreter
        commande = new Arreter(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        assertTrue("Erreur Enregitrement  de la commande coller", concreteEnregistreur.getSize()== 4 );
    }

    /**
     * Test Rejouer de la commande Coller
     * @throws Exception
     */
   @Test
    public void testRejouerCommandeColler() throws Exception
    {
        String texte1 = "Bonjour monsieur";

        //Début de l'enregistrement
        commande = new Enregistrer(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Insertion de texte1
        controleur.setText(texte1);
        insererTexte = new CommandeEnregistrableInsererText(moteurEdition,concreteEnregistreur, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();

        //Appel de la commande selectionner
        controleur.setDebutSelection(0);
        controleur.setFinSelection(7);
        commande = new CommandeEnregistrableSelectionner(moteurEdition,concreteEnregistreur, controleur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Appel de la commande Copier
        commande = new CommandeEnregistrableCopier(concreteEnregistreur,moteurEdition);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Appel de la commande coller
        moteurEdition.getBuffer().setCurseur(0);
        commande = new CommandeEnregistrableColler(moteurEdition,concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Appel de la commander Arreter
        commande = new Arreter(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Appel de la commander Rejouer
        commande = new Rejouer(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();
        String chaine = texte1+"Bonjour";
        // chaine.lenght*2 car on garde le texte qu'on a dans le buffeur lorsqu'on appel la commande rejouer
        assertTrue("Erreur Rejouer de la commande Coller ", moteurEdition.getBuffer().getZoneTexte().length() == chaine.length()*2 );
    }



    /**
     * Test succession de commandes (Enregistrer/ rejouer)
     * @throws Exception
     */
    @Test
    public void testSuccessionCommandes() throws Exception
    {
        String texte1 = "Bonjour";
        String texte2 = "Holla";
        String texte3 = "Good Morning";
        String texteRejouer;
        //Début de l'enregistrement
        commande = new Enregistrer(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Insertion de texte1
        controleur.setText(texte1);
        insererTexte = new CommandeEnregistrableInsererText(moteurEdition,concreteEnregistreur, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();
        texteRejouer =texte1;


        //Insetion texte de texte2
        controleur.setText(texte2);
        insererTexte = new CommandeEnregistrableInsererText(moteurEdition,concreteEnregistreur, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();
        texteRejouer +=texte2;

        //Insetion texte de texte3
        controleur.setText(texte3);
        insererTexte = new CommandeEnregistrableInsererText(moteurEdition,concreteEnregistreur, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();
        texteRejouer +=texte3;


        //appel de la commande selectionner
        controleur.setDebutSelection(0);
        controleur.setFinSelection(7);
        commande = new CommandeEnregistrableSelectionner(moteurEdition,concreteEnregistreur, controleur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Appel de Caommande Copier
        commande = new CommandeEnregistrableCopier(concreteEnregistreur,moteurEdition);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        // appel de la commande Coller
        commande = new CommandeEnregistrableColler(moteurEdition, concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();
        texteRejouer +="Bonjour";


        //appel de la commande selectionner
        controleur.setDebutSelection(0);
        controleur.setFinSelection(7);
        commande = new CommandeEnregistrableSelectionner(moteurEdition,concreteEnregistreur, controleur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Appel de Commande couper
        commande = new CommandeEnregistrableCouper(moteurEdition,concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //Appel de Cammande Coller
        commande = new CommandeEnregistrableColler(moteurEdition, concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();




        //appel de la commander Arreter
        commande = new Arreter(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();

        //appel de la commander Arreter
        commande = new Rejouer(concreteEnregistreur);
        invoker = new InvokerImplementation(commande);
        invoker.InvokeCommande();
        assertTrue("Erreur Enregitrer/Rejouer ", moteurEdition.getBuffer().getZoneTexte().length()== texteRejouer.length()*2);
    }

}
