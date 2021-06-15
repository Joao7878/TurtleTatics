package turtletatics.classesJogo.personagens;

import javafx.scene.image.Image;
import javax.swing.JOptionPane;

public class CientistaMaluco extends Personagem {

    public CientistaMaluco() {
        super("Cientista Maluco", 110, 20, 2, 35, new Image("turtletatics/view/imagens/imagensPersonagens/CientistaMaluco.png"));
    }

    @Override
    public void atkEspecial(Personagem pAtacado) {
        JOptionPane.showMessageDialog(null, this.getNome() + " disparou um raio laser no " + pAtacado.getNome(), "Ataque especial", JOptionPane.INFORMATION_MESSAGE);
        pAtacado.setQuantVital(pAtacado.getQuantVital() - 60);
        this.setCargaEspecial(4);
    }
}

// habilidade especial: Atirar um laser em qualquer inimigo do mapa,
// tirando 60 de vida deste
