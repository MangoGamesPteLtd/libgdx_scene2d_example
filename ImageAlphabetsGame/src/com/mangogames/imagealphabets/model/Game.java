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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;

public class Game {
	
	private List<Alphabets> alphabetList;
	private List<Alphabets> usedAlphabetsList=new ArrayList<Alphabets>();
	private int             gameinstance;
	
	public Game()
	{
		alphabetList = new ArrayList<Alphabets>();
		for(int i = 97; i < 123; i++){
		      if(Character.isLowerCase((char)i))
		    	  alphabetList.add(new Alphabets((char)i));	 
		    }
		setGameinstance(0);
	}
		
	/**
	 * Get the next three alphabets from the alphabet list and 
	 * 
	 * @return
	 */
	public List<Alphabets> getNextThreeAlphabetList(){
		
		List<Alphabets> threeAlphabets= new ArrayList<Alphabets>();
		shuffle(alphabetList);
		for(int i=0; i<3;i++)
		{
			Gdx.app.log("alphabets size","::"+alphabetList.size());
			threeAlphabets.add(alphabetList.get(i));
			alphabetList.remove(i);
		}
		Gdx.app.log("three alphabets size", "::"+threeAlphabets.size());
		usedAlphabetsList.addAll(threeAlphabets);
		Gdx.app.log("used alphabets size", "::"+usedAlphabetsList.size());
		return threeAlphabets;
	} 
	
	public List<Alphabets> getAlphabetList() {
		return alphabetList;
	}

	public List<Alphabets> getUsedAlphabetsList() {
		return usedAlphabetsList;
	}
	
	/**
	 * Shuffles the list for random alphabets
	 * @param shufflelist
	 * @return
	 */
	public List<Alphabets> shuffle(List<Alphabets> shufflelist)
	{
		List<Alphabets> shuffleList = shufflelist;
		Random random = new Random();
		
		for (int i = shufflelist.size() - 1; i >= 0; i--) 
		{
		    int j = random.nextInt(i + 1);
		    
		    if( i != j )
			{
			    /* swap cards i,j */
			    Alphabets alphabeti = shuffleList.get(i);
			    Alphabets alphabetj = shuffleList.get(j);
			    shuffleList.remove(alphabeti);
			    shuffleList.remove(alphabetj);
			     
			    shuffleList.add(i-1, alphabetj);
			    shuffleList.add(j, alphabeti);
			    
			    //test
			    Alphabets nAlphabeti = shuffleList.get(i);
			    Alphabets nAlphabetj = shuffleList.get(j);
			    
			    assert(alphabeti == nAlphabetj);
			    assert(alphabetj == nAlphabeti);
			}	
		}
		return shuffleList;
	}

	public int getGameinstance() {
		return gameinstance;
	}

	public void setGameinstance(int gameinstance) {
		this.gameinstance = gameinstance;
	}
}
