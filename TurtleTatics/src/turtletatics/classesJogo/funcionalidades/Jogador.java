package turtletatics.classesJogo.funcionalidades;

import java.util.ArrayList;

import turtletatics.classesJogo.itens.Item;
import turtletatics.classesJogo.personagens.Personagem;

public class Jogador {
  private String nome;
  private ArrayList<Personagem> personagens = new ArrayList<Personagem>();
  private ArrayList<Item> inventario = new ArrayList<Item>();

  public Jogador(String nome) {
    this.nome = nome;
  }

  public String getNome() {
    return this.nome;
  }

  public ArrayList<Personagem> getPersonagens() {
    return this.personagens;
  }

  public ArrayList<Item> getInventario() {
    return this.inventario;
  }

  public void inserirPersonagem(Personagem p) {
    this.personagens.add(p);
  }

  public void removerPersonagem(int posicao) {
    this.personagens.remove(posicao);
  }

  public void inserirItem(Item i) {
    this.inventario.add(i);
  }

  public void removerItem(int posicao) {
    this.inventario.remove(posicao);
  }

}
