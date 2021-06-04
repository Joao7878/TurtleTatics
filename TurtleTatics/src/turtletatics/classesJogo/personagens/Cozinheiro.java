package turtletatics.classesJogo.personagens;

import javafx.scene.image.Image;
import javax.swing.JOptionPane;

public class Cozinheiro extends Personagem {

    public Cozinheiro() {
        super("Cozinheiro", 220, 50, 1, 20, new Image("turtletatics/view/imagens/imagensPersonagens/Cozinheiro.png"));
    }

    @Override
    public void atkEspecial(Personagem pAliado) {
        pAliado.setQuantVital(pAliado.getQuantVital() + 20);
        pAliado.setValorDefesa(pAliado.getValorDefesa() + 5);

        JOptionPane.showMessageDialog(null, "O " + pAliado.getNome() + " ganhou 20 de vida e 5 de defesa.", pAliado.getNome() + " curado", JOptionPane.INFORMATION_MESSAGE);

        this.setCargaEspecial(4);
    }
}
// habilidade especial: Dar a gororoba a si mesmo ou a um aliado, dando 20
// pontos de vida e 5 pontos de defesa. Não existe limite de alcançe para esse
// ataque especial
