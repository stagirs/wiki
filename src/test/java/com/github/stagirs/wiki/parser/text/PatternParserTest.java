/*
 * Copyright 2018 Dmitriy Malakhov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.stagirs.wiki.parser.text;

import com.github.stagirs.wiki.model.WikiText;
import com.github.stagirs.wiki.model.text.WikiPattern;
import com.github.stagirs.wiki.parser.WikiTextParser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Dmitriy Malakhov
 */
public class PatternParserTest {
    
    @Test
    public void test1(){
        WikiText text = WikiTextParser.get(0, "{{Карточка факультета\n" +
                                                        "| название       = Факультет вычислительной математики и кибернетики\n" +
                                                        "| вуз            = [[Московский государственный университет|Московский государственный университет им. М. В. Ломоносова]]\n" +
                                                        "| изображение    = [[Файл:Logo_faculty_CMC_MSU.jpg|center|54px]]\n" +
                                                        "| междуназвание  = Faculty of Computational Mathematics and Cybernetics of Moscow State University (MSU)\n" +
                                                        "| девиз          = \n" +
                                                        "| основан        = [[1970]]\n" +
                                                        "| декан          = [[Моисеев, Евгений Иванович]]\n" +
                                                        "| расположение   = 119991, [[Москва]], [[Ленинские горы]], [[МГУ]], 2-й учебный корпус\n" +
                                                        "| сайт           = {{url|cs.msu.ru}}\n" +
                                                        "}}");
        WikiPattern pattern = text.getPatterns().get(0);
        assertEquals(pattern.getName(), "Карточка факультета");
        assertEquals(pattern.getAttrs().size(), 9);
        assertEquals(pattern.getAttrs().get("название").getRawText().trim(), "Факультет вычислительной математики и кибернетики");
        assertEquals(pattern.getAttrs().get("вуз").getRawText().trim(), "[[Московский государственный университет|Московский государственный университет им. М. В. Ломоносова]]");
        assertEquals(pattern.getAttrs().get("изображение").getRawText().trim(), "[[Файл:Logo_faculty_CMC_MSU.jpg|center|54px]]");
        assertEquals(pattern.getAttrs().get("междуназвание").getRawText().trim(), "Faculty of Computational Mathematics and Cybernetics of Moscow State University (MSU)");
        assertEquals(pattern.getAttrs().get("девиз").getRawText().trim(), "");
        assertEquals(pattern.getAttrs().get("основан").getRawText().trim(), "[[1970]]");
        assertEquals(pattern.getAttrs().get("декан").getRawText().trim(), "[[Моисеев, Евгений Иванович]]");
        assertEquals(pattern.getAttrs().get("расположение").getRawText().trim(), "119991, [[Москва]], [[Ленинские горы]], [[МГУ]], 2-й учебный корпус");
        assertEquals(pattern.getAttrs().get("сайт").getRawText().trim(), "{{url|cs.msu.ru}}");
        
    }
    
    @Test
    public void test2(){
        WikiText text = WikiTextParser.get(0, "{{url|cs.msu.ru}}");
        WikiPattern pattern = text.getPatterns().get(0);
        assertEquals(pattern.getName(), "url");
        assertEquals(pattern.getAttrs().size(), 1);
        assertEquals(pattern.getAttrs().get("1").getRawText(), "cs.msu.ru");
    }
    
    @Test
    public void test3(){
        WikiText text = WikiTextParser.get(0, "{{{1}}}");
        WikiPattern pattern = text.getPatterns().get(0);
        assertEquals(pattern.getName(), "{1}");
        assertEquals(pattern.getAttrs().size(), 0);
    }
    
    @Test
    public void test4(){
        WikiText text = WikiTextParser.get(0, "{{quote|Ломоносов обнял все отрасли просвещения. Жажда науки была сильнейшею страстью сей души, исполненной страстей. Историк, ритор, механик, химик, минералог, художник и стихотворец, он всё испытал и всё проник: первый углубляется в историю отечества, утверждает правила общественного языка его, даёт законы и образцы классического красноречия, с несчастным Рихманом предугадывает открытие Франклина, учреждает фабрику, сам сооружает махины, дарит художественные мозаические произведения, и наконец открывает нам истинные источники нашего поэтического языка<ref name=\"push\">Пушкин А. С. Полное собрание сочинений в девятнадцати томах (23 книгах). М.: Воскресенье 1994г</ref>}}");
        WikiPattern pattern = text.getPatterns().get(0);
        assertEquals(pattern.getName(), "quote");
        assertEquals(pattern.getAttrs().size(), 1);
        assertTrue(pattern.getAttrs().containsKey("1"));
    }
    
    }
