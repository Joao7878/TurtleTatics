package turtletatics.controller;

import java.net.URL;
import java.util.ArrayList;
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

import turtletatics.classesTelas.*;

public class TelaPosicionamentoController implements Initializable {

    static final int TAM_CASA = 70;
    static final int TAM_PERS_CASA = 60;
    static final int ESP_CASA_PERS = (TAM_CASA - TAM_PERS_CASA) / 2;
    
    boolean ehVezDeJ1 = true;
    Rectangle[][] tabuleiro = new Rectangle[TelaPosicionamento.tamTabuleiro][TelaPosicionamento.tamTabuleiro];
    ArrayList<ImageView> persJ1 = new ArrayList<ImageView>();
    ArrayList<ImageView> persJ2 = new ArrayList<ImageView>();
    ImageView personagemSelecionado = null;

    @FXML
    private AnchorPane panePrincipal;
    @FXML
    private Label labelJ1;
    @FXML
    private Label labelJ2;
    @FXML
    private Label labelJogadorAtual;

    boolean estahPosicionado(ImageView pers) {
        return pers.getFitWidth() == TAM_PERS_CASA;
    }

    boolean estahOcupado(Rectangle casa) {
        if (ehVezDeJ1) {
            for (ImageView p : persJ1) {
                if (p.getLayoutX() - ESP_CASA_PERS == casa.getX() && p.getLayoutY() - ESP_CASA_PERS == casa.getY()) {
                    return true;
                }
            }
            return false;
        } else {
            for (ImageView p : persJ2) {
                if (p.getLayoutX() - ESP_CASA_PERS == casa.getX() && p.getLayoutY() - ESP_CASA_PERS == casa.getY()) {
                    return true;
                }
            }
            return false;
        }
    }

    void resetarTela() {
        personagemSelecionado = null;

        for (int i = 0; i < TelaPosicionamento.tamTabuleiro; i++) {
            for (int j = 0; j < TelaPosicionamento.tamTabuleiro; j++) {
                tabuleiro[i][j].setOpacity(1);
                tabuleiro[i][j].toBack();
            }
        }

        if (ehVezDeJ1) {
            labelJogadorAtual.setText(TelaPosicionamento.j1.getNome() + ", posicione um personagem");

            for (ImageView im : persJ1) {
                im.setOpacity(1);
                im.disableProperty().set(false);
            }
            for (ImageView im : persJ2) {
                if (!estahPosicionado(im)) {
                    im.setOpacity(0.2);
                    im.disableProperty().set(true);
                }
            }
        } else {
            labelJogadorAtual.setText(TelaPosicionamento.j2.getNome() + ", posicione um personagem");

            for (ImageView im : persJ2) {
                im.setOpacity(1);
                im.disableProperty().set(false);
            }
            for (ImageView im : persJ1) {
                if (!estahPosicionado(im)) {
                    im.setOpacity(0.2);
                    im.disableProperty().set(true);
                }

            }
        }
    }

    boolean casaEhValida(Rectangle casa) {
        return casa.getOpacity() == 1;
    }

    boolean terminouFasePosicionamento() {
        for (ImageView p : persJ1) {
            if (!estahPosicionado(p)) {
                return false;
            }
        }
        for (ImageView p : persJ2) {
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

        labelJ1.setText("Personagens de " + TelaPosicionamento.j1.getNome() + ":");
        labelJ2.setText("Personagens de " + TelaPosicionamento.j2.getNome() + ":");

        labelJogadorAtual.setText(TelaEscolhaPersonagens.j1.getNome() + ", posicione um personagem");

        //Insere os personagens de J1
        for (int i = 0; i < 5; i++) {
            ImageView im = new ImageView(TelaPosicionamento.j1.getPersonagens().get(i).getImagem());

            im.fitWidthProperty().set(109);
            im.fitHeightProperty().set(113);

            im.layoutXProperty().set(29 + i * 113);
            im.layoutYProperty().set(125);

            persJ1.add(im);
            panePrincipal.getChildren().add(im);
        }

        //Insere os personagens de J2
        for (int i = 0; i < 5; i++) {
            ImageView im = new ImageView(TelaPosicionamento.j2.getPersonagens().get(i).getImagem());

            im.fitWidthProperty().set(109);
            im.fitHeightProperty().set(113);

            im.layoutXProperty().set(29 + i * 113);
            im.layoutYProperty().set(276);

            persJ2.add(im);
            panePrincipal.getChildren().add(im);
        }

        //Insere o tabuleiro na tela
        for (int i = 0; i < TelaPosicionamento.tamTabuleiro; i++) {
            for (int j = 0; j < TelaPosicionamento.tamTabuleiro; j++) {
                Rectangle r = new Rectangle(620 + i * TAM_CASA, 108 + j * TAM_CASA, TAM_CASA, TAM_CASA);

                if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
                    r.setStyle("-fx-fill : red;");
                } else {
                    r.setStyle("-fx-fill : yellow;");
                }

                tabuleiro[i][j] = r;
                panePrincipal.getChildren().add(r);
            }
        }

        //Criar o método de clicar em um personagem de J1
        for (ImageView per : persJ1) {
            per.setOnMouseClicked(event -> {
                if (estahPosicionado(per)) {
                    return;
                }

                resetarTela();
                per.setOpacity(0.5);
                personagemSelecionado = per;

                for (int j = TelaPosicionamento.tamTabuleiro / 2 - 1; j < TelaPosicionamento.tamTabuleiro; j++) {
                    for (int i = 0; i < TelaPosicionamento.tamTabuleiro; i++) {
                        tabuleiro[i][j].setOpacity(0.5);
                    }
                }
            });
        }

        //Criar o método de clicar em um personagem de J2
        for (ImageView per : persJ2) {
            per.setOnMouseClicked(event -> {
                if (estahPosicionado(per)) {
                    return;
                }

                resetarTela();
                per.setOpacity(0.5);
                personagemSelecionado = per;

                for (int j = 0; j < TelaPosicionamento.tamTabuleiro / 2 + 1; j++) {
                    for (int i = 0; i < TelaPosicionamento.tamTabuleiro; i++) {
                        tabuleiro[i][j].setOpacity(0.5);
                    }
                }
            });
        }

        //Criar o método de clicar em uma casa do tabuleiro
        for (int i = 0; i < TelaPosicionamento.tamTabuleiro; i++) {
            for (int j = 0; j < TelaPosicionamento.tamTabuleiro; j++) {
                Rectangle r = tabuleiro[i][j];
                tabuleiro[i][j].setOnMouseClicked(event -> {
                    if (!estahOcupado(r) && casaEhValida(r) && personagemSelecionado != null) {
                        personagemSelecionado.setLayoutX(r.getX() + ESP_CASA_PERS);
                        personagemSelecionado.setLayoutY(r.getY() + ESP_CASA_PERS);

                        personagemSelecionado.fitWidthProperty().set(TAM_PERS_CASA);
                        personagemSelecionado.fitHeightProperty().set(TAM_PERS_CASA);

                        removerPers();
                    }
                });
            }
        }

        resetarTela();
    }
}
