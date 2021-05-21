
package turtletatics.classesTelas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TelaInicial extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/turtletatics/view/TelaInicial.fxml"));
        
        Scene scene = new Scene(root);
                
        stage.setScene(scene);
        stage.getIcons().add(new Image("/turtletatics/view/imagens/IconeAbas.png"));
        stage.setTitle("Tela inicial");
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
