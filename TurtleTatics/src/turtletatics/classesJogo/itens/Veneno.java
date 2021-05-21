package turtletatics.classesJogo.itens;

import turtletatics.classesJogo.personagens.Personagem;

public class Veneno extends Item {
  public Veneno() {
    super("Veneno", 1);
  }

  @Override
  public void efeito(Personagem Patacado) {
    Patacado.setQuantVital(Patacado.getQuantVital() - 30);
  }
}
