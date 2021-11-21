package com.pischyk.task4.parser.impl;

import com.pischyk.task4.entity.ComponentType;
import com.pischyk.task4.entity.LetterAndSymbol;
import com.pischyk.task4.entity.TextComposite;
import com.pischyk.task4.parser.TextParser;

public class SymbolParser implements TextParser {

    @Override
    public TextComposite parse(String text) {
        TextComposite symbolComposite = new TextComposite(ComponentType.SYMBOL);
        char[] symbols = text.toCharArray();
        for (char symbol : symbols) {
            LetterAndSymbol leaf = new LetterAndSymbol(symbol);
            symbolComposite.add(leaf);
        }
        return symbolComposite;
    }
}
