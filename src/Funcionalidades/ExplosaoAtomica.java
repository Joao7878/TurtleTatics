package Funcionalidades;

import javax.swing.JOptionPane;

import Personagens.Personagem;

public class ExplosaoAtomica {
  private int x, y;
  private double radioatividade;

  public ExplosaoAtomica(int x, int y) {
    this.x = x;
    this.y = y;
    radioatividade = 5.0;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public void darDanoArea(Tabuleiro tabuleiro, Jogador j1, Jogador j2) {
    int i;
    Personagem p;

    i = 0;
    for (i = 0; i < j1.getPersonagens().size(); i++) {
      p = j1.getPersonagens().get(i);
      if (Main.calcularDistancia(p.getX(), p.getY(), this.x, this.y) <= 1) {
        JOptionPane.showMessageDialog(null, p.getNome() + " levou dano da radiação");
        p.setQuantVital(p.getQuantVital() - 10 * this.radioatividade);
        if (p.getQuantVital() <= 0) {
          tabuleiro.removerDoTabuleiro(p);
          j1.removerPersonagem(i);
          i--;
        }
      }
    }

    i = 0;
    for (i = 0; i < j2.getPersonagens().size(); i++) {
      p = j2.getPersonagens().get(i);
      if (Main.calcularDistancia(p.getX(), p.getY(), this.x, this.y) <= 1) {
        JOptionPane.showMessageDialog(null, p.getNome() + " levou dano da radiação");
        p.setQuantVital(p.getQuantVital() - 10 * this.radioatividade);
        if (p.getQuantVital() <= 0) {
          tabuleiro.removerDoTabuleiro(p);
          j2.removerPersonagem(i);
          i--;
        }
      }
    }
    this.radioatividade /= 1.07;
  }
}
