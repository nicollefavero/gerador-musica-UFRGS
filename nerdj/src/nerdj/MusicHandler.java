package nerdj;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class MusicHandler {

    Player player;

    public MusicHandler() {
        this.player = new Player();
    }

    public void playSong(String string) {
        Pattern pattern = new Pattern(string);
        this.player.play(pattern);
    }
}




//    Player player = new Player();
//    Pattern pattern1 = new Pattern("V0 I[" + instrument + "] Dq Eq Gi Gi Gi Gs Gi Gs Gi");
//        pattern1.add("V0 I[" + instrument + "] Di Ei Gi Gi Gi Gs Gi Gs Gi", 2).add("V0 I[" + instrument + "] Gi Gi F#h").repeat(4);
//        player.play(pattern1);

