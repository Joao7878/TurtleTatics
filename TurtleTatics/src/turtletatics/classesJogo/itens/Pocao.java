package turtletatics.classesJogo.itens;

import turtletatics.classesJogo.personagens.Personagem;

public class Pocao extends Item {
  public Pocao() {
    super("Poção de Cura", 2);
  }

  @Override
  public void efeito(Personagem Pafetado) {
    Pafetado.setQuantVital(Pafetado.getQuantVital() + 25);
  }
}