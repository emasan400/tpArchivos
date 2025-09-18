package demo;

import java.util.HashMap;
import java.util.Map;

public class Persona {
    private Map<Integer, String> m = new HashMap<>();

    public Persona(Map<Integer, String> mapa) {
        this.m = mapa;
    }
    public Map<Integer,String> getMapa(){
        return this.m;
    }
}
