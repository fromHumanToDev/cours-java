package packageUn;

public abstract class Animal implements Deplacable{

    private Integer age;

    public Animal(Integer age){
        this.age = age;
    }

    public abstract void boire();

    @Override
    public void seNourrir() {
        System.out.println("Manger");
        boire();
    }

}
