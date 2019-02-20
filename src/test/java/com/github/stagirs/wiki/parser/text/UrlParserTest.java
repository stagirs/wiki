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
import com.github.stagirs.wiki.model.text.WikiUrl;
import com.github.stagirs.wiki.parser.WikiTextParser;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Dmitriy Malakhov
 */
public class UrlParserTest {
    
    @Test
    public void test1(){
        WikiText text = WikiTextParser.get(0, "[https://vm.cs.msu.ru// Кафедра вычислительных методов — сайт кафедры ВМ]");
        WikiUrl link = text.getUrls().get(0);
        assertEquals(link.getUrl(), "https://vm.cs.msu.ru//");
        assertEquals(link.getText(), "Кафедра вычислительных методов — сайт кафедры ВМ");
    }
    
    @Test
    public void test2(){
        WikiText text = WikiTextParser.get(0, "[http://www.example.org]");
        WikiUrl link = text.getUrls().get(0);
        assertEquals(link.getUrl(), "http://www.example.org");
        assertEquals(link.getText(), "");
    }
    
    @Test
    public void test3(){
        WikiText text = WikiTextParser.get(0, "http://www.example.org");
        WikiUrl link = text.getUrls().get(0);
        assertEquals(link.getUrl(), "http://www.example.org");
        assertEquals(link.getText(), "");
    }
    
    @Test
    public void test4(){
        WikiText text = WikiTextParser.get(0, "[https://books.google.ru/books?id=xUJnAAAAQBAJ&printsec=frontcover&dq=%D0%92%D0%9C%D0%9A+%D0%9C%D0%93%D0%A3&hl=ru&sa=X&ei=ASUVUqGEOcai4gTlnoDYAQ&redir_esc=y#v=onepage&q&f=false Подготовка кадров на факультете ВМК МГУ /[[Прикладная информатика (журнал)|Прикладная информатика]]]");
        WikiUrl link = text.getUrls().get(0);
        assertEquals(link.getUrl(), "https://books.google.ru/books?id=xUJnAAAAQBAJ&printsec=frontcover&dq=%D0%92%D0%9C%D0%9A+%D0%9C%D0%93%D0%A3&hl=ru&sa=X&ei=ASUVUqGEOcai4gTlnoDYAQ&redir_esc=y#v=onepage&q&f=false");
        assertEquals(link.getBody().getRawText(), "Подготовка кадров на факультете ВМК МГУ /[[Прикладная информатика (журнал)|Прикладная информатика]]");
        assertEquals(link.getText(), "Подготовка кадров на факультете ВМК МГУ /Прикладная информатика");
    }
    
}
