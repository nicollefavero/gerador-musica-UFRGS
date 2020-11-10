package nerdj;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import nerdj.parser.Parser;

public class Controller {

    @FXML
    TextArea musicString;
    MusicHandler musicHandler = new MusicHandler();
//    Parser parser = new Parser();

    public void run(){
//        this.musicHandler.playSong(this.parser.parser(this.musicString.getText()));
    }

}
