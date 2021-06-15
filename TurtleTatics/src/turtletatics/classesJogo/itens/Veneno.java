package turtletatics.classesJogo.itens;

import javax.swing.JOptionPane;

import turtletatics.classesJogo.personagens.Personagem;

public class Veneno extends Item {

    public Veneno() {
        super("Veneno", 1);
    }

    @Override
    public void efeito(Personagem Pafetado) {
        JOptionPane.showMessageDialog(null, Pafetado.getNome() + " perdeu 30 pontos de vida", "Utilização de item", JOptionPane.INFORMATION_MESSAGE);
        Pafetado.setQuantVital(Pafetado.getQuantVital() - 30);
        this.setDurabilidade(this.getDurabilidade() - 1);
    }
}
