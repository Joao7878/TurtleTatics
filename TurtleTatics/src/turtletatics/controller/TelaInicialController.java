
package turtletatics.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import turtletatics.classesJogo.funcionalidades.*;
import turtletatics.classesTelas.*;

public class TelaInicialController implements Initializable {
    
    @FXML
    private javafx.scene.control.Button botaoRegras;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void fecharTela() {
        ((Stage) botaoRegras.getScene().getWindow()).close();
    }
    
    public boolean abrirTelaRegras() {
        TelaRegras tela = new TelaRegras();
        try {
            tela.start(new Stage());
            return true;
        } catch(Exception ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean jogadorEhValido(Jogador j) {
        if(j.getNome() == null) { //Clicou no botão cancelar ou X para fechar a aba
            return false;
        } else if(j.getNome().equals("")) { //Não digitou nada no nome
            JOptionPane.showMessageDialog(null, "Campo não preenchido", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    public boolean dadosEstaoCorretas(String nomeJ1, String nomeJ2, int tamTabuleiro) {
        int resposta = JOptionPane.showConfirmDialog(null,
                "Os dados estão corretos?\n" +
                "Nome do 1º jogador..: " + nomeJ1 + "\n" +
                "Nome do 2º jogador..: " + nomeJ2 + "\n" +
                "Tamanho do tabuleiro: " + tamTabuleiro + "x" + tamTabuleiro,
                "Confirmação de dados", JOptionPane.YES_NO_OPTION);
        
        return resposta == 0;
    }
    
    public void iniciarFasePosicionamento() throws IOException {
        Jogador j1 = new Jogador(JOptionPane.showInputDialog(null, "Insira o nome do 1º jogador", "Inserir nome", JOptionPane.INFORMATION_MESSAGE));
        if(!jogadorEhValido(j1)) return;
        Jogador j2 = new Jogador(JOptionPane.showInputDialog(null, "Insira o nome do 2º jogador", "Inserir nome", JOptionPane.INFORMATION_MESSAGE));
        if(!jogadorEhValido(j2)) {
            return;
        } else if(j2.getNome().equals(j1.getNome())) {
            JOptionPane.showMessageDialog(null, "O nome dos jogadores não podem ser iguais", "Erro", 0);
            return;
        }
        
        String[] opcsTamMapa = {"8x8", "10x10", "12x12"};
        int tamEscolhido = JOptionPane.showOptionDialog(null, "Selecione o tamanho do mapa",
                "Tamanho do mapa", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, opcsTamMapa, opcsTamMapa[0]);
        if(tamEscolhido == -1) {
            return;
        } else {
            switch (tamEscolhido) {
                case 0:
                    tamEscolhido = 8;
                    break;
                case 1:
                    tamEscolhido = 10;
                    break;
                case 2:
                    tamEscolhido = 12;
                    break;
            }
        }
        Tabuleiro tabuleiro = new Tabuleiro(tamEscolhido, tamEscolhido);
        
        if(!dadosEstaoCorretas(j1.getNome(), j2.getNome(), tamEscolhido)) return;
        
        TelaEscolhaPersonagens tela;
        Random gerador = new Random();
        int sorteio = gerador.nextInt();
        if (sorteio % 2 == 0) {// Jogador 1 foi sorteado o 1º a jogar
            tela = new TelaEscolhaPersonagens(j1, j2, tamEscolhido);
            JOptionPane.showMessageDialog(null, j1.getNome() + " foi sorteado como 1º a jogar", "Sorteio", JOptionPane.INFORMATION_MESSAGE);
        } else {// Jogador 2 foi sorteado o 1º a jogar
            tela = new TelaEscolhaPersonagens(j2, j1, tamEscolhido);
            JOptionPane.showMessageDialog(null, j2.getNome() + " foi sorteado como 1º a jogar", "Sorteio", JOptionPane.INFORMATION_MESSAGE);
        }
        
        try {
            tela.start(new Stage());
            fecharTela();
        } catch(Exception ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao tentar abrir tela", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void acaoClicarBotaoRegras(javafx.event.ActionEvent event) {
        if(abrirTelaRegras()) fecharTela();
        else JOptionPane.showMessageDialog(null, "Falha ao tentar abrir tela", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    @FXML
    private void acaoTeclarBotaoRegras(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            if(abrirTelaRegras()) fecharTela();
            else JOptionPane.showMessageDialog(null, "Falha ao tentar abrir tela", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void acaoClicarBotaoSair(ActionEvent event) {
        fecharTela();
    }

    @FXML
    private void acaoTeclarBotaoSair(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            fecharTela();
        }
    }

    @FXML
    private void acaoClicarBotaoJogar(ActionEvent event) throws IOException {
        iniciarFasePosicionamento();
    }
    
    @FXML
    private void acaoTeclarBotaoJogar(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER) {
            iniciarFasePosicionamento();
        }
    }
    
}
