
package turtletatics.controller;

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
import turtletatics.classesTelas.TelaInicial;

public class TelaHabEspPersController implements Initializable {

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
    
}
