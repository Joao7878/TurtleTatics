package turtletatics.classesJogo.personagens;

import javafx.scene.image.Image;
import javax.swing.JOptionPane;

public class Cavaleiro extends Personagem {
  private double coefEspecial;

  public Cavaleiro() {
    super("Cavaleiro", 200, 40, 1, 55, new Image("turtletatics/view/imagens/imagensPersonagens/Cavaleiro.png"));
    this.coefEspecial = 0.5;
  }

  public void setCoefEspecial(double novoCoef) {
    this.coefEspecial = novoCoef;
  }

  public double getCoefEspecial() {
    return this.coefEspecial;
  }

  @Override
  public void atkEspecial(Personagem pAtacado) {
    JOptionPane.showMessageDialog(null, this.getNome() + " desembainhou a Samehada e atacou o " + pAtacado.getNome()
                                  + ". A Samehada consumiu chakra, seu próximo ataque especial dará mais dano",
                                  pAtacado.getNome() + " atacado", JOptionPane.INFORMATION_MESSAGE);
    pAtacado.setQuantVital((pAtacado.getQuantVital() / (1 + this.getCoefEspecial())));
    System.out.println("vida do personagem atacado: " + pAtacado.getQuantVital());
    this.setCoefEspecial(this.getCoefEspecial() + 0.5);
    this.setCargaEspecial(6);
  }
}
// habilidade especial: Utilizar a Samehada, dividindo a vida atual do atacado a
// partir do coeficiente de ataque especial. O atacado deve estar no raio de
// ação do cavaleiro
