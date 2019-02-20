/*
 * Copyright 2018 Dmitriy Malakhov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.stagirs.wiki.parser;

/**
 *
 * @author Dmitriy Malakhov
 */
public class IntervalParser {
    
    public static int[] get(String wiki, String begin, String end, int pos){
        int beginPos = wiki.indexOf(begin, pos);
        if(beginPos < 0){
            return null;
        }
        int curPos = beginPos + begin.length();
        
        int level = 1;
        while(level > 0){
            int curBeginPos = wiki.indexOf(begin, curPos);
            int curEndPos = wiki.indexOf(end, curPos);
            if(curEndPos < 0){
                curPos = -1;
                break;
            }
            if(curBeginPos < 0 || curEndPos < curBeginPos){
                level--;
                curPos = curEndPos + end.length();
            }else{
                level++;
                curPos = curBeginPos + begin.length();
            }
        }
        int endPos = curPos < 0 ? beginPos + begin.length() : curPos;
        return new int[]{beginPos, endPos};
    }
}
