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
import originators.CommandeEnregistrableInsererText;

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
    private ConcreteEnregistreur concreteEnregistreur;
    private CommandeEnregistrableInsererText commandeEnregistrableInsererText;
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
     * Initialize the view
     */
    @FXML
    public void initialize()
    {

        this.moteurEdition = new MoteurEditionImplementation();
        this.insererTexte = new InsererTexte(this.moteurEdition, this);
        this.selectionnerTexte = new SelectionnerTexte(this.moteurEdition, this);
        concreteEnregistreur = new ConcreteEnregistreur();
        commandeEnregistrableInsererText = new CommandeEnregistrableInsererText();

        buttonCouper.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!testCouper())
                {
                    commande = new Couper(moteurEdition);
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
            curseur = textEdit.getCaretPosition();
            textEdit.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>(){
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode().equals(KeyCode.BACK_SPACE) && !textEdit.getText().equals("") ){
                       testEffacer = true;

                    }
                    if(textEdit.getText().equals(""))
                    {
                        textEdit.positionCaret(0);
                    }
                }
            });
            if (!testEffacer && !testCouper && !testColler){
                curseur = textEdit.getCaretPosition();
                setText(newValue.substring(curseur, curseur+1));
                moteurEdition.getBuffer().setCurseur(curseur);
                invoker = new InvokerImplementation(insererTexte);
                invoker.InvokeCommande();
                if(concreteEnregistreur.isStartLoading())
                {

                }
                textEdit.positionCaret(moteurEdition.getBuffer().getCurseur());
                textEdit.setText(moteurEdition.getBuffer().getZoneTexte());
            }
            else if(testEffacer &&  curseur > 0){
                commande = new Effacer(moteurEdition);
                curseur = textEdit.getCaretPosition();
                moteurEdition.getBuffer().setCurseur(curseur);
                invoker = new InvokerImplementation(commande);
                invoker.InvokeCommande();
                testEffacer = true;
                textEdit.positionCaret(moteurEdition.getBuffer().getCurseur());
                textEdit.setText(moteurEdition.getBuffer().getZoneTexte());

                if(concreteEnregistreur.isStartLoading())
                {

                }
            }
            testEffacer = false;
            testCouper = false;
            testColler = false;
        }
    });
        buttonCopier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectionner();
                commande = new Copier(moteurEdition);
                curseur = textEdit.getCaretPosition();
                moteurEdition.getBuffer().setCurseur(curseur);
                invoker = new InvokerImplementation(commande);
                invoker.InvokeCommande();
                if(concreteEnregistreur.isStartLoading())
                {

                }
            }
        });

        buttonColler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                testColler = true;
                commande = new Coller(moteurEdition);
                curseur = textEdit.getCaretPosition();
                moteurEdition.getBuffer().setCurseur(curseur);
                invoker = new InvokerImplementation(commande);
                invoker.InvokeCommande();
                textEdit.positionCaret(moteurEdition.getBuffer().getCurseur());
                textEdit.setText(moteurEdition.getBuffer().getZoneTexte());
                if(concreteEnregistreur.isStartLoading())
                {

                }

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




    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCurseur() {
        return curseur;
    }

    public void setCurseur(int curseur) {
        this.curseur = curseur;
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

    /**
     * select the text
     */
    public void selectionner()
    {
        setDebutSelection(textEdit.getSelection().getStart());
        setFinSelection(textEdit.getSelection().getEnd());
        invoker = new InvokerImplementation(selectionnerTexte);
        invoker.InvokeCommande();
        if(concreteEnregistreur.isStartLoading())
        {

        }
    }



    /**
     *  check  if start of selection is different end of selection
     * @return boolean
     */
    public boolean testCouper()
    {
         return  textEdit.getSelection().getStart()==textEdit.getSelection().getEnd();
    }
}
