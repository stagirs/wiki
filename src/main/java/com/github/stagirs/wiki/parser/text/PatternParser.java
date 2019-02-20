/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.stagirs.wiki.parser.text;

import com.github.stagirs.wiki.parser.IntervalParser;
import com.github.stagirs.wiki.model.WikiText;
import com.github.stagirs.wiki.model.text.WikiPattern;
import com.github.stagirs.wiki.parser.WikiTextParser;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author NPSP-MalakhovDA
 */
public class PatternParser implements WikiTextParser.Parser{
    @Override
    public int curIndex(String wiki, int i) {
        return wiki.indexOf("{{", i);
    }

    @Override
    public int process(WikiText text, StringBuilder sb, String wiki, int i) {
        int[] interval = getIntervalPattern(wiki, i);
        if(interval == null || interval[1] - interval[0] < 4){
            sb.append("{{");
            return i + 2;
        }
        
        String rawText = wiki.substring(interval[0], interval[1]);
        String body = rawText.substring(2 , rawText.length() - 2);
        if(!body.contains("|")){
            text.getPatterns().add(new WikiPattern(sb, " ", interval[0], rawText, body, new HashMap<String, WikiText>()));
            return interval[1];
        }
        String name;
        Map<String, WikiText> map = new HashMap<String, WikiText>();
        if(body.startsWith("#")){
            if(body.contains(" ")){
                name = body.substring(0, body.indexOf(' ')).trim();
                body = body.substring(body.indexOf(' '));
            }else{
                name = body;
                body = "";
            }
        }else{
            if(body.contains("|")){
                name = body.substring(0, body.indexOf('|')).trim();
                body = body.substring(body.indexOf('|') + 1);
            }else{
                name = body;
                body = "";
            }
        }

        int index = 1;
        while(!body.trim().isEmpty()){
            String key;
            if(!body.contains("=") || 
                    before(body.indexOf("{"), body.indexOf("=")) ||  
                    before(body.indexOf("["), body.indexOf("=")) ||  
                    before(body.indexOf("/"), body.indexOf("=")) ||  
                    before(body.indexOf("|"), body.indexOf("=")) || 
                    before(body.indexOf("<"), body.indexOf("="))){
                key = String.valueOf(index++);
            }else{
                key = body.substring(0, body.indexOf("=")).trim();
                body = body.substring(body.indexOf('=') + 1);
            }
            WikiText attrText = WikiTextParser.get(rawText.length() - body.length() - 2, rawText.substring(rawText.length() - body.length() - 2), "|", "}}");
            map.put(key, attrText);
            body = body.substring(Math.min(body.length(), attrText.getRawText().length() + 1));
        }
        text.getPatterns().add(new WikiPattern(sb, " ", interval[0], rawText, name, map));
        return interval[1];
    }
    
    
    private boolean before(int index1, int index2){
        if(index1 < 0){
            return false;
        }
        return index1 <= index2;
    }
    
    private int[] getIntervalPattern(String wiki, int pos){
        return IntervalParser.get(wiki, "{", "}", pos);
    }
}
