
package turtletatics.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import turtletatics.classesJogo.personagens.Personagem;

import turtletatics.classesTelas.*;

public class TelaEscolhaPersonagensController implements Initializable {
    
    String personagemSelecionado = null;
    boolean ehVezDeJ1 = true;
    
    @FXML
    private SplitPane carcereiro;
    @FXML
    private SplitPane cavaleiro;
    @FXML
    private SplitPane cientistaMaluco;
    @FXML
    private SplitPane comandante;
    @FXML
    private SplitPane construtor;
    @FXML
    private SplitPane cozinheiro;
    @FXML
    private SplitPane engenheiro;
    @FXML
    private SplitPane espiao;
    @FXML
    private SplitPane padre;
    @FXML
    private SplitPane pescador;
    @FXML
    private Label labelJogadorAtual;
    
    public void resetarTela() {
        carcereiro.opacityProperty().set(1);
        cavaleiro.opacityProperty().set(1);
        cientistaMaluco.opacityProperty().set(1);
        comandante.opacityProperty().set(1);
        construtor.opacityProperty().set(1);
        cozinheiro.opacityProperty().set(1);
        engenheiro.opacityProperty().set(1);
        espiao.opacityProperty().set(1);
        padre.opacityProperty().set(1);
        pescador.opacityProperty().set(1);
        personagemSelecionado = null;
        
        if(ehVezDeJ1) labelJogadorAtual.setText(TelaEscolhaPersonagens.j1.getNome() + ", escolha um personagem");
        else labelJogadorAtual.setText(TelaEscolhaPersonagens.j2.getNome() + ", escolha um personagem");
    }
    
    public void removerBotao() {
        if(personagemSelecionado != null) {
            switch (personagemSelecionado) {
                case "Carcereiro":
                    carcereiro.disableProperty().set(true);
                    break;
                case "Cavaleiro":
                    cavaleiro.disableProperty().set(true);
                    break;
                case "Cientista Maluco":
                    cientistaMaluco.disableProperty().set(true);
                    break;
                case "Comandante":
                    comandante.disableProperty().set(true);
                    break;
                case "Construtor":
                    construtor.disableProperty().set(true);
                    break;
                case "Cozinheiro":
                    cozinheiro.disableProperty().set(true);
                    break;
                case "Engenheiro":
                    engenheiro.disableProperty().set(true);
                    break;
                case "Espião":
                    espiao.disableProperty().set(true);
                    break;
                case "Padre":
                    padre.disableProperty().set(true);
                    break;
                case "Pescador":
                    pescador.disableProperty().set(true);
                    break;
            }
        }
    }
    
    @FXML
    private void inicializarLabel(MouseEvent event) {
        if(ehVezDeJ1) labelJogadorAtual.setText(TelaEscolhaPersonagens.j1.getNome() + ", escolha um personagem");
        else labelJogadorAtual.setText(TelaEscolhaPersonagens.j2.getNome() + ", escolha um personagem");
    }
    
    public void fecharTela() {
        ((Stage) carcereiro.getScene().getWindow()).close();
    }
    
    public void iniciarFasePosicionamento() {
        TelaPosicionamento tela = new TelaPosicionamento(TelaEscolhaPersonagens.j1,
                                                         TelaEscolhaPersonagens.j2,
                                                         TelaEscolhaPersonagens.tabuleiro);
        
        fecharTela();
        
        try {
            tela.start(new Stage());
        } catch(Exception ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void acaoClicarCarcereiro(MouseEvent event) {        
        resetarTela();
        carcereiro.opacityProperty().set(0.5);
        personagemSelecionado = "Carcereiro";
    }

    @FXML
    private void acaoClicarCavaleiro(MouseEvent event) {
        resetarTela();
        cavaleiro.opacityProperty().set(0.5);
        personagemSelecionado = "Cavaleiro";
    }

    @FXML
    private void acaoClicarCientistaMaluco(MouseEvent event) {
        resetarTela();
        cientistaMaluco.opacityProperty().set(0.5);
        personagemSelecionado = "Cientista Maluco";
    }

    @FXML
    private void acaoClicarComandante(MouseEvent event) {
        resetarTela();
        comandante.opacityProperty().set(0.5);
        personagemSelecionado = "Comandante";
    }

    @FXML
    private void acaoClicarConstrutor(MouseEvent event) {
        resetarTela();
        construtor.opacityProperty().set(0.5);
        personagemSelecionado = "Construtor";
    }

    @FXML
    private void acaoClicarCozinheiro(MouseEvent event) {
        resetarTela();
        cozinheiro.opacityProperty().set(0.5);
        personagemSelecionado = "Cozinheiro";
    }

    @FXML
    private void acaoClicarEngenheiro(MouseEvent event) {
        resetarTela();
        engenheiro.opacityProperty().set(0.5);
        personagemSelecionado = "Engenheiro";
    }

    @FXML
    private void acaoClicarEspiao(MouseEvent event) {
        resetarTela();
        espiao.opacityProperty().set(0.5);
        personagemSelecionado = "Espião";
    }

    @FXML
    private void acaoClicarPadre(MouseEvent event) {
        resetarTela();
        padre.opacityProperty().set(0.5);
        personagemSelecionado = "Padre";
    }

    @FXML
    private void acaoClicarPescador(MouseEvent event) {
        resetarTela();
        pescador.opacityProperty().set(0.5);
        personagemSelecionado = "Pescador";
    }
    
    @FXML
    private void acaoClicarTela(MouseEvent event) {
        resetarTela();
    }
    
    @FXML
    private void acaoClicarBotaoConfirmEsc(ActionEvent event) {
        if(personagemSelecionado != null) {
            removerBotao();
            if(TelaEscolhaPersonagens.inserirPersonagemJ1(personagemSelecionado, ehVezDeJ1)) {
                System.out.println("Peronagens de " + TelaEscolhaPersonagens.j1.getNome() + ":");
                for(Personagem p : TelaEscolhaPersonagens.j1.getPersonagens()) {
                    System.out.println(p.getNome());
                }
                System.out.println("\nPeronagens de " + TelaEscolhaPersonagens.j2.getNome() + ":");
                for(Personagem p : TelaEscolhaPersonagens.j2.getPersonagens()) {
                    System.out.println(p.getNome());
                }
                iniciarFasePosicionamento();
            }
            ehVezDeJ1 = !ehVezDeJ1;
            resetarTela();
        }
    }
    
}
