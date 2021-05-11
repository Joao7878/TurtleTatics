package Jogo.itens;
import Jogo.personagens.Personagem;

public class Espada extends Item{
  public Espada(){
    super("Espada",3);
  }
  @Override
  public void efeito(Personagem Pafetado){
    Pafetado.setQuantAtaque(Pafetado.getQuantAtaque()+15);
  }
}