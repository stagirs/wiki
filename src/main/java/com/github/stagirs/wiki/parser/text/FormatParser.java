/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.stagirs.wiki.parser.text;

import com.github.stagirs.wiki.model.WikiText;
import com.github.stagirs.wiki.model.text.WikiFormat;
import com.github.stagirs.wiki.parser.WikiTextParser;

/**
 *
 * @author NPSP-MalakhovDA
 */
public class FormatParser implements WikiTextParser.Parser{
    @Override
    public int curIndex(String wiki, int i) {
        if(i == 0 && wiki.charAt(i) == '*' || wiki.charAt(i) == '#'){
            return i;
        }
        if(wiki.charAt(i) == ':'){
            return i;
        }
        return getMinExists(wiki.indexOf("''", i), wiki.indexOf("\t*", i), wiki.indexOf("\t#", i), wiki.indexOf("\t:", i));
    }

    @Override
    public int process(WikiText text, StringBuilder sb, String wiki, int i) {
        wiki = wiki.substring(i);
        if(wiki.startsWith("'''''")){
            text.getFormats().add(new WikiFormat(sb, i, "'''''"));
            return i + 5;
        }
        if(wiki.startsWith("'''")){
            text.getFormats().add(new WikiFormat(sb, i, "'''"));
            return i + 3;
        }
        if(wiki.startsWith("''")){
            text.getFormats().add(new WikiFormat(sb, i, "''"));
            return i + 2;
        }
        if(wiki.startsWith("\t*")){
            text.getFormats().add(new WikiFormat(sb, i, "*"));
            return i + 2;
        }
        if(wiki.startsWith("\t#")){
            text.getFormats().add(new WikiFormat(sb, i, "#"));
            return i + 2;
        }
        if(wiki.startsWith("*")){
            text.getFormats().add(new WikiFormat(sb, i, "*"));
            return i + 1;
        }
        if(wiki.startsWith("#")){
            text.getFormats().add(new WikiFormat(sb, i, "#"));
            return i + 1;
        }
        if(wiki.startsWith(":")){
            text.getFormats().add(new WikiFormat(sb, i, ":"));
            return i + 1;
        }
        return i + 1;
    }
    
    private int getMinExists(int ... pos){
        int min = -1;
        for (int p : pos) {
            if(p < 0){
                continue;
            }
            if(min < 0 || p < min){
                min = p;
            }
        }
        return min;
    }
}
