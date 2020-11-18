package nerdj;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import nerdj.parser.Parser;

import java.io.IOException;

public class Controller {

    @FXML
    TextArea musicString;

    @FXML
    TextField initialInstrument;

    @FXML
    TextField initialVolume;

    @FXML
    TextField initialOctave;

    @FXML
    TextField initialBPM;

    private MusicHandler musicHandler = new MusicHandler();
    private Parser parser = new Parser();
    private String previousMusicString = "";

    public void run(){
        if(!compareString()){
            musicHandler.setPattern(this.parser.parser(getInitialVolume(), getInitialBPM(), getInitialOctave(), getInitialInstrument(), musicString.getText()));
        }
        musicHandler.playSong();
    }

    public void createMidi() throws IOException {
        if(!compareString()){
            musicHandler.setPattern(this.parser.parser(getInitialVolume(), getInitialBPM(), getInitialOctave(), getInitialInstrument(), musicString.getText()));
        }
        musicHandler.saveSong();
    }

    private boolean compareString(){
        return musicString.toString().equals(previousMusicString);
    }

    private int getInitialVolume(){
        try{
            return Math.abs(Integer.parseInt(initialVolume.getText()));
        } catch (NumberFormatException e) {
            return 10200;
        }
    }

    private int getInitialInstrument(){
        try{
            return Math.abs(Integer.parseInt(initialOctave.getText())) - 1;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private int getInitialOctave(){
        try{
            return Math.abs(Integer.parseInt(initialOctave.getText())) % 11;
        } catch (NumberFormatException e) {
            return 5;
        }
    }

    private int getInitialBPM(){
        try{
            return Math.abs(Integer.parseInt(initialBPM.getText()));
        } catch (NumberFormatException e) {
            return 120;
        }
    }

}
