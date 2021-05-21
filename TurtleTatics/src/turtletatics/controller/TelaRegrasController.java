
package turtletatics.controller;

import turtletatics.classesTelas.TelaHabEspPers;
import turtletatics.classesTelas.TelaInicial;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class TelaRegrasController implements Initializable {

    @FXML
    private Button botaoVerHabEsp;
    @FXML
    private Button botaoVoltarTelaIni;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void fecharTela() {
        ((Stage) botaoVoltarTelaIni.getScene().getWindow()).close();
    }
    
    public void abrirTelaInicial() {
        TelaInicial tela = new TelaInicial();
        try {
            tela.start(new Stage());
        } catch(Exception ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void abrirTelaHabEspPers() {
        TelaHabEspPers tela = new TelaHabEspPers();
        try {
            tela.start(new Stage());
        } catch(Exception ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void acaoClicarBotaoVoltarTelaIni(ActionEvent event) {
        fecharTela();
        abrirTelaInicial();
    }

    @FXML
    private void acaoTeclarBotaoVoltarTelaIni(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            fecharTela();
            abrirTelaInicial();
        }
    }
    
    @FXML
    private void acaoClicarBotaoVerHabEsp(ActionEvent event) {
        fecharTela();
        abrirTelaHabEspPers();
    }

    @FXML
    private void acaoTeclarBotaoVerHabEsp(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            fecharTela();
            abrirTelaHabEspPers();
        }
    }
    
}
