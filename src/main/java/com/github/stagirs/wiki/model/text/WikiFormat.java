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

/**
 *
 * @author Dmitriy Malakhov
 */
public class WikiFormat extends WikiText.Item{
    public enum Type{ITALICS("''"), BOLD("'''"), ITALICS_BOLD("'''''"), LIST("*"), NLIST("#"), INDENT(":"); 
    
        String name;

        private Type(String name) {
            this.name = name;
        }
    }
    
    Type type;

    public WikiFormat(StringBuilder sb, int rawPos, String rawText) {
        super(sb, "", rawPos, rawText);
        for (Type value : Type.values()) {
            if(value.name.equals(rawText)){
                type = value;
                return;
            }
        }
    }
    
    public Type getType() {
        return type;
    }
}
