package Jogo.personagens;

import javax.swing.JOptionPane;

import Jogo.itens.ExplosaoAtomica;



public class Comandante extends Personagem {
  private double radioatividade;

  public Comandante() {
    super("Comandante", 150, 20, 4, 30);
    this.radioatividade = 1.0;
  }

  public void setRadioatividade(double novo_radiatividade) {
    this.radioatividade = novo_radiatividade;
  }

  public double getRadioatividade() {
    return this.radioatividade;
  }

  @Override
  public void atkEspecial(Personagem pAtacado, ExplosaoAtomica explosao) {
    JOptionPane.showMessageDialog(null, this.getNome() + " usou seu cargo do exército para lançar uma bomba atômica no "
        + pAtacado.getNome() + ". Os arredores dessa zona estarão contaminados com radiação pelo resto da partida");
    pAtacado.setQuantVital((pAtacado.getQuantVital() - 70));
    System.out.println("vida do personagem atacado: " + pAtacado.getQuantVital());
    explosao.setX(pAtacado.getX());
    explosao.setY(pAtacado.getY());
    this.setCargaEspecial(-1);
  }
}
/*
 * habilidade especial: lançar uma bomba atomica durante o jogo apenas uma vez
 * que ataca qualquer inimigo do mapa. O inimigo atingido toma 70 dano e as
 * casas em volta são contaminadas com radiação. Quem estiver em uma casa
 * contaminada perde vida por turno. A contaminação diminui por rodada mas nunca
 * acaba
 */