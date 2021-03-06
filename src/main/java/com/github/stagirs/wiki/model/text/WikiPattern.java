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
package com.github.stagirs.wiki.model.text;

import com.github.stagirs.wiki.model.WikiText;
import java.util.Map;

/**
 *
 * @author Dmitriy Malakhov
 */
public class WikiPattern extends WikiText.Item{
    private final String name;
    private final Map<String, WikiText> map;
    
    public WikiPattern(StringBuilder sb, String text, int rawPos, String rawText, String name, Map<String, WikiText> map) {
        super(sb, text, rawPos, rawText);
        this.name = name;
        this.map = map;
    }
    
    public String getName() {
        return name;
    }

    public Map<String, WikiText> getAttrs() {
        return map;
    }
    
}
