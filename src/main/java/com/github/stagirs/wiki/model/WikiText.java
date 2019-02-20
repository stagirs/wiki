/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.stagirs.wiki.model;

import com.github.stagirs.wiki.model.text.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NPSP-MalakhovDA
 */
public class WikiText {
    public static class Item{
        protected final int pos;
        protected final int rawPos;
        protected final String text;
        protected final String rawText;

        public Item(StringBuilder sb, String text, int rawPos, String rawText) {
            this.rawText = rawText;
            this.text = text;
            this.rawPos = rawPos;
            this.pos = sb.length();
            sb.append(text);
        }

        public String getText() {
            return text;
        }

        public String getRawText() {
            return rawText;
        }

        public int getPos() {
            return pos;
        }

        public int getRawPos() {
            return rawPos;
        }

        @Override
        public String toString() {
            return rawText; 
        }
        
    }
    
    protected final int rawPos;
    private String rawText;
    private String text;
    
    private List<WikiTag> tags = new ArrayList<WikiTag>();
    private List<WikiLink> links = new ArrayList<WikiLink>();
    private List<WikiUrl> urls = new ArrayList<WikiUrl>();
    private List<WikiFile> files = new ArrayList<WikiFile>();
    private List<WikiCategory> categories = new ArrayList<WikiCategory>();
    private List<WikiPattern> patterns = new ArrayList<WikiPattern>();
    private List<WikiTable> tables = new ArrayList<WikiTable>();
    private List<WikiFormat> formats = new ArrayList<WikiFormat>();
    private List<WikiComment> comments = new ArrayList<WikiComment>();

    public WikiText(int rawPos) {
        this.rawPos = rawPos;
    }
    
    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRawPos() {
        return rawPos;
    }

    public String getRawText() {
        return rawText;
    }

    public String getText() {
        return text;
    }

    public List<WikiCategory> getCategories() {
        return categories;
    }

    public List<WikiComment> getComments() {
        return comments;
    }

    public List<WikiFile> getFiles() {
        return files;
    }

    public List<WikiFormat> getFormats() {
        return formats;
    }

    public List<WikiLink> getLinks() {
        return links;
    }

    public List<WikiPattern> getPatterns() {
        return patterns;
    }

    public List<WikiTable> getTables() {
        return tables;
    }

    public List<WikiTag> getTags() {
        return tags;
    }

    public List<WikiUrl> getUrls() {
        return urls;
    }

    @Override
    public String toString() {
        return rawText;
    }
    
}
