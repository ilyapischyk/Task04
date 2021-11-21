package com.pischyk.task4.parser.impl;

import com.pischyk.task4.entity.ComponentType;
import com.pischyk.task4.entity.TextComposite;
import com.pischyk.task4.parser.TextParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser implements TextParser {

    private final String SENTENCE_REGEX = "([А-ЯA-Z]((!=|.toString)|[^?!.(]|\\([^)]*\\))*[.?!]{1,3})";
    private final TextParser lexemeParser = new LexemeParser();

    @Override
    public TextComposite parse(String data) {
        TextComposite sentenceComposite = new TextComposite(ComponentType.SENTENCE);
        Pattern pattern = Pattern.compile(SENTENCE_REGEX);
        Matcher matcher = pattern.matcher(data);
        List<String> sentences = new ArrayList<>();

        while (matcher.find()) {
            sentences.add(matcher.group());
        }
        for (String sentence : sentences) {
            TextComposite nextComposite = lexemeParser.parse(sentence);
            sentenceComposite.add(nextComposite);
        }
        return sentenceComposite;
    }
}