package principal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Boolean bool;
        Byte byt;
        Short shor;
        Integer entier;
        Long lon;

        Float d;
        Double dd;

        BigDecimal a;

        String s = "ma chaine";
        Character c = 'c';

       // System.out.println(c + " " + (int)c);

        Integer[] tab = new Integer[25];
        List<Object> liste = new ArrayList<>();

        String elt = ""+tab[0];

       // Integer eltInteger = Integer.valueOf(elt);

        Integer res = new Main().fact(12);
        System.out.println(res);

        String chaine1 = "toto";
        String chaine2 = "toto";

        System.out.println(new String(chaine1) == new String(chaine2));
    }

    public static Integer fact(Integer v){
        /*Integer res = 1;
        for(int i = 1 ; i <= v ; i++){
            res = res * i;
        }
        return res;
        */


        if(v == 0){
            return 1;
        }
        return v * fact(v-1);
    }
}
