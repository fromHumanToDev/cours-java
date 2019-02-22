package fr.coursjava.detectioncycle;

import java.util.*;

public class Main {

    public static void main2(String[] args) {
        System.out.println("coucou");
    }

    public static void main(String[] args) throws Exception {

        String startPattern = "https://www.monsite.com";

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("/input.csv"));

        Map<String, String> correspondances = new HashMap<>();

        while(scanner.hasNext()){
            String line = scanner.nextLine();
            String[] tableau = line.split(";");

            /*
            if(correspondances.containsKey(tableau[1])){
                System.out.println("doublon sur la cle :" + tableau[1]);
            }
            */
            correspondances.put(tableau[1].replaceAll("\"", ""), tableau[2].replace(startPattern, "").replaceAll("\"", ""));
        }

        System.out.println("taille apres dedoublonnage : " + correspondances.size());


        Map<String, CycleDetection> avecCycle = new HashMap<>();

        for(String entry : correspondances.keySet()){
            CycleDetection detection = isCycle(correspondances, entry);
            if(detection.isCycle()){
               avecCycle.put(entry, detection);
            }
        }

        System.out.println("Affichage des dernieres redirections avant presence d'un cycle");
        for(Map.Entry<String, CycleDetection> entry : avecCycle.entrySet()){
            System.out.println(entry.getKey() + " " + startPattern + entry.getValue().getDernierAvantCycle());
        }
    }

    private static CycleDetection isCycle(Map<String, String> correspondances, String key){
        CycleDetection resultat = new CycleDetection();
        resultat.setCycle(false);


        Set<String> liensParcourus = new HashSet<>();

        liensParcourus.add(key);

        while(correspondances.containsKey(key)){
            String savedKey = key;

            key = correspondances.get(key);
            if(liensParcourus.contains(key)){
                resultat.setCycle(true);
                resultat.setDernierAvantCycle(savedKey);
                break;
            }else{
                liensParcourus.add(key);
            }
        }

        return resultat;
    }
}
