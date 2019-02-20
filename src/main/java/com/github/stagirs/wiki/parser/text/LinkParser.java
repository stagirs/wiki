/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.stagirs.wiki.parser.text;

import com.github.stagirs.wiki.parser.IntervalParser;
import com.github.stagirs.wiki.model.WikiText;
import com.github.stagirs.wiki.model.text.WikiLink;
import com.github.stagirs.wiki.parser.WikiTextParser;

/**
 *
 * @author NPSP-MalakhovDA
 */
public class LinkParser implements WikiTextParser.Parser{
    
    @Override
    public int curIndex(String wiki, int i) {
        return wiki.indexOf("[[", i);
    }

    @Override
    public int process(WikiText text, StringBuilder sb, String wiki, int i) {
        int[] interval = getIntervalLink(wiki, i);
        if(interval != null && interval[1] - interval[0] > 4){
            text.getLinks().add(get(sb, interval[0], wiki.substring(interval[0], interval[1])));
            return interval[1];
        }
        sb.append("[[");
        return i + 2;
    }
    
    public WikiLink get(StringBuilder sb, int pos, String wiki) {
        String body = wiki.substring(2 , wiki.length() - 2);
        String text;
        String id;
        if(!body.contains("|")){
            id = body.trim();
            text = id;
        }else{
            id = body.substring(0, body.indexOf("|")).trim();
            text = body.substring(body.indexOf("|") + 1).trim();
        }
        return new WikiLink(sb, text, pos, wiki, id);
    }
    
    public int[] getIntervalLink(String wiki, int pos){
        return IntervalParser.get(wiki, "[[", "]]", pos);
    }
}
