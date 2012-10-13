package com.cloy.ottavino.generator.melody;

import java.util.Random;

import com.cloy.ottavino.Key;
import com.cloy.ottavino.Melody;
import com.cloy.ottavino.Note;
import com.cloy.ottavino.Octave;
import com.cloy.ottavino.Scale;

public class SequentialMelodyCreator extends AbstractMelodyCreator {
	
	private static final Random random = new Random(1);
	
	private final int startNote;
	
	public SequentialMelodyCreator(Key key, Octave rootOctave, Scale scale, int startNote) {
		super(key, rootOctave, scale);
		this.startNote = startNote;
	}
	
	@Override
	public Melody createMelody(int length) {
		Note[] notes = new Note[length];
		int currentNote = startNote;
		
		for(int i=0; i<length; i++) {
			int nextNote = nextNote(currentNote);
			notes[i] = new Note(nextNote);
			currentNote = nextNote;
		}
		
		return new Melody(notes);
	}
	
	public int nextNote(int currentNote) {
		double chance = random.nextDouble();
		if(chance < 0.1)
			return scale.moveNote(key, currentNote, -1);
		else if(chance < 0.9)
			return currentNote;
		else
			return scale.moveNote(key, currentNote, +1);
	}
}
