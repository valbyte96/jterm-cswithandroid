/*
 *  Copyright 2016 Google Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.google.engedu.ghost;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class SimpleDictionaryTest {
    String[] wordsArray = {"apple","bat","car","cat","charles","snaps"};

    @Test
    public void testIsWord() {

    }

    @Test
    public void testGetAnyWordStartingWith() {
        ArrayList<String> words = new ArrayList<String>(Arrays.asList(wordsArray));
        SimpleDictionary dict = new SimpleDictionary(words,0);
        assertEquals("snaps",dict.getAnyWordStartingWith("snap"));
        assertEquals("apple",dict.getAnyWordStartingWith("a"));
        assertEquals("charles",dict.getAnyWordStartingWith("CHARLES"));
        assertEquals("car",dict.getAnyWordStartingWith("car"));
        assertEquals("car",dict.getAnyWordStartingWith("ca"));
        assertTrue(dict.getAnyWordStartingWith("ca").startsWith("ca"));
        System.out.println(dict.getAnyWordStartingWith(""));






    }
}
