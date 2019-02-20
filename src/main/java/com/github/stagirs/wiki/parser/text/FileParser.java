/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.stagirs.wiki.parser.text;

import com.github.stagirs.wiki.parser.IntervalParser;
import com.github.stagirs.wiki.model.WikiText;
import com.github.stagirs.wiki.model.text.WikiFile;
import com.github.stagirs.wiki.parser.WikiTextParser;

/**
 *
 * @author NPSP-MalakhovDA
 */
public class FileParser implements WikiTextParser.Parser{
    @Override
    public int curIndex(String wiki, int i) {
        return wiki.indexOf("[[Файл:", i);
    }

    @Override
    public int process(WikiText text, StringBuilder sb, String wiki, int i) {
        int[] interval = getIntervalLink(wiki, i);
        if(interval != null && interval[1] - interval[0] > 4){
            text.getFiles().add(get(sb, interval[0], wiki.substring(interval[0], interval[1])));
            return interval[1];
        }
        sb.append("[[");
        return i + 2;
    }
    
    public WikiFile get(StringBuilder sb, int pos, String wiki) {
        String rawText = wiki.substring(2 , wiki.length() - 2);
        int curPos = 2;
        String id;
        String type;
        if(!rawText.contains("|")){
            id = rawText;
            rawText = "";
        }else{
            id = rawText.substring(5, rawText.indexOf("|"));
            rawText = rawText.substring(rawText.indexOf("|") + 1);
        }
        curPos += 5 + id.length() + 1;
        int bodyPos = rawText.indexOf("|") + 1;
        if(bodyPos <= 0){
            type = rawText;
            return new WikiFile(sb, " ", pos, wiki, id, type, WikiTextParser.get(wiki.length(), ""));
        }
        while(rawText.indexOf("|", bodyPos) >= 0 && 
                (rawText.indexOf("|", bodyPos) < rawText.indexOf("{", bodyPos) || rawText.indexOf("{", bodyPos) < 0) && 
                (rawText.indexOf("|", bodyPos) < rawText.indexOf("[", bodyPos) || rawText.indexOf("[", bodyPos) < 0)){
            bodyPos = rawText.indexOf("|", bodyPos) + 1;
        }
        type = rawText.substring(0, bodyPos - 1);
        rawText = rawText.substring(bodyPos);
        curPos += type.length() + 1;
        return new WikiFile(sb, " ", pos, wiki, id, type, WikiTextParser.get(curPos, rawText));
    }
    
    public int[] getIntervalLink(String wiki, int pos){
        return IntervalParser.get(wiki, "[[", "]]", pos);
    }
}
