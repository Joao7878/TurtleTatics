package turtletatics.classesJogo.personagens;

import java.util.Random;
import javafx.scene.image.Image;
import javax.swing.JOptionPane;

public class Construtor extends Personagem {

    public Construtor() {
        super("Construtor", 120, 25, 2, 30, new Image("turtletatics/view/imagens/imagensPersonagens/Construtor.png"));
    }

    @Override
    public void atkEspecial(Personagem pAliado) {
        Random gerador = new Random();
        double numReal = gerador.nextDouble();
        if (numReal > 0.5) {
            pAliado.setQuantAtaque(pAliado.getQuantAtaque() + 15);
            //JOptionPane.showMessageDialog(null, "O " + aliados.getPersonagens().get(opcPersonagemInt).getNome()
            //    + " teve suas armas melhoradas, ganhando 15 de ataque.");
            JOptionPane.showMessageDialog(null, "O " + pAliado.getNome() + " teve suas armas melhoradas, ganhando 15 de ataque.", pAliado.getNome() + " ganhou ataque", JOptionPane.INFORMATION_MESSAGE);
        } else {
            pAliado.setValorDefesa(pAliado.getValorDefesa() + 10);
            //JOptionPane.showMessageDialog(null, "O " + aliados.getPersonagens().get(opcPersonagemInt).getNome()
            //    + " teve suas armadura melhorada, ganhando 10 de defesa.");
            JOptionPane.showMessageDialog(null, "O " + pAliado.getNome() + " teve sua armadura melhorada, ganhando 10 de defesa.", pAliado.getNome() + " ganhou ataque", JOptionPane.INFORMATION_MESSAGE);
        }
        this.setCargaEspecial(8);
    }
}
