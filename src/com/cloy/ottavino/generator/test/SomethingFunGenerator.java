package com.cloy.ottavino.generator.test;

import static com.cloy.ottavino.Key.A;
import static com.cloy.ottavino.Octave.Central;
import static com.cloy.ottavino.Scale.Major;
import static com.cloy.ottavino.midi.MidiInstrument.GrandPiano;
import static com.cloy.ottavino.midi.MidiInstrument.Harpsichord;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import com.cloy.ottavino.Melody;
import com.cloy.ottavino.generator.Generator;
import com.cloy.ottavino.generator.melody.SequentialMelodyCreator;
import com.cloy.ottavino.midi.MidiInstrument;
import com.cloy.ottavino.sequencer.MidiSequencer;
import com.cloy.ottavino.sequencer.Sequencer;

public class SomethingFunGenerator implements Generator {

	@Override
	public void play() throws MidiUnavailableException {
		
		Synthesizer synth = MidiSystem.getSynthesizer();
		synth.open();
		Sequencer<MidiInstrument> s = new MidiSequencer(1200, synth);
		
		Melody melody1 = new SequentialMelodyCreator(A, Central, Major, 48).createMelody(1024);
		Melody melody2 = new SequentialMelodyCreator(A, Central, Major, 48).createMelody(1024);
		
		s.add(GrandPiano, melody1);
		s.add(Harpsichord, melody2);
		s.play(1024);
		
		synth.close();
	}

}
