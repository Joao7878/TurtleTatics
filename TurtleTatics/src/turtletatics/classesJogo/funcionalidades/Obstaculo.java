package turtletatics.classesJogo.funcionalidades;

import javafx.scene.image.ImageView;

public class Obstaculo {

    private int x;
    private int y;
    private ImageView imagem;

    public Obstaculo(int x, int y, ImageView im) {
        this.x = x;
        this.y = y;
        this.imagem = im;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public ImageView getImagem() {
        return this.imagem;
    }
}
