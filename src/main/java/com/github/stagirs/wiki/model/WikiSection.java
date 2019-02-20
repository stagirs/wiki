/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.stagirs.wiki.model;

import java.util.List;

/**
 *
 * @author NPSP-MalakhovDA
 */
public class WikiSection {
    protected final int level;
    protected final WikiText title;
    protected final List<WikiText> points;
    protected final List<WikiSection> subSections;
    protected final int posBegin;
    protected final int posEnd;

    public WikiSection(int level, WikiText title, List<WikiText> points, List<WikiSection> subSections, int posBegin, int posEnd) {
        this.level = level;
        this.title = title;
        this.points = points;
        this.subSections = subSections;
        this.posBegin = posBegin;
        this.posEnd = posEnd;
    }

    public int getLevel() {
        return level;
    }

    public List<WikiText> getPoints() {
        return points;
    }

    public WikiText getTitle() {
        return title;
    }

    public List<WikiSection> getSubSections() {
        return subSections;
    }

    public int getPosBegin() {
        return posBegin;
    }

    public int getPosEnd() {
        return posEnd;
    }

    @Override
    public String toString() {
        return title.toString();
    }
    
}
