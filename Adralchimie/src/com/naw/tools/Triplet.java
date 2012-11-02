package com.naw.tools;

public class Triplet<L, R, S> {
	private final L a;
	private final R b;
	private final S c;
	
	public Triplet(L first,R second,S third) {
		this.a = first;
		this.b = second;
		this.c = third;
	}


	public L getA() {
		return a;
	}


	public R getB() {
		return b;
	}

	

	public S getC() {
		return c;
	}

}
