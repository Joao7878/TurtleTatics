package Personagens;

import javax.swing.JOptionPane;
//Criado por Luan
public class Cavaleiro extends Personagem {
  private double coefEspecial;

  public Cavaleiro() {
    super("Cavaleiro", 200, 40, 1, 55);
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
    JOptionPane.showMessageDialog(null, this.getNome() + " desembainhou a Samehada e atacou: " + pAtacado.getNome()
        + ". A Samehada consumiu chakra do " + pAtacado.getNome() + ", seu próximo ataque dará mais dano");
    pAtacado.setQuantVital((pAtacado.getQuantVital() / (1 + this.getCoefEspecial())));
    System.out.println("vida do personagem atacado: " + pAtacado.getQuantVital());
    this.setCoefEspecial(this.getCoefEspecial() + 0.5);
    this.setCargaEspecial(6);
  }
}
/* habilidade especial: Utilizar a Samehada, dividindo a vida atual do atacado a
partir do coeficiente de ataque especial. O atacado deve estar no raio de
 ação do cavaleiro*/
