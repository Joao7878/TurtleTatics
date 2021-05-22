
package turtletatics.classesTelas;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import turtletatics.classesJogo.funcionalidades.*;

public class TelaPosicionamento extends Application {
    public static Jogador j1;
    public static Jogador j2;
    public static Tabuleiro tabuleiro;
    
    public TelaPosicionamento(Jogador j1, Jogador j2, Tabuleiro tabuleiro) {
        this.j1 = j1;
        this.j2 = j2;
        this.tabuleiro = tabuleiro;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/turtletatics/view/TelaPosicionamento.fxml"));
        
        Scene scene = new Scene(root);
                
        stage.setScene(scene);
        stage.getIcons().add(new Image("/turtletatics/view/imagens/IconeAbas.png"));
        stage.setTitle("Posicionamento");
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
