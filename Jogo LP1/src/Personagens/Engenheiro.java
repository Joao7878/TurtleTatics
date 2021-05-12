package Personagens;

public class Engenheiro extends Personagem {
  public Engenheiro() {
    super("Engenheiro", 120, 40, 3, 30);
  }

  @Override
  public void atkEspecial(Personagem pAtacado, Personagem pAliado) {
    pAliado.setQuantVital(pAliado.getQuantVital() + 20);
    pAtacado.setQuantVital(pAtacado.getQuantVital() - 20);
    this.setCargaEspecial(3);
  }
}
