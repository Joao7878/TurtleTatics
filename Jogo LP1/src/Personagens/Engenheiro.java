package Personagens;

import javax.swing.JOptionPane;
//Criado por João
public class Engenheiro extends Personagem {
  public Engenheiro() {
    super("Engenheiro", 120, 40, 3, 30);
  }

  @Override
  public void atkEspecial(Personagem pAtacado, Personagem pAliado) {
    JOptionPane.showMessageDialog(null, this.getNome() + " usou o poder do SENAI CIMATEC DE ROUBAR VIDAS, para roubar a vida de " 
    + pAtacado.getNome()
    + ". Apos isso deu a vida para " + pAliado.getNome() + "!");
    pAliado.setQuantVital(pAliado.getQuantVital() + 20);
    pAtacado.setQuantVital(pAtacado.getQuantVital() - 20);
    this.setCargaEspecial(4);
  }
}
/* ataca um inimigo roubando sua vida e dando ela para si mesmo ou um alido*/