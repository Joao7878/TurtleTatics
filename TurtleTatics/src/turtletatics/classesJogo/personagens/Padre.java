package turtletatics.classesJogo.personagens;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.image.Image;
import javax.swing.JOptionPane;

public class Padre extends Personagem {
  private int quantTentativas;

  public Padre() {
    super("Padre", 100, 15, 4, 15, new Image("turtletatics/view/imagens/imagensPersonagens/Padre.png"));
    quantTentativas = 0;
  }

  public int getQuantTentativas() {
    return this.quantTentativas;
  }

  @Override
  public boolean atkEspecial(Personagem pAtacado, ArrayList<Personagem> aliados, ArrayList<Personagem> inimigos) {
    Random gerador = new Random();
    double numReal = gerador.nextDouble();
    
    if (numReal <= 0.25) {// Conversão realizada com sucesso
      //JOptionPane.showMessageDialog(null, "O " + this.getNome() + " conseguiu converter o "
      //    + inimigos.getPersonagens().get(opcPersonagemInt).getNome());
      JOptionPane.showMessageDialog(null, "O " + this.getNome() + " conseguiu converter o "
          + pAtacado.getNome(), pAtacado.getNome() + " atacado", JOptionPane.INFORMATION_MESSAGE);
      aliados.add(pAtacado);
      inimigos.remove(pAtacado);
      this.setCargaEspecial(-2);
      
      return true;
    } else {// Conversão falhou
      this.quantTentativas++;
      if(this.quantTentativas == 5) {
          this.setCargaEspecial(-1);
          JOptionPane.showMessageDialog(null, "A tentativa de conversão falhou. Não restam mais tentativas", pAtacado.getNome() + " atacado", JOptionPane.INFORMATION_MESSAGE);
          return false;
      }
      
      //JOptionPane.showMessageDialog(null,
      //    "A tentativa de conversão falhou. Tentativas restantes: " + (5 - this.getQuantTentativas()));
      JOptionPane.showMessageDialog(null, "A tentativa de conversão falhou. Tentativas restantes: " + (5 - this.getQuantTentativas()), pAtacado.getNome() + " atacado", JOptionPane.INFORMATION_MESSAGE);
      this.setCargaEspecial(4);
      
      return false;
    }
  }
}

// habilidade especial: Tenta converter um inimigo dentro do seu raio de ação em
// aliado
// permanentemente.
// Ao tentar conveter um inimigo, o padre tem 25% de chance de sucesso
// O padre pode tentar realizar a conversão até 5 vezes durante a partida
// Caso a conversão seja bem sucessida, o padre não poderá mais utilizar a
// habilidade especial
