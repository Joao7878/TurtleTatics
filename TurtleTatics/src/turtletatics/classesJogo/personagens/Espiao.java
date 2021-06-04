package turtletatics.classesJogo.personagens;

import javafx.scene.image.Image;
import javax.swing.JOptionPane;

public class Espiao extends Personagem {
  public Espiao() {
    super("Espião", 100, 40, 2, 25, new Image("turtletatics/view/imagens/imagensPersonagens/Espiao.png"));
  }

  @Override
  public void atkEspecial() {
    //JOptionPane.showMessageDialog(null,
    //    this.getNome() + " sabotou as cominicações do inimigo, deixando-lhes confusos. Você pode jogar novamente");
    JOptionPane.showMessageDialog(null, this.getNome() + " sabotou as cominicações do inimigo, deixando-lhes confusos. Você pode jogar 2 vezes", "Ataque especial", JOptionPane.INFORMATION_MESSAGE);
    this.setCargaEspecial(6);
  }
}
// Habilidade especial: Faz o adversário perder a vez