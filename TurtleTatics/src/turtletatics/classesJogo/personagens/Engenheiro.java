package turtletatics.classesJogo.personagens;

import javafx.scene.image.Image;

public class Engenheiro extends Personagem {

    public Engenheiro() {
        super("Engenheiro", 120, 40, 3, 30, new Image("turtletatics/view/imagens/imagensPersonagens/Engenheiro.png"));
    }

    @Override
    public void atkEspecial(Personagem pAtacado, Personagem pAliado) {
        pAliado.setQuantVital(pAliado.getQuantVital() + 20);
        pAtacado.setQuantVital(pAtacado.getQuantVital() - 20);
        this.setCargaEspecial(6);
    }
}

// habilidade especial: Rouba 20 de vida de um inimigo para
// dar para ele mesmo ou um aliado