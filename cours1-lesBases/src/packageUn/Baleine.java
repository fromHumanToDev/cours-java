package packageUn;

public class Baleine extends Animal  {


    public Baleine(Integer age) {
        super(age);
    }

    @Override
    public void boire() {
        System.out.println("ouvre la bouche");
    }

    @Override
    public void seDeplacer() {
        System.out.println("nager");
    }


}
