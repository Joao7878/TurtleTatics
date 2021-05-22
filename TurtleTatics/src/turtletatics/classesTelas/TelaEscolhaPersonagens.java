
package turtletatics.classesTelas;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import turtletatics.classesJogo.funcionalidades.*;
import turtletatics.classesJogo.personagens.*;

public class TelaEscolhaPersonagens extends Application {
    
    public static Jogador j1;
    public static Jogador j2;
    public static Tabuleiro tabuleiro;
    static ArrayList<Personagem> selecaoPersonagens = new ArrayList<Personagem>();
    
    public Jogador getJ1() {
        return this.j1;
    }
    public Jogador getJ2() {
        return this.j2;
    }
    public ArrayList<Personagem> getSelecaoPersonagens() {
        return this.selecaoPersonagens;
    }
    
    public TelaEscolhaPersonagens(Jogador j1, Jogador j2, Tabuleiro tabuleiro) {
        this.j1 = j1;
        this.j2 = j2;
        
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
    
    public static boolean inserirPersonagemJ1(String nomePersonagem, boolean ehVezDeJ1) {
        for(Personagem p : selecaoPersonagens) {
            if(p.getNome().equals(nomePersonagem)) {
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
