package turtletatics.classesJogo.funcionalidades;

import javax.swing.JOptionPane;

import turtletatics.classesJogo.personagens.Personagem;

public class ExplosaoAtomica {

    private int x, y;
    private double radioatividade;

    public ExplosaoAtomica(int x, int y) {
        this.x = x;
        this.y = y;
        radioatividade = 5.0;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void darDanoArea() {
        int i;
        Personagem p;

        for (i = 0; i < Main.j1.getPersonagens().size(); i++) {
            p = Main.j1.getPersonagens().get(i);
            if (Main.calcularDistancia(p.getX(), p.getY(), this.x, this.y) <= 1) {
                JOptionPane.showMessageDialog(null, p.getNome() + " recebeu dano de radiação", "Radiação", JOptionPane.INFORMATION_MESSAGE);
                p.setQuantVital(p.getQuantVital() - 6 * this.radioatividade);
                if (p.getQuantVital() <= 0) {
                    JOptionPane.showMessageDialog(null, p.getNome() + " morreu", "Radiação", JOptionPane.INFORMATION_MESSAGE);
                    p.getImagem().setVisible(false);
                    p.getImagem().disableProperty().set(true);
                    Main.persJ1.remove(p.getImagem());
                    Main.j1.getPersonagens().remove(p);
                    i--;
                }
            }
        }

        for (i = 0; i < Main.j2.getPersonagens().size(); i++) {
            p = Main.j2.getPersonagens().get(i);
            if (Main.calcularDistancia(p.getX(), p.getY(), this.x, this.y) <= 1) {
                JOptionPane.showMessageDialog(null, p.getNome() + " recebeu dano de radiação", "Radiação", JOptionPane.INFORMATION_MESSAGE);
                p.setQuantVital(p.getQuantVital() - 6 * this.radioatividade);
                if (p.getQuantVital() <= 0) {
                    JOptionPane.showMessageDialog(null, p.getNome() + " morreu", "Radiação", JOptionPane.INFORMATION_MESSAGE);
                    p.getImagem().setVisible(false);
                    p.getImagem().disableProperty().set(true);
                    Main.persJ2.remove(p.getImagem());
                    Main.j2.getPersonagens().remove(p);
                    i--;
                }
            }
        }
        this.radioatividade /= 1.07;
    }
}
