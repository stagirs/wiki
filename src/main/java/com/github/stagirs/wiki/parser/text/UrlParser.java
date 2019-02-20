/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.stagirs.wiki.parser.text;

import com.github.stagirs.wiki.parser.IntervalParser;
import com.github.stagirs.wiki.model.WikiText;
import com.github.stagirs.wiki.model.text.WikiUrl;
import com.github.stagirs.wiki.parser.WikiTextParser;

/**
 *
 * @author NPSP-MalakhovDA
 */
public class UrlParser implements WikiTextParser.Parser{

    @Override
    public int curIndex(String wiki, int i) {
        while(i >= 0){
            int extLinkIndex = wiki.indexOf("://", i) - 1;
            if(extLinkIndex < 0){
                return -1;
            }
            if(wiki.charAt(extLinkIndex) < 'a' || 'z' < wiki.charAt(extLinkIndex)){
                i = extLinkIndex + 4;
                continue;
            }
            for (; extLinkIndex >= i; extLinkIndex--) {
                if('a' <= wiki.charAt(extLinkIndex) && wiki.charAt(extLinkIndex) <= 'z'){
                    continue;
                }
                break;
            }
            return extLinkIndex >= i && wiki.charAt(extLinkIndex) == '[' ? extLinkIndex : extLinkIndex + 1;
        }
        return -1;
    }

    @Override
    public int process(WikiText text, StringBuilder sb, String wiki, int i) {
        if(wiki.charAt(i) == '['){
            int curPos = i;
            while(wiki.indexOf(']', curPos) >= 0 && 
                    wiki.indexOf("[[", curPos) >= 0 && 
                    wiki.indexOf("[[", curPos) < wiki.indexOf("]]", curPos) && 
                    wiki.indexOf("]]", curPos) == wiki.indexOf(']', curPos)){
                curPos = IntervalParser.get(wiki, "[[", "]]", curPos)[1];
            }
            int end = wiki.indexOf(']', curPos) + 1;
            if(end > 0){
                text.getUrls().add(get(sb, i, wiki.substring(i, end)));
                return end;
            }
            i++;
        }
        int j = i;
        for (; j < wiki.length(); j++) {
            if(Character.isLetter(wiki.charAt(j))){
                continue;
            }
            if(Character.isDigit(wiki.charAt(j))){
                continue;
            }
            if(wiki.charAt(j) == ':' || wiki.charAt(j) == '.' || wiki.charAt(j) == '/' || wiki.charAt(j) == '%' || wiki.charAt(j) == '?' || wiki.charAt(j) == '&' || wiki.charAt(j) == '=' || wiki.charAt(j) == '_'){
                continue;
            }
            break;
        }
        text.getUrls().add(get(sb, i, wiki.substring(i, j)));
        return j;
    }
    
    public WikiUrl get(StringBuilder sb, int rawPos, String rawText) {
        if(!rawText.contains("://")){
            throw new RuntimeException("bad link: " + rawText);
        }
        if(rawText.startsWith("[")){
            rawText = rawText.substring(1, rawText.length() - 1);
            rawPos++;
        }
        if(rawText.substring(0, rawText.indexOf("://") + 3).matches("[\\w]+\\://")){
            String text;
            String url;
            WikiText body;
            if(rawText.contains(" ")){
                url = rawText.substring(0, rawText.indexOf(" "));
                body = WikiTextParser.get(url.length() + 1, rawText.substring(url.length() + 1));
                text = body.getText();
            }else{
                url = rawText;
                body = WikiTextParser.get(rawText.length(), "");
                text = body.getText();
            }
            return new WikiUrl(sb, text, rawPos, rawText, url, body);
        }else{
            throw new RuntimeException("bad link: " + rawText);
        }
    }
}
