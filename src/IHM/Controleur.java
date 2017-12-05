package IHM;


import caretaker.ConcreteEnregistreur;
import commandes.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import moteurEdition.MoteurEditionImplementation;
import originators.*;

public class Controleur {

    private MoteurEditionImplementation moteurEdition;
    private Commande commande;
    private InvokerImplementation invoker;
    private String text;
    private int debutSelection;
    private int finSelection;
    private int curseur;
    private boolean testEffacer = false;
    private boolean testCouper = false;
    private boolean testColler = false;
    private InsererTexte insererTexte;
    private SelectionnerTexte selectionnerTexte;
    private CommandeEnregistrableSelectionner selectionnerTexteEnregistrable;
    private ConcreteEnregistreur concreteEnregistreur;
    private CommandeEnregistrableInsererText insererTexteEnregistrable;
    @FXML
    private TextArea textEdit;
    @FXML
    Button buttonCouper;
    @FXML
    Button buttonCopier;
    @FXML
    Button buttonEnregistrer;
    @FXML
    Button buttonArreter;
    @FXML
    Button buttonRejouer;
    @FXML
    Button buttonColler;
    /**
     * Initialise la vue
     */
    @FXML
    public void initialize()
    {

        this.moteurEdition = new MoteurEditionImplementation();
        concreteEnregistreur = new ConcreteEnregistreur();
        this.insererTexte = new InsererTexte(this.moteurEdition, this);
        this.selectionnerTexte = new SelectionnerTexte(this.moteurEdition, this);
        this.selectionnerTexteEnregistrable = new CommandeEnregistrableSelectionner(this.moteurEdition, this.concreteEnregistreur,this);
        this.insererTexteEnregistrable = new CommandeEnregistrableInsererText(this.moteurEdition, this.concreteEnregistreur, this);

        buttonCouper.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!testCouper())
                {
                    if(concreteEnregistreur.isRecording())
                    {
                        commande = new CommandeEnregistrableCouper(moteurEdition, concreteEnregistreur);
                    }
                    else{
                        commande = new Couper(moteurEdition);
                    }
                    selectionner();
                    testCouper = true;
                    curseur = textEdit.getCaretPosition();
                    moteurEdition.getBuffer().setCurseur(curseur);
                    invoker = new InvokerImplementation(commande);
                    invoker.InvokeCommande();
                    textEdit.positionCaret(moteurEdition.getBuffer().getCurseur());
                    textEdit.setText(moteurEdition.getBuffer().getZoneTexte());

                }

            }
        });

        textEdit.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
            if(!concreteEnregistreur.isReplay()){
                curseur = textEdit.getCaretPosition();
                textEdit.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if (event.getCode().equals(KeyCode.BACK_SPACE) && !textEdit.getText().equals("")){
                            testEffacer = true;

                        }
                        if(textEdit.getText().equals(""))
                        {
                            textEdit.positionCaret(0);
                        }
                        if (event.getCode().equals(KeyCode.DELETE) && !textEdit.getText().equals("") ){
                            event.consume();
                            System.out.println("delete");
                        }
                    }
                });


                if (!testEffacer && !testCouper && !testColler){
                    curseur = textEdit.getCaretPosition();
                    setText(newValue.substring(curseur, curseur+1));
                    moteurEdition.getBuffer().setCurseur(curseur);
                    if(concreteEnregistreur.isRecording())
                    { invoker = new InvokerImplementation(insererTexteEnregistrable);
                    }
                    else{
                        invoker = new InvokerImplementation(insererTexte);
                    }
                    invoker.InvokeCommande();
                    textEdit.positionCaret(moteurEdition.getBuffer().getCurseur());
                    textEdit.setText(moteurEdition.getBuffer().getZoneTexte());
                }
                else if(testEffacer &&  curseur > 0){
                    if(concreteEnregistreur.isRecording())
                    {
                        commande = new CommandeEnregistrableEffacer(moteurEdition, concreteEnregistreur);
                    }
                    else{
                        commande = new Effacer(moteurEdition);
                    }
                    curseur = textEdit.getCaretPosition();
                    moteurEdition.getBuffer().setCurseur(curseur);
                    invoker = new InvokerImplementation(commande);
                    invoker.InvokeCommande();
                    testEffacer = true;
                    textEdit.positionCaret(moteurEdition.getBuffer().getCurseur());
                    textEdit.setText(moteurEdition.getBuffer().getZoneTexte());
                }
                testEffacer = false;
                testCouper = false;
                testColler = false;
            }
        }
    });
        buttonCopier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectionner();
                if(concreteEnregistreur.isRecording())
                {
                    commande = new CommandeEnregistrableCopier(concreteEnregistreur, moteurEdition);
                }
                else{
                    commande = new Copier(moteurEdition);
                }
                curseur = textEdit.getCaretPosition();
                moteurEdition.getBuffer().setCurseur(curseur);
                invoker = new InvokerImplementation(commande);
                invoker.InvokeCommande();
            }
        });

        buttonColler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                testColler = true;
                if(concreteEnregistreur.isRecording())
                {
                    commande = new CommandeEnregistrableColler(moteurEdition, concreteEnregistreur);
                }
                else{
                    commande = new Coller(moteurEdition);
                }
                curseur = textEdit.getCaretPosition();
                moteurEdition.getBuffer().setCurseur(curseur);
                invoker = new InvokerImplementation(commande);
                invoker.InvokeCommande();
                textEdit.positionCaret(moteurEdition.getBuffer().getCurseur());
                textEdit.setText(moteurEdition.getBuffer().getZoneTexte());
            }
        });

        buttonEnregistrer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                commande = new Enregistrer(concreteEnregistreur);
                invoker = new InvokerImplementation(commande);
                invoker.InvokeCommande();
            }
        });

        buttonArreter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                commande = new Arreter(concreteEnregistreur);
                invoker = new InvokerImplementation(commande);
                invoker.InvokeCommande();
            }
        });

        buttonRejouer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                commande = new Rejouer(concreteEnregistreur);
                invoker = new InvokerImplementation(commande);
                invoker.InvokeCommande();
                textEdit.setText(moteurEdition.getBuffer().getZoneTexte());
                concreteEnregistreur.setReplay(false);
            }
        });

    }
    /**
     * Retourne le texte Courant
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     * Défini le texte courant
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }
    /**
     * Retourne la position du Curseur
     * @return
     */
    public int getCurseur() {
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
     * Retourne le début de la sélection
     * @return
     */
    public int getDebutSelection() {
        return debutSelection;
    }

    /**
     * Défini le début de la sélection
     * @param debutSelection
     */
    public void setDebutSelection(int debutSelection) {
        this.debutSelection = debutSelection;
    }

    /**
     * Retourne la fin de la sélection
     * @return
     */
    public int getFinSelection() {
        return finSelection;
    }

    /**
     * Défini la fin de la sélection
     * @param finSelection
     */
    public void setFinSelection(int finSelection) {
        this.finSelection = finSelection;
    }

    /**
     * Appel de la Commande SelectionnerTexte pour sélectionner le texte
     * à partir de debutSelection et finSelection
     */
    public void selectionner()
    {
        setDebutSelection(textEdit.getSelection().getStart());
        setFinSelection(textEdit.getSelection().getEnd());
        if(concreteEnregistreur.isRecording())
        {
            invoker = new InvokerImplementation(selectionnerTexteEnregistrable);
        }
        else{
            invoker = new InvokerImplementation(selectionnerTexte);
        }
        invoker.InvokeCommande();
    }
    /**
     *  Vérifie s'il y a eu sélection
     * @return
     */
    public boolean testCouper()
    {
         return  textEdit.getSelection().getStart()==textEdit.getSelection().getEnd();
    }
}
