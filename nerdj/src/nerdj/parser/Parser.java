package nerdj.parser;

import org.jfugue.midi.MidiDictionary;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Parser {

    private final List<Character> NATURAL_NOTES = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G');
    private final List<Character> OTHER_VOWELS = Arrays.asList('O', 'I', 'U');
    private final char PAUSE = ' ';
    private final char DOUBLE_VOLUME = '+';
    private final char BACK_TO_INITIAL_VOLUME = '-';
    private final char RANDOM_NOTE_1 = '?';
    private final char RANDOM_NOTE_2 = '.';

    StringBuilder jfugueSintaxString = new StringBuilder();

    private int initialVolume;
    private int initialBPM;
    private int initialOctave;


    public Parser(int initialVolume, int initialBPM, int initialOctave) {
        this.initialVolume = initialVolume;
        this.initialBPM = initialBPM;
        this.initialOctave = initialOctave;
    }

    public boolean isInstrument(String instrument){
        return MidiDictionary.INSTRUMENT_STRING_TO_BYTE.containsKey(instrument);
    }

    public String generateRandomNote(){
        Random generator = new Random();
        return String.valueOf(Character.toChars(generator.nextInt(7) + 65));
    }

    public String parser(String musicString) {

        //Separa em NL para verificar os instrumentos
        String[] musicStringLines = musicString.split("\n");

        for(String line : musicStringLines) {
            if(isInstrument(line)){
                setInstrument(line);

            } else {
                char[] lineChars = line.toCharArray();
                char previousChar = ' ';
                char actualChar;
                char nextChar;

                int actualOctave = initialOctave;
                int actualVolume = initialVolume;
                int actualBPM = initialBPM;

                for(int i = 0; i < lineChars.length; i ++){
                    actualChar = lineChars[i];
                    nextChar = (i == lineChars.length - 1) ? ' ' : lineChars[i + 1];

                    // Testa se é A, B, C, D, E, F ou G. Se for B, testa se é bpm B+ ou B-
                    if(NATURAL_NOTES.contains(Character.toUpperCase(actualChar))) {
                        if (Character.toUpperCase(actualChar) == 'B') {     // Se for B, testa se na verdade não é para aumentar o BPM verificando o proximo caractere
                            if (nextChar == '+') {
                                actualBPM = actualBPM + 50;
                                setBPM(actualBPM);
                                previousChar = nextChar;
                                i ++;
                            } else if (nextChar == '-') {
                                actualBPM = actualBPM - 50;
                                setBPM(actualBPM);
                                previousChar = nextChar;
                                i ++;
                            } else {
                                setNote(actualChar, actualOctave);
                                previousChar = actualChar;
                            }
                        } else {
                            setNote(actualChar, actualOctave);
                            previousChar = actualChar;
                        }

                    // Testa se é O, U ou I. Se for O, testa se é oitava O+ ou O-
                    } else if(OTHER_VOWELS.contains(Character.toUpperCase(actualChar))) {
                        if (Character.toUpperCase(actualChar) == 'O'){
                            if (nextChar == '+') {
                                actualOctave = actualOctave + 1;
                                previousChar = nextChar;
                                i ++;
                            } else if (nextChar == '-') {
                                actualOctave = actualOctave - 1;
                                previousChar = nextChar;
                                i ++;
                            } else {
                                if (NATURAL_NOTES.contains(Character.toUpperCase(previousChar))){
                                    setNote(previousChar, actualOctave);
                                    previousChar = actualChar;
                                } else {
                                    setPause();
                                }
                            }
                        } else {
                            if (NATURAL_NOTES.contains(Character.toUpperCase(previousChar))){
                                setNote(previousChar, actualOctave);
                                previousChar = actualChar;
                            } else {
                                setPause();
                            }
                        }

                    // Testa se é para aumentar o volume
                    } else if(actualChar == DOUBLE_VOLUME){
                        actualVolume = actualVolume * 2;
                        setVolume(actualVolume);

                    // Testa se é para aumentar o volume
                    } else if(actualChar == BACK_TO_INITIAL_VOLUME){
                        actualVolume = initialVolume;
                        setVolume(actualVolume);

                    // Testa se é ? ou . para inserir uma nota aleatória
                    } else if(actualChar == RANDOM_NOTE_1 || actualChar == RANDOM_NOTE_2){
                        setRandomNote();

                    // Testa se é espaço em branco para inserir uma pausa
                    } else if(actualChar == PAUSE){
                        setPause();
                    }
                }
            }
        }
        return jfugueSintaxString.toString();
    }


    private void setInstrument(String instrument){
        jfugueSintaxString.append("I[").append(instrument).append("]").append(" ");
    }

    private void setBPM(int bpm){
        jfugueSintaxString.append("T[").append(bpm).append("]").append(" ");
    }

    private void setNote(char note, int octave){
        jfugueSintaxString.append(note).append(octave).append(" ");
    }

    private void setRandomNote(){
        jfugueSintaxString.append(generateRandomNote()).append(" ");
    }

    private void setPause(){
        jfugueSintaxString.append("R").append(" ");
    }

    private void setVolume(int volume){
        jfugueSintaxString.append("X[").append(volume).append("]").append(" ");
    }
}






























