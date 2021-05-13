package Personagens;

import javax.swing.JOptionPane;

public class CientistaMaluco extends Personagem {
  public CientistaMaluco() {
    super("Cientista Maluco", 100, 25, 3, 35);
  }

  @Override
  public void atkEspecial(Personagem pAtacado) {
    JOptionPane.showMessageDialog(null, this.getNome() + " lançou um míssil no " + pAtacado.getNome());
    pAtacado.setQuantVital(pAtacado.getQuantVital() - 85);
    System.out.println("vida do personagem atacado: " + pAtacado.getQuantVital());
    this.setCargaEspecial(4);
  }
}