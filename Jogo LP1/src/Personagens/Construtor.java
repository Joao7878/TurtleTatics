package Personagens;

import java.util.Random;
import javax.swing.JOptionPane;

import Funcionalidades.Jogador;
//Criado por Antonio
public class Construtor extends Personagem {
  public Construtor() {
    super("Construtor", 120, 25, 2, 30);
  }

  @Override
  public int atkEspecial(Jogador aliados) {
    int i = 0, opcPersonagemInt;
    String opcPersonagem;

    for (Personagem p : aliados.getPersonagens()) {
      System.out.println(i + " - " + p.getNome());
      i++;
    }
    opcPersonagem = JOptionPane.showInputDialog(null,
        aliados.getNome() + " escolha um personagem para o Construtor forjar uma arma.");
    try {
      opcPersonagemInt = Integer.parseInt(opcPersonagem);
      if (!((opcPersonagemInt >= 0 && opcPersonagemInt < aliados.getPersonagens().size()))) {
        JOptionPane.showMessageDialog(null, "Não existem personagens nessa posição");
        return 0;
      }
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(null, "Entrada inválida");
      return 0;
    }

    Random gerador = new Random();
    double numReal = gerador.nextDouble();
    if (numReal > 0.5) {
      aliados.getPersonagens().get(opcPersonagemInt)
          .setQuantVital(aliados.getPersonagens().get(opcPersonagemInt).getQuantAtaque() + 15);
      JOptionPane.showMessageDialog(null, "O " + aliados.getPersonagens().get(opcPersonagemInt).getNome()
          + " teve suas armas melhoradas, ganhando 15 de ataque.");
    } else {
      aliados.getPersonagens().get(opcPersonagemInt)
          .setValorDefesa(aliados.getPersonagens().get(opcPersonagemInt).getValorDefesa() + 10);
      JOptionPane.showMessageDialog(null, "O " + aliados.getPersonagens().get(opcPersonagemInt).getNome()
          + " teve suas armadura melhorada, ganhando 10 de defesa.");
    }
    this.setCargaEspecial(8);

    aliados.getPersonagens().get(opcPersonagemInt).printStatus();
    return 1;
  }
}
/**/
