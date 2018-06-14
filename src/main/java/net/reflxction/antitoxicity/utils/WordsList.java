/*
 * * Copyright 2018 github.com/ReflxctionDev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.reflxction.antitoxicity.utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Class which contains all the bad/toxic words
 * Majority of those were taken from http://www.slate.com/blogs/lexicon_valley/2013/09/11/top_swear_words_most_popular_curse_words_on_facebook.html
 */
public abstract class WordsList {

    /**
     * List of all bad words
     */
    private String[] words = new String[]{
            "shit",
            "fuck",
            "trash",
            "u suck",
            "you suck",
            "asshole",
            "bitch",
            "bastard",
            "cunt",
            "dick",
            "peasant",
            "kys",
            "pussy",
            "slut",
            "fag",
            "piss",
            "douche",
            "dickhead",
            "nigga",
            "nigger"
    };


    /**
     * Bad words as an array list (so we can stream it)
     *
     * @return ArrayList of bad words
     */
    protected List<String> getWords() {
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(words));
        return list;
    }

}
