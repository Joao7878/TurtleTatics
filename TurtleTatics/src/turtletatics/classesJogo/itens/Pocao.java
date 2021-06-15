package turtletatics.classesJogo.itens;

import javax.swing.JOptionPane;

import turtletatics.classesJogo.personagens.Personagem;

public class Pocao extends Item {

    public Pocao() {
        super("Poção de Cura", 2);
    }

    @Override
    public void efeito(Personagem Pafetado) {
        JOptionPane.showMessageDialog(null, Pafetado.getNome() + " recebeu 25 pontos de vida", "Utilização de item", JOptionPane.INFORMATION_MESSAGE);
        Pafetado.setQuantVital(Pafetado.getQuantVital() + 25);
        this.setDurabilidade(this.getDurabilidade() - 1);
    }
}
