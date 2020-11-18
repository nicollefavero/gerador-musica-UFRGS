package nerdj.parser;

import org.jfugue.pattern.Pattern;

public class Parser {

    private final String NATURAL_NOTES = "[ABCDEFG]";

    private final String OCTAVE_SETTER_1 = ".";
    private final String OCTAVE_SETTER_2 = "?";
    private final String VOLUME_SETTER = " ";
    private final String INSTRUMENT_DIGIT_SETTER = "[0-9]";

    private final String AGOGO_INSTRUMENT = "!";
    private final int AGOGO_COD = 113;

    private String HARPSICHORD_INSTRUMENT = "[OuUuIi]";
    private final int HARPSICHORD_COD = 6;

    private final String TUBULAR_BELLS_INSTRUMENT = "\n";
    private final int TUBULAR_BELLS_COD = 14;

    private final String PAN_FLUTE_INSTRUMENT = ";";
    private final int PAN_FLUTE_COD = 75;

    private final String CHURCH_ORGAN_INSTRUMENT = ",";
    private final int CHURCH_ORGAN_COD = 19;


    public Pattern parser(int initialVolume, int initialBPM, int initialOctave, int initialInstrument, String musicString){

        Pattern pattern = new Pattern().setTempo(initialBPM);

        setPatternVolume(pattern, initialVolume);
        setPatternInstrument(pattern, initialInstrument);

        String previousChar = " ";
        int actualInstrument = initialInstrument;
        int actualOctave = initialOctave;
        int actualVolume = initialVolume;

        String[] musicStringCharacters = musicString.split("(?!^)");

        for(String actualChar:musicStringCharacters){

            // testar se é nota natural
            if(actualChar.matches(NATURAL_NOTES)){
                pattern.add(actualChar + actualOctave);

            // testar se é !
            } else if(actualChar.equals(AGOGO_INSTRUMENT)){
                actualInstrument = AGOGO_COD;
                setPatternInstrument(pattern, AGOGO_COD);
                setPatternVolume(pattern, actualVolume);

            // testar se é O, U, I
            } else if (actualChar.matches(HARPSICHORD_INSTRUMENT)){
                actualInstrument = HARPSICHORD_COD;
                setPatternInstrument(pattern, HARPSICHORD_COD);
                setPatternVolume(pattern, actualVolume);

            // testar se é nova linha
            } else if(actualChar.equals(TUBULAR_BELLS_INSTRUMENT)){
                actualInstrument = TUBULAR_BELLS_COD;
                setPatternInstrument(pattern, TUBULAR_BELLS_COD);
                setPatternVolume(pattern, actualVolume);

            // testar se é ;
            } else if(actualChar.equals(PAN_FLUTE_INSTRUMENT)){
                actualInstrument = PAN_FLUTE_COD;
                setPatternInstrument(pattern, PAN_FLUTE_COD);
                setPatternVolume(pattern, actualVolume);

            // testar se é ,
            } else if(actualChar.equals(CHURCH_ORGAN_INSTRUMENT)){
                actualInstrument = CHURCH_ORGAN_COD;
                setPatternInstrument(pattern, CHURCH_ORGAN_COD);
                setPatternVolume(pattern, actualVolume);

            // testar se é dígito
            } else if(actualChar.matches(INSTRUMENT_DIGIT_SETTER)){
                actualInstrument = actualInstrument + Integer.parseInt(actualChar);
                setPatternInstrument(pattern, actualInstrument);
                setPatternVolume(pattern, actualVolume);

            // testar se é caractere espaço
            } else if(actualChar.equals(VOLUME_SETTER)){
                actualVolume = actualVolume * 2;
                setPatternVolume(pattern, actualVolume);

            // testar é ? ou .
            } else if(actualChar.equals(OCTAVE_SETTER_1) || actualChar.equals(OCTAVE_SETTER_2)){
                if (actualOctave == 10){
                    actualOctave = initialOctave;
                } else {
                    actualOctave++;
                }

            // qualquer outro caractere (incluindo a, b, c, d, e, f, g e consoantes)
            } else {
                if(previousChar.matches(NATURAL_NOTES)){
                    pattern.add(previousChar + actualOctave);
                } else {
                    pattern.add("R");
                }
            }
            previousChar = actualChar;
        }

        return pattern;
    }

    public void setPatternVolume(Pattern pattern, int volume){
        pattern.add("X[" +  volume + "]");
    }

    public void setPatternInstrument(Pattern pattern, int instrument){
        pattern.add("I" +  instrument);
    }

}
