public class Main2 {
    public static void main(String[] args) {
        new Main2().privMethod();
    }

    // sera accessible a toutes les classes qui auront instancier un objet de la class Main2
    public void pubMethod(){

    }

    // ne sera accessible que dans ce fichier (et a condition d'avoir instancier Main2)
    private void privMethod(){

    }

    protected void protectedMethod(){

    }

    void friendlyMethod(){

    }
}
