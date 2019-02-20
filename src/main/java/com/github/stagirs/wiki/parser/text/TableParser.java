/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.stagirs.wiki.parser.text;

import com.github.stagirs.wiki.model.WikiText;
import com.github.stagirs.wiki.model.text.WikiTable;
import com.github.stagirs.wiki.parser.IntervalParser;
import com.github.stagirs.wiki.parser.WikiTextParser;

/**
 *
 * @author NPSP-MalakhovDA
 */
public class TableParser implements WikiTextParser.Parser{
    
    @Override
    public int curIndex(String wiki, int i) {
        return wiki.indexOf("{|", i);
    }

    @Override
    public int process(WikiText text, StringBuilder sb, String wiki, int i) {
        int[] interval = getIntervalTable(wiki, i);
        if(interval != null && interval[1] - interval[0] > 4){
            text.getTables().add(get(sb, interval[0], wiki.substring(interval[0], interval[1])));
            return interval[1];
        }
        sb.append("{|");
        return i + 2;
    }
    
    private WikiTable get(StringBuilder sb, int pos, String wiki) {
        return new WikiTable(sb, " ", pos, wiki);
    }
    
    private int[] getIntervalTable(String wiki, int pos){
        return IntervalParser.get(wiki, "{|", "|}", pos);
    }
}
