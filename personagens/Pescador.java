package Jogo.personagens;
import javax.swing.JOptionPane;

public class Pescador extends Personagem {
  public Pescador() {
    super("Pescador", 150, 18, 5, 25);
  }

  @Override
  public void atkEspecial(Personagem pAtacado){
    JOptionPane.showMessageDialog(null, this.getNome() + " lançou sua vara em " + pAtacado.getNome() + " e retirou");/*+ nome do item + "do seu inventario");*/

  }
}
/* habilidade especial: Lançar a vara no inigimo e retirar o item dele caso tenha um equipado*/