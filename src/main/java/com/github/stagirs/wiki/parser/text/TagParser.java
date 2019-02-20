/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.stagirs.wiki.parser.text;

import com.github.stagirs.wiki.model.WikiText;
import com.github.stagirs.wiki.model.text.WikiTag;
import com.github.stagirs.wiki.parser.IntervalParser;
import com.github.stagirs.wiki.parser.WikiTextParser;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author NPSP-MalakhovDA
 */
public class TagParser implements WikiTextParser.Parser{
    @Override
    public int curIndex(String wiki, int i) {
        return wiki.indexOf("<", i);
    }

    @Override
    public int process(WikiText text, StringBuilder sb, String wiki, int i) {
        int spaceIndex = wiki.indexOf(" ", i);
        int endIndex = wiki.indexOf(">", i);
        int tagEnd = spaceIndex >= 0 && spaceIndex < endIndex ? spaceIndex : endIndex;
        if(tagEnd >= 0){
            String tag = wiki.substring(i + 1, tagEnd);
            if(!tag.isEmpty()){
                int[] interval = getIntervalTag(wiki, tag, i);
                if(interval != null){
                    text.getTags().add(get(sb, interval[0], wiki.substring(interval[0], interval[1])));
                    return interval[1];
                }
            }
        }
        sb.append("<");
        return i + 1;
    }
    
    public WikiTag get(StringBuilder sb, int rawPos, String rawText) {
        int beginBody = rawText.indexOf(">") + 1;
        int endBody = rawText.lastIndexOf("<");
        WikiText body;
        if(beginBody < endBody){
            body = WikiTextParser.get(beginBody, rawText.substring(beginBody, endBody));
        }else{
            body = WikiTextParser.get(rawText.length(), "");
        }
        String tag = rawText.substring(1, beginBody - 1);
        String name;
        Map<String, String> map = new HashMap<String, String>();
        if(tag.contains(" ")){
            name = tag.substring(0 , tag.indexOf(" "));
            Matcher m = Pattern.compile("([\\w-\\d]+)=(?:'(.*?)'|\"(.*?)\"|([^\\s]+))", Pattern.MULTILINE | Pattern.DOTALL).matcher(tag.substring(tag.indexOf(" ") + 1));
            while(m.find()){
                map.put(m.group(1), m.group(2) != null ? m.group(2) : m.group(3) != null ? m.group(3) : m.group(4));
            }
        }else{
            name = tag;
        }
        String text;
        if(name.equals("br")){
            text = "\n";
        }else if(name.equals("div") || name.equals("span") || name.equals("p") || 
                name.equals("small") || name.equals("center") || name.equals("blockquote") || 
                name.equals("abbr") || name.equals("includeonly") || name.equals("onlyinclude")){
            text = " " + body.getText().trim() + " ";
        }else if(name.equals("ref") || name.equals("gallery") || name.equals("noinclude")){
            text = " ";
        }else{
            text = " " + body.getText().trim() + " ";
        }
        return new WikiTag(sb, text, rawPos, rawText, name, map, body);
    }
    
    private int[] getIntervalTag(String wiki, String name, int pos){
        String begin = "<" + name;
        String end = "</" + name;
        int beginPos = wiki.indexOf(begin, pos);
        if(beginPos < 0){
            return null;
        }
        int endPos = wiki.indexOf("/>", beginPos + begin.length());
        if(endPos >= 0 && endPos < wiki.indexOf("<", beginPos + begin.length())){
            return new int[]{beginPos, endPos + 2};
        }
        int[] interval = IntervalParser.get(wiki, begin, end, pos);
        endPos = wiki.indexOf(">", interval[1]);
        if(endPos == -1){
            return null;
        }
        return new int[]{beginPos, endPos + 1};
    }
}
