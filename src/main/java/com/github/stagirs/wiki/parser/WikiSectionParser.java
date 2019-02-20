/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.stagirs.wiki.parser;

import com.github.stagirs.wiki.model.WikiSection;
import com.github.stagirs.wiki.model.WikiText;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NPSP-MalakhovDA
 */
public class WikiSectionParser {
    
    public static WikiSection get(int level, int titlePos, String title, int wikiPos, String wiki){
        int i = 0;
        List<WikiText> points = new ArrayList<WikiText>();
        List<WikiSection> subSections = new ArrayList<WikiSection>();
        while(i < wiki.length()) {
            if(Character.isSpaceChar(wiki.charAt(i)) || Character.isWhitespace(wiki.charAt(i))){
                i++;
                continue;
            }
            if(i < wiki.length() - 1 && wiki.charAt(i) == '=' && wiki.charAt(i + 1) == '='){
                int newLevel = getLevel(i, wiki);
                int titleEnd = getTitleEnd(newLevel, i, wiki);
                if(titleEnd >= 0){
                    if(newLevel > level){
                        WikiSection subSection = WikiSectionParser.get(newLevel, i + newLevel, wiki.substring(i + newLevel, titleEnd), titleEnd + newLevel, wiki.substring(titleEnd + newLevel));
                        i = subSection.getPosEnd();
                        subSections.add(subSection);
                        continue;
                    }else{
                        break;
                    }
                }
            }
            WikiText text = WikiTextParser.get(i, wiki.substring(i), "\t\t", "\t==");
            points.add(text);
            i += text.getRawText().length();
        }
        return new WikiSection(level, WikiTextParser.get(titlePos, title), points, subSections, wikiPos, wikiPos + i);
    }
    
    private static int getTitleEnd(int level, int i, String wiki){
        int tabPos = wiki.indexOf("\t", i);
        if(tabPos < 0){
            tabPos = wiki.length();
        }
        int endPos = wiki.indexOf(wiki.substring(i, i + level), i + level);
        if(endPos > 0 && endPos < tabPos){
            return endPos;
        }
        return -1;
    }
    
    private static int getLevel(int i, String wiki){
        int newLevel = 0;
        for (int j = i; j < wiki.length(); j++) {
            if(wiki.charAt(j) == '='){
                newLevel++;
            }else{
                break;
            }
        }
        return newLevel;
    }
}
