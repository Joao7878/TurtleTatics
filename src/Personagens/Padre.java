package Personagens;

import java.util.Random;
import javax.swing.JOptionPane;

import Funcionalidades.*;

public class Padre extends Personagem {
  private int quantTentativas;

  public Padre() {
    super("Padre", 90, 15, 4, 15);
    quantTentativas = 0;
  }

  public int getQuantTentativas() {
    return this.quantTentativas;
  }

  @Override
  public int atkEspecial(Jogador inimigos, Jogador aliados) {
    if (this.quantTentativas >= 5) {
      JOptionPane.showMessageDialog(null, "O padre pode tentar converter o inimigo apenas 5 vezes");
      return 0;
    }

    int i = 0, opcPersonagemInt;
    String opcPersonagem;

    for (Personagem p : inimigos.getPersonagens()) {
      System.out.println(i + " - " + p.getNome());
      i++;
    }
    opcPersonagem = JOptionPane.showInputDialog(null,
        aliados.getNome() + " escolha um herege para o padre tentar converter.");
    try {
      opcPersonagemInt = Integer.parseInt(opcPersonagem);
      if (!((opcPersonagemInt >= 0 && opcPersonagemInt < inimigos.getPersonagens().size()))) {
        JOptionPane.showMessageDialog(null, "Não existem personagens nessa posição");
        return 0;
      }
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(null, "Entrada inválida");
      return 0;
    }
    if (Main.calcularDistanciaPersonagens(this, inimigos.getPersonagens().get(opcPersonagemInt)) > this.getAlcance()) {
      JOptionPane.showMessageDialog(null, "Alcance insuficiente");
      return 0;
    }
    Random gerador = new Random();
    double numReal = gerador.nextDouble();
    System.out.print("\nresultado do gerador: " + numReal + "\n");
    if (numReal <= 0.25) {// Conversão realizada com sucesso
      JOptionPane.showMessageDialog(null, "O " + this.getNome() + " conseguiu converter o "
          + inimigos.getPersonagens().get(opcPersonagemInt).getNome());
      aliados.inserirPersonagem(inimigos.getPersonagens().get(opcPersonagemInt));
      inimigos.getPersonagens().remove(opcPersonagemInt);
      this.setCargaEspecial(-1);
      return 1;
    } else {// Conversão falhou
      this.quantTentativas++;
      JOptionPane.showMessageDialog(null,
          "A tentativa de conversão falhou. Tentativas restantes: " + (5 - this.getQuantTentativas()));
      this.setCargaEspecial(4);
      return 1;
    }
  }
}

// habilidade especial: Tenta converter um inimigo dentro do seu raio de ação em
// aliado
// permanentemente.
// Ao tentar conveter um inimigo, o padre tem 25% de chance de sucesso
// O padre pode tentar realizar a conversão até 5 vezes durante a partida
// Caso a conversão seja bem sucessida, o padre não poderá mais utilizar a
// habilidade especial
