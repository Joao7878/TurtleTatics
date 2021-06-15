package turtletatics.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import turtletatics.classesJogo.funcionalidades.*;
import turtletatics.classesJogo.personagens.Personagem;
import turtletatics.classesTelas.*;

public class TelaPosicionamentoController implements Initializable {

    static final int TAM_CASA = 60; //Tamanho de cada casa do tabuleiro
    static final int TAM_PERS_CASA = 55; //Tamanho da imagem de cada personagem dentro da casa
    static final int ESP_CASA_PERS = (TAM_CASA - TAM_PERS_CASA) / 2; //Espaço entre a borda da casa e a imagem do personagem
    static final int ESP_VERT_TAB = (Main.tamTabuleiro == 12) ? 15 : 25; //Distância vertical entre o tabuleiro e as bordas do tabuleiro

    boolean ehVezDeJ1 = true;
    ImageView personagemSelecionado = null;

    @FXML
    private AnchorPane panePrincipal;
    @FXML
    private Label labelJ1;
    @FXML
    private Label labelJ2;
    @FXML
    private Label labelJogadorAtual;

    //Verifica se um personagem já está posicionado no tabuleiro
    boolean estahPosicionado(ImageView pers) {
        return pers.getFitWidth() == TAM_PERS_CASA;
    }

    //Verifica se uma casa do tabuleiro já está ocupada
    boolean estahOcupado(Rectangle casa) {
        ArrayList<ImageView> persJogadorAtual = (ehVezDeJ1) ? Main.persJ1 : Main.persJ2;

        for (ImageView p : persJogadorAtual) {
            if (p.getLayoutX() - ESP_CASA_PERS == casa.getX() && p.getLayoutY() - ESP_CASA_PERS == casa.getY()) {
                return true;
            }
        }
        for (Obstaculo o : Main.obstaculos) {
            if (casa.getOpacity() == 1 && o.getImagem().getLayoutX() - ESP_CASA_PERS == casa.getX() && o.getImagem().getLayoutY() - ESP_CASA_PERS == casa.getY()) {
                return true;
            }
        }
        return false;
    }

    void resetarTela() {
        personagemSelecionado = null;

        for (int i = 0; i < Main.tamTabuleiro; i++) {
            for (int j = 0; j < Main.tamTabuleiro; j++) {
                Main.tabuleiro[i][j].setOpacity(1);
                Main.tabuleiro[i][j].toBack();
            }
        }

        ArrayList<ImageView> persJogAtual;
        ArrayList<ImageView> persJogProxRodada;
        if (ehVezDeJ1) {
            persJogAtual = Main.persJ1;
            persJogProxRodada = Main.persJ2;
            labelJogadorAtual.setText(Main.j1.getNome() + ", posicione um personagem");
        } else {
            persJogAtual = Main.persJ2;
            persJogProxRodada = Main.persJ1;
            labelJogadorAtual.setText(Main.j2.getNome() + ", posicione um personagem");
        }

        for (ImageView im : persJogAtual) {
            im.setOpacity(1);
            Main.removerEfeitoClicado(im);
            im.disableProperty().set(false);
        }
        for (ImageView im : persJogProxRodada) {
            Main.removerEfeitoClicado(im);
            if (!estahPosicionado(im)) {
                im.setOpacity(0.2);
                im.disableProperty().set(true);
            }
        }
    }

    //Verifica se uma casa é selecionável
    boolean casaEhValida(Rectangle r) {
        return r.getOpacity() == 1;
    }

    boolean terminouFasePosicionamento() {
        for (ImageView p : Main.persJ1) {
            if (!estahPosicionado(p)) {
                return false;
            }
        }
        for (ImageView p : Main.persJ2) {
            if (!estahPosicionado(p)) {
                return false;
            }
        }
        return true;
    }

    void fecharTela() {
        ((Stage) panePrincipal.getScene().getWindow()).close();
    }

    void iniciarFaseBatalha() {
        TelaBatalha tela = new TelaBatalha();

        try {
            tela.start(new Stage());
            fecharTela();
        } catch (Exception ex) {
            Logger.getLogger(TelaInicialController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao tentar abrir tela", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Insere um personagem no tabuleiro
    void inserirNoTabuleiro(Rectangle casa, Jogador j, int x, int y) {
        //Busca o personagem a partir da sua imagem contida em 'personagemSelecionado'
        for (Personagem p : j.getPersonagens()) {
            if (p.getImagem().equals(personagemSelecionado)) {
                p.setX(x);
                p.setY(y);
                break;
            }
        }

        personagemSelecionado.setLayoutX(casa.getX() + ESP_CASA_PERS);
        personagemSelecionado.setLayoutY(casa.getY() + ESP_CASA_PERS);

        personagemSelecionado.fitWidthProperty().set(TAM_PERS_CASA);
        personagemSelecionado.fitHeightProperty().set(TAM_PERS_CASA);

        removerPers();
    }

    void removerPers() {
        if (personagemSelecionado != null) {
            personagemSelecionado.setOpacity(1);

            if (terminouFasePosicionamento()) {
                iniciarFaseBatalha();
            }

            ehVezDeJ1 = !ehVezDeJ1;
            resetarTela();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        panePrincipal.setPrefWidth(620 + TAM_CASA * (Main.tamTabuleiro - 1) + 80);
        panePrincipal.setPrefHeight(2 * ESP_VERT_TAB + TAM_CASA * Main.tamTabuleiro);

        labelJ1.setText("Personagens de " + Main.j1.getNome() + ":");
        labelJ2.setText("Personagens de " + Main.j2.getNome() + ":");

        labelJogadorAtual.setText(Main.j1.getNome() + ", posicione um personagem");

        //Insere os personagens de J1
        for (int i = 0; i < 5; i++) {
            ImageView im = Main.j1.getPersonagens().get(i).getImagem();
            
            im.effectProperty().set(Main.efeito);

            im.fitWidthProperty().set(109);
            im.fitHeightProperty().set(113);

            im.layoutXProperty().set(29 + i * 113);
            im.layoutYProperty().set(125);

            Main.persJ1.add(im);
            panePrincipal.getChildren().add(im);
        }

        //Insere os personagens de J2
        for (int i = 0; i < 5; i++) {
            ImageView im = Main.j2.getPersonagens().get(i).getImagem();
            
            im.effectProperty().set(Main.efeito);

            im.fitWidthProperty().set(109);
            im.fitHeightProperty().set(113);

            im.layoutXProperty().set(29 + i * 113);
            im.layoutYProperty().set(276);

            Main.persJ2.add(im);
            panePrincipal.getChildren().add(im);
        }

        //Insere o tabuleiro na tela
        for (int i = 0; i < Main.tamTabuleiro; i++) {
            for (int j = 0; j < Main.tamTabuleiro; j++) {
                Rectangle r = new Rectangle(620 + i * TAM_CASA, ESP_VERT_TAB + j * TAM_CASA, TAM_CASA, TAM_CASA);

                if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
                    r.setStyle("-fx-fill : red;");
                } else {
                    r.setStyle("-fx-fill : yellow;");
                }

                Main.tabuleiro[i][j] = r;
                panePrincipal.getChildren().add(r);
            }
        }

        //Inserir obstaculos
        if (Main.tamTabuleiro == 10) {
            Random r = new Random();
            int sorteio;
            Obstaculo obstaculo;
            for (int i = 2; i < 8; i++) {
                sorteio = r.nextInt(2);
                if (sorteio == 0) {
                    obstaculo = new Obstaculo(i, 3, new ImageView("turtletatics/view/imagens/Obstaculo1.png"));
                } else {
                    obstaculo = new Obstaculo(i, 3, new ImageView("turtletatics/view/imagens/Obstaculo2.png"));
                }
                obstaculo.getImagem().setLayoutX(Main.tabuleiro[i][3].getX() + ESP_CASA_PERS);
                obstaculo.getImagem().setLayoutY(Main.tabuleiro[i][3].getY() + ESP_CASA_PERS);
                obstaculo.getImagem().fitWidthProperty().set(TAM_PERS_CASA);
                obstaculo.getImagem().fitHeightProperty().set(TAM_PERS_CASA);
                Main.obstaculos.add(obstaculo);
                panePrincipal.getChildren().add(obstaculo.getImagem());

                sorteio = r.nextInt(2);
                if (sorteio == 0) {
                    obstaculo = new Obstaculo(i, 6, new ImageView("turtletatics/view/imagens/Obstaculo1.png"));
                } else {
                    obstaculo = new Obstaculo(i, 6, new ImageView("turtletatics/view/imagens/Obstaculo2.png"));
                }
                obstaculo.getImagem().setLayoutX(Main.tabuleiro[i][6].getX() + ESP_CASA_PERS);
                obstaculo.getImagem().setLayoutY(Main.tabuleiro[i][6].getY() + ESP_CASA_PERS);
                obstaculo.getImagem().fitWidthProperty().set(TAM_PERS_CASA);
                obstaculo.getImagem().fitHeightProperty().set(TAM_PERS_CASA);
                Main.obstaculos.add(obstaculo);
                panePrincipal.getChildren().add(obstaculo.getImagem());
            }
        } else if (Main.tamTabuleiro == 12) {
            Random r = new Random();
            int sorteio;
            Obstaculo obstaculo;
            for (int i = 2; i < 10; i++) {
                sorteio = r.nextInt(2);
                if (sorteio == 0) {
                    obstaculo = new Obstaculo(5, i, new ImageView("turtletatics/view/imagens/Obstaculo1.png"));
                } else {
                    obstaculo = new Obstaculo(5, i, new ImageView("turtletatics/view/imagens/Obstaculo2.png"));
                }
                obstaculo.getImagem().setLayoutX(Main.tabuleiro[5][i].getX() + ESP_CASA_PERS);
                obstaculo.getImagem().setLayoutY(Main.tabuleiro[5][i].getY() + ESP_CASA_PERS);
                obstaculo.getImagem().fitWidthProperty().set(TAM_PERS_CASA);
                obstaculo.getImagem().fitHeightProperty().set(TAM_PERS_CASA);
                Main.obstaculos.add(obstaculo);
                panePrincipal.getChildren().add(obstaculo.getImagem());

                sorteio = r.nextInt(2);
                if (sorteio == 0) {
                    obstaculo = new Obstaculo(6, i, new ImageView("turtletatics/view/imagens/Obstaculo1.png"));
                } else {
                    obstaculo = new Obstaculo(6, i, new ImageView("turtletatics/view/imagens/Obstaculo2.png"));
                }
                obstaculo.getImagem().setLayoutX(Main.tabuleiro[6][i].getX() + ESP_CASA_PERS);
                obstaculo.getImagem().setLayoutY(Main.tabuleiro[6][i].getY() + ESP_CASA_PERS);
                obstaculo.getImagem().fitWidthProperty().set(TAM_PERS_CASA);
                obstaculo.getImagem().fitHeightProperty().set(TAM_PERS_CASA);
                Main.obstaculos.add(obstaculo);
                panePrincipal.getChildren().add(obstaculo.getImagem());

                sorteio = r.nextInt(2);
                if (sorteio == 0) {
                    obstaculo = new Obstaculo(i, 5, new ImageView("turtletatics/view/imagens/Obstaculo1.png"));
                } else {
                    obstaculo = new Obstaculo(i, 5, new ImageView("turtletatics/view/imagens/Obstaculo2.png"));
                }
                obstaculo.getImagem().setLayoutX(Main.tabuleiro[i][5].getX() + ESP_CASA_PERS);
                obstaculo.getImagem().setLayoutY(Main.tabuleiro[i][5].getY() + ESP_CASA_PERS);
                obstaculo.getImagem().fitWidthProperty().set(TAM_PERS_CASA);
                obstaculo.getImagem().fitHeightProperty().set(TAM_PERS_CASA);
                Main.obstaculos.add(obstaculo);
                panePrincipal.getChildren().add(obstaculo.getImagem());

                sorteio = r.nextInt(2);
                if (sorteio == 0) {
                    obstaculo = new Obstaculo(i, 6, new ImageView("turtletatics/view/imagens/Obstaculo1.png"));
                } else {
                    obstaculo = new Obstaculo(i, 6, new ImageView("turtletatics/view/imagens/Obstaculo2.png"));
                }
                obstaculo.getImagem().setLayoutX(Main.tabuleiro[i][6].getX() + ESP_CASA_PERS);
                obstaculo.getImagem().setLayoutY(Main.tabuleiro[i][6].getY() + ESP_CASA_PERS);
                obstaculo.getImagem().fitWidthProperty().set(TAM_PERS_CASA);
                obstaculo.getImagem().fitHeightProperty().set(TAM_PERS_CASA);
                Main.obstaculos.add(obstaculo);
                panePrincipal.getChildren().add(obstaculo.getImagem());
            }
        }

        resetarTela();

        //Criar o método de clicar em um personagem de J1
        for (ImageView per : Main.persJ1) {
            per.setOnMouseClicked(event -> {
                if (estahPosicionado(per)) {
                    return;
                }

                resetarTela();
                Main.aplicarEfeitoClicado(per);
                personagemSelecionado = per;

                for (int j = Main.tamTabuleiro / 2 - 1; j < Main.tamTabuleiro; j++) {
                    for (int i = 0; i < Main.tamTabuleiro; i++) {
                        Main.tabuleiro[i][j].setOpacity(0.5);
                    }
                }
            });
        }

        //Criar o método de clicar em um personagem de J2
        for (ImageView per : Main.persJ2) {
            per.setOnMouseClicked(event -> {
                if (estahPosicionado(per)) {
                    return;
                }

                resetarTela();
                Main.aplicarEfeitoClicado(per);
                personagemSelecionado = per;

                for (int j = 0; j < Main.tamTabuleiro / 2 + 1; j++) {
                    for (int i = 0; i < Main.tamTabuleiro; i++) {
                        Main.tabuleiro[i][j].setOpacity(0.5);
                    }
                }
            });
        }

        //Criar o método de clicar em uma casa do tabuleiro
        for (int i = 0; i < Main.tamTabuleiro; i++) {
            for (int j = 0; j < Main.tamTabuleiro; j++) {
                Rectangle r = Main.tabuleiro[i][j];
                int x = i;
                int y = j;

                Main.tabuleiro[i][j].setOnMouseClicked(event -> {
                    if (estahOcupado(r)) {
                        JOptionPane.showMessageDialog(null, "A posição já está ocupada", "Erro", 0);
                        return;
                    } else if (personagemSelecionado == null) {
                        JOptionPane.showMessageDialog(null, "Nenhum personagem selecionado", "Erro", 0);
                        return;
                    }

                    if (casaEhValida(r)) {
                        if (ehVezDeJ1) {
                            inserirNoTabuleiro(r, Main.j1, x, y);
                        } else {
                            inserirNoTabuleiro(r, Main.j2, x, y);
                        }
                    }
                });
            }
        }
        resetarTela();
    }
}
