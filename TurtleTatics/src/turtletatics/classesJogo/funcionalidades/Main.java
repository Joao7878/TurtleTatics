package turtletatics.classesJogo.funcionalidades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.scene.control.SplitPane;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javax.swing.JOptionPane;

import turtletatics.classesJogo.itens.*;
import turtletatics.classesJogo.personagens.*;

public class Main {

    public static Jogador j1;
    public static Jogador j2;
    public static int tamTabuleiro;
    public static Rectangle[][] tabuleiro;
    public static ArrayList<ImageView> persJ1 = new ArrayList<ImageView>();
    public static ArrayList<ImageView> persJ2 = new ArrayList<ImageView>();
    public static ArrayList<Obstaculo> obstaculos = new ArrayList<Obstaculo>();
    public static ExplosaoAtomica explosao = new ExplosaoAtomica(-1, -1);
    public static InnerShadow efeito = new InnerShadow();

    public static void aplicarEfeitoClicado(SplitPane sp) {
        efeito = new InnerShadow();
        efeito.setChoke(0.5);
        sp.effectProperty().set(efeito);
    }
    
    public static void aplicarEfeitoClicado(ImageView iv) {
        efeito = new InnerShadow();
        efeito.setChoke(0.5);
        iv.effectProperty().set(efeito);
    }
    
    public static void removerEfeitoClicado(SplitPane sp) {
        efeito = new InnerShadow();
        efeito.setChoke(0);
        sp.effectProperty().set(efeito);
    }
    
    public static void removerEfeitoClicado(ImageView iv) {
        efeito = new InnerShadow();
        efeito.setChoke(0);
        iv.effectProperty().set(efeito);
    }

    public static int calcularDistancia(int xAtual, int yAtual, int xNovo, int yNovo) {
        int deltaX = Math.abs(xNovo - xAtual), deltaY = Math.abs(yNovo - yAtual);

        if (deltaX >= deltaY) {
            return deltaX;
        } else {
            return deltaY;
        }
    }

    public static int calcularDistanciaPersonagens(Personagem p1, Personagem p2) {
        return calcularDistancia(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    public static void pegarItensSalvos(Jogador j) throws IOException {
        FileReader arqR;
        BufferedReader leitor;

        try {
            arqR = new FileReader("arqJogadores.txt");
            leitor = new BufferedReader(arqR);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha ao tentar abrir o arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String linha = leitor.readLine();
            while (linha != null) {
                if (linha.equals(j.getNome())) {
                    linha = leitor.readLine();
                    String itensSalvos[] = linha.split(",");

                    for (String item : itensSalvos) {
                        if (/*item.equals("Couraça")*/item.equals("CouraÃ§a")) {
                            j.inserirItem(new Couraca());
                        } else if (item.equals("Espada")) {
                            j.inserirItem(new Espada());
                        } else if (item.equals("Estilingue")) {
                            j.inserirItem(new Estilingue());
                        } else if (/*item.equals("Poção de Cura")*/item.equals("PoÃ§Ã£o de Cura")) {
                            j.inserirItem(new Pocao());
                        } else if (item.equals("Porrete")) {
                            j.inserirItem(new Porrete());
                        } else // item == Veneno
                        {
                            j.inserirItem(new Veneno());
                        }
                    }
                    break;
                } else {
                    linha = leitor.readLine();
                }

                linha = leitor.readLine();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Falha durante a leitura do arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
            arqR.close();
            leitor.close();
        }

        arqR.close();
        leitor.close();
    }

    public static void salvarGanhadorArquivo(Jogador ganhador, Item itemGanhado) throws IOException {
        FileReader arqR;
        BufferedReader leitor;

        try {
            arqR = new FileReader("arqJogadores.txt");
            leitor = new BufferedReader(arqR);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha ao tentar abrir o arquivo");
            return;
        }

        ArrayList<String> nomeJogadores = new ArrayList<String>();
        ArrayList<String> itensJogadores = new ArrayList<String>();
        String linha = "";
        boolean jogadorExiste = false;

        try {
            linha = leitor.readLine();
            while (linha != null) {
                nomeJogadores.add(linha);
                if (linha.equals(ganhador.getNome())) {
                    linha = leitor.readLine() + "," + itemGanhado.getNome();
                    jogadorExiste = true;
                } else {
                    linha = leitor.readLine();
                }

                itensJogadores.add(linha);
                linha = leitor.readLine();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Falha durante a leitura do arquivo");
            leitor.close();
        }
        leitor.close();
        arqR.close();

        FileWriter arqW;
        PrintWriter gravador;

        try {
            arqW = new FileWriter("arqJogadores.txt");
            gravador = new PrintWriter(arqW);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha ao tentar abrir o arquivo");
            return;
        }

        int i = 0;
        for (String nome : nomeJogadores) {
            gravador.println(nome);
            gravador.println(itensJogadores.get(i));
            i++;
        }
        if (!jogadorExiste) {
            gravador.println(ganhador.getNome());
            gravador.println(itemGanhado.getNome());
        }
        gravador.close();
        arqW.close();
    }
}
