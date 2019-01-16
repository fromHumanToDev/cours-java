package packageUn;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProgrammePrincipal {
    public static void main(String[] args) throws IOException {

        FileWriter writer = new FileWriter("/tmp/cyril.txt", false);
        writer.write("12 30");
        writer.flush();
        writer.close();

        Scanner scanner = new Scanner(new File("/tmp/cyril.txt"));
        Integer premier = scanner.nextInt();
        Integer deuxieme = scanner.nextInt();
        scanner.close();

        System.out.println(premier + " --> " + deuxieme);
        /*
        Chien chien = new Chien(5);
        Deplacable baleine = new Baleine(100);
        Deplacable chat = new Chat(20);
        chien.parler();

        action(chien);
        action(baleine);
        action(chat);

        */

        List<Integer> liste1 = new ArrayList<>();
        liste1.add(1);
        liste1.add(65);
        liste1.add(32);

        List<Integer> liste2 = new LinkedList<>();
        liste2.addAll(liste1);

        afficherList(liste1);

        afficherList(liste2);

        List<Double> liste3 = new LinkedList<>();
        liste3.add(3.0);
        liste3.add(4d);
        liste3.add(-2d);

        somme(liste1);
        somme(liste2);
        somme(liste3);


        liste3.parallelStream().forEach((Double d) -> {
            //TODO faire qqchose
        });


    }

    public static void action(Deplacable animal){
        animal.seDeplacer();
        animal.seNourrir();
    }

    public static void afficherList(List<? extends Object> liste){

        for(Object o : liste){
            System.out.println(o);
        }
    }

    public static void somme(List<? extends Number> liste){
        Double res = 0d;

        for(Number number : liste){
            res = res + number.doubleValue();
        }
        System.out.println(res);
    }

}
