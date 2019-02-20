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
import com.github.stagirs.wiki.model.text.WikiFile;
import com.github.stagirs.wiki.parser.WikiTextParser;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Dmitriy Malakhov
 */
public class FileParserTest {
    @Test
    public void test1(){
        WikiText text = WikiTextParser.get(0, "[[Файл:Example.png|мини|Пояснительный текст]]");
        WikiFile file = text.getFiles().get(0);
        assertEquals(file.getId(), "Example.png");
        assertEquals(file.getBody().getRawText(), "Пояснительный текст");
    }
    
    @Test
    public void test2(){
        WikiText text = WikiTextParser.get(0, "[[Файл:And1236.jpg|мини|[[Андомский геологический разрез|Андома-гора]], геологический памятник на побережье Онежского озера, в 30 км от города [[Вытегра|Вытегры]]]]");
        WikiFile file = text.getFiles().get(0);
        assertEquals(file.getId(), "And1236.jpg");
        assertEquals(file.getBody().getRawText(), "[[Андомский геологический разрез|Андома-гора]], геологический памятник на побережье Онежского озера, в 30 км от города [[Вытегра|Вытегры]]");
    }
    
    @Test
    public void test3(){
        WikiText text = WikiTextParser.get(0, "[[Файл:Lestvitsa-1387.jpg|мини|слева|Первое известное упоминание слова «Россия»]]");
        WikiFile file = text.getFiles().get(0);
        assertEquals(file.getId(), "Lestvitsa-1387.jpg");
        assertEquals(file.getType(), "мини|слева");
        assertEquals(file.getBody().getRawText(), "Первое известное упоминание слова «Россия»");
    }
    
}
