package turtletatics.classesTelas;

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import turtletatics.classesJogo.funcionalidades.*;
import turtletatics.classesJogo.personagens.*;

public class TelaEscolhaPersonagens extends Application {

    static ArrayList<Personagem> selecaoPersonagens = new ArrayList<Personagem>();

    public TelaEscolhaPersonagens(Jogador j1, Jogador j2, int tamTabuleiro) throws IOException {
        Main.j1 = j1;
        Main.j2 = j2;
        Main.tamTabuleiro = tamTabuleiro;
        Main.tabuleiro = new Rectangle[Main.tamTabuleiro][Main.tamTabuleiro];

        Main.pegarItensSalvos(Main.j1);
        Main.pegarItensSalvos(Main.j2);

        selecaoPersonagens.add(new Carcereiro());
        selecaoPersonagens.add(new Cavaleiro());
        selecaoPersonagens.add(new CientistaMaluco());
        selecaoPersonagens.add(new Comandante());
        selecaoPersonagens.add(new Construtor());
        selecaoPersonagens.add(new Cozinheiro());
        selecaoPersonagens.add(new Engenheiro());
        selecaoPersonagens.add(new Espiao());
        selecaoPersonagens.add(new Padre());
        selecaoPersonagens.add(new Pescador());
    }

    public static boolean inserirPersonagem(String nomePersonagem, boolean ehVezDeJ1) {
        if (nomePersonagem.equals("espiao")) {
            nomePersonagem = "Espião";
        } else if (nomePersonagem.equals("cientistaMaluco")) {
            nomePersonagem = "Cientista Maluco";
        }

        for (Personagem p : selecaoPersonagens) {
            if (p.getNome().equalsIgnoreCase(nomePersonagem)) {
                if (ehVezDeJ1) {
                    Main.j1.inserirPersonagem(p);
                } else {
                    Main.j2.inserirPersonagem(p);
                }

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
