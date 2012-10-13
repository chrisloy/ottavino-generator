package com.cloy.ottavino.generator.test;

import static com.cloy.ottavino.ChordShape.*;
import static com.cloy.ottavino.Key.*;
import static com.cloy.ottavino.DefaultInstrument.*;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import com.cloy.ottavino.ChordBuilder;
import com.cloy.ottavino.Chord;
import com.cloy.ottavino.generator.Generator;
import com.cloy.ottavino.sequencer.Sequencer;

public class TestGenerator implements Generator {
	
	public void play() throws MidiUnavailableException {
		
		Synthesizer synth = MidiSystem.getSynthesizer();
		Sequencer s = new Sequencer(160, synth);
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
