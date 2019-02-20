/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.stagirs.wiki.parser;

import com.github.stagirs.wiki.model.WikiText;
import com.github.stagirs.wiki.parser.text.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author NPSP-MalakhovDA
 */
public class WikiTextParser {
    public interface Parser{
        int curIndex(String wiki, int i);
        int process(WikiText text, StringBuilder sb, String wiki, int i);
    }
    
    
    public static WikiText get(int rawPos, String rawText, String ... ends){
        List<Parser> parsers = new ArrayList(
                Arrays.asList(
                    new PatternParser(), 
                    new FileParser(),
                    new CategoryParser(),
                    new LinkParser(), 
                    new UrlParser(),
                    new CommentParser(),
                    new TagParser(),
                    new FormatParser(),
                    new TableParser()
                )
        );
        for(final String end : ends){
            parsers.add(new Parser() {
                @Override
                public int curIndex(String wiki, int i) {
                    return wiki.indexOf(end, i);
                }

                @Override
                public int process(WikiText text, StringBuilder sb, String wiki, int i) {
                    return i;
                }
            });
        }
        WikiText text = new WikiText(rawPos);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while(i < rawText.length()){
            int minIndex = Integer.MAX_VALUE;
            Parser minParser = null;
            for (Parser parser : parsers) {
                int index = parser.curIndex(rawText, i);
                if(index >= 0 && i > index){
                    throw new RuntimeException("index >= 0 && i > index");
                }
                if(index >= 0 && index < minIndex){
                    minParser = parser;
                    minIndex = index;
                }
            }
            if(minParser == null){
                sb.append(rawText.substring(i));
                i = rawText.length();
            }else{
                sb.append(rawText.substring(i, minIndex));
                i = minParser.process(text, sb, rawText, minIndex);
                if(minIndex == i){
                    rawText = rawText.substring(0, i);
                }
            }
        }
        text.setRawText(rawText);
        text.setText(sb.toString());
        return text;
    }
}
