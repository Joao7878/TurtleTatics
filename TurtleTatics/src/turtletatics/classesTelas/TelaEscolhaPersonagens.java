
package turtletatics.classesTelas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import turtletatics.classesJogo.funcionalidades.*;
import turtletatics.classesJogo.itens.*;
import turtletatics.classesJogo.personagens.*;

public class TelaEscolhaPersonagens extends Application {
    
    public static Jogador j1;
    public static Jogador j2;
    public static int tamTabuleiro;
    static ArrayList<Personagem> selecaoPersonagens = new ArrayList<Personagem>();
    
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
            if (item.equals("Couraça"))
              j.inserirItem(new Couraca());
            else if (item.equals("Espada"))
              j.inserirItem(new Espada());
            else if (item.equals("Estilingue"))
              j.inserirItem(new Estilingue());
            else if (item.equals("Poção de Cura"))
              j.inserirItem(new Pocao());
            else if (item.equals("Porrete"))
              j.inserirItem(new Porrete());
            else // item == Veneno
              j.inserirItem(new Veneno());
          }
          break;
        } else
          linha = leitor.readLine();

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
    
    public TelaEscolhaPersonagens(Jogador j1, Jogador j2, int tamTabuleiro) throws IOException {
        this.j1 = j1;
        this.j2 = j2;
        this.tamTabuleiro = tamTabuleiro;
        
        pegarItensSalvos(this.j1);
        pegarItensSalvos(this.j2);
        
        this.selecaoPersonagens.add(new Carcereiro());
        this.selecaoPersonagens.add(new Cavaleiro());
        this.selecaoPersonagens.add(new CientistaMaluco());
        this.selecaoPersonagens.add(new Comandante());
        this.selecaoPersonagens.add(new Construtor());
        this.selecaoPersonagens.add(new Cozinheiro());
        this.selecaoPersonagens.add(new Engenheiro());
        this.selecaoPersonagens.add(new Espiao());
        this.selecaoPersonagens.add(new Padre());
        this.selecaoPersonagens.add(new Pescador());
    }
    
    public static boolean inserirPersonagem(String nomePersonagem, boolean ehVezDeJ1) {
        if(nomePersonagem.equals("espiao")) nomePersonagem = "Espião";
        else if(nomePersonagem.equals("cientistaMaluco")) nomePersonagem = "Cientista Maluco";
        
        for(Personagem p : selecaoPersonagens) {
            if(p.getNome().equalsIgnoreCase(nomePersonagem)) {
                if(ehVezDeJ1) j1.inserirPersonagem(p);
                else j2.inserirPersonagem(p);
                
                selecaoPersonagens.remove(p);
                break;
            }
        }
        return selecaoPersonagens.isEmpty();
    }
        
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/turtletatics/view/TelaEscolhaPersonagens.fxml"));
        
        Scene scene = new Scene(root);
                        
        stage.setScene(scene);
        stage.getIcons().add(new Image("/turtletatics/view/imagens/IconeAbas.png"));
        stage.setTitle("Escolha de personagens");
        stage.setResizable(false);
        
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
