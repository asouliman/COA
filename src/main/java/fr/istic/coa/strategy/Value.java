package fr.istic.coa.strategy;

public class Value {

    private int value;
    private int version;
    
    public Value(int value) {
        this.value = value;
        this.version = -1;
    }
    
    public Value(int value, int version) {
        this.value = value;
        this.version = version;
    }
    
    public int getValue() {
        return value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public int getVersion() {
        return version;
    }
    
    public void next(int value) {
        this.value = value;
        this.version++;
    }
}
