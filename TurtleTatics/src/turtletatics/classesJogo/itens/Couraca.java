package turtletatics.classesJogo.itens;

import javax.swing.JOptionPane;

import turtletatics.classesJogo.personagens.Personagem;

public class Couraca extends Item {

    public Couraca() {
        super("Couraça", 3);
    }

    @Override
    public void efeito(Personagem Pafetado) {
        JOptionPane.showMessageDialog(null, Pafetado.getNome() + " recebeu 25 pontos de defesa", "Utilização de item", JOptionPane.INFORMATION_MESSAGE);
        Pafetado.setValorDefesa(Pafetado.getValorDefesa() + 25);
        this.setDurabilidade(this.getDurabilidade() - 1);
    }
}
