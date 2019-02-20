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
import com.github.stagirs.wiki.model.text.WikiLink;
import com.github.stagirs.wiki.parser.WikiTextParser;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Dmitriy Malakhov
 */
public class LinkParserTest {
    @Test
    public void test1(){
        WikiText text = WikiTextParser.get(0, "[[Ломоносов (суперкомпьютер)|Суперкомпьютер «Ломоносов» в МГУ]]");
        WikiLink link = text.getLinks().get(0);
        assertEquals(link.getId(), "Ломоносов (суперкомпьютер)");
        assertEquals(link.getText(), "Суперкомпьютер «Ломоносов» в МГУ");
    }
    
    @Test
    public void test2(){
        WikiText text = WikiTextParser.get(0, "[[Московский государственный университет]]");
        WikiLink link = text.getLinks().get(0);
        assertEquals(link.getId(), "Московский государственный университет");
        assertEquals(link.getText(), "Московский государственный университет");
    }
    
}
