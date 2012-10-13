package com.cloy.ottavino.generator.melody;

import java.util.Random;

import com.cloy.ottavino.Key;
import com.cloy.ottavino.Melody;
import com.cloy.ottavino.Note;
import com.cloy.ottavino.Octave;
import com.cloy.ottavino.Scale;

/**
 * Creates a simple melody with no expression of notes or particular
 * emphasis to have longer notes. Also no pauses.
 * 
 * @author chrisloy
 */
public class SimpleMelodyCreator extends AbstractMelodyCreator {
	
	private static final Random randomMelody  = new Random(1);
	private static final Random randomPauses  = new Random(2);
	private static final Random randomSustain = new Random(3);
	
	private final int startNote;
	private final double pauseProbability;
	private final double sustainProbability;
	
	public SimpleMelodyCreator(
			Key key,
			Octave rootOctave,
			Scale scale,
			int startNote,
			double pauseProbability,
			double sustainProbability) {
		
		super(key, rootOctave, scale);
		this.startNote = startNote;
		this.pauseProbability = pauseProbability;
		this.sustainProbability = sustainProbability;
	}
	
	@Override
	public Melody createMelody(int length) {
		
		Note[] notes = new Note[length];
		Note lastNote = new Note(startNote);
		for(int i=0; i<length; i++) {
			notes[i] = getNextNote(lastNote);
			if(notes[i] != null)
				lastNote = notes[i];
		}
		
		return new Melody(notes);
	}
	
	private Note getNextNote(Note lastNote) {
		
		if(pause()) {
			// pause instead
			return null;
		}
		else if(sustain()) {
			// sustain the last note
			return lastNote;
		}
		else {
			// algorithmically return a new note
			int pitch = nextNote(lastNote.getNoteNumber());
			return new Note(pitch);
		}
	}
	
	private int nextNote(int lastNote) {
		double chance = randomMelody.nextDouble();
		int magnitude = randomMelody.nextInt(3);
		if(chance < 0.1)
			return scale.moveNote(key, lastNote, -magnitude);
		else if(chance < 0.9)
			return lastNote;
		else
			return scale.moveNote(key, lastNote, +magnitude);
	}

	private boolean pause() {
		double chance = randomPauses.nextDouble();
		return chance < pauseProbability;
	}
	
	private boolean sustain() {
		double chance = randomSustain.nextDouble();
		return chance < sustainProbability;
	}
}
