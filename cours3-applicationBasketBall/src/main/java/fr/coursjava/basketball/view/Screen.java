package fr.coursjava.basketball.view;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Screen extends JFrame {

    private Image[] onGroupImage;
    private Image[] onAirImage;

    public Screen() throws IOException {
        super.setSize(200, 400);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);
        loadSprites();
        ImagePanel p = new ImagePanel(onGroupImage[0]);

        p.setBackground(Color.CYAN);
        getContentPane().add(p);
        p.setBounds(0,0,142,400);


        super.setVisible(true);

        int current = 0;
        Image[] tmp = onGroupImage;
        for(int i = 1 ; i < 1000 ; i++){
            int index = i%tmp.length;
            if(index == 0){
                if(onGroupImage.equals(tmp)){
                    tmp = onAirImage;
                    p.setAir();
                }else{
                    tmp = onGroupImage;
                    p.setGround();
                }
            }
            Image next = tmp[index];
            p.setImage(next);
            p.repaint();
            try {
                Thread.sleep(100);
            }catch(Exception e){

            }
        }
    }

    private void loadSprites() throws IOException {
        onGroupImage = loadSprites("ground");
        onAirImage = loadSprites("air");
    }


    private Image[] loadSprites(String pattern) throws IOException {
        Map<Integer, Image> images = new HashMap<>();

        int i = 0;
        URL elt;
        while ((elt = getClass().getClassLoader().getResource(pattern + "-" + i + ".png")) != null) {
            images.put(images.size(), Toolkit.getDefaultToolkit().getImage(elt));
            i++;
        }

        Image[] result = new Image[images.size()];

        for (Map.Entry<Integer, Image> entry : images.entrySet()) {
            result[entry.getKey()] = entry.getValue();
        }

        return result;
    }
}
