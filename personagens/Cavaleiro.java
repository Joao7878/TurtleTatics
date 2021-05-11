package Jogo.personagens;

import javax.swing.JOptionPane;

public class Cavaleiro extends Personagem {
  private double CoefEspecial;

  public Cavaleiro() {
    super("Cavaleiro", 200, 40, 1, 55);
    this.CoefEspecial = 0.5;
  }

  public void setCoefEspecial(double novoCoef) {
    this.CoefEspecial = novoCoef;
  }

  public double getCoefEspecial() {
    return this.CoefEspecial;
  }

  @Override
  public void atkEspecial(Personagem pAtacado) {
    JOptionPane.showMessageDialog(null, this.getNome() + " desembainhou a Samehada e atacou: " + pAtacado.getNome()
        + ". A Samehada consumiu chakra do " + pAtacado.getNome() + ", seu próximo ataque dará mais dano");
    pAtacado.setQuantVital((pAtacado.getQuantVital() / (1 + this.getCoefEspecial())));
    System.out.println("vida do personagem atacado: " + pAtacado.getQuantVital());
    this.setCoefEspecial(this.getCoefEspecial() + 0.5);
    this.setCargaEspecial(6);
  }
}
// habilidade especial: Utilizar a Samehada, dividindo a vida atual do atacado a
// partir do coeficiente de ataque especial. O atacado deve estar no raio de
// ação do cavaleiro