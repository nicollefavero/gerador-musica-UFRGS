package nerdj;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

import java.io.File;
import java.io.IOException;

public class MusicHandler {

    private Player player;
    private Pattern pattern;

    public MusicHandler() {
        this.player = new Player();
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public void playSong() {
        player.play(this.pattern);
    }

    public void saveSong() throws IOException {
        File midiFile = new File("song.midi");
        midiFile.createNewFile();

        MidiFileManager.savePatternToMidi(this.pattern, midiFile);
    }
}

