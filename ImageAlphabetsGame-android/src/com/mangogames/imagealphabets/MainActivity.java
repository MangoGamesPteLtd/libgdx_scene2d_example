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
package com.mangogames.imagealphabets;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration; 
import com.mangogames.imagealphabets.ImageAplhabetsGame;;

/**
*
*@author vennela
*/
public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = true;
        
        initialize(new ImageAplhabetsGame(), cfg);
    }
}