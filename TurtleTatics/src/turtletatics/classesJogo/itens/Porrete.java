package turtletatics.classesJogo.itens;

import turtletatics.classesJogo.personagens.Personagem;

public class Porrete extends Item {
  public Porrete() {
    super("Porrete", 1);
  }

  @Override
  public void efeito(Personagem Pafetado) {
    Pafetado.setValorDefesa(Pafetado.getValorDefesa() / 4);
  }
}