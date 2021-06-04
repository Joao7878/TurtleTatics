package turtletatics.classesJogo.personagens;

import javafx.scene.image.Image;
import javax.swing.JOptionPane;

public class CientistaMaluco extends Personagem {
  public CientistaMaluco() {
    super("Cientista Maluco", 100, 25, 3, 35, new Image("turtletatics/view/imagens/imagensPersonagens/CientistaMaluco.png"));
  }

  @Override
  public void atkEspecial(Personagem pAtacado) {
    //JOptionPane.showMessageDialog(null, this.getNome() + " disparou um raio laser no " + pAtacado.getNome());
    JOptionPane.showMessageDialog(null, this.getNome() + " disparou um raio laser no " + pAtacado.getNome(), "Ataque especial", JOptionPane.INFORMATION_MESSAGE);
    pAtacado.setQuantVital(pAtacado.getQuantVital() - 60);
    this.setCargaEspecial(4);
  }
}