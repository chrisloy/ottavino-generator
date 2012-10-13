package com.cloy.ottavino.generator;

import java.util.Random;

import com.cloy.ottavino.generator.test2.AnotherGenerator;

public class Server {
	
	public static void main(String...args) {
		try {
			//new SequentialTestGenerator().play();
			//new SimpleRandomGenerator().play();
			//new HeartAndSoulGenerator().play();
			//new SomethingFunGenerator().play();
			//new TestGenerator().play();
			new AnotherGenerator(new Random().nextLong()).play();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
