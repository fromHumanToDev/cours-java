package packageUn;

public class Chat extends Animal  {


    public Chat(Integer age) {
        super(age);
    }

    @Override
    public void boire() {
        System.out.println("lepe");
    }


    @Override
    public void seDeplacer() {
        System.out.println("courir vite");
    }

}
