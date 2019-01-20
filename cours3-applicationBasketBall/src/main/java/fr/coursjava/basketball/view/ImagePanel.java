package fr.coursjava.basketball.view;

import java.awt.*;
import javax.swing.*;
import java.io.*;

public class ImagePanel extends JPanel implements Serializable {
    Image image = null;
    public ImagePanel(Image image) {
        this.image = image;
    }
    public ImagePanel() {
    }
    public void setImage(Image image){
        this.image = image;
    }
    public Image getImage(Image image){
        return image;
    }

    Integer x, y;
    public void setGround(){
        x = 0;
        y = 400 - 210 - 40;
    }

    public void setAir(){
        x = 0;
        y = 400 - 210 - 100;
    }

    @Override
    public void paintComponent(Graphics g) {
        if(x == null){
            setGround();
        }
        super.paintComponent(g); //paint background
        if (image != null) { //there is a picture: draw it
          g.drawImage(image,x,y, 142, 210, this);
        }
    }

}

