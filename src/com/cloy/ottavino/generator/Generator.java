package com.cloy.ottavino.generator;

import javax.sound.midi.MidiUnavailableException;

public interface Generator {
	
	public void play() throws MidiUnavailableException;
}
