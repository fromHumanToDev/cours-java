package fr.coursjava.detectioncycle;

public class CycleDetection {

    private boolean isCycle;
    private String dernierAvantCycle;

    public boolean isCycle() {
        return isCycle;
    }

    public void setCycle(boolean cycle) {
        isCycle = cycle;
    }

    public String getDernierAvantCycle() {
        return dernierAvantCycle;
    }

    public void setDernierAvantCycle(String dernierAvantCycle) {
        this.dernierAvantCycle = dernierAvantCycle;
    }
}
