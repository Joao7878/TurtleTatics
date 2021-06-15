package turtletatics.classesJogo.personagens;

import javafx.scene.image.Image;
import javax.swing.JOptionPane;

public class Carcereiro extends Personagem {

    public Carcereiro() {
        super("Carcereiro", 250, 50, 1, 25, new Image("turtletatics/view/imagens/imagensPersonagens/Carcereiro.png"));
    }

    @Override
    public void atkEspecial(Personagem pAtacado) {
        pAtacado.setValorDefesa(pAtacado.getValorDefesa() / 2);
        pAtacado.setQuantVital(pAtacado.getQuantVital() - 30);
        JOptionPane.showMessageDialog(null, pAtacado.getNome() + " recebeu um golpe de cassetete do " + this.getNome(), pAtacado.getNome() + " atacado", JOptionPane.INFORMATION_MESSAGE);
        this.setCargaEspecial(3);
    }
}
/*
 * habilidade especial: dar um golpe com o cassetete
 * tirando pela metade a defesa do atacado além de 30 de vida do mesmo
 */