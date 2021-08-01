import org.jfugue.devices.MusicTransmitterToParserListener;
import org.jfugue.parser.ParserListenerAdapter;
import org.jfugue.realtime.RealtimeMidiParserListener;
import org.jfugue.realtime.RealtimePlayer;
import org.jfugue.theory.Note;

import javax.sound.midi.*;
import java.util.List;
import java.util.Scanner;

public class MidiHandler {
    private String currentNote;
    private String correctNote = "D5";

    private RealtimeMidiParserListener RealtimeMidiParserListener;
    private Sequencer sequencer;

    public MidiHandler() {
        Scanner sc = new Scanner(System.in);

        MidiDevice device;
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo(); //gets all midi devices in PC
        for (int i = 0; i < infos.length; i++) {
            System.out.println(infos[i]); //prints out the info
        }

        try {
            device = MidiSystem.getMidiDevice(infos[7]);  //select a midi device and open it
            device.open();

            MusicTransmitterToParserListener m = new MusicTransmitterToParserListener(device); //set a musicTransmitterToParserListener to the device
            m.addParserListener(new MyParserListener(this)); //Add a specific parserListener (custom one)
            m.startListening(); //start listening


            boolean playing = true;
            while (playing) { //this doesnt seem to work. Something about startListening blocking callStack (new thread perhaps?)
                if (currentNote != null) {
                    if (currentNote.equals(correctNote)) {
                        System.out.println("-- Correct! --");
                    }
                    System.out.println("current: " + currentNote + "\s correct: " + correctNote);

                }
            }

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }

    }

    public void setCurrentNote(String currentNote) {
        this.currentNote = currentNote;
    }

    public String getCurrentNote() {
        return currentNote;
    }

    public String getCorrectNote() {
        return correctNote;
    }
}

