package moteurEdition;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoteurEditionImplementationTest {

    MoteurEditionImplementation moteurEdition;

    @Before
    public void setUp() throws Exception {
      moteurEdition = new MoteurEditionImplementation();

    }

    @Test
    public void insererTexte() throws Exception {

        //teste insertion quand le buffeur est vide
        moteurEdition.insererTexte("Il en faut peu pour être hereux");
        assertEquals("Erreur d'insertion 1",moteurEdition.getBuffer().getZoneTexte(),"Il en faut peu pour être hereux");
        assertEquals("Erreur Curseur",moteurEdition.getBuffer().getCurseur(),31);

        // teste insertion en fin de texte
        moteurEdition.insererTexte(", tout est possible");
        assertEquals("Erreur d'insertion 2",moteurEdition.getBuffer().getZoneTexte(),"Il en faut peu pour être hereux, tout est possible");
        assertEquals("Erreur de Curseur 2",moteurEdition.getBuffer().getCurseur(),50);

        // test insertion dans le texte
        moteurEdition.getBuffer().setCurseur(32);
        moteurEdition.insererTexte(" est-ce-varaix que");
        assertEquals("Erreur d'insertion 3", moteurEdition.getBuffer().getZoneTexte(),"Il en faut peu pour être hereux, est-ce-varaix que tout est possible");
        assertEquals("Erreur de Curseur 3",moteurEdition.getBuffer().getCurseur(),50);

        // test insertion début de texte
        moteurEdition.getBuffer().setCurseur(0);
        moteurEdition.insererTexte("Holla, ");
        assertEquals("Erreur d'insertion 4", moteurEdition.getBuffer().getZoneTexte(),"Holla, Il en faut peu pour être hereux, est-ce-varaix que tout est possible");
        assertEquals("Erreur de Curseur 4",moteurEdition.getBuffer().getCurseur(),7);




    }

    @Test
    public void selectionnerTexte() throws Exception {
        //Selection de gauche à droite
        moteurEdition.selectionnerTexte(1,10);
        assertEquals("Selection: Erreur de Curseur 1", moteurEdition.getBuffer().getCurseur(),10);
        assertEquals("Selection: Erreur de selection 1", moteurEdition.getSelection().getDebutSelection(),1);
        assertEquals("Selection: Erreur de selection 2", moteurEdition.getSelection().getFinSelection(),10);

        //Selection de droite à gauche
        moteurEdition.selectionnerTexte(10,1);
        assertEquals("Selection: Erreur de Curseur 1", moteurEdition.getBuffer().getCurseur(),1);
        assertEquals("Selection: Erreur de selection 1", moteurEdition.getSelection().getDebutSelection(),10);
        assertEquals("Selection: Erreur de selection 2", moteurEdition.getSelection().getFinSelection(),1);
    }

    @Test
    public void couper() throws Exception {
        moteurEdition.insererTexte("Il en faut peu pour être hereux");

        //Couper une selection de gauche à droite
        moteurEdition.selectionnerTexte(3,10);
        moteurEdition.couper();
        assertEquals("Couper: Erreur PressePapier 1", moteurEdition.getPressePapier().coller(),"en faut" );
        assertEquals("Couper: Erreur Buffeur 1", moteurEdition.getBuffer().getZoneTexte(),"Il  peu pour être hereux");
        assertEquals("Couper: Erreur Curseur 1", moteurEdition.getBuffer().getCurseur(),
                Math.abs(moteurEdition.getSelection().getDebutSelection()-moteurEdition.getSelection().getFinSelection()));

        //Couper une selection de de droite à gauche
        moteurEdition.selectionnerTexte(12,4);
        moteurEdition.couper();
        assertEquals("Couper: Erreur Couper 2", moteurEdition.getPressePapier().coller(),"peu pour" );
        assertEquals("Couper: Erreur Buffeur 2", moteurEdition.getBuffer().getZoneTexte(),"Il   être hereux");
        assertEquals("Couper: Erreur Curseur 2", moteurEdition.getBuffer().getCurseur(),
                Math.abs(moteurEdition.getSelection().getDebutSelection()-moteurEdition.getSelection().getFinSelection()));
        //couper un caractère
        moteurEdition.selectionnerTexte(1,2);
        moteurEdition.couper();
        assertEquals("Couper: Erreur PressePapier 3", moteurEdition.getPressePapier().coller(),"l" );
        assertEquals("Couper: Erreur Buffeur 3", moteurEdition.getBuffer().getZoneTexte(),"I   être hereux");
        assertEquals("Couper: Erreur Curseur 3", moteurEdition.getBuffer().getCurseur(),
                Math.abs(moteurEdition.getSelection().getDebutSelection()-moteurEdition.getSelection().getFinSelection()));
    }

    @Test
    public void copier() throws Exception {

        moteurEdition.insererTexte("Il en faut peu pour être hereux");

        //test copier de gauche à droite
        moteurEdition.selectionnerTexte(3,10);
        moteurEdition.copier();
        assertEquals("Copier: Erreur copie 1", moteurEdition.getPressePapier().coller(),"en faut" );

        //test copier de droite à gauche
        moteurEdition.selectionnerTexte(20,0);
        moteurEdition.copier();
        assertEquals("Copier: Erreur copie 2", moteurEdition.getPressePapier().coller(),"Il en faut peu pour " );

        // si début de sélection est égale à la fin de sélection rien ne se passe et le contenu du pressepapier reste inchangé
        moteurEdition.selectionnerTexte(20,20);
        moteurEdition.copier();
        assertEquals("Copier: Erreur copie 3", moteurEdition.getPressePapier().coller(),"Il en faut peu pour " );

    }

    @Test
    public void copier_coller() throws Exception {
        moteurEdition.insererTexte("Il en faut peu pour être hereux");
        moteurEdition.selectionnerTexte(3,10);
        moteurEdition.copier();

        //collage au début du texte
        moteurEdition.getBuffer().setCurseur(0);
        moteurEdition.coller();
        assertEquals("Coller : Erreur de collage 1", moteurEdition.getBuffer().getZoneTexte(),"en fautIl en faut peu pour être hereux");
        assertEquals("Coller : Erreur Curseeur 1 ", moteurEdition.getBuffer().getCurseur(),
                0+Math.abs(moteurEdition.getSelection().getDebutSelection()- moteurEdition.getSelection().getFinSelection()));
        //Collage dans le text

        moteurEdition.getBuffer().setCurseur(9);
        moteurEdition.coller();
        assertEquals("Coller : Erreur de collage 2", moteurEdition.getBuffer().getZoneTexte(),"en fautIlen faut en faut peu pour être hereux");
        assertEquals("Coller : Erreur Curseeur 2 ", moteurEdition.getBuffer().getCurseur(),
                9+Math.abs(moteurEdition.getSelection().getDebutSelection()- moteurEdition.getSelection().getFinSelection()));
        //Collage dans a la fin text

        moteurEdition.getBuffer().setCurseur(45);
        moteurEdition.coller();
        assertEquals("Coller : Erreur de collage 3", moteurEdition.getBuffer().getZoneTexte(),"en fautIlen faut en faut peu pour être hereuxen faut");
        assertEquals("Coller : Erreur Curseeur 3 ", moteurEdition.getBuffer().getCurseur(),
                45+Math.abs(moteurEdition.getSelection().getDebutSelection()- moteurEdition.getSelection().getFinSelection()));
    }

    @Test
    public void couper_coller() throws Exception {
//en faut
        moteurEdition.insererTexte("Il en faut peu pour être hereux");
        moteurEdition.selectionnerTexte(3,10);
        moteurEdition.couper();

        //collage au début du texte
        moteurEdition.getBuffer().setCurseur(0);
        moteurEdition.coller();
        assertEquals("Coller : Erreur de collage 1", moteurEdition.getBuffer().getZoneTexte(),"en fautIl  peu pour être hereux");
        assertEquals("Coller : Erreur Curseeur 1 ", moteurEdition.getBuffer().getCurseur(),
                0+Math.abs(moteurEdition.getSelection().getDebutSelection()- moteurEdition.getSelection().getFinSelection()));
        //Collage dans le text

        moteurEdition.getBuffer().setCurseur(9);
        moteurEdition.coller();
        assertEquals("Coller : Erreur de collage 2", moteurEdition.getBuffer().getZoneTexte(),"en fautIlen faut  peu pour être hereux");
        assertEquals("Coller : Erreur Curseeur 2 ", moteurEdition.getBuffer().getCurseur(),
                9+Math.abs(moteurEdition.getSelection().getDebutSelection()- moteurEdition.getSelection().getFinSelection()));
        //Collage dans a la fin text

        moteurEdition.getBuffer().setCurseur(38);
        moteurEdition.coller();
        assertEquals("Coller : Erreur de collage 3", moteurEdition.getBuffer().getZoneTexte(),"en fautIlen faut  peu pour être hereuxen faut");
        assertEquals("Coller : Erreur Curseeur 3 ", moteurEdition.getBuffer().getCurseur(),
                38+Math.abs(moteurEdition.getSelection().getDebutSelection()- moteurEdition.getSelection().getFinSelection()));
    }

}