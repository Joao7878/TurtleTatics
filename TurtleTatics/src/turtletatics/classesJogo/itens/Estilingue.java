package turtletatics.classesJogo.itens;

import javax.swing.JOptionPane;

import turtletatics.classesJogo.personagens.Personagem;

public class Estilingue extends Item {

    public Estilingue() {
        super("Estilingue", 3);
    }

    @Override
    public void efeito(Personagem Pafetado) {
        JOptionPane.showMessageDialog(null, Pafetado.getNome() + " recebeu 10 pontos de ataque", "Utilização de item", JOptionPane.INFORMATION_MESSAGE);
        Pafetado.setQuantAtaque(Pafetado.getQuantAtaque() + 10);
        this.setDurabilidade(this.getDurabilidade() - 1);
    }
}
