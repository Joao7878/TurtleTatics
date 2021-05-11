package Jogo.itens;
import Jogo.personagens.Personagem;

public abstract class Item {
  private String nome;
  private int durabilidade;

  public Item(String nome, int durabilidade) {
    this.nome = nome;
    this.durabilidade = durabilidade;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getNome() {
    return this.nome;
  }

  public void setDurabilidade(int durabilidade) {
    this.durabilidade = durabilidade;
  }

  public int getDurabilidade() {
    return this.durabilidade;
  }

  public abstract void efeito(Personagem Pafetado);
}