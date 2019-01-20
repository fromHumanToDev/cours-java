package fr.coursjava.basketball;

import fr.coursjava.basketball.model.Man;
import fr.coursjava.basketball.view.Screen;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        Man man = new Man(loadSprites("ground"), loadSprites("air"));
        Thread thread = new Thread(new Screen(man));
        thread.start();
    }

    private static Image[] loadSprites(String pattern) throws IOException {
        Map<Integer, Image> images = new HashMap<>();

        int i = 0;
        URL elt;
        while ((elt = Main.class.getClassLoader().getResource(pattern + "-" + i + ".png")) != null) {
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
