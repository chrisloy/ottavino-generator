package com.cloy.ottavino.generator.test;

import static com.cloy.ottavino.ChordShape.Major;
import static com.cloy.ottavino.ChordShape.Minor;
import static com.cloy.ottavino.Key.A;
import static com.cloy.ottavino.Key.C;
import static com.cloy.ottavino.Key.F;
import static com.cloy.ottavino.Key.G;
import static com.cloy.ottavino.midi.MidiInstrument.GrandPiano;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import com.cloy.ottavino.Chord;
import com.cloy.ottavino.ChordBuilder;
import com.cloy.ottavino.generator.Generator;
import com.cloy.ottavino.midi.MidiInstrument;
import com.cloy.ottavino.sequencer.MidiSequencer;
import com.cloy.ottavino.sequencer.Sequencer;

public class TestGenerator implements Generator {
	
	public void play() throws MidiUnavailableException {
		
		Synthesizer synth = MidiSystem.getSynthesizer();
		Sequencer<MidiInstrument> s = new MidiSequencer(160, synth);
		synth.open();

		ChordBuilder builder = new ChordBuilder();
		
		Chord cMajor = builder.get(C, Major);
		Chord aMinor = builder.get(A, Minor);
		Chord fMajor = builder.get(F, Major);
		Chord gMajor = builder.get(G, Major);
		
		s.add(GrandPiano, cMajor, aMinor, fMajor, gMajor);
		s.play(96);
		
		synth.close();
	}
}
