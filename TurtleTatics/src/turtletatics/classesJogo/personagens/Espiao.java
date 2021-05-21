package turtletatics.classesJogo.personagens;

import javax.swing.JOptionPane;

public class Espiao extends Personagem {
  public Espiao() {
    super("Espião", 100, 40, 2, 25);
  }

  @Override
  public void atkEspecial() {
    JOptionPane.showMessageDialog(null,
        this.getNome() + " sabotou as cominicações do inimigo, deixando-lhes confusos. Você pode jogar novamente");
    this.setCargaEspecial(6);
  }
}
// Habilidade especial: Faz o adversário perder a vez