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
import com.github.stagirs.wiki.model.text.WikiCategory;
import com.github.stagirs.wiki.parser.WikiTextParser;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Dmitriy Malakhov
 */
public class CategoryParserTest {
    @Test
    public void test1(){
        WikiText text = WikiTextParser.get(0, "[[Категория:Москва| ]]");
        WikiCategory category = text.getCategories().get(0);
        assertEquals(category.getId(), "Москва");
        assertEquals(category.getText(), " ");
    }
    
    @Test
    public void test2(){
        WikiText text = WikiTextParser.get(0, "[[Категория:Золотое кольцо России]]");
        WikiCategory category = text.getCategories().get(0);
        assertEquals(category.getId(), "Золотое кольцо России");
        assertEquals(category.getText(), " ");
    }
}
