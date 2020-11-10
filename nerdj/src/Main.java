import nerdj.WindowHandler;
import nerdj.parser.Parser;

public class Main {

    public static void main(String[] args) {
        Parser parser = new Parser(10200, 120, 5);
        System.out.println(parser.parser("PIANO\nAbC+ +O+AObCUIOB+??.\nFRENCH_HORN\nB-B- O+O+DefGUui"));
//        new WindowHandler().load();
    }

}



//        JFugue String
//        "V0 I[PIANO] Di Ei Gi Gi Gi Gs Gi Gs Gi", 2).add("V0 I[" + instrument + "] Gi Gi F#h"
//        "I[PIANO] A B A B D E G G G G G G G F"

//        Trabalho String
//        PIANO
//        AbC+ +O+AbCB+??.
//        FRENCH_HORN
//        B-B-O+DefGUui