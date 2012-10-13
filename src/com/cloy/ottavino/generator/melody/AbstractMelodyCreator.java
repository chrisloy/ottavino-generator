package com.cloy.ottavino.generator.melody;

import com.cloy.ottavino.Key;
import com.cloy.ottavino.Octave;
import com.cloy.ottavino.Scale;
import com.cloy.ottavino.util.ArrayUtil;

public abstract class AbstractMelodyCreator implements MelodyCreator {
	
	protected final Key key;
	protected final Octave rootOctave;
	protected final Scale scale;
	
	public AbstractMelodyCreator(Key key, Octave rootOctave, Scale scale) {
		this.key = key;
		this.rootOctave = rootOctave;
		this.scale = scale;
	}
	
	protected int[] getScaleNotes(Octave...octaves) {
		int[] result = new int[0];
		for(Octave o : octaves)
			result = ArrayUtil.concatAll(result, scale.getNotes(key, o));
		return result;
	}

	protected Octave getTransposedOctave(int octaveTransposition) {
		return rootOctave.transpose(octaveTransposition);
	}
}
