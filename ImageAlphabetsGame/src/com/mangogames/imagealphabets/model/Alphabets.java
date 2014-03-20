/*******************************************************************************

Copyright (c) 2014 Mango Games Interactive Pte Ltd

This source file is generated from Mango Games prototype code
 
Permission is hereby granted, free of charge, to any person obtaining a copy

of this software and associated documentation files (the "Software"), to deal

in the Software without restriction, including without limitation the rights

to use, copy, modify, merge, publish, distribute, sublicense, and/or sell

copies of the Software, and to permit persons to whom the Software is

furnished to do so, subject to the following conditions:

*******************************************************************************/
package com.mangogames.imagealphabets.model;

public class Alphabets 
{
	char c;
	
	public Alphabets(char d) {
		c=d;
	}

	public String getFilledImageName(){
	
		return c+"i2";
	}
	public String getImageName(){
		return c+"i1";
	}
	public String getFilledAlphabetName(){
		return c+"2";
	}
	public String getAlphabetName(){
		return c+"1";
	}
}
