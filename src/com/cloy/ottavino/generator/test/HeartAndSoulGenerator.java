package com.cloy.ottavino.generator.test;

import static com.cloy.ottavino.ChordShape.Major;
import static com.cloy.ottavino.ChordShape.Minor;
import static com.cloy.ottavino.Key.A;
import static com.cloy.ottavino.Key.C;
import static com.cloy.ottavino.Key.F;
import static com.cloy.ottavino.Key.G;

import javax.sound.midi.MidiUnavailableException;

import com.cloy.ottavino.Chord;
import com.cloy.ottavino.ChordBuilder;
import com.cloy.ottavino.Melody;
import com.cloy.ottavino.generator.Generator;
import com.cloy.ottavino.sequencer.Sequence;
import com.cloy.ottavino.sequencer.Sequencer;
import com.cloy.ottavino.sequencer.ToneSequence;
import com.cloy.ottavino.sequencer.ToneSequencer;
import com.cloy.ottavino.tone.SineTone;
import com.cloy.ottavino.tone.ToneInstrument;
import com.cloy.ottavino.util.ArrayUtil;

public class HeartAndSoulGenerator implements Generator {

	@Override
	public void play() throws MidiUnavailableException {
		
		//Synthesizer synth = MidiSystem.getSynthesizer();
		Sequencer<ToneInstrument> s = new ToneSequencer(400);
		//synth.open();

		ChordBuilder builder = new ChordBuilder();
		
		Chord cMajor = builder.get(C, Major);
		Chord aMinor = builder.get(A, Minor);
		Chord fMajor = builder.get(F, Major);
		Chord gMajor = builder.get(G, Major);
		
		Sequence vibes = new ToneSequence(63, new SineTone(), 
				cMajor, null, null, null, null, null,
				aMinor, null, null, null, null, null,
				fMajor, null, null, null, null, null,
				gMajor, null, null, null, null, null
			);
		
		Melody bassMelody = new Melody(ArrayUtil.convertToNotes(
				60, 60, 60, 60, 60, 59,
				57, 57, 57, 57, 57, 55,
				53, 53, 53, 53, 53, 57,
				55, 55, 55, 55, 55, 55
			)).transpose(-2 * 12);
		
		Melody rhythmMelody = new Melody(ArrayUtil.convertToNotes(
				48, null, 60, 64, null, 67,
				45, null, 57, 60, null, 64,
				41, null, 53, 57, null, 60,
				43, null, 55, 59, null, 62
			)).transpose(-1 * 12);
		
		Melody leadMelody = new Melody(ArrayUtil.convertToNotes(
				72, 72, null, 72, 72, null,
				72, 72, 72, 72, 72, null,
				null, null, 72, 71, null, 69,
				71, null, 72, 74, null, null,
				76, 76, null, 76, 76, null,
				76, 76, 76, 76, 76, null,
				null, null, 76, 74, null, 72,
				74, null, 76, 77, null, null,
				79, 79, 79, 79, 79, null,
				72, 72, 72, 72, 72, null,
				null, null, 81, 79, null, 77,
				76, null, null, 74, null, null,
				72, 72, 72, 72, 72, 71,
				69, 69, 69, 69, 69, 67,
				65, 65, 65, 65, 65, 69,
				67, 67, 67, null, null, null
			));
		
		s.add(new SineTone(), bassMelody);
		s.add(new SineTone(), rhythmMelody);
		s.add(new SineTone(), leadMelody);
		s.add(vibes);
		
		s.play(192); //twice through
		
		//synth.close();
	}

}
