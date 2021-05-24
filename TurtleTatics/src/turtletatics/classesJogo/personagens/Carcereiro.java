package turtletatics.classesJogo.personagens;

import javafx.scene.image.Image;
import javax.swing.JOptionPane;

public class Carcereiro extends Personagem {
  public Carcereiro() {
    super("Carcereiro", 250, 50, 1, 25, new Image("turtletatics/view/imagens/imagensPersonagens/Carcereiro.png"));
  }

  @Override
  public void atkEspecial(Personagem Patacado) {
    Patacado.setValorDefesa(Patacado.getValorDefesa() / 2);
    Patacado.setQuantVital(Patacado.getQuantVital() - 30);
    JOptionPane.showMessageDialog(null, Patacado.getNome() + " foi encarcerado por " + this.getNome());
    this.setCargaEspecial(3);
  }
}
