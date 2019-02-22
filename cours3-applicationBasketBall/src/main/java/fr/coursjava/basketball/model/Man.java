package fr.coursjava.basketball.model;

import java.awt.*;

public class Man {

    private Image[] ground;
    private Image[] air;
    private Direction direction;
    private boolean isJumping;

    private Image nextImage;
    private Integer pos;

    public Man(Image[] ground, Image[] air) {
        this.ground = ground;
        this.air = air;
        this.direction = Direction.RIGHT;
        this.isJumping = false;
        this.pos = 0;
        this.nextImage = ground[this.pos];
    }

    public Image getNextImage() {
        Image img = this.nextImage;
        computeNextImage();
        return img;
    }

    public boolean isJumping() {
        return this.isJumping;
    }

    public void jump() {
        if (Direction.RIGHT.equals(this.direction)) {
            this.direction = Direction.JUMP_RIGHT;
        } else {
            this.direction = Direction.JUMP_LEFT;
        }
        this.isJumping = true;
        this.pos = 0;
    }

    public void computeNextImage() {
        System.out.println("nextImage");
        if(this.direction.toString().endsWith("RIGHT")){
            System.out.println("droite");
            this.pos++;
        }else{
            System.out.println("gauche");
            this.pos--;
        }

        if(this.direction.toString().startsWith("JUMP")){
            System.out.println("saut");
            this.nextImage = air[pos%air.length];
            if(pos%air.length == 0){
                this.pos = 0;
            }
        }else{
            System.out.println("sol");
            this.nextImage = ground[pos%ground.length];
            if(pos%ground.length == 0){
                this.pos = 0;
            }
        }

        if(this.pos == 0){
            System.out.println("retour a 0");
        }
        if(this.pos == 0 && isJumping()){
            this.isJumping = false;
            System.out.println("fin du saut");
            this.direction = Direction.valueOf(this.direction.name().substring(5));
        }
    }
}
