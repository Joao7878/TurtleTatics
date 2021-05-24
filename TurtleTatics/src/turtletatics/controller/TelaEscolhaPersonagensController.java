package turtletatics.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import turtletatics.classesTelas.*;

public class TelaEscolhaPersonagensController implements Initializable {

    String personagemSelecionado = null;
    boolean ehVezDeJ1 = true;
    ArrayList<SplitPane> listaPersonagens = new ArrayList<>();

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

    void resetarTela() {
        for (SplitPane sp : listaPersonagens) {
            sp.opacityProperty().set(1);
        }

        personagemSelecionado = null;

        if (ehVezDeJ1) {
            labelJogadorAtual.setText(TelaEscolhaPersonagens.j1.getNome() + ", escolha um personagem");
        } else {
            labelJogadorAtual.setText(TelaEscolhaPersonagens.j2.getNome() + ", escolha um personagem");
        }
    }

    void removerBotao() {
        if (personagemSelecionado != null) {
            for (SplitPane sp : listaPersonagens) {
                if (personagemSelecionado.equals(sp.getId())) {
                    sp.disableProperty().set(true);
                    break;
                }
            }
        }
    }

    void fecharTela() {
        ((Stage) carcereiro.getScene().getWindow()).close();
    }

    void iniciarFasePosicionamento() {
        TelaPosicionamento tela = new TelaPosicionamento(TelaEscolhaPersonagens.j1,
                TelaEscolhaPersonagens.j2,
                TelaEscolhaPersonagens.tamTabuleiro);

        try {
            tela.start(new Stage());
            fecharTela();
        } catch (Exception ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao tentar abrir tela", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        listaPersonagens.add(carcereiro);
        listaPersonagens.add(cavaleiro);
        listaPersonagens.add(cientistaMaluco);
        listaPersonagens.add(comandante);
        listaPersonagens.add(construtor);
        listaPersonagens.add(cozinheiro);
        listaPersonagens.add(engenheiro);
        listaPersonagens.add(espiao);
        listaPersonagens.add(padre);
        listaPersonagens.add(pescador);

        labelJogadorAtual.setText(TelaEscolhaPersonagens.j1.getNome() + ", escolha um personagem");
        
        //iniciarFasePosicionamento();//APAGA ESSA LINHA DEPOIS
    }

    @FXML
    private void acaoClicarCarcereiro(MouseEvent event) {
        resetarTela();
        carcereiro.opacityProperty().set(0.5);
        personagemSelecionado = "carcereiro";
    }

    @FXML
    private void acaoClicarCavaleiro(MouseEvent event) {
        resetarTela();
        cavaleiro.opacityProperty().set(0.5);
        personagemSelecionado = "cavaleiro";
    }

    @FXML
    private void acaoClicarCientistaMaluco(MouseEvent event) {
        resetarTela();
        cientistaMaluco.opacityProperty().set(0.5);
        personagemSelecionado = "cientistaMaluco";
    }

    @FXML
    private void acaoClicarComandante(MouseEvent event) {
        resetarTela();
        comandante.opacityProperty().set(0.5);
        personagemSelecionado = "comandante";
    }

    @FXML
    private void acaoClicarConstrutor(MouseEvent event) {
        resetarTela();
        construtor.opacityProperty().set(0.5);
        personagemSelecionado = "construtor";
    }

    @FXML
    private void acaoClicarCozinheiro(MouseEvent event) {
        resetarTela();
        cozinheiro.opacityProperty().set(0.5);
        personagemSelecionado = "cozinheiro";
    }

    @FXML
    private void acaoClicarEngenheiro(MouseEvent event) {
        resetarTela();
        engenheiro.opacityProperty().set(0.5);
        personagemSelecionado = "engenheiro";
    }

    @FXML
    private void acaoClicarEspiao(MouseEvent event) {
        resetarTela();
        espiao.opacityProperty().set(0.5);
        personagemSelecionado = "espiao";
    }

    @FXML
    private void acaoClicarPadre(MouseEvent event) {
        resetarTela();
        padre.opacityProperty().set(0.5);
        personagemSelecionado = "padre";
    }

    @FXML
    private void acaoClicarPescador(MouseEvent event) {
        resetarTela();
        pescador.opacityProperty().set(0.5);
        personagemSelecionado = "pescador";
    }

    @FXML
    private void acaoClicarTela(MouseEvent event) {
        resetarTela();
    }

    @FXML
    private void acaoClicarBotaoConfirmEsc(ActionEvent event) {
        if (personagemSelecionado != null) {
            removerBotao();
            if (TelaEscolhaPersonagens.inserirPersonagem(personagemSelecionado, ehVezDeJ1)) {
                iniciarFasePosicionamento();
            }

            ehVezDeJ1 = !ehVezDeJ1;
            resetarTela();
        }
    }

    @FXML
    private void acaoTeclarBotaoConfirmEsc(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            acaoClicarBotaoConfirmEsc(new ActionEvent());
        }
    }
}
