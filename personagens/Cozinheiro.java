package Jogo.personagens;

import javax.swing.JOptionPane;

import Jogo.funcionalidades.Jogador;



public class Cozinheiro extends Personagem {
  public Cozinheiro() {
    super("Cozinheiro", 220, 50, 1, 20);
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
          aliados.getNome() + " escolha um personagem para ser alimentado pela gororoba do Cozinheiro.");
    try {
      opcPersonagemInt = Integer.parseInt(opcPersonagem);
      if (!(opcPersonagemInt >= 0 && opcPersonagemInt < aliados.getPersonagens().size())) {
        JOptionPane.showMessageDialog(null, "Não existem personagens nessa posição");
        return 0;
      }
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(null, "Entrada inválida");
      return 0;
    }

    aliados.getPersonagens().get(opcPersonagemInt)
        .setQuantVital(aliados.getPersonagens().get(opcPersonagemInt).getQuantVital() + 20);
    aliados.getPersonagens().get(opcPersonagemInt)
        .setValorDefesa(aliados.getPersonagens().get(opcPersonagemInt).getValorDefesa() + 5);

    JOptionPane.showMessageDialog(null, "O " + aliados.getPersonagens().get(opcPersonagemInt).getNome()
        + " ganhou 20 de vida e 5 de defesa.");
    
    this.setCargaEspecial(4);

    aliados.getPersonagens().get(opcPersonagemInt).printStatus();
    return 1;
  }
}
// habilidade especial: Dar a gororoba a si mesmo ou a um aliado, dando 20
// pontos de vida e 5 pontos de defesa. Não existe limite de alcançe para esse
// ataque especial