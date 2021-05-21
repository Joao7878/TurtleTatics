package turtletatics.classesJogo.personagens;

import javax.swing.JOptionPane;

public class CientistaMaluco extends Personagem {
  public CientistaMaluco() {
    super("Cientista Maluco", 100, 25, 3, 35);
  }

  @Override
  public void atkEspecial(Personagem pAtacado) {
    JOptionPane.showMessageDialog(null, this.getNome() + " disparou um raio laser no " + pAtacado.getNome());
    pAtacado.setQuantVital(pAtacado.getQuantVital() - 60);
    this.setCargaEspecial(4);
  }
}