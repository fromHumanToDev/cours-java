package fr.coursjava.calculatrice;

import fr.coursjava.calculatrice.model.Calculatrice;
import fr.coursjava.calculatrice.view.Screen;

public class Controller {

    private Calculatrice calculatrice;
    private Screen screen;

    public Controller(Calculatrice calculatrice, Screen screen) {
        this.calculatrice = calculatrice;
        this.screen = screen;
        this.screen.setController(this);
    }

    public void calculer(Integer premier, String operation, Integer deuxieme) {
        Integer res;
        if("+".equals(operation)){
            res = calculatrice.add(premier, deuxieme);
        } else if("-".equals(operation)){
            res = calculatrice.sub(premier, deuxieme);
        }else if("*".equals(operation)){
            res = calculatrice.multiply(premier, deuxieme);
        }else{
            //divide
            try {
                res = calculatrice.divide(premier, deuxieme);
            }catch(ArithmeticException e){
                this.screen.setResultat(e.getMessage());
                return;
            }
        }

        this.screen.setResultat(res.toString());
    }
}
