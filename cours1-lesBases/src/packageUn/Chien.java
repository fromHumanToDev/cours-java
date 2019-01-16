package packageUn;

public class Chien extends Animal {

    private String nom;

    public Chien(Integer age){
        super(age);
        this.nom = "medort";
    }

    @Override
    public void boire() {
        System.out.println("lepe");
    }


    public void parler(){
        System.out.println("coucou je suis " + this.nom);
    }

    @Override
    public void seDeplacer(){
        System.out.println("courir");
    }


}

