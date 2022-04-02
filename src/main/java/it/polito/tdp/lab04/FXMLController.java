package it.polito.tdp.lab04;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox checkBox;

    @FXML
    private ComboBox<Corso> cmbCorsi;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextArea txtResult;

    @FXML
    void cercaCorsi(ActionEvent event) {
    	this.txtResult.clear();
    	if(this.txtMatricola.getText().isBlank() || this.txtMatricola.getText().length()!=6) {
    		this.txtResult.setText("Devi inserire una matricola");
    		this.checkBox.setSelected(false);
    		return;
    	}
    	try {
    		int matricola = Integer.parseInt(this.txtMatricola.getText());
	    	List<Corso> corsi = this.model.getCorsiStudente(matricola);
	    	if(corsi.isEmpty()) {
	    		this.txtResult.setText("Matricola non esistente");
	    	}	    		
			for(Corso c: corsi) {
				this.txtResult.appendText(c.toString()+"\n");
			}
    	} catch (Exception e) {
    		this.txtResult.setText("Matricola non esistente");
    		return;
    	}
    }

    @FXML
    void cercaIscrittiCorso(ActionEvent event) {
    	this.txtResult.clear();
    	try {
	    	List<Studente> studenti = this.model.getStudentiIscrittiAlCorso(this.cmbCorsi.getValue());
			for(Studente s: studenti) {
				this.txtResult.appendText(s.toString()+"\n");
			}
    	} catch (Exception e) {
    		this.txtResult.setText("Devi scegliere un corso");
    		return;
    	}
    }
    
    @FXML
    void completamentoAutomatico(ActionEvent event) {
    	this.txtResult.clear();
    	if(!this.checkBox.isSelected()) {
    		this.txtCognome.clear();
        	this.txtNome.clear();
        	return;
    	}
    	if(this.txtMatricola.getText().isBlank() || this.txtMatricola.getText().length()!=6) {
    		this.txtResult.setText("Devi inserire una matricola per il completamento automatico");
    		this.checkBox.setSelected(false);
    		return;
    	}
    	try {
    		int matricola = Integer.parseInt(this.txtMatricola.getText());
    		Studente studente = this.model.getStudenteByMatricola(matricola);
        	this.txtCognome.setText(studente.getCognome());
        	this.txtNome.setText(studente.getNome());
    	} catch (Exception e) {
    		this.txtResult.setText("Matricola non esistente");
    		this.checkBox.setSelected(false);
    	}
    }
    
    @FXML
    void iscrivi(ActionEvent event) {
    	//TODO cercare se lo studente frequenta il corso, in caso di risposta positiva
    	// stampare qualcosa in result altrimenti aggiungi(iscrivi) lo studente al corso
    	this.txtResult.clear();
    	if(this.txtMatricola.getText().isBlank() || this.txtMatricola.getText().length()!=6) {
    		this.txtResult.setText("Devi inserire una matricola");
    		return;
    	}
    	try {
    		int matricola = Integer.parseInt(this.txtMatricola.getText());
    		if(!this.model.getStudentiIscrittiAlCorso(this.cmbCorsi.getValue()).contains(this.model.getStudenteByMatricola(matricola))) {
				boolean b = this.model.inscriviStudenteACorso(matricola, this.cmbCorsi.getValue());
				if(b) {
					this.txtResult.setText("Studente iscritto al corso");
				} else {
					this.txtResult.setText("Errore nell'iscrizione");
				}
					
			}
		} catch(Exception e) {
			this.txtResult.setText("Errore nei dati inseriti");
		}
    }

    @FXML
    void reset(ActionEvent event) {
    	this.cmbCorsi.setValue(null);
    	this.checkBox.setSelected(false);
    	this.txtMatricola.clear();
    	this.txtCognome.clear();
    	this.txtNome.clear();
    	this.txtResult.clear();
    }

    @FXML
    void initialize() {
        assert checkBox != null : "fx:id=\"checkBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbCorsi != null : "fx:id=\"cmbCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	
    	this.cmbCorsi.getItems().clear();
        for(Corso c: this.model.getTuttiICorsi()) {
        	this.cmbCorsi.getItems().add(c);
        }
    }

}
