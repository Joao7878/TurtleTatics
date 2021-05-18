
package Personagens;

import javax.swing.JOptionPane;

import Funcionalidades.Jogador;
import Itens.Item;
//Criado por Guilherme
public class Pescador extends Personagem {
  public Pescador() {
    super("Pescador", 150, 18, 5, 25);
  }

  @Override
  public int atkEspecial(Jogador itensInimigo) {
    if (itensInimigo.getInventario().size() == 0) {
      JOptionPane.showMessageDialog(null, "O inventário do inimigo está vazio");
      return 0;
    }

    int j = 0;
    String opcItem;

    for (Item i : itensInimigo.getInventario()) {
      System.out.println(j + " - " + i.getNome());
      j++;
    }
    opcItem = JOptionPane.showInputDialog(null,
        itensInimigo.getNome() + " escolha um item para o " + this.getNome() + " fisgar");

    try {
      int opcItemInt = Integer.parseInt(opcItem);

      if (opcItemInt >= 0 && opcItemInt < itensInimigo.getInventario().size()) {
        itensInimigo.removerItem(opcItemInt);
        JOptionPane.showMessageDialog(null, "Item " + itensInimigo.getInventario().get(opcItemInt).getNome()
            + " removido do inventário do " + itensInimigo.getNome());
        this.setCargaEspecial(12);
        return 1;
      } else {
        JOptionPane.showMessageDialog(null, "Não existem personagens nessa posição");
        return 0;
      }
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(null, "Entrada inválida");
      return 0;
    }
  }
}
/* habilidade especial: Lançar a vara no inigimo e destruir um item escolhido*/