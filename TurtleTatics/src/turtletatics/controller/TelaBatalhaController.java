package turtletatics.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javax.swing.JOptionPane;

import turtletatics.classesJogo.funcionalidades.*;
import turtletatics.classesJogo.personagens.*;
import static turtletatics.controller.TelaPosicionamentoController.*;

public class TelaBatalhaController implements Initializable {

    boolean ehVezDeJ1 = true;
    Personagem persSelecionado = null;
    Personagem persAtacado = null;
    ImageView imPersSelecionado = null;

    boolean acaoPosicionamento = false;
    boolean acaoAtqBasico = false;
    boolean acaoAtqEspecialCarCav = false;
    boolean acaoAtqEspecialCozCon = false;
    boolean acaoAtqEspecialEngEscAliado = false;
    boolean acaoAtqEspecialEngEscInimigo = false;
    boolean acaoAtqEspecialCom = false;
    boolean acaoAtqEspecialPad = false;
    boolean especialEspiao = false;
    boolean acaoAtqEspecialCie = false;

    @FXML
    private Label labelJogadorAtual;
    @FXML
    private AnchorPane panePrincipal;

    //Verifica se uma casa está ocupada
    boolean estahOcupado(int x, int y) {
        for (Personagem p : Main.j1.getPersonagens()) {
            if (p.getX() == x && p.getY() == y) {
                return true;
            }
        }
        for (Personagem p : Main.j2.getPersonagens()) {
            if (p.getX() == x && p.getY() == y) {
                return true;
            }
        }
        return false;
    }

    void resetarTabuleiro() {
        for (int i = 0; i < Main.tamTabuleiro; i++) {
            for (int j = 0; j < Main.tamTabuleiro; j++) {
                Main.tabuleiro[i][j].setOpacity(1);
            }
        }
    }

    void resetarTela() {
        acaoPosicionamento = false;
        acaoAtqBasico = false;
        acaoAtqEspecialCarCav = false;
        acaoAtqEspecialCozCon = false;
        acaoAtqEspecialEngEscAliado = false;
        acaoAtqEspecialEngEscInimigo = false;
        acaoAtqEspecialCom = false;
        acaoAtqEspecialPad = false;
        acaoAtqEspecialCie = false;

        imPersSelecionado = null;
        persAtacado = null;
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

        resetarTabuleiro();

        for (ImageView im : persJogAtual) {
            im.disableProperty().set(false);
            im.setOpacity(1);
        }
        for (ImageView im : persJogProxRodada) {
            im.disableProperty().set(true);
            im.setOpacity(0.3);
        }
    }

    //Retorna o personagem cujo atributo 'imagem' é 'imPersonagem'
    Personagem obterPersonagem(ImageView imPersonagem, Jogador jogador) {
        for (Personagem p : jogador.getPersonagens()) {
            if (imPersonagem.equals(p.getImagem())) {
                return p;
            }
        }
        return null;
    }

    boolean terminouBatalha() {
        //...
        return false;
    }

    //Atualiza os dados da partida após um turno se passar
    void atualizarDados() {
        for (Personagem p : Main.j1.getPersonagens()) {
            if (p.getCargaEspecial() != 0) {
                p.setCargaEspecial(p.getCargaEspecial() - 1);
            }
        }
        for (Personagem p : Main.j2.getPersonagens()) {
            if (p.getCargaEspecial() != 0) {
                p.setCargaEspecial(p.getCargaEspecial() - 1);
            }
        }
        if (Main.explosao.getX() != -1) {
            Main.explosao.darDanoArea();

            if (terminouBatalha()) {
                //...
            }
        }
        if (especialEspiao) {
            ehVezDeJ1 = !ehVezDeJ1;
            especialEspiao = false;
        }
    }

    void iniciarMetodosPersJ1() {
        for (Personagem p : Main.j1.getPersonagens()) {
            p.getImagem().setOnMouseClicked(event -> {
                //Ação de ser atacado por ataque básico
                if (!ehVezDeJ1 && p.getImagem().getOpacity() == 1 && acaoAtqBasico) {
                    ataqueBasico(p);
                    return;

                    //Ação de ser atacado por ataque especial de carcereiro ou cavaleiro
                } else if (!ehVezDeJ1 && p.getImagem().getOpacity() == 1 && acaoAtqEspecialCarCav) {
                    ataqueEspecialCarCav(p);
                    return;

                    //Ação de utilizar o ataque especial do cozinheiro ou construtor
                } else if (ehVezDeJ1 && p.getImagem().getOpacity() == 1 && acaoAtqEspecialCozCon) {
                    ataqueEspecialCozCon(p);
                    return;

                    //Ação de ser atacado por ataque especial do engenheiro
                } else if (!ehVezDeJ1 && p.getImagem().getOpacity() == 1 && acaoAtqEspecialEngEscInimigo) {
                    persAtacado = p;
                    acaoAtqEspecialEngEscInimigo = false;
                    acaoAtqEspecialEngEscAliado = true;

                    for (ImageView im : Main.persJ1) {
                        im.setOpacity(0.2);
                        im.disableProperty().set(true);
                    }
                    for (ImageView im : Main.persJ2) {
                        im.setOpacity(1);
                        im.disableProperty().set(false);
                    }

                    JOptionPane.showMessageDialog(null, "Selecione um aliado para receber a vida roubada", "Ataque especial", JOptionPane.INFORMATION_MESSAGE);
                    return;

                    //Ação de receber buff do ataque especial do engenheiro
                } else if (ehVezDeJ1 && p.getImagem().getOpacity() == 1 && acaoAtqEspecialEngEscAliado) {
                    ataqueEspecialEng(persAtacado, p);
                    return;

                    //Ação de ser atacado por ataque especial do comandante
                } else if (!ehVezDeJ1 && p.getImagem().getOpacity() == 1 && acaoAtqEspecialCom) {
                    ataqueEspecialCom(p);
                    return;

                    //Ação de ser atacado por ataque especial do padre
                } else if (!ehVezDeJ1 && p.getImagem().getOpacity() == 1 && acaoAtqEspecialPad) {
                    ataqueEspecialPad(p, Main.j2, Main.j1);
                    return;
                    
                   //Ação de ser atacado por ataque especial do cientista maluco
                } else if (!ehVezDeJ1 && p.getImagem().getOpacity() == 1 && acaoAtqEspecialCie) {
                    ataqueEspecialCie(p);
                    return;
                }

                resetarTela();
                p.getImagem().setOpacity(0.5);
                imPersSelecionado = p.getImagem();
            });
        }
    }

    void iniciarMetodosPersJ2() {
        for (Personagem p : Main.j2.getPersonagens()) {
            p.getImagem().setOnMouseClicked(event -> {
                //Ação de ser atacado por ataque básico
                if (ehVezDeJ1 && p.getImagem().getOpacity() == 1 && acaoAtqBasico) {
                    ataqueBasico(p);
                    return;

                    //Ação de ser atacado por ataque especial de carcereiro ou cavaleiro
                } else if (ehVezDeJ1 && p.getImagem().getOpacity() == 1 && acaoAtqEspecialCarCav) {
                    ataqueEspecialCarCav(p);
                    return;

                    //Ação de utilizar o ataque especial do cozinheiro ou construtor
                } else if (!ehVezDeJ1 && p.getImagem().getOpacity() == 1 && acaoAtqEspecialCozCon) {
                    ataqueEspecialCozCon(p);
                    return;

                    //Ação de ser atacado por ataque especial do engenheiro
                } else if (ehVezDeJ1 && p.getImagem().getOpacity() == 1 && acaoAtqEspecialEngEscInimigo) {
                    persAtacado = p;
                    acaoAtqEspecialEngEscInimigo = false;
                    acaoAtqEspecialEngEscAliado = true;

                    for (ImageView im : Main.persJ2) {
                        im.setOpacity(0.2);
                        im.disableProperty().set(true);
                    }
                    for (ImageView im : Main.persJ1) {
                        im.setOpacity(1);
                        im.disableProperty().set(false);
                    }

                    JOptionPane.showMessageDialog(null, "Selecione um aliado para receber a vida roubada", "Ataque especial", JOptionPane.INFORMATION_MESSAGE);
                    return;

                    //Ação de receber buff do ataque especial do engenheiro
                } else if (!ehVezDeJ1 && p.getImagem().getOpacity() == 1 && acaoAtqEspecialEngEscAliado) {
                    ataqueEspecialEng(persAtacado, p);
                    return;

                    //Ação de ser atacado por ataque especial do comandante
                } else if (ehVezDeJ1 && p.getImagem().getOpacity() == 1 && acaoAtqEspecialCom) {
                    ataqueEspecialCom(p);
                    return;

                    //Ação de ser atacado por ataque especial do padre
                } else if (ehVezDeJ1 && p.getImagem().getOpacity() == 1 && acaoAtqEspecialPad) {
                    ataqueEspecialPad(p, Main.j1, Main.j2);
                    return;
                    
                    //Ação de ser atacado por ataque especial do cientista maluco
                } else if (ehVezDeJ1 && p.getImagem().getOpacity() == 1 && acaoAtqEspecialCie) {
                    ataqueEspecialCie(p);
                    return;
                }

                resetarTela();
                p.getImagem().setOpacity(0.5);
                imPersSelecionado = p.getImagem();
            });
        }
    }

    void ataqueBasico(Personagem pAtacado) {
        Jogador jogAtacado = (ehVezDeJ1) ? Main.j1 : Main.j2;
        ArrayList<ImageView> imPersAtacado = (ehVezDeJ1) ? Main.persJ1 : Main.persJ2;

        persSelecionado.atkBasico(pAtacado);

        JOptionPane.showMessageDialog(null, persSelecionado.getNome() + " atacou " + pAtacado.getNome(), pAtacado.getNome() + " atacado", JOptionPane.INFORMATION_MESSAGE);
        if (pAtacado.getQuantVital() > 0) {
            JOptionPane.showMessageDialog(null, pAtacado.getNome() + " ficou com " + pAtacado.getQuantVital() + " pontos de quantidade vital", pAtacado.getNome() + " atacado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, pAtacado.getNome() + " morreu", pAtacado.getNome() + " atacado", JOptionPane.INFORMATION_MESSAGE);
            pAtacado.getImagem().setVisible(false);
            pAtacado.getImagem().disableProperty().set(true);
            imPersAtacado.remove(pAtacado.getImagem());
            jogAtacado.getPersonagens().remove(pAtacado);

            if (terminouBatalha()) {
                //...
            }
        }
        ehVezDeJ1 = !ehVezDeJ1;
        atualizarDados();
        resetarTela();
    }

    //Ataque especial dos personagens que dão dano em um inimigo ao alcance
    void ataqueEspecialCarCav(Personagem pAtacado) {
        Jogador jogAtacado = (ehVezDeJ1) ? Main.j1 : Main.j2;
        ArrayList<ImageView> imPersAtacado = (ehVezDeJ1) ? Main.persJ1 : Main.persJ2;

        persSelecionado.atkEspecial(pAtacado);

        if (pAtacado.getQuantVital() > 0) {
            JOptionPane.showMessageDialog(null, pAtacado.getNome() + " ficou com " + pAtacado.getQuantVital() + " pontos de quantidade vital", pAtacado.getNome() + " atacado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, pAtacado.getNome() + " morreu", pAtacado.getNome() + " atacado", JOptionPane.INFORMATION_MESSAGE);
            pAtacado.getImagem().setVisible(false);
            pAtacado.getImagem().disableProperty().set(true);
            imPersAtacado.remove(pAtacado.getImagem());
            jogAtacado.getPersonagens().remove(pAtacado);

            if (terminouBatalha()) {
                //...
            }
        }
        ehVezDeJ1 = !ehVezDeJ1;
        atualizarDados();
        resetarTela();
    }

    //Ataque especial dos personagens que dão buff em aliado em qualquer posição
    void ataqueEspecialCozCon(Personagem pAliado) {
        persSelecionado.atkEspecial(pAliado);

        ehVezDeJ1 = !ehVezDeJ1;
        atualizarDados();
        resetarTela();
    }

    void ataqueEspecialEng(Personagem pAtacado, Personagem pAliado) {
        Jogador jogAtacado = (ehVezDeJ1) ? Main.j1 : Main.j2;
        ArrayList<ImageView> imPersAtacado = (ehVezDeJ1) ? Main.persJ1 : Main.persJ2;

        persSelecionado.atkEspecial(pAtacado, pAliado);

        if (pAtacado.getQuantVital() > 0) {
            JOptionPane.showMessageDialog(null, pAtacado.getNome() + " ficou com " + pAtacado.getQuantVital() + " pontos de quantidade vital", pAtacado.getNome() + " atacado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, pAtacado.getNome() + " morreu", pAtacado.getNome() + " atacado", JOptionPane.INFORMATION_MESSAGE);
            pAtacado.getImagem().setVisible(false);
            pAtacado.getImagem().disableProperty().set(true);
            imPersAtacado.remove(pAtacado.getImagem());
            jogAtacado.getPersonagens().remove(pAtacado);

            if (terminouBatalha()) {
                //...
            }
        }

        ehVezDeJ1 = !ehVezDeJ1;
        atualizarDados();
        resetarTela();
    }

    void ataqueEspecialCom(Personagem pAtacado) {
        Jogador jogAtacado = (ehVezDeJ1) ? Main.j1 : Main.j2;
        ArrayList<ImageView> imPersAtacado = (ehVezDeJ1) ? Main.persJ1 : Main.persJ2;

        persSelecionado.atkEspecial(pAtacado, Main.explosao);

        if (pAtacado.getQuantVital() > 0) {
            JOptionPane.showMessageDialog(null, pAtacado.getNome() + " ficou com " + pAtacado.getQuantVital() + " pontos de quantidade vital", pAtacado.getNome() + " atacado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, pAtacado.getNome() + " morreu", pAtacado.getNome() + " atacado", JOptionPane.INFORMATION_MESSAGE);
            pAtacado.getImagem().setVisible(false);
            pAtacado.getImagem().disableProperty().set(true);
            imPersAtacado.remove(pAtacado.getImagem());
            jogAtacado.getPersonagens().remove(pAtacado);

            if (terminouBatalha()) {
                //...
            }
        }

        for (int i = 0; i < Main.tamTabuleiro; i++) {
            for (int j = 0; j < Main.tamTabuleiro; j++) {
                if (Main.calcularDistancia(Main.explosao.getX(), Main.explosao.getY(), i, j) <= 1) {
                    if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
                        Main.tabuleiro[i][j].setStyle("-fx-fill : #16bf00;");
                    } else {
                        Main.tabuleiro[i][j].setStyle("-fx-fill : #0d6f00;");
                    }
                }
            }
        }

        ehVezDeJ1 = !ehVezDeJ1;
        atualizarDados();
        resetarTela();
    }

    void ataqueEspecialPad(Personagem pAtacado, Jogador jogAliados, Jogador jogInimigos) {
        if (persSelecionado.atkEspecial(pAtacado, jogAliados.getPersonagens(), jogInimigos.getPersonagens())) { // Conversão realizada com sucesso
            ArrayList<ImageView> persJogAliados = (ehVezDeJ1) ? Main.persJ1 : Main.persJ2;
            ArrayList<ImageView> persJogProxRodada = (ehVezDeJ1) ? Main.persJ2 : Main.persJ1;
            ImageView imPersAtacado = null;

            for (ImageView im : persJogProxRodada) {
                if (im.equals(pAtacado.getImagem())) {
                    imPersAtacado = im;
                    break;
                }
            }
            persJogProxRodada.remove(imPersAtacado);
            persJogAliados.add(imPersAtacado);
            if (ehVezDeJ1) {
                iniciarMetodosPersJ1();
            } else {
                iniciarMetodosPersJ2();
            }

            if (terminouBatalha()) {
                //...
            }
        }

        ehVezDeJ1 = !ehVezDeJ1;
        atualizarDados();
        resetarTela();
    }
    
    void ataqueEspecialCie(Personagem pAtacado) {
        Jogador jogAtacado = (ehVezDeJ1) ? Main.j1 : Main.j2;
        ArrayList<ImageView> imPersAtacado = (ehVezDeJ1) ? Main.persJ1 : Main.persJ2;

        persSelecionado.atkEspecial(pAtacado);

        if (pAtacado.getQuantVital() > 0) {
            JOptionPane.showMessageDialog(null, pAtacado.getNome() + " ficou com " + pAtacado.getQuantVital() + " pontos de quantidade vital", pAtacado.getNome() + " atacado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, pAtacado.getNome() + " morreu", pAtacado.getNome() + " atacado", JOptionPane.INFORMATION_MESSAGE);
            pAtacado.getImagem().setVisible(false);
            pAtacado.getImagem().disableProperty().set(true);
            imPersAtacado.remove(pAtacado.getImagem());
            jogAtacado.getPersonagens().remove(pAtacado);

            if (terminouBatalha()) {
                //...
            }
        }
        
        ehVezDeJ1 = !ehVezDeJ1;
        atualizarDados();
        resetarTela();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        panePrincipal.setPrefWidth(450 + TAM_CASA * (Main.tamTabuleiro - 1) + 80);
        panePrincipal.setPrefHeight(2 * ESP_VERT_TAB + TAM_CASA * Main.tamTabuleiro);

        labelJogadorAtual.setText(Main.j1.getNome() + ", escolha um personagem e uma ação");

        //Insere o tabuleiro na tela e cria o método de clicar em uma casa
        for (int i = 0; i < Main.tamTabuleiro; i++) {
            for (int j = 0; j < Main.tamTabuleiro; j++) {
                Main.tabuleiro[i][j].setX(450 + i * TAM_CASA);

                panePrincipal.getChildren().add(Main.tabuleiro[i][j]);

                Rectangle casa = Main.tabuleiro[i][j];
                int x = i;
                int y = j;
                //Método de clicar
                Main.tabuleiro[i][j].setOnMouseClicked(event -> {
                    //Ação de movimentar o personagem
                    if (casa.getOpacity() == 1 && acaoPosicionamento) {
                        if (estahOcupado(x, y)) {
                            JOptionPane.showMessageDialog(null, "A posião já está ocupada", "Erro", 0);
                            return;
                        }

                        persSelecionado.setX(x);
                        persSelecionado.setY(y);

                        imPersSelecionado.setLayoutX(casa.getX() + ESP_CASA_PERS);
                        imPersSelecionado.setLayoutY(casa.getY() + ESP_CASA_PERS);

                        ehVezDeJ1 = !ehVezDeJ1;
                        atualizarDados();
                        resetarTela();
                    }
                });

            }
        }

        //Insere os personagens de J1 na tela
        for (Personagem p : Main.j1.getPersonagens()) {
            p.getImagem().setLayoutX(Main.tabuleiro[p.getX()][p.getY()].getX() + ESP_CASA_PERS);
            p.getImagem().setLayoutY(Main.tabuleiro[p.getX()][p.getY()].getY() + ESP_CASA_PERS);

            panePrincipal.getChildren().add(p.getImagem());
        }
        //Cria o método de clicar nos personagens de J1
        iniciarMetodosPersJ1();

        //Insere os personagens de J2 na tela e cria o método de clicar neles
        for (Personagem p : Main.j2.getPersonagens()) {
            p.getImagem().setLayoutX(Main.tabuleiro[p.getX()][p.getY()].getX() + ESP_CASA_PERS);
            p.getImagem().setLayoutY(Main.tabuleiro[p.getX()][p.getY()].getY() + ESP_CASA_PERS);

            panePrincipal.getChildren().add(p.getImagem());
        }
        //Cria o método de clicar nos personagens de J2
        iniciarMetodosPersJ2();

        resetarTela();
    }

    @FXML
    private void acaoClicarBotaoMovimentar(ActionEvent event) {
        if (imPersSelecionado == null) {
            JOptionPane.showMessageDialog(null, "Nenhum personagem selecionado", "Erro", 0);
            return;
        }

        Jogador jogAtual = (ehVezDeJ1) ? Main.j1 : Main.j2;
        persSelecionado = obterPersonagem(imPersSelecionado, jogAtual);

        for (int i = 0; i < Main.tamTabuleiro; i++) {
            for (int j = 0; j < Main.tamTabuleiro; j++) {
                if (Main.calcularDistancia(persSelecionado.getX(), persSelecionado.getY(), i, j) > persSelecionado.getAlcance()) {
                    Main.tabuleiro[i][j].setOpacity(0.5);
                }
            }
        }
        acaoPosicionamento = true;
        JOptionPane.showMessageDialog(null, "Selecione a nova casa", "Movimentar personagem", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    private void acaoClicarBotaoAtqBas(ActionEvent event) {
        if (imPersSelecionado == null) {
            JOptionPane.showMessageDialog(null, "Nenhum personagem selecionado", "Erro", 0);
            return;
        }

        resetarTabuleiro();

        Jogador jogAtual = (ehVezDeJ1) ? Main.j1 : Main.j2;
        persSelecionado = obterPersonagem(imPersSelecionado, jogAtual);
        Jogador jogProxRodada = (ehVezDeJ1) ? Main.j2 : Main.j1;
        ArrayList<ImageView> persJogProxRodada = (ehVezDeJ1) ? Main.persJ2 : Main.persJ1;
        ArrayList<ImageView> persJogAtual = (ehVezDeJ1) ? Main.persJ1 : Main.persJ2;
        boolean temInimigoAoAlcance = false;
        for (ImageView im : persJogAtual) {
            im.setOpacity(0.5);
        }
        for (ImageView im : persJogProxRodada) {
            persAtacado = obterPersonagem(im, jogProxRodada);
            if (Main.calcularDistanciaPersonagens(persAtacado, persSelecionado) <= persSelecionado.getAlcance()) {
                im.setOpacity(1);
                im.disableProperty().set(false);
                temInimigoAoAlcance = true;
            }
        }

        if (!temInimigoAoAlcance) {
            JOptionPane.showMessageDialog(null, "Nenhum inimigo ao alcance para atacar", "Erro", JOptionPane.ERROR_MESSAGE);
            resetarTela();
            return;
        }

        acaoAtqBasico = true;
        JOptionPane.showMessageDialog(null, "Selecione um personagem para atacar", "Movimentar personagem", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    private void acaoClicarBotaoAtqEsp(ActionEvent event) {
        if (imPersSelecionado == null) {
            JOptionPane.showMessageDialog(null, "Nenhum personagem selecionado", "Erro", 0);
            return;
        }

        resetarTabuleiro();

        Jogador jogAtual = (ehVezDeJ1) ? Main.j1 : Main.j2;
        persSelecionado = obterPersonagem(imPersSelecionado, jogAtual);
        if (persSelecionado.getCargaEspecial() > 0) {
            JOptionPane.showMessageDialog(null, "Especial não carregado", "Erro", 0);
            return;
        } else if (persSelecionado.getCargaEspecial() < 0) {
            if (persSelecionado instanceof Comandante) {
                JOptionPane.showMessageDialog(null, "O comandante pode usar a bomba atômica apenas uma vez na partida", "Erro", 0);
            } else if (persSelecionado instanceof Padre) {
                if (persSelecionado.getCargaEspecial() == -1) {
                    JOptionPane.showMessageDialog(null, "O padre pode tentar converter o inimigo apenas 5 vezes", "Erro", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "O padre pode converter apenas um inimigo na partida", "Erro", 0);
                }

            }
            return;
        }

        if (persSelecionado instanceof Carcereiro || persSelecionado instanceof Cavaleiro) {
            Jogador jogProxRodada = (ehVezDeJ1) ? Main.j2 : Main.j1;
            ArrayList<ImageView> persJogProxRodada = (ehVezDeJ1) ? Main.persJ2 : Main.persJ1;
            ArrayList<ImageView> persJogAtual = (ehVezDeJ1) ? Main.persJ1 : Main.persJ2;
            boolean temInimigoAoAlcance = false;
            for (ImageView im : persJogAtual) {
                im.setOpacity(0.5);
            }
            for (ImageView im : persJogProxRodada) {
                persAtacado = obterPersonagem(im, jogProxRodada);
                if (Main.calcularDistanciaPersonagens(persAtacado, persSelecionado) <= persSelecionado.getAlcance()) {
                    im.setOpacity(1);
                    im.disableProperty().set(false);
                    temInimigoAoAlcance = true;
                }
            }

            if (!temInimigoAoAlcance) {
                JOptionPane.showMessageDialog(null, "Nenhum inimigo ao alcance para atacar", "Erro", JOptionPane.ERROR_MESSAGE);
                resetarTela();
                return;
            }

            acaoAtqEspecialCarCav = true;
            JOptionPane.showMessageDialog(null, "Selecione um personagem para atacar", "Ataque especial", JOptionPane.INFORMATION_MESSAGE);

        } else if (persSelecionado instanceof Cozinheiro || persSelecionado instanceof Construtor) {
            ArrayList<ImageView> persJogAtual = (ehVezDeJ1) ? Main.persJ1 : Main.persJ2;

            for (ImageView im : persJogAtual) {
                im.setOpacity(1);
            }

            acaoAtqEspecialCozCon = true;
            JOptionPane.showMessageDialog(null, "Selecione um personagem para curar", "Ataque especial", JOptionPane.INFORMATION_MESSAGE);
        } else if (persSelecionado instanceof Engenheiro) {
            ArrayList<ImageView> persJogAtual = (ehVezDeJ1) ? Main.persJ1 : Main.persJ2;
            ArrayList<ImageView> persJogProxRodada = (ehVezDeJ1) ? Main.persJ2 : Main.persJ1;

            for (ImageView im : persJogAtual) {
                im.setOpacity(0.5);
                //im.disableProperty().set(true);
            }
            for (ImageView im : persJogProxRodada) {
                im.setOpacity(1);
                im.disableProperty().set(false);
            }

            acaoAtqEspecialEngEscInimigo = true;
            JOptionPane.showMessageDialog(null, "Selecione um inimigo para roubar vida", "Ataque especial", JOptionPane.INFORMATION_MESSAGE);
        } else if (persSelecionado instanceof Comandante) {
            ArrayList<ImageView> persJogAtual = (ehVezDeJ1) ? Main.persJ1 : Main.persJ2;
            ArrayList<ImageView> persJogProxRodada = (ehVezDeJ1) ? Main.persJ2 : Main.persJ1;

            for (ImageView im : persJogAtual) {
                im.setOpacity(0.5);
                //im.disableProperty().set(true);
            }
            for (ImageView im : persJogProxRodada) {
                im.setOpacity(1);
                im.disableProperty().set(false);
            }

            acaoAtqEspecialCom = true;
            JOptionPane.showMessageDialog(null, "Selecione um inimigo para receber a explosão atômica", "Ataque especial", JOptionPane.INFORMATION_MESSAGE);
        } else if (persSelecionado instanceof Padre) {
            Jogador jogProxRodada = (ehVezDeJ1) ? Main.j2 : Main.j1;
            ArrayList<ImageView> persJogProxRodada = (ehVezDeJ1) ? Main.persJ2 : Main.persJ1;
            ArrayList<ImageView> persJogAtual = (ehVezDeJ1) ? Main.persJ1 : Main.persJ2;
            boolean temInimigoAoAlcance = false;
            for (ImageView im : persJogAtual) {
                im.setOpacity(0.5);
            }
            for (ImageView im : persJogProxRodada) {
                persAtacado = obterPersonagem(im, jogProxRodada);
                if (Main.calcularDistanciaPersonagens(persAtacado, persSelecionado) <= persSelecionado.getAlcance()) {
                    im.setOpacity(1);
                    im.disableProperty().set(false);
                    temInimigoAoAlcance = true;
                }
            }

            if (!temInimigoAoAlcance) {
                JOptionPane.showMessageDialog(null, "Nenhum inimigo ao alcance para atacar", "Erro", JOptionPane.ERROR_MESSAGE);
                resetarTela();
                return;
            }

            acaoAtqEspecialPad = true;
            JOptionPane.showMessageDialog(null, "Selecione um inimigo para o padre tentar converter", "Ataque especial", JOptionPane.INFORMATION_MESSAGE);
        } else if (persSelecionado instanceof Espiao) {
            ((Espiao) persSelecionado).atkEspecial();
            especialEspiao = true;
            resetarTela();
        } else if (persSelecionado instanceof CientistaMaluco) {
            Jogador jogProxRodada = (ehVezDeJ1) ? Main.j2 : Main.j1;
            ArrayList<ImageView> persJogProxRodada = (ehVezDeJ1) ? Main.persJ2 : Main.persJ1;
            ArrayList<ImageView> persJogAtual = (ehVezDeJ1) ? Main.persJ1 : Main.persJ2;
            boolean temInimigoAoAlcance = false;
            for (ImageView im : persJogAtual) {
                im.setOpacity(0.5);
            }
            for (ImageView im : persJogProxRodada) {
                im.setOpacity(1);
                im.disableProperty().set(false);
                temInimigoAoAlcance = true;
            }

            acaoAtqEspecialCie = true;
            JOptionPane.showMessageDialog(null, "Selecione um personagem para atacar", "Ataque especial", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    @FXML
    private void acaoTeclarBotaoMovimentar(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            acaoClicarBotaoMovimentar(new ActionEvent());
        }
    }

    @FXML
    private void acaoTeclarBotaoAtqBas(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            acaoClicarBotaoAtqBas(new ActionEvent());
        }
    }

    @FXML
    private void acaoTeclarBotaoAtqEsp(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            acaoClicarBotaoAtqEsp(new ActionEvent());
        }
    }

}
