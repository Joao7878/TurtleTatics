package Personagens;

import javax.swing.JOptionPane;

public class Carcereiro extends Personagem {
  public Carcereiro() {
    super("Carcereiro", 250, 50, 1, 25);
  }

  @Override
  public void atkEspecial(Personagem Patacado) {
    Patacado.setValorDefesa(Patacado.getValorDefesa() / 2);
    Patacado.setQuantVital(Patacado.getQuantVital() - 30);
    JOptionPane.showMessageDialog(null, Patacado.getNome() + " foi encarcerado por " + this.getNome());
    this.setCargaEspecial(3);
  }
}
