package turtletatics.classesTelas;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import turtletatics.classesJogo.funcionalidades.Main;

public class TelaPosicionamento extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/turtletatics/view/TelaPosicionamento.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.getIcons().add(new Image("/turtletatics/view/imagens/IconeAbas.png"));
        stage.setTitle("Posicionamento");
        stage.setResizable(false);
        if(Main.tamTabuleiro == 12) {
            stage.setFullScreen(true);
        }
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
