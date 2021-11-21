package com.pischyk.task4.parser.impl;

import static org.testng.Assert.*;

import com.pischyk.task4.entity.TextComposite;
import com.pischyk.task4.exception.CustomException;
import com.pischyk.task4.reader.impl.TextReaderImpl;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class ParagraphParserTest {


    private TextReaderImpl readerMock;
    private ParagraphParser parser;
    private TextComposite actual;
    private String expected;
    private String text;


    @Test//(enabled = false)
    public void testParse() throws CustomException {
        MockitoAnnotations.openMocks(this);
        parser = new ParagraphParser();
        readerMock = spy(new TextReaderImpl());//java.lang.ExceptionInInitializerError
        text = "First paragraph. First sentence. Second sentence.\n" +
                "Second paragraph. First sentence of second paragraph.";
        doReturn(text)
                .when(readerMock)
                .readText(any());
        actual = parser.parse(readerMock.readText(any()));
        expected = " PARAGRAPH [ SENTENCE [ LEXEME [ WORD [ SYMBOL [F] SYMBOL [i] SYMBOL [r] SYMBOL [s] SYMBOL [t]]]  " +
                "LEXEME [ WORD [ SYMBOL [p] SYMBOL [a] SYMBOL [r] SYMBOL [a] SYMBOL [g] SYMBOL [r] SYMBOL [a] SYMBOL [p] SYMBOL [h]]] " +
                " LEXEME [ SYMBOL [.]] ] SENTENCE [ LEXEME [ WORD [ SYMBOL [F] SYMBOL [i] SYMBOL [r] SYMBOL [s] SYMBOL [t]]]  " +
                "LEXEME [ WORD [ SYMBOL [s] SYMBOL [e] SYMBOL [n] SYMBOL [t] SYMBOL [e] SYMBOL [n] SYMBOL [c] SYMBOL [e]]] " +
                " LEXEME [ SYMBOL [.]] ] SENTENCE [ LEXEME [ WORD [ SYMBOL [S] SYMBOL [e] SYMBOL [c] SYMBOL [o] SYMBOL [n] SYMBOL [d]]] " +
                " LEXEME [ WORD [ SYMBOL [s] SYMBOL [e] SYMBOL [n] SYMBOL [t] SYMBOL [e] SYMBOL [n] SYMBOL [c] SYMBOL [e]]] " +
                " LEXEME [ SYMBOL [.]] ]]\n" +
                " PARAGRAPH [ SENTENCE [ LEXEME [ WORD [ SYMBOL [S] SYMBOL [e] SYMBOL [c] SYMBOL [o] SYMBOL [n] SYMBOL [d]]]  " +
                "LEXEME [ WORD [ SYMBOL [p] SYMBOL [a] SYMBOL [r] SYMBOL [a] SYMBOL [g] SYMBOL [r] SYMBOL [a] SYMBOL [p] SYMBOL [h]]]  " +
                "LEXEME [ SYMBOL [.]] ] SENTENCE [ LEXEME [ WORD [ SYMBOL [F] SYMBOL [i] SYMBOL [r] SYMBOL [s] SYMBOL [t]]]  " +
                "LEXEME [ WORD [ SYMBOL [s] SYMBOL [e] SYMBOL [n] SYMBOL [t] SYMBOL [e] SYMBOL [n] SYMBOL [c] SYMBOL [e]]]  " +
                "LEXEME [ WORD [ SYMBOL [o] SYMBOL [f]]]  " +
                "LEXEME [ WORD [ SYMBOL [s] SYMBOL [e] SYMBOL [c] SYMBOL [o] SYMBOL [n] SYMBOL [d]]]  " +
                "LEXEME [ WORD [ SYMBOL [p] SYMBOL [a] SYMBOL [r] SYMBOL [a] SYMBOL [g] SYMBOL [r] SYMBOL [a] SYMBOL [p] SYMBOL [h]]]  LEXEME [ SYMBOL [.]] ]]\n";
        assertEquals(expected, actual.toString());
    }
}