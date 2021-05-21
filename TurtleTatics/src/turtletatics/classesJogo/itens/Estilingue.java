package turtletatics.classesJogo.itens;

import turtletatics.classesJogo.personagens.Personagem;

public class Estilingue extends Item {
  public Estilingue() {
    super("Estilingue", 3);
  }

  @Override
  public void efeito(Personagem atacado) {
    atacado.setQuantAtaque(atacado.getQuantAtaque() + 10);
  }
}