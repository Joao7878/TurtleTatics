package turtletatics.classesJogo.personagens;

import javafx.scene.image.Image;
import javax.swing.JOptionPane;

import turtletatics.classesJogo.funcionalidades.Jogador;
import turtletatics.classesJogo.itens.Item;

public class Pescador extends Personagem {

    public Pescador() {
        super("Pescador", 150, 22, 4, 39, new Image("turtletatics/view/imagens/imagensPersonagens/Pescador.png"));
    }

    @Override
    public void atkEspecial(Item itemAtacado, Jogador jogAtacado) {
        jogAtacado.getInventario().remove(itemAtacado);
        JOptionPane.showMessageDialog(null, "O pescador fisgou " + itemAtacado.getNome() + ", destruindo esse item", "Ataque especial", JOptionPane.INFORMATION_MESSAGE);
        this.setCargaEspecial(12);
    }
}
/*
 * habilidade especial: Lançar a vara no inigimo e destruir um item escolhido
 */
