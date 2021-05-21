
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
import turtletatics.classesJogo.itens.*;
import turtletatics.classesJogo.personagens.*;

public class TelaEscolhaPersonagens extends Application {
    
    private Jogador j1;
    private Jogador j2;
    private ArrayList<Personagem> selecaoPersonagens = new ArrayList<Personagem>();
    ArrayList<Item> sorteioItem = new ArrayList<Item>();
    
    public TelaEscolhaPersonagens(Jogador j1, Jogador j2, Tabuleiro tabuleiro) {
        this.j1 = j1;
        this.j2 = j2;
        
        this.selecaoPersonagens.add(new Carcereiro());
        this.selecaoPersonagens.add(new Padre());
        this.selecaoPersonagens.add(new CientistaMaluco());
        this.selecaoPersonagens.add(new Comandante());
        this.selecaoPersonagens.add(new Construtor());
        this.selecaoPersonagens.add(new Cavaleiro());
        this.selecaoPersonagens.add(new Cozinheiro());
        this.selecaoPersonagens.add(new Engenheiro());
        this.selecaoPersonagens.add(new Espiao());
        this.selecaoPersonagens.add(new Pescador());
        
        this.sorteioItem.add(new Veneno());
        this.sorteioItem.add(new Couraca());
        this.sorteioItem.add(new Espada());
        this.sorteioItem.add(new Estilingue());
        this.sorteioItem.add(new Porrete());
        this.sorteioItem.add(new Pocao());
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
