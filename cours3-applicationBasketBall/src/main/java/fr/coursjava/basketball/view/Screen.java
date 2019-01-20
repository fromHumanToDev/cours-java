package fr.coursjava.basketball.view;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class Screen extends JFrame {

    private Image[] onGroupImage;
    private Image[] onAirImage;
    private JPanel panel;

    public Screen() throws IOException {
        super.setSize(300, 300);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        GridLayout gridlayout = new GridLayout(1,1);
        panel.setLayout(gridlayout);
        add(panel);

        loadSprites();
        ImagePanel p = new ImagePanel(onGroupImage[0]);
        panel.add(p);


        super.setVisible(true);



        int current = 0;
        for(int i = 1 ; i < 10 ; i++){
            Image next = onGroupImage[i%onGroupImage.length];
            p.setImage(next);
            p.repaint();
            try {
                Thread.sleep(1000);
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
