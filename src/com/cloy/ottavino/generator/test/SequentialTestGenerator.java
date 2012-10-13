package com.cloy.ottavino.generator.test;

import static com.cloy.ottavino.Key.*;
import static com.cloy.ottavino.Octave.*;
import static com.cloy.ottavino.Scale.*;
import static com.cloy.ottavino.DefaultInstrument.*;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import com.cloy.ottavino.Melody;
import com.cloy.ottavino.generator.Generator;
import com.cloy.ottavino.generator.melody.SequentialMelodyCreator;
import com.cloy.ottavino.sequencer.Sequencer;

public class SequentialTestGenerator implements Generator {

	@Override
	public void play() throws MidiUnavailableException {
		
		Synthesizer synth = MidiSystem.getSynthesizer();
		synth.open();
		
		Melody melody1 = new SequentialMelodyCreator(C, Central, Major, 60).createMelody(12);
		Melody melody2 = new SequentialMelodyCreator(C, Central, Major, 72).createMelody(8);
		Melody melody3 = new SequentialMelodyCreator(C, Central, Major, 48).createMelody(32);
		Melody melody4 = new SequentialMelodyCreator(C, Central, Major, 36).createMelody(8);
		Melody melody5 = new SequentialMelodyCreator(C, Central, Major, 84).createMelody(16);
		Melody melody6 = new SequentialMelodyCreator(C, Central, Major, 60).createMelody(64);
		
		Sequencer s = new Sequencer(300, synth);
		
		s.add(GrandPiano, melody1);
		s.add(GrandPiano, melody2);
		s.add(GrandPiano, melody3);
		s.add(GrandPiano, melody4);
		s.add(GrandPiano, melody5);
		s.add(GrandPiano, melody6);
		
		s.play(128);
		
		synth.close();
	}
}
