package fr.coursjava.basketball.view;

import fr.coursjava.basketball.model.Man;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Screen extends JFrame implements Runnable {

    private ImagePanel p;
    private Man man;

    public Screen(Man man) throws IOException {
        this.man = man;
        super.setSize(200, 400);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);
        p = new ImagePanel(this.man.getNextImage());

        p.setBackground(Color.CYAN);
        getContentPane().add(p);
        p.setBounds(0,0,142,400);


        super.setVisible(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_SPACE){
                    man.jump();
                    man.computeNextImage();
                }
            }
        });


    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(100);
            }catch(Exception e){

            }

            p.setImage(man.getNextImage());
            p.repaint();

            if(man.isJumping()){
                p.setAir();
            }else{
                p.setGround();
            }

        }
    }
}
