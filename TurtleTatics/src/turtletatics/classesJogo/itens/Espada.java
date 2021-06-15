package turtletatics.classesJogo.itens;

import javax.swing.JOptionPane;

import turtletatics.classesJogo.personagens.Personagem;

public class Espada extends Item {

    public Espada() {
        super("Espada", 3);
    }

    @Override
    public void efeito(Personagem Pafetado) {
        JOptionPane.showMessageDialog(null, Pafetado.getNome() + " recebeu 15 pontos de ataque", "Utilização de item", JOptionPane.INFORMATION_MESSAGE);
        Pafetado.setQuantAtaque(Pafetado.getQuantAtaque() + 15);
        this.setDurabilidade(this.getDurabilidade() - 1);
    }
}
