/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.stagirs.wiki.parser.text;

import com.github.stagirs.wiki.parser.IntervalParser;
import com.github.stagirs.wiki.model.WikiText;
import com.github.stagirs.wiki.model.text.WikiComment;
import com.github.stagirs.wiki.parser.WikiTextParser;

/**
 *
 * @author NPSP-MalakhovDA
 */
public class CommentParser implements WikiTextParser.Parser{
    @Override
    public int curIndex(String wiki, int i) {
        return wiki.indexOf("<!--", i);
    }

    @Override
    public int process(WikiText text, StringBuilder sb, String wiki, int i) {
        int[] interval = getIntervalComment(wiki, i);
        if(interval != null){
            text.getComments().add(new WikiComment(sb, interval[0], wiki.substring(interval[0], interval[1])));
            return interval[1];
        }
        sb.append("<");
        return i + 1;
    }
    
    private int[] getIntervalComment(String wiki, int pos){
        return IntervalParser.get(wiki, "<!--", "-->", pos);
    }
    
}
