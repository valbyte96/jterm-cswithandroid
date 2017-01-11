/* Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.ghost;

import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;

public class TrieNode {
    private HashMap<String, TrieNode> children;
    private boolean isWord;
    private Random mRandom;


    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
        mRandom = new Random();
    }

    public void add(String s) {
        if (s.length()==0){
            isWord = true;
            return;
        }
        String firstChar = s.substring(0,1);
        String suffix = s.substring(1);
        if (children.containsKey(firstChar)) {
            //then ask the child to add remaining string suffix
            children.get(firstChar).add(suffix);
        }
        else{
            //doesn't exist in children so make new
            TrieNode newNode = new TrieNode();
            children.put(firstChar,newNode);
            newNode.add(suffix);

        }
    }

    public boolean isWord(String s) {
        if(s.length()==0){
            return isWord;
        }
        String firstChar = s.substring(0,1);
        String suffix = s.substring(1);
        if(children.containsKey(firstChar)){
            return children.get(firstChar).isWord(suffix);
        }
        return false;
    }

    public String getAnyWordStartingWith(String s) {
        // Base case: No prefix at all -- s is null.
        if (s == null) {
            if (children.size() > 0) {
                // Pick any next character and return that word
                String nextChar = pickRandomChildChar();
                String nextSuffix = children.get(nextChar).getAnyWordStartingWith(null);
                return nextChar + nextSuffix;
            } else {
                return "";
            }
        }
        if (s.length() == 0) {
            if (children.size() == 0 && isWord) {
                // We are a leaf node and a word!
                return "";
            }
            if (children.size() > (children.containsKey("") ? 1 : 0)) {
                // Pick any next character and return that word
                String nextChar = pickRandomChildChar();
                String nextSuffix = children.get(nextChar).getAnyWordStartingWith(null);
                return nextChar + nextSuffix;
            }
            // Otherwise we are a leaf node -- we have no children. Return null; we couldn't make
            // a valid word.
            return null;
        }
        String firstChar = s.substring(0, 1);
        if (children.containsKey(firstChar)) {
            String remaining = s.substring(1);
            String nextSuffix = children.get(firstChar).getAnyWordStartingWith(remaining);
            if (nextSuffix == null) {
                return null;
            }
            return firstChar + nextSuffix;
        }
        return null;
    }

    private String pickRandomChildChar() {
        int index = mRandom.nextInt(children.size());
        int reached=0;
        for (String s: children.keySet()){
            if (index==reached){
                return s;
            }
            reached++;
        }
        return null;
    }
    public String getGoodWordStartingWith(String s) {
        return null;
    }
}