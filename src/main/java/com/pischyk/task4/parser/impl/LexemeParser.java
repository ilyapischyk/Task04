package com.pischyk.task4.parser.impl;

import com.pischyk.task4.entity.ComponentType;
import com.pischyk.task4.entity.TextComposite;
import com.pischyk.task4.parser.TextParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser implements TextParser {

    private final String MATH_REGEX = "([-+/*|%$&~><^.)(\\d]+){2,}";
    private final String LEXEME_REGEX = "\\s";
    private final String WORD_DELIMITER_REGEX = "[А-я\\p{Alpha}]+";
    private final String PRE_PUNCTUATION_REGEX = "^\\p{Punct}(?!\\d)";
    private final String POST_PUNCTUATION_REGEX = "(?<!\\d)\\p{Punct}+$";
    private final int POST_PUNCTUATION_MIN_LENGTH = 1;
    private final TextParser wordParser = new WordParser();
    private final TextParser symbolParser = new SymbolParser();

    @Override
    public TextComposite parse(String data) {
        TextComposite lexemeComposite = new TextComposite(ComponentType.LEXEME);
        String[] lexemes = data.split(LEXEME_REGEX);

        for (String lexeme : lexemes) {
            regexParse(lexeme, MATH_REGEX, lexemeComposite);
            regexParse(lexeme, PRE_PUNCTUATION_REGEX, lexemeComposite);

            Pattern pattern = Pattern.compile(WORD_DELIMITER_REGEX);
            Matcher matcher = pattern.matcher(lexeme);
            if (matcher.find()) {
                TextComposite nextComposite = wordParser.parse(matcher.group());
                lexemeComposite.add(nextComposite);
            }
            if (lexeme.length() > POST_PUNCTUATION_MIN_LENGTH) {
                regexParse(lexeme, POST_PUNCTUATION_REGEX, lexemeComposite);
            }
        }
        return lexemeComposite;
    }

    private void regexParse(String lexeme, String REGEX, TextComposite lexemeComposite) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(lexeme);
        List<String> component = new ArrayList<>();
        while (matcher.find()) {
            component.add(matcher.group());
        }
        if (!component.isEmpty()) {
            for (String symbol : component) {
                TextComposite composite = symbolParser.parse(symbol);
                lexemeComposite.add(composite);
            }
        }
    }
}
