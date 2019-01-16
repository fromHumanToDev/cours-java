package fr.coursjava.calculatrice;

import fr.coursjava.calculatrice.model.Calculatrice;
import fr.coursjava.calculatrice.view.Screen;

public class Main {

    public static void main(String[] args) {
        Calculatrice calculatrice = new Calculatrice();
        Screen screen = new Screen();
        new Controller(calculatrice, screen);
    }



}
