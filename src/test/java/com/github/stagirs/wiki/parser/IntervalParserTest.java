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
package com.github.stagirs.wiki.parser;

import com.github.stagirs.wiki.parser.IntervalParser;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dmitriy Malakhov
 */
public class IntervalParserTest {
    
    @Test
    public void test1() throws IOException{
        String content = FileUtils.readFileToString(new File("src/main/resources/interval/Таблица дивизионов"), "utf-8");
        int[] interval = IntervalParser.get(content, "{", "}", 0);
        assertEquals(content.length(), interval[1] - interval[0]); 
    } 
}
