package com.pischyk.task4.service.impl;

import com.pischyk.task4.entity.TextComponent;
import com.pischyk.task4.entity.TextComposite;
import com.pischyk.task4.parser.impl.ParagraphParser;
import com.pischyk.task4.parser.impl.SentenceParser;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class ServiceImplTest {

    TextComposite parseText(String text) {
        ParagraphParser paragraphParser = new ParagraphParser();
        TextComposite composite = paragraphParser.parse(text);
        return composite;
    }

    @Test
    public void testSortParagraphs() {
        List<TextComponent> expectedSortedParagraphs = parseText(
                "First paragraph. First sentence.\n" +
                        "Second paragraph. First sentence. Second sentence.\n" +
                        "Third paragraph. First sentence. Second sentence. Third sentence.").getChild();
        TextComposite composite = parseText("Second paragraph. First sentence. Second sentence.\n" +
                "First paragraph. First sentence.\n" +
                "Third paragraph. First sentence. Second sentence. Third sentence.");
        ServiceImpl service = new ServiceImpl();
        List<TextComponent> actualSortedParagraphs = service.sortParagraphs(composite);
        Assert.assertEquals(actualSortedParagraphs.toString(), expectedSortedParagraphs.toString());

    }

    @Test
    public void testFindSentencesWithLongWord() {
        TextComposite composite = parseText("First paragraph. First sentence. Second sentence. \n" +
                "Second paragraph. First sentence of second paragraph.\n");
        ServiceImpl service = new ServiceImpl();
        List<TextComponent> actualSentences = service.findSentencesWithLongWord(composite);
        SentenceParser sentenceParser = new SentenceParser();
        TextComposite expectedComposite = sentenceParser.parse("First paragraph.");
        List<TextComponent> expectedSentences = expectedComposite.getChild();
        Assert.assertEquals(expectedSentences.toString(), actualSentences.toString());


    }

    @Test
    public void testDeleteSentencesWithLessWords() {
        TextComposite actualcomposite = parseText("First paragraph. First sentence. Second and the last sentence.\n" +
                "Second paragraph. First sentence of second paragraph.");
        ServiceImpl service = new ServiceImpl();
        service.deleteSentencesWithLessWords(actualcomposite, 3);
        TextComposite expectedcomposite = parseText("Second and the last sentence.\n" +
                "First sentence of second paragraph.");
        Assert.assertEquals(actualcomposite.toString(), expectedcomposite.toString());


    }

    @Test
    public void testCountRepeatWords() {
        TextComposite composite = parseText("First paragraph. First sentence. Second sentence.");
        ServiceImpl service = new ServiceImpl();
        List<String> actualWords = service.countRepeatWords(composite);
        List<String> expectedWords = new ArrayList<>();
        expectedWords.add(" symbol [f] symbol [i] symbol [r] symbol [s] symbol [t]");
        expectedWords.add(" symbol [s] symbol [e] symbol [n] symbol [t] symbol [e] symbol [n] symbol [c] symbol [e]");
        Assert.assertEquals(actualWords, expectedWords);

    }

    @Test //(enabled = false)
    public void testCountVowels() {
        TextComposite composite = parseText("First paragraph. First sentence. Second sentence.");
        ServiceImpl service = new ServiceImpl();
        List<TextComponent> paragraphs = composite.getChild();
        List<TextComponent> sentences = paragraphs.get(0).getChild();
        int actualCount = service.countVowels(sentences.get(0));
        int expectedCount = 4;
        Assert.assertEquals(actualCount, expectedCount);
    }

    @Test
    public void testCountConsonants() {
        TextComposite composite = parseText("First paragraph. First sentence. Second sentence.");
        ServiceImpl service = new ServiceImpl();
        List<TextComponent> paragraphs = composite.getChild();
        List<TextComponent> sentences = paragraphs.get(0).getChild();
        int actualCount = service.countConsonants(sentences.get(0));
        int expectedCount = 10;
        Assert.assertEquals(actualCount, expectedCount);
    }
}