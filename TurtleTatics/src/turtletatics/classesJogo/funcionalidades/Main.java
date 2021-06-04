package turtletatics.classesJogo.funcionalidades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javax.swing.JOptionPane;
import turtletatics.classesJogo.itens.Couraca;
import turtletatics.classesJogo.itens.Espada;
import turtletatics.classesJogo.itens.Estilingue;
import turtletatics.classesJogo.itens.Pocao;
import turtletatics.classesJogo.itens.Porrete;
import turtletatics.classesJogo.itens.Veneno;
import turtletatics.classesJogo.personagens.*;

/*Equipe: Guilherme Martinho Chumbinho De Andrade,Luan Machado Silva Vidal, Antonio Mesquita da Silveiro Neto,
Rafael Santos de Jesus,João Pedro Moreira de Almeida Santos*/
public class Main {

    public static Jogador j1;
    public static Jogador j2;
    public static int tamTabuleiro;
    public static Rectangle[][] tabuleiro;
    public static ArrayList<ImageView> persJ1 = new ArrayList<ImageView>();
    public static ArrayList<ImageView> persJ2 = new ArrayList<ImageView>();
    public static ExplosaoAtomica explosao = new ExplosaoAtomica(-1, -1);

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
                        if (item.equals("Couraça")) {
                            j.inserirItem(new Couraca());
                        } else if (item.equals("Espada")) {
                            j.inserirItem(new Espada());
                        } else if (item.equals("Estilingue")) {
                            j.inserirItem(new Estilingue());
                        } else if (item.equals("Poção de Cura")) {
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
            JOptionPane.showMessageDialog(null, "Falha durante a leiruta do arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
            arqR.close();
            leitor.close();
        }

        arqR.close();
        leitor.close();
    }
}
