import org.jfugue.parser.ParserListenerAdapter;
import org.jfugue.theory.Note;

import javax.sound.midi.MidiSystem;

public class MyParserListener extends ParserListenerAdapter {
    private MidiHandler midiHandler;
    private String currentNote;


    public MyParserListener(MidiHandler midiHandler) {
        this.midiHandler = midiHandler;
        System.out.println("Please press " + midiHandler.getCorrectNote());
    }

    @Override
    public void onNotePressed(Note note) {
        currentNote = note.toStringWithoutDuration();
        System.out.println(currentNote + " is pressed");
        midiHandler.setCurrentNote(currentNote);


        if (currentNote.equals(midiHandler.getCorrectNote())) {
            System.out.println("-- Correct! --");
        } else {
            System.out.println("current: " + currentNote + "\s correct: " + midiHandler.getCorrectNote());
        }
    }

    @Override
    public void onNoteReleased(Note note) {
        super.onNoteReleased(note);
    }
}