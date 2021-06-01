package turtletatics.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javax.swing.JOptionPane;
import turtletatics.classesJogo.funcionalidades.Jogador;
import turtletatics.classesJogo.funcionalidades.Main;
import turtletatics.classesJogo.personagens.Personagem;

public class TelaBatalhaController implements Initializable {

    static final int TAM_CASA = 60;
    static final int TAM_PERS_CASA = 55;
    static final int ESP_CASA_PERS = (TAM_CASA - TAM_PERS_CASA) / 2;
    static final int ESP_SUP_TAB = (Main.tamTabuleiro == 12) ? 0 : 25;

    boolean ehVezDeJ1 = true;
    Personagem persSelecionado = null;
    ImageView imPersSelecionado = null;
    
    boolean acaoPosicionamento = false;

    @FXML
    private Label labelJogadorAtual;
    @FXML
    private AnchorPane panePrincipal;

    boolean estahOcupado(int x, int y) {
        for(Personagem p : Main.j1.getPersonagens()) {
            if(p.getX() == x && p.getY() == y) {
                return true;
            }
        }
        for(Personagem p : Main.j2.getPersonagens()) {
            if(p.getX() == x && p.getY() == y) {
                return true;
            }
        }
        return false;
    }
    
    void resetarTela() {
        acaoPosicionamento = false;
        
        imPersSelecionado = null;
        persSelecionado = null;
        ArrayList<ImageView> persJogAtual;
        ArrayList<ImageView> persJogProxRodada;

        if (ehVezDeJ1) {
            persJogAtual = Main.persJ1;
            persJogProxRodada = Main.persJ2;
            labelJogadorAtual.setText(Main.j1.getNome() + ", escolha um personagem e uma ação");
        } else {
            persJogAtual = Main.persJ2;
            persJogProxRodada = Main.persJ1;
            labelJogadorAtual.setText(Main.j2.getNome() + ", escolha um personagem e uma ação");
        }
        
        for(int i = 0; i < Main.tamTabuleiro; i++) {
            for(int j = 0; j < Main.tamTabuleiro; j++) {
                Main.tabuleiro[i][j].setOpacity(1);
            }
        }
        
        for(ImageView im : persJogAtual) {
            im.disableProperty().set(false);
            im.setOpacity(1);
        }
        for(ImageView im : persJogProxRodada) {
            im.disableProperty().set(true);
            im.setOpacity(0.3);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        panePrincipal.setPrefWidth(450 + TAM_CASA * (Main.tamTabuleiro - 1) + 80);
        panePrincipal.setPrefHeight(ESP_SUP_TAB + TAM_CASA * (Main.tamTabuleiro - 1) + 80);

        labelJogadorAtual.setText(Main.j1.getNome() + ", escolha um personagem e uma ação");

        //Insere o tabuleiro na tela e cria o método de clicar em uma casa
        for (int i = 0; i < Main.tamTabuleiro; i++) {
            for (int j = 0; j < Main.tamTabuleiro; j++) {
                Main.tabuleiro[i][j].setX(450 + i * TAM_CASA);

                panePrincipal.getChildren().add(Main.tabuleiro[i][j]);
                
                Rectangle casa = Main.tabuleiro[i][j];
                int x = i;
                int y = j;
                Main.tabuleiro[i][j].setOnMouseClicked(event -> {
                    //Método de clicar...
                    
                    if(casa.getOpacity() == 1 && acaoPosicionamento) {
                        if(estahOcupado(x, y)) {
                            JOptionPane.showMessageDialog(null, "A posião já está ocupada", "Erro", 0);
                            return;
                        }
                        
                        persSelecionado.setX(x);
                        persSelecionado.setY(y);
                        
                        imPersSelecionado.setLayoutX(casa.getX() + ESP_CASA_PERS);
                        imPersSelecionado.setLayoutY(casa.getY() + ESP_CASA_PERS);
                        
                        ehVezDeJ1 = !ehVezDeJ1;
                        resetarTela();
                    }
                });

            }
        }

        //Insere os personagens de J1 na tela e cria o método de clicar neles
        for (Personagem p : Main.j1.getPersonagens()) {
            p.getImagem().setLayoutX(Main.tabuleiro[p.getX()][p.getY()].getX() + ESP_CASA_PERS);
            p.getImagem().setLayoutY(Main.tabuleiro[p.getX()][p.getY()].getY() + ESP_CASA_PERS);

            panePrincipal.getChildren().add(p.getImagem());

            p.getImagem().setOnMouseClicked(event -> {
                //Método de clicar...
                
                resetarTela();
                p.getImagem().setOpacity(0.5);
                imPersSelecionado = p.getImagem();
            });
        }
        //Insere os personagens de J2 na tela e cria o método de clicar neles
        for (Personagem p : Main.j2.getPersonagens()) {
            p.getImagem().setLayoutX(Main.tabuleiro[p.getX()][p.getY()].getX() + ESP_CASA_PERS);
            p.getImagem().setLayoutY(Main.tabuleiro[p.getX()][p.getY()].getY() + ESP_CASA_PERS);

            panePrincipal.getChildren().add(p.getImagem());

            p.getImagem().setOnMouseClicked(event -> {
                //Método de clicar...
                
                resetarTela();
                p.getImagem().setOpacity(0.5);
                imPersSelecionado = p.getImagem();
            });
        }

        resetarTela();
    }
    
    @FXML
    private void acaoClicarBotaoMovimentar(ActionEvent event) {
        if(imPersSelecionado == null) {
            JOptionPane.showMessageDialog(null, "Nenhum personagem selecionado", "Erro", 0);
            return;
        }
        
        Jogador jogAtual = (ehVezDeJ1) ? Main.j1 : Main.j2;
        for(Personagem p : jogAtual.getPersonagens()) {
            if(p.getImagem().equals(imPersSelecionado)) {
                persSelecionado = p;
                break;
            }
        }
        
        for(int i = 0; i < Main.tamTabuleiro; i++) {
            for(int j = 0; j < Main.tamTabuleiro; j++) {
                if(Main.calcularDistancia(persSelecionado.getX(), persSelecionado.getY(), i, j) > persSelecionado.getAlcance()) {
                    Main.tabuleiro[i][j].setOpacity(0.5);
                }
            }
        }
        acaoPosicionamento = true;
        JOptionPane.showMessageDialog(null, "Selecione a nova casa", "Movimentar personagem", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    private void acaoClicarBotaoAtqBas(ActionEvent event) {
        if(imPersSelecionado == null) {
            JOptionPane.showMessageDialog(null, "Nenhum personagem selecionado", "Erro", 0);
            return;
        }
    }

    @FXML
    private void acaoClicarBotaoAtqEsp(ActionEvent event) {
        if(imPersSelecionado == null) {
            JOptionPane.showMessageDialog(null, "Nenhum personagem selecionado", "Erro", 0);
            return;
        }
    }

}
