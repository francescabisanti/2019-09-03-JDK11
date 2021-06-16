/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.food.model.Connesso;
import it.polito.tdp.food.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtCalorie"
    private TextField txtCalorie; // Value injected by FXMLLoader

    @FXML // fx:id="txtPassi"
    private TextField txtPassi; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorrelate"
    private Button btnCorrelate; // Value injected by FXMLLoader

    @FXML // fx:id="btnCammino"
    private Button btnCammino; // Value injected by FXMLLoader

    @FXML // fx:id="boxPorzioni"
    private ComboBox<String> boxPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCammino(ActionEvent event) {
    	txtResult.clear();
    	this.txtResult.clear();
    	if(this.model.getGrafo()==null) {
    		this.txtResult.setText("Crea prima il grafo");
    		return;
    	}
    	String sel= this.boxPorzioni.getValue();
    	if(sel==null) {
    		this.txtResult.appendText("Seleziona un tipo di porzione!");
    		return;
    	}
    	String nS= this.txtPassi.getText();
    	Integer N;
    	try {
    		N=Integer.parseInt(nS);
    	}catch(NumberFormatException e) {
    		this.txtResult.setText("Inserisci un numero valido!");
    		return;
    	}
    	if(nS==null) {
    		this.txtResult.setText("Inserisci un numero!");
    		return;
    	}
    	
    	List <String> result= model.trovaPercorso(N, sel);
    	for(String s: result) {
    		this.txtResult.appendText(s+"\n");
    	}
    	this.txtResult.appendText(model.calcolaPeso(result)+"");
    	
    }

    @FXML
    void doCorrelate(ActionEvent event) {
    	this.txtResult.clear();
    	if(this.model.getGrafo()==null) {
    		this.txtResult.setText("Crea prima il grafo");
    		return;
    	}
    	String sel= this.boxPorzioni.getValue();
    	List <Connesso> result= model.connessi(sel);
    	for(Connesso s: result) {
    		this.txtResult.appendText(s.toString());
    	}
    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	this.txtResult.clear();
    	String cS= this.txtCalorie.getText();
    	Integer cal;
    	try {
    		cal=Integer.parseInt(cS);
    	}catch(NumberFormatException e) {
    		this.txtResult.setText("Inserisci un numero valido!");
    		return;
    	}
    	if(cS==null) {
    		this.txtResult.setText("Inserisci un numero!");
    		return;
    	}
    	this.model.creaGrafo(cal);
    	this.txtResult.appendText("#VERTICI: "+this.model.getNVertici()+"\n");
    	this.txtResult.appendText("#ARCHI: "+this.model.getNArchi()+"\n");
    	this.boxPorzioni.getItems().clear();
    	this.boxPorzioni.getItems().addAll(this.model.getGrafo().vertexSet());
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtCalorie != null : "fx:id=\"txtCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtPassi != null : "fx:id=\"txtPassi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCorrelate != null : "fx:id=\"btnCorrelate\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCammino != null : "fx:id=\"btnCammino\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxPorzioni != null : "fx:id=\"boxPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	
    }
}
