package turtletatics.classesJogo.itens;

import turtletatics.classesJogo.personagens.Personagem;

public class Espada extends Item {
  public Espada() {
    super("Espada", 3);
  }

  @Override
  public void efeito(Personagem Pafetado) {
    Pafetado.setQuantAtaque(Pafetado.getQuantAtaque() + 15);
  }
}