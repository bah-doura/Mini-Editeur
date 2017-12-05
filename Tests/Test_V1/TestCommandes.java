package Test_V1;

import IHM.Controleur;
import IHM.InvokerImplementation;
import commandes.*;
import moteurEdition.MoteurEditionImplementation;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestCommandes {

    private MoteurEditionImplementation moteurEdition;
    private InvokerImplementation invoker;
    private Controleur controleur;
    private Coller coller;
    private Copier copier;
    private Couper couper;
    private Effacer effacer;
    private InsererTexte insererTexte;
    private SelectionnerTexte selectionnerTexte;


    @Before
    public void setUp() throws Exception {
        moteurEdition = new MoteurEditionImplementation();
        controleur = new Controleur();
        selectionnerTexte = new SelectionnerTexte(moteurEdition, controleur);
    }

    /**
     * Test insertion quand le buffeur est vide
     */


    @Test
    public void InsererText1() throws Exception {

        //Insertion de text1
        String texte1 = "Bonjour";
        controleur.setText(texte1);
        insererTexte = new InsererTexte(moteurEdition, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();
        assertTrue("Erreur d'insertion 1", moteurEdition.getBuffer().getZoneTexte().equals(texte1));
    }

    /**
     * Test insértion à la fin du texte
     * @throws Exception
     */
    @Test
    public void InsererText2() throws Exception {
        String texte1 = "Bonjour";
        String texte2 = "Les Terriens";


        controleur.setText(texte1);
        insererTexte = new InsererTexte(moteurEdition, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();

        controleur.setText(texte2);
        invoker = new InvokerImplementation(insererTexte);
        insererTexte = new InsererTexte(moteurEdition, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();
        assertTrue("Erreur d'insertion 2-1", moteurEdition.getBuffer().getZoneTexte().equals(texte1 + texte2));
        assertFalse("Erreur d'insertion 2-2", moteurEdition.getBuffer().getZoneTexte().equals(""));

    }

    /**
     * Test insértion  au début du texte (positionnement du curseur  au début du texte
     * @throws Exception
     */
    @Test
    public void InsererText3() throws Exception {

        String texte1 = "Bonjour";
        String texte2 = "Les Terriens";

        //première insertion
        controleur.setText(texte1);
        insererTexte = new InsererTexte(moteurEdition, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();

        // positionnement du curseur au début du texte
        moteurEdition.getBuffer().setCurseur(0);
        //insertion au début du texte
        controleur.setText(texte2);
        insererTexte = new InsererTexte(moteurEdition, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();

        assertFalse("Erreur d'insertion 3-1", moteurEdition.getBuffer().getZoneTexte().equals(texte1 + texte2));
        assertTrue("Erreur d'insertion 3-2", moteurEdition.getBuffer().getZoneTexte().equals(texte2 + texte1));
    }

    /**
     * Test insértion  dans le texte (positionnement du curseur dans le texte)
     * @throws Exception
     */
    @Test
    public void InsererText4() throws Exception {
        String texte1 = "Bonjour";
        String texte2 = "Les Terriens";
        String texte3 = "Il en faut peu pour être hereux";

        controleur.setText(texte1);
        insererTexte = new InsererTexte(moteurEdition, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();

        controleur.setText(texte2);
        insererTexte = new InsererTexte(moteurEdition, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();

        moteurEdition.getBuffer().setCurseur(texte1.length());
        controleur.setText(texte3);
        insererTexte = new InsererTexte(moteurEdition, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();


        assertTrue("Erreur d'insertion 3", moteurEdition.getBuffer().getZoneTexte().equals(texte1 + texte3 + texte2));
        assertFalse("Erreur d'insertion 3", moteurEdition.getBuffer().getZoneTexte().equals(texte2 + texte1));
    }

    /**
     * Test sélection quand le buffeur est vide
     * @throws Exception
     */
    @Test
    public void testSelection1() throws Exception {
        selectionnerTexte = new SelectionnerTexte(moteurEdition, controleur);
        invoker = new InvokerImplementation(selectionnerTexte);
        invoker.InvokeCommande();
        assertTrue("Erreur selection 1", moteurEdition.getSelection().getDebutSelection() ==
                moteurEdition.getSelection().getFinSelection());
    }

    /**
     * Test sélection
     * @throws Exception
     */
    @Test
    public void testSelection2() throws Exception {
        String texte1 = "Il en faut peu pour être hereux";
        controleur.setText(texte1);

        //Insertion du texte dans le buffeur
        insererTexte = new InsererTexte(moteurEdition, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();

        //Sélection de 1 à 8
        controleur.setDebutSelection(1);
        controleur.setFinSelection(8);
        selectionnerTexte = new SelectionnerTexte(moteurEdition, controleur);
        invoker = new InvokerImplementation(selectionnerTexte);
        invoker.InvokeCommande();

        assertTrue("Erreur selection 2", moteurEdition.getSelection().getDebutSelection() == 1 &&
                moteurEdition.getSelection().getFinSelection() == 8);
    }

    /**
     * Test copier quand le buffeur est vide
     * @throws Exception
     */
    @Test
    public void testCopier1() throws Exception {
        copier = new Copier(moteurEdition);
        invoker = new InvokerImplementation(copier);
        invoker.InvokeCommande();
        assertTrue("Erreur copier 1", moteurEdition.getPressePapier().coller().equals(""));
    }

    /**
     * Test copier  avant de sélectionner
     * @throws Exception
     */
    @Test
    public void testCopier2() throws Exception {

        // Insertion du text
        String texte1 = "Il en faut peu pour être hereux";
        controleur.setText(texte1);

        insererTexte = new InsererTexte(moteurEdition, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();

        //copier sans sélection
        copier = new Copier(moteurEdition);
        invoker = new InvokerImplementation(copier);
        invoker.InvokeCommande();
        assertTrue("Erreur copier 2", moteurEdition.getPressePapier().coller().equals(""));
    }

    /**
     * Test copier  après sélection
     * @throws Exception
     */
    @Test
    public void testCopier3() throws Exception {

        // insertion du texte dans le buffeur
        String texte1 = "Il en faut peu pour être hereux";
        controleur.setText(texte1);
        insererTexte = new InsererTexte(moteurEdition, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();

        // sélection de 0 à 10
        controleur.setDebutSelection(0);
        controleur.setFinSelection(10);
        selectionnerTexte = new SelectionnerTexte(moteurEdition, controleur);
        invoker = new InvokerImplementation(selectionnerTexte);
        invoker.InvokeCommande();

        //copier dans le pressepapier le texte sélectionné
        copier = new Copier(moteurEdition);
        invoker = new InvokerImplementation(copier);
        invoker.InvokeCommande();

        assertTrue("Erreur copier 3", moteurEdition.getPressePapier().coller().equals("Il en faut"));
    }

    /**
     * Test couper quand le buffeur est vide
     * @throws Exception
     */

    @Test
    public void testCouper1() throws Exception {
        couper = new Couper(moteurEdition);
        invoker = new InvokerImplementation(couper);
        invoker.InvokeCommande();
        assertTrue("Erreur couper 1", moteurEdition.getPressePapier().coller().equals(""));
    }

    /**
     * Test couper avant de sélectionner
     * @throws Exception
     */

    @Test
    public void testCouper2() throws Exception {
        String texte1 = "Il en faut peu pour être hereux";
        controleur.setText(texte1);

        //insertion du texte dans le buffeur
        insererTexte = new InsererTexte(moteurEdition, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();

        // couper sans la une sélection
        couper = new Couper(moteurEdition);
        invoker = new InvokerImplementation(couper);
        invoker.InvokeCommande();
        assertTrue("Erreur couper 2-1", moteurEdition.getPressePapier().coller().equals(""));
        assertTrue("Erreur couper 2-2", moteurEdition.getBuffer().getZoneTexte().equals(texte1));
    }

    /**
     * Test couper après sélection
     * @throws Exception
     */

    @Test
    public void testCouper3() throws Exception {
        String texte1 = "Il en faut peu pour être hereux";

        // Insertion du texte
        controleur.setText(texte1);
        insererTexte = new InsererTexte(moteurEdition, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();

        //Seléction du caratère 0 à 10
        controleur.setDebutSelection(0);
        controleur.setFinSelection(10);
        selectionnerTexte = new SelectionnerTexte(moteurEdition, controleur);
        invoker = new InvokerImplementation(selectionnerTexte);
        invoker.InvokeCommande();

        //couper la sélection
        couper = new Couper(moteurEdition);
        invoker = new InvokerImplementation(couper);
        invoker.InvokeCommande();

        assertTrue("Erreur couper 3-1", moteurEdition.getPressePapier().coller().equals("Il en faut"));
        assertTrue("Erreur couper 3-2", moteurEdition.getBuffer().getZoneTexte().equals(" peu pour être hereux"));
    }

    /**
     * Test coller sans seléction ni copier
     * @throws Exception
     */
    @Test
    public void testColler1() throws Exception {
        String texte1 = "Il en faut peu pour être hereux";

        // initialisation du buffeur
        controleur.setText(texte1);
        insererTexte = new InsererTexte(moteurEdition, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();

        //Coller du texte
        coller = new Coller(moteurEdition);
        invoker = new InvokerImplementation(coller);
        invoker.InvokeCommande();

        assertTrue("Erreur coller 1", moteurEdition.getBuffer().getZoneTexte().equals(texte1));
    }

    /**
     * Test copier coller
     * @throws Exception
     */
    @Test
    public void testCopierColler() throws Exception {
        //insértion du text
        String texte1 = "Il en faut peu pour être hereux";
        controleur.setText(texte1);
        insererTexte = new InsererTexte(moteurEdition, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();

        //sélection du caractère 0 au caractère 10
        controleur.setDebutSelection(0);
        controleur.setFinSelection(10);
        selectionnerTexte = new SelectionnerTexte(moteurEdition, controleur);
        invoker = new InvokerImplementation(selectionnerTexte);
        invoker.InvokeCommande();

        //copier la sélection
        copier = new Copier(moteurEdition);
        invoker = new InvokerImplementation(copier);
        invoker.InvokeCommande();

        //coller la sélection à la fin du texte

        moteurEdition.getBuffer().setCurseur(texte1.length());
        coller = new Coller(moteurEdition);
        invoker = new InvokerImplementation(coller);
        invoker.InvokeCommande();
        assertTrue("Erreur copier/Coller", moteurEdition.getBuffer().getZoneTexte().equals(texte1 + "Il en faut"));
    }

    /**
     * Test copier coller
     * @throws Exception
     */
    @Test
    public void testCouperColler() throws Exception {
        //insértion du text
        String texte1 = "Il en faut peu pour être hereux ";
        controleur.setText(texte1);
        insererTexte = new InsererTexte(moteurEdition, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();

        //sélection du caractère 0 au caractère 10
        controleur.setDebutSelection(0);
        controleur.setFinSelection(10);
        selectionnerTexte = new SelectionnerTexte(moteurEdition, controleur);
        invoker = new InvokerImplementation(selectionnerTexte);
        invoker.InvokeCommande();

        //couper la sélection
        couper = new Couper(moteurEdition);
        invoker = new InvokerImplementation(couper);
        invoker.InvokeCommande();

        //coller la sélection au début du texte

        moteurEdition.getBuffer().setCurseur(moteurEdition.getBuffer().getZoneTexte().length());
        coller = new Coller(moteurEdition);
        invoker = new InvokerImplementation(coller);
        invoker.InvokeCommande();
        assertTrue("Erreur Couper/Coller", moteurEdition.getBuffer().getZoneTexte().equals(" peu pour être hereux Il en faut"));
    }

    /**
     * Test copier coller dans le texte
     */
    @Test
    public void testCouperColler2() throws Exception {
        //insértion du text
        String texte1 = "Il en faut peu  pour être hereux";
        controleur.setText(texte1);
        insererTexte = new InsererTexte(moteurEdition, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();

        //sélection du caractère 0 au caractère 10
        controleur.setDebutSelection(0);
        controleur.setFinSelection(10);
        selectionnerTexte = new SelectionnerTexte(moteurEdition, controleur);
        invoker = new InvokerImplementation(selectionnerTexte);
        invoker.InvokeCommande();

        //couper la sélection
        couper = new Couper(moteurEdition);
        invoker = new InvokerImplementation(couper);
        invoker.InvokeCommande();

        //coller la sélection dans le  texte

        moteurEdition.getBuffer().setCurseur(5);
        coller = new Coller(moteurEdition);
        invoker = new InvokerImplementation(coller);
        invoker.InvokeCommande();
        assertTrue("Erreur Couper/Coller", moteurEdition.getBuffer().getZoneTexte().equals(" peu Il en faut pour être hereux"));
    }

    /**
     * Test Effacer
     * @throws Exception
     */
    @Test
    public void testEffacer1 ()throws Exception
    {
        //insértion du text
        String texte1 = "Il en faut peu  pour être hereux";
        controleur.setText(texte1);
        insererTexte = new InsererTexte(moteurEdition, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();

        //Effacer les 6 dernier caractères
        effacer = new Effacer(moteurEdition);
        invoker = new InvokerImplementation(effacer);
        for(int i =0; i < 6; i++)
        {
            invoker.InvokeCommande();
        }


        assertFalse("Erreur Effacer 1-1", moteurEdition.getBuffer().getZoneTexte().equals(texte1));
        assertTrue("Erreur Effacer 1-2 ", moteurEdition.getBuffer().getZoneTexte().equals("Il en faut peu  pour être "));
    }

    /**
     * test Effacer 2
     * @throws Exception
     */

    public void testEffacer2 ()throws Exception
    {
        //insértion du text
        String texte1 = "Il en faut peu  pour être hereux";
        controleur.setText(texte1);
        insererTexte = new InsererTexte(moteurEdition, controleur);
        invoker = new InvokerImplementation(insererTexte);
        invoker.InvokeCommande();

        //effacer le tous le texte
        effacer = new Effacer(moteurEdition);
        invoker = new InvokerImplementation(effacer);
        for(int i =0; i < 50; i++)
        {
            invoker.InvokeCommande();
        }

        assertFalse("Erreur Effacer 1", moteurEdition.getBuffer().getZoneTexte().equals(texte1));
        assertTrue("Erreur Effacer 2 ", moteurEdition.getBuffer().getZoneTexte().equals(""));
    }
}