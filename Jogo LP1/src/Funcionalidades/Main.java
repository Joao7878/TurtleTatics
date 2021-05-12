package Funcionalidades;

import javax.swing.JOptionPane;

import Itens.*;
import Personagens.*;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

public class Main {
  public static void limparTelaConsole() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void printaPersonagens(Jogador j) {
    int i = 0;
    System.out.println("Lista de personagens de " + j.getNome());
    for (Personagem p : j.getPersonagens()) {
      System.out.println(i + "-" + p.getNome());
      i++;
    }
  }

  public static void borda() {
    System.out.println("==========================================================");
  }

  public static void mostraAtributos(Personagem atacante, Personagem atacado) {
    borda();
    System.out.println("Vida do atacante " + atacante.getNome() + ":" + atacante.getQuantVital());
    System.out.println("Defesa do atacante " + atacante.getNome() + ":" + atacante.getValorDefesa());
    System.out.println("Dano do atacante " + atacante.getNome() + ":" + atacante.getQuantAtaque());
    borda();
    System.out.println("Vida do atacado " + atacado.getNome() + ":" + atacado.getQuantVital());
    System.out.println("Defesa do atacado " + atacado.getNome() + ":" + atacado.getValorDefesa());
    System.out.println("Dano do atacado " + atacado.getNome() + ":" + atacado.getQuantAtaque());
    borda();
  }

  public static int calcularDistancia(int xAtual, int yAtual, int xNovo, int yNovo) {
    int deltaX = Math.abs(xNovo - xAtual), deltaY = Math.abs(yNovo - yAtual);

    if (deltaX >= deltaY)
      return deltaX;
    else
      return deltaY;
  }

  public static int calcularDistanciaPersonagens(Personagem p1, Personagem p2) {
    return calcularDistancia(p1.getX(), p1.getY(), p2.getX(), p2.getY());
  }

  public static boolean temPersEmPosicao(ArrayList<Personagem> listaPersonagens, int x, int y) {
    for (Personagem p : listaPersonagens) {
      if (p.getX() == x && p.getY() == y)
        return true;
    }
    return false;
  }

  public static boolean passouTurno(boolean ehVezDeJ1, Jogador jogadorAtual, Jogador j1, Jogador j2) {
    if ((ehVezDeJ1 && jogadorAtual == j2) || (!ehVezDeJ1 && jogadorAtual == j1))
      return true;
    else
      return false;
  }

  // função de escolha dos personagens=======================
  public static void escolhaPersonagens(Jogador j1, Jogador j2, ArrayList<Personagem> selecaoPersonagens) {
    boolean ehVezDeJ1 = true;
    int i = 0, escInt = 0;
    String esc;
    Jogador jogadorAtual;

    while (selecaoPersonagens.size() > 0) {
      i = 0;
      System.out.println("---------------");
      for (Personagem p : selecaoPersonagens) {
        System.out.println(i + " - " + p.getNome());
        i++;
      }
      System.out.println("---------------");

      if (ehVezDeJ1) {
        jogadorAtual = j1;
      } else {
        jogadorAtual = j2;
      }
      while (true) {
        esc = JOptionPane
            .showInputDialog("Jogador " + jogadorAtual.getNome() + ", digite o número do personagem que você quer:");
        try {
          escInt = Integer.parseInt(esc);
          if (escInt >= 0 && escInt < selecaoPersonagens.size())
            break;
          else
            JOptionPane.showMessageDialog(null, "Não existem personagens nessa posição");

        } catch (Exception ex) {
          JOptionPane.showMessageDialog(null, "Entrada inválida");
        }
      }
      jogadorAtual.inserirPersonagem(selecaoPersonagens.get(escInt));
      ehVezDeJ1 = !ehVezDeJ1;
      selecaoPersonagens.remove(escInt);
      printaPersonagens(j1);
      printaPersonagens(j2);
    }
  }

  public static void sorteioItens(Jogador j, ArrayList<Item> sorteioItem) {
    Random gerador = new Random();
    int i = 0;
    while (i > sorteioItem.size()) {
      i = gerador.nextInt();
    }
    System.out.println("Voce recebeu o item: " + sorteioItem.get(i).getNome());
    j.inserirItem(sorteioItem.get(i));
    sorteioItem.remove(i);
  }

  public static void mostraItens(Jogador j){
    int x=0;
    System.out.println("Esses são os itens do jogador "+j.getNome());
    for(Item i: j.getInventario()){
      System.out.println(x+ "-" +i.getNome());
      x++;
    }
  }
  
  public static void utilizarItem(Jogador j, Jogador j2){
    borda();
    mostraItens(j);
    borda();
    int opInt;
    String escolhido;
    int escolhidoInt;
    String op = JOptionPane.showInputDialog( j.getNome() + " escolha um item para utilizar");
    opInt=Integer.parseInt(op);

    if(j.getInventario().get(opInt) instanceof Espada || j.getInventario().get(opInt) instanceof Pocao || j.getInventario().get(opInt) instanceof Couraca || j.getInventario().get(opInt) instanceof Estilingue){
      printaPersonagens(j);
      escolhido = JOptionPane.showInputDialog( j.getNome() + " escolha um personagem aliado para utilizar o item escolhido");
      escolhidoInt=Integer.parseInt(escolhido);
      j.getInventario().get(opInt).efeito(j.getPersonagens().get(escolhidoInt));
    }
    else{
      printaPersonagens(j2);
      escolhido = JOptionPane.showInputDialog( j.getNome() + " escolha um personagem inimigo para utilizar o item escolhido");
      escolhidoInt=Integer.parseInt(escolhido);
      j.getInventario().get(opInt).efeito(j2.getPersonagens().get(escolhidoInt));
    }

    if(j.getInventario().get(opInt).getDurabilidade()<=0){
      j.removerItem(opInt);
    }
    else{
    j.getInventario().get(opInt).setDurabilidade(j.getInventario().get(opInt).getDurabilidade()-1);
    }
  }

  // ==========================================================
  // Funcao de posicionar os personagens
  public static void posicionarPersonagens(Jogador j1, Jogador j2, Tabuleiro tabuleiro) {
    int posPersonagemAtual = 0, x, y;
    boolean ehVezDeJ1 = true;
    String coordenada;
    Jogador jogadorAtual;

    while (posPersonagemAtual < 10) {
      if (ehVezDeJ1)
        jogadorAtual = j1;
      else
        jogadorAtual = j2;

      while (true) {
        coordenada = JOptionPane.showInputDialog(jogadorAtual.getNome() + ", insira a coordenada X do "
            + jogadorAtual.getPersonagens().get((int) (posPersonagemAtual / 2.0)).getNome());
        try {
          x = Integer.parseInt(coordenada);
          if (x > 0 && x < tabuleiro.getSizeX() - 1)
            break;
          else {
            JOptionPane.showMessageDialog(null, "Coordenada fora dos limites do mapa");
          }
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(null, "Entrada inválida");
        }
      }
      while (true) {
        coordenada = JOptionPane.showInputDialog(jogadorAtual.getNome() + ", insira a coordenada Y do "
            + jogadorAtual.getPersonagens().get((int) (posPersonagemAtual / 2.0)).getNome());
        try {
          y = Integer.parseInt(coordenada);
          if (y > 0 && y < tabuleiro.getSizeY() - 1)
            break;
          else {
            JOptionPane.showMessageDialog(null, "Coordenada fora dos limites do mapa");
          }
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(null, "Entrada inválida");
        }
      }
      if (tabuleiro.getMapa()[x][y].equals(" ")) {
        jogadorAtual.getPersonagens().get((int) (posPersonagemAtual / 2)).setX(x);
        jogadorAtual.getPersonagens().get((int) (posPersonagemAtual / 2)).setY(y);
        posPersonagemAtual++;
        ehVezDeJ1 = !ehVezDeJ1;
      } else {
        JOptionPane.showMessageDialog(null, "A posição já está ocupada");
      }
      tabuleiro.printMapa(j1, j2);
    }
  }

  // Função de movimentar o personagem============================
  public static int movimentarPersonagem(Jogador j, Tabuleiro tabuleiro) {
    String opcPersonagem, coordenada;
    int i, opcPersonagemInt, coordenadaXInt, coordenadaYInt, distancia;

    i = 0;
    for (Personagem p : j.getPersonagens()) {
      System.out.println(i + " - " + p.getNome());
      i++;
    }
    opcPersonagem = JOptionPane.showInputDialog(null, j.getNome() + " escolha um personagem para movimentar");
    try {
      opcPersonagemInt = Integer.parseInt(opcPersonagem);
      if (!(opcPersonagemInt >= 0 && opcPersonagemInt < j.getPersonagens().size())) {
        JOptionPane.showMessageDialog(null, "Não existem personagens nessa posição");
        return 0;
      }
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(null, "Entrada inválida");
      return 0;
    }

    try {
      coordenada = JOptionPane.showInputDialog(null,
          "Insira a nova coordenada X do " + j.getPersonagens().get(opcPersonagemInt).getNome());
      coordenadaXInt = Integer.parseInt(coordenada);
      if (!(coordenadaXInt > 0 && coordenadaXInt < tabuleiro.getSizeX() - 1)) {
        JOptionPane.showMessageDialog(null, "Coordenada fora dos limites do mapa");
        return 0;
      }

      coordenada = JOptionPane.showInputDialog(null,
          "Insira a nova coordenada Y do " + j.getPersonagens().get(opcPersonagemInt).getNome());
      coordenadaYInt = Integer.parseInt(coordenada);
      if (!(coordenadaYInt > 0 && coordenadaYInt < tabuleiro.getSizeY() - 1)) {
        JOptionPane.showMessageDialog(null, "Coordenada fora dos limites do mapa");
        return 0;
      }

    } catch (Exception ex) {
      JOptionPane.showMessageDialog(null, "Entrada inválida");
      return 0;
    }

    distancia = calcularDistancia(j.getPersonagens().get(opcPersonagemInt).getX(),
        j.getPersonagens().get(opcPersonagemInt).getY(), coordenadaXInt, coordenadaYInt);

    if (distancia == 0) {
      JOptionPane.showMessageDialog(null, "Insira uma coordenada diferente da atual");
      return 0;
    } else if (distancia > j.getPersonagens().get(opcPersonagemInt).getAlcance()) {
      JOptionPane.showMessageDialog(null, "Alcance insuficiente");
      return 0;
    } else if (!tabuleiro.getMapa()[coordenadaXInt][coordenadaYInt].equals(" ")) {
      JOptionPane.showMessageDialog(null, "A posição já está ocupada");
      return 0;
    } else {
      tabuleiro.getMapa()[j.getPersonagens().get(opcPersonagemInt).getX()][j.getPersonagens().get(opcPersonagemInt)
          .getY()] = " ";
      j.getPersonagens().get(opcPersonagemInt).setX(coordenadaXInt);
      j.getPersonagens().get(opcPersonagemInt).setY(coordenadaYInt);
      return 1;
    }
  }

  // =======================Função da batalha===============
  public static void batalha(Jogador j1, Jogador j2, Tabuleiro tabuleiro, ArrayList<Item> sorteioItem) {
    System.out.println("=========================== HORA DE LUTAR ========================");

    String op, escolhido, atacado, aliado;
    int opInt, atacadoInt, aliadoInt;
    boolean ehVezDeJ1 = true, especialEspiaoEstaAtivado = false;
    Jogador jogadorAtual = j1, jogadorProxRodada = j2;
    ExplosaoAtomica explosao = new ExplosaoAtomica(-1, -1);

    while (j1.getPersonagens().size() != 0 || j2.getPersonagens().size() != 0) {
      if (passouTurno(ehVezDeJ1, jogadorAtual, j1, j2)) {
        if (especialEspiaoEstaAtivado) {
          ehVezDeJ1 = !ehVezDeJ1;
          especialEspiaoEstaAtivado = false;
        }
        for (Personagem p : jogadorAtual.getPersonagens()) {
          if (p.getCargaEspecial() > 0) {
            p.setCargaEspecial(p.getCargaEspecial() - 1);
          }
        }
        for (Personagem p : jogadorProxRodada.getPersonagens()) {
          if (p.getCargaEspecial() > 0) {
            p.setCargaEspecial(p.getCargaEspecial() - 1);
          }
        }
        if (explosao.getX() != -1) {
          explosao.darDanoArea(tabuleiro, j1, j2);
        }
      }
      if (ehVezDeJ1) {
        jogadorAtual = j1;
        jogadorProxRodada = j2;
      } else {
        jogadorAtual = j2;
        jogadorProxRodada = j1;
      }

      tabuleiro.printMapa(j1, j2);
      printaPersonagens(jogadorAtual);
      escolhido = JOptionPane.showInputDialog(
          jogadorAtual.getNome() + ", escolha o que fazer: 1-Movimentar/2-Ataque basico/3-Ataque especial");

      if (escolhido.equals("1")) {
        if(jogadorAtual.getInventario().size()>0){
          op = JOptionPane.showInputDialog(jogadorAtual.getNome() + ", deseja utilizar um item? (1-Sim/2-Não)");
          opInt=Integer.parseInt(op);
            if(opInt==1){
            utilizarItem(jogadorAtual, jogadorProxRodada);
          }
        }
        if (movimentarPersonagem(jogadorAtual, tabuleiro) == 1) {// Ação validada
          ehVezDeJ1 = !ehVezDeJ1;
        }
      } else if (escolhido.equals("2")) {
        if(jogadorAtual.getInventario().size()>0){
          op = JOptionPane.showInputDialog(jogadorAtual.getNome() + ", deseja utilizar um item? (1-Sim/2-Não)");
          opInt=Integer.parseInt(op);
            if(opInt==1){
            utilizarItem(jogadorAtual, jogadorProxRodada);
          }
        }
        printaPersonagens(jogadorAtual);
        op = JOptionPane.showInputDialog(jogadorAtual.getNome() + ", escolha qual personagem utilizar:");
        try {
          opInt = Integer.parseInt(op);
          if (opInt >= 0 && opInt < jogadorAtual.getPersonagens().size()) {
            printaPersonagens(jogadorProxRodada);
            atacado = JOptionPane.showInputDialog(jogadorAtual.getNome() + ", escolha qual personagem atacar:");
            atacadoInt = Integer.parseInt(atacado);
            if (atacadoInt >= 0 && atacadoInt < jogadorProxRodada.getPersonagens().size()) {
              if (calcularDistanciaPersonagens((jogadorAtual.getPersonagens().get(opInt)),
                  jogadorProxRodada.getPersonagens().get(atacadoInt)) <= jogadorAtual.getPersonagens().get(opInt)
                      .getAlcance()) {// Ação validada
                jogadorAtual.getPersonagens().get(opInt).atkBasico(jogadorProxRodada.getPersonagens().get(atacadoInt));
                mostraAtributos(jogadorAtual.getPersonagens().get(opInt),
                    jogadorProxRodada.getPersonagens().get(atacadoInt));
                if (jogadorProxRodada.getPersonagens().get(atacadoInt).getQuantVital() <= 0) {
                  tabuleiro.removerDoTabuleiro(jogadorProxRodada.getPersonagens().get(atacadoInt));
                  jogadorProxRodada.removerPersonagem(atacadoInt);
                  sorteioItens(jogadorAtual, sorteioItem);
                }
                ehVezDeJ1 = !ehVezDeJ1;
              } else {
                JOptionPane.showMessageDialog(null, "Alcance insuficiente");
              }
            } else {
              JOptionPane.showMessageDialog(null, "Não existem personagens nessa posição");
            }
          } else {
            JOptionPane.showMessageDialog(null, "Não existem personagens nessa posição");
          }
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(null, "Entrada inválida");
        }
      } else if (escolhido.equals("3")) {
        if(jogadorAtual.getInventario().size()>0){
          op = JOptionPane.showInputDialog(jogadorAtual.getNome() + ", deseja utilizar um item? (1-Sim/2-Não)");
          opInt=Integer.parseInt(op);
            if(opInt==1){
            utilizarItem(jogadorAtual, jogadorProxRodada);
          }
        }
        op = JOptionPane.showInputDialog(j1.getNome() + " escolha qual personagem utilizar:");
        try {
          opInt = Integer.parseInt(op);

          if (jogadorAtual.getPersonagens().get(opInt).getCargaEspecial() > 0) {
            JOptionPane.showMessageDialog(null, "Especial indisponível");
          } else if (opInt >= 0 && opInt < jogadorAtual.getPersonagens().size()) {
            if (jogadorAtual.getPersonagens().get(opInt) instanceof Cavaleiro
                || jogadorAtual.getPersonagens().get(opInt) instanceof Carcereiro) {
              printaPersonagens(jogadorProxRodada);
              atacado = JOptionPane.showInputDialog(jogadorAtual.getNome() + " escolha o personagem para atacar");
              atacadoInt = Integer.parseInt(atacado);
              if (atacadoInt >= 0 && atacadoInt < jogadorProxRodada.getPersonagens().size()) {
                if (calcularDistanciaPersonagens(jogadorAtual.getPersonagens().get(opInt),
                    jogadorProxRodada.getPersonagens().get(atacadoInt)) <= jogadorAtual.getPersonagens().get(opInt)
                        .getAlcance()) {// Ação validada
                  jogadorAtual.getPersonagens().get(opInt)
                      .atkEspecial(jogadorProxRodada.getPersonagens().get(atacadoInt));
                  if (jogadorProxRodada.getPersonagens().get(atacadoInt).getQuantVital() <= 0) {
                    tabuleiro.removerDoTabuleiro(jogadorProxRodada.getPersonagens().get(atacadoInt));
                    jogadorProxRodada.removerPersonagem(atacadoInt);
                    sorteioItens(jogadorAtual, sorteioItem);
                  }
                  ehVezDeJ1 = !ehVezDeJ1;
                } else {
                  JOptionPane.showMessageDialog(null, "Alcance insuficiente");
                }
              } else {
                JOptionPane.showMessageDialog(null, "Não existem personagens nessa posição");
              }
            } else if (jogadorAtual.getPersonagens().get(opInt) instanceof Cozinheiro
                || jogadorAtual.getPersonagens().get(opInt) instanceof Construtor) {
              printaPersonagens(jogadorAtual);
              if (jogadorAtual.getPersonagens().get(opInt).atkEspecial(jogadorAtual) == 1) // Ação validada
                ehVezDeJ1 = !ehVezDeJ1;
            } else if (jogadorAtual.getPersonagens().get(opInt) instanceof Pescador) {
              if (jogadorAtual.getPersonagens().get(opInt).atkEspecial(jogadorProxRodada) == 1) // Ação validada
                ehVezDeJ1 = !ehVezDeJ1;
            } else if (jogadorAtual.getPersonagens().get(opInt) instanceof Engenheiro) {
              printaPersonagens(jogadorProxRodada);
              atacado = JOptionPane.showInputDialog(jogadorAtual.getNome() + " escolha o personagem para atacar");
              atacadoInt = Integer.parseInt(atacado);
              if (atacadoInt >= 0 && atacadoInt < jogadorProxRodada.getPersonagens().size()) {
                printaPersonagens(jogadorAtual);
                aliado = JOptionPane.showInputDialog(jogadorAtual.getNome() + " escolha o personagem para beneficiar");
                aliadoInt = Integer.parseInt(aliado);
                if (aliadoInt >= 0 && aliadoInt < jogadorAtual.getPersonagens().size()) {// Ação validada
                  jogadorAtual.getPersonagens().get(opInt).atkEspecial(
                      jogadorProxRodada.getPersonagens().get(atacadoInt), jogadorAtual.getPersonagens().get(aliadoInt));
                  if (jogadorProxRodada.getPersonagens().get(atacadoInt).getQuantVital() <= 0) {
                    tabuleiro.removerDoTabuleiro(jogadorProxRodada.getPersonagens().get(atacadoInt));
                    jogadorProxRodada.removerPersonagem(atacadoInt);
                    sorteioItens(jogadorAtual, sorteioItem);
                  }
                  ehVezDeJ1 = !ehVezDeJ1;
                } else {
                  JOptionPane.showMessageDialog(null, "Não existem personagens nessa posição");
                }
              } else {
                JOptionPane.showMessageDialog(null, "Não existem personagens nessa posição");
              }
            } else if (jogadorAtual.getPersonagens().get(opInt) instanceof Comandante) {
              if (jogadorAtual.getPersonagens().get(opInt).getCargaEspecial() == -1) {
                JOptionPane.showMessageDialog(null, "O comandante pode usar a bomba atômica apenas uma vez na partida");
              } else {
                printaPersonagens(jogadorProxRodada);
                atacado = JOptionPane.showInputDialog(jogadorAtual.getNome() + " escolha o personagem para atacar");
                atacadoInt = Integer.parseInt(atacado);
                if (atacadoInt >= 0 && atacadoInt < jogadorProxRodada.getPersonagens().size()) {// Ação validada
                  jogadorAtual.getPersonagens().get(opInt)
                      .atkEspecial(jogadorProxRodada.getPersonagens().get(atacadoInt), explosao);
                  if (jogadorProxRodada.getPersonagens().get(atacadoInt).getQuantVital() <= 0) {
                    tabuleiro.removerDoTabuleiro(jogadorProxRodada.getPersonagens().get(atacadoInt));
                    jogadorProxRodada.removerPersonagem(atacadoInt);
                    sorteioItens(jogadorAtual, sorteioItem);
                  }
                  ehVezDeJ1 = !ehVezDeJ1;
                } else {
                  JOptionPane.showMessageDialog(null, "Não existem personagens nessa posição");
                }
              }
            } else if (jogadorAtual.getPersonagens().get(opInt) instanceof Padre) {
              if (jogadorAtual.getPersonagens().get(opInt).getCargaEspecial() == -1) {
                JOptionPane.showMessageDialog(null, "O padre pode converter apenas 1 inimigo");
              } else if (jogadorAtual.getPersonagens().get(opInt).atkEspecial(jogadorProxRodada, jogadorAtual) == 1) {// Ação
                                                                                                                      // validada
                ehVezDeJ1 = !ehVezDeJ1;
              }
            } else if (jogadorAtual.getPersonagens().get(opInt) instanceof Espiao) {// Ação validada
              jogadorAtual.getPersonagens().get(opInt).atkEspecial();
              especialEspiaoEstaAtivado = true;
            }
          } else {
            JOptionPane.showMessageDialog(null, "Não existem personagens nessa posição");
            ehVezDeJ1 = !ehVezDeJ1;
          }
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(null, "Entrada inválida");
        }
      } else {
        JOptionPane.showMessageDialog(null, "Entrada inválida");
      }

      tabuleiro.printMapa(j1, j2);
    }
  }

  public static void menuVencedor(Jogador j) {
    borda();
    System.out.println("Parabéns " + j.getNome() + " !");
    System.out.println("Você é o grande campeão!");
    borda();
  }

  public static void verificaVencedor(Jogador j1, Jogador j2) {
    if (j1.getPersonagens().size() == 0) {
      menuVencedor(j2);
    } else
      menuVencedor(j1);
  }

  // =====================================================================================
  public static void main(String[] args) {
    // Variáveis do mapa=====================================
    Tabuleiro tabuleiro;
    int tamanhoMapa = 0;
    String escolheTamanhoMapa;
    // ======================================================
    // Variáveis dos jogadores===============================
    Jogador j1 = new Jogador(JOptionPane.showInputDialog("Insira o nome do jogador 1:"));
    Jogador j2 = new Jogador(JOptionPane.showInputDialog("Insira o nome do jogador 2:"));
    // ======================================================
    // Array da selecao de personagens=======================
    ArrayList<Personagem> selecaoPersonagens = new ArrayList<Personagem>();

    selecaoPersonagens.add(new Carcereiro());
    selecaoPersonagens.add(new Padre());
    selecaoPersonagens.add(new CientistaMaluco());
    selecaoPersonagens.add(new Comandante());
    selecaoPersonagens.add(new Construtor());
    selecaoPersonagens.add(new Cavaleiro());
    selecaoPersonagens.add(new Cozinheiro());
    selecaoPersonagens.add(new Engenheiro());
    selecaoPersonagens.add(new Espiao());
    selecaoPersonagens.add(new Pescador());
    // Array da selecao de itens
    ArrayList<Item> sorteioItem = new ArrayList<Item>();

    sorteioItem.add(new Veneno());
    sorteioItem.add(new Couraca());
    sorteioItem.add(new Espada());
    sorteioItem.add(new Estilingue());
    sorteioItem.add(new Porrete());
    sorteioItem.add(new Pocao());
    // ======================================================
    // Configurando o mapa===================================
    while (tamanhoMapa == 0) {
      escolheTamanhoMapa = JOptionPane.showInputDialog("Digite o tamanho do mapa: (1 - 8x8) (2 - 10x10) (3 - 12x12)");
      switch (escolheTamanhoMapa) {
      case "1":
        tamanhoMapa = 8;
        break;
      case "2":
        tamanhoMapa = 10;
        break;
      case "3":
        tamanhoMapa = 12;
        break;
      default:
        JOptionPane.showMessageDialog(null, "Entrada inválida");
      }
    }
    tabuleiro = new Tabuleiro(tamanhoMapa, tamanhoMapa);
    System.out.printf(tabuleiro.toString());
    // ======================================================
    // Sorteio dos jogadores e escolha dos personagens=======
    Random gerador = new Random();
    int sorteio = gerador.nextInt();

    if (sorteio % 2 == 0) {// Jogador 1 foi o sorteado
      escolhaPersonagens(j1, j2, selecaoPersonagens);
    } else {// Jogador 2 foi o sorteado
      escolhaPersonagens(j2, j1, selecaoPersonagens);
    }
    // ======================================================
    // Posicionamento dos personagens========================
    if (sorteio % 2 == 0) {// Jogador 1 foi o sorteado
      posicionarPersonagens(j1, j2, tabuleiro);
    } else {// Jogador 2 foi o sorteado
      posicionarPersonagens(j2, j1, tabuleiro);
    }
    // ======================================================
    if (sorteio % 2 == 0)
      batalha(j1, j2, tabuleiro, sorteioItem);
    else
      batalha(j2, j1, tabuleiro, sorteioItem);
    verificaVencedor(j1, j2);
  }
}
