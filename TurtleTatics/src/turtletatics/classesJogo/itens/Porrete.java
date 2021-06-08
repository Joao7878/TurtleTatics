package turtletatics.classesJogo.itens;

import javax.swing.JOptionPane;
import turtletatics.classesJogo.personagens.Personagem;

public class Porrete extends Item {
  public Porrete() {
    super("Porrete", 1);
  }

  @Override
  public void efeito(Personagem Pafetado) {
    JOptionPane.showMessageDialog(null, Pafetado.getNome() + " perdeu 75% de defesa", "Utilização de item", JOptionPane.INFORMATION_MESSAGE);
    Pafetado.setValorDefesa(Pafetado.getValorDefesa() / 4);
    this.setDurabilidade(this.getDurabilidade() - 1);
  }
}