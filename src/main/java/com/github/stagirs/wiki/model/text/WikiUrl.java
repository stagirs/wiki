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
public class WikiUrl extends WikiText.Item{
    private String url;
    private WikiText body;

    public WikiUrl(StringBuilder sb, String text, int rawPos, String rawText, String url, WikiText body) {
        super(sb, text, rawPos, rawText);
        this.url = url;
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public WikiText getBody() {
        return body;
    }
}
