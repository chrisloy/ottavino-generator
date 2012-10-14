package com.cloy.ottavino.generator.test2;

import static com.cloy.ottavino.Octave.Central;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import javax.sound.midi.MidiUnavailableException;

import com.cloy.ottavino.Key;
import com.cloy.ottavino.Melody;
import com.cloy.ottavino.Octave;
import com.cloy.ottavino.Scale;
import com.cloy.ottavino.generator.Generator;
import com.cloy.ottavino.generator.melody.SimpleMelodyCreator;
import com.cloy.ottavino.sequencer.Sequencer;
import com.cloy.ottavino.sequencer.ToneSequencer;
import com.cloy.ottavino.tone.SawTone;
import com.cloy.ottavino.tone.SineTone;
import com.cloy.ottavino.tone.SquareTone;
import com.cloy.ottavino.tone.ToneInstrument;

/**
 * Some nice seeds:
 * 
 *   1234567890
 *   Hello, world!
 *   Big Brother
 *   Chris Loy
 *   
 * @author chrisloy
 */
public class AnotherGenerator implements Generator {
	
	private final long seed;
	
	public AnotherGenerator(long seed) {
		this.seed = seed;
	}
	
	@Override
	public void play() throws MidiUnavailableException {
		
		final Random random = new Random(seed);
		
		//Synthesizer synth = MidiSystem.getSynthesizer();
		//synth.open();
		
		int timeSig = 4;
		
		int melodies = random.nextInt(32);
		int bpm = 50 + random.nextInt(400);
		int totalBeats = timeSig * random.nextInt(64);
		
		//Sequencer<MidiInstrument> s = new MidiSequencer(bpm, synth);
		Sequencer<ToneInstrument> s = new ToneSequencer(bpm);
		
		Key key = Key.getFromRootNote(random.nextInt(12) + 60);
		Scale scale = random.nextBoolean() ? Scale.Major : Scale.Minor;
		
		for(int i=0; i<melodies; i++) {
			
			int startNote = 12 * random.nextInt(12) + key.getRootNote(Octave.Minus5);    // 12 octaves
			int length = timeSig * random.nextInt(8);   // 8 bars
			int instrument = random.nextInt(16);        // 16 instruments
			
			if(length <= 0) {
				System.out.println("Ignoring melody of length " + length);
				continue;
			}
			
			Melody melody = new SimpleMelodyCreator(key, Central, scale, startNote, 0.4, 0.4).createMelody(length);
			double pan = 128d * new Double(i) / new Double(melodies);
			//s.add(MidiInstrument.getInstrument(instrument), (int)pan, melody);
			switch(instrument % 3) {
				case 0: s.add(new SineTone(), (int)pan, melody);
				case 1: s.add(new SawTone(), (int)pan, melody);
				case 2: s.add(new SquareTone(), (int)pan, melody);
			}
		}
		
		System.out.println();
		System.out.println("Seed     [" + seed + "]");
		System.out.println("Key      [" + key + " " + scale + "]");
		System.out.println("Melodies [" + melodies + "]");
		System.out.println("BPM      [" + bpm + "]");
		System.out.println("Beats    [" + totalBeats + "]");
		System.out.println("Time     [" + timeSig + "]");
		System.out.println("Length   [" + (totalBeats * 60) / bpm + "s]");
		
		if(melodies > 0) {
			s.play(totalBeats);
		}
		
		System.out.println();
		System.out.println("Done");
		
		//synth.close();
	}
	
	public static void main(String...args) {
		
		System.out.println("Enter a seed, or leave empty for random");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String seedString;
		try {
			seedString = br.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
			seedString = null;
		}
		
		System.out.println("Entered seed [" + seedString + "]");
		
		long seed;
		
		if(seedString == null || seedString.isEmpty()) {
			seed = new Random().nextLong();
		} else {
			try {
				seed = Long.valueOf(seedString);
			} catch (NumberFormatException nfe) {
				seed = seedString.hashCode();
			}
		}
		
		try {
			new AnotherGenerator(seed).play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
