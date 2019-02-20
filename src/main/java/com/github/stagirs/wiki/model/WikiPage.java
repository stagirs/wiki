/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.stagirs.wiki.model;

import com.github.stagirs.wiki.parser.WikiSectionParser;
import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

/**
 *
 * @author NPSP-MalakhovDA
 */
public class WikiPage{
    protected final WikiText title;
    protected final List<WikiText> points;
    protected final List<WikiSection> sections;

    public WikiPage(String title, String text) {
        text = text.replace("\r\n", "\t").replace("\n", "\t");
        WikiSection section = WikiSectionParser.get(0, 0, title, 0, text);
        this.title = section.getTitle();
        this.points = section.getPoints();
        this.sections = section.getSubSections();
    }

    public List<WikiText> getPoints() {
        return points;
    }

    public List<WikiSection> getSections() {
        return sections;
    }

    
    public WikiText getTitle() {
        return title;
    }
    
    public List<WikiText> getAllPoints(){
        List<WikiText> result = new ArrayList<WikiText>();
        result.addAll(points);
        for (WikiSection subSection : sections) {
            setPoints(result, subSection);
        }
        return result;
    }
    
    private void setPoints(List<WikiText> result, WikiSection wikiSection){
        result.addAll(wikiSection.points);
        for (WikiSection subSection : wikiSection.subSections) {
            setPoints(result, subSection);
        }
    }
    
    public List<WikiText> getAllTitles(){
        List<WikiText> result = new ArrayList<WikiText>();
        for (WikiSection subSection : sections) {
            result.add(subSection.getTitle());
            setTitles(result, subSection);
        }
        return result;
    }
    
    private void setTitles(List<WikiText> result, WikiSection wikiSection){
        for (WikiSection subSection : wikiSection.subSections) {
            result.add(subSection.getTitle());
            setTitles(result, subSection);
        }
    }
    
    public List<WikiSection> getAllSections(){
        List<WikiSection> result = new ArrayList<WikiSection>();
        for (WikiSection subSection : sections) {
            result.add(subSection);
            setSections(result, subSection);
        }
        return result;
    }
    
    private void setSections(List<WikiSection> result, WikiSection wikiSection){
        for (WikiSection subSection : wikiSection.subSections) {
            result.add(subSection);
            setSections(result, subSection);
        }
    }
    
    public static WikiPage fromXml(String xml){
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new ByteArrayInputStream(xml.getBytes(Charset.forName("utf-8"))));
            String title = doc.getElementsByTagName("title").item(0).getTextContent();
            String text = doc.getElementsByTagName("text").item(0).getTextContent();
            return new WikiPage(title, text);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
