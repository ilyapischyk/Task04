package com.pischyk.task4.service;

import com.pischyk.task4.entity.TextComponent;
import com.pischyk.task4.entity.TextComposite;
import com.pischyk.task4.exception.CustomException;

import java.util.List;

public interface Service {
    List<TextComponent> sortParagraphs(TextComposite composite) throws CustomException;

    List<TextComponent> findSentencesWithLongWord(TextComposite composite) throws CustomException;


    void deleteSentencesWithLessWords(TextComposite composite, int wordsAmount);


    List<String> countRepeatWords(TextComposite composite);


    int countVowels(TextComponent sentence) throws CustomException;


    int countConsonants(TextComponent sentence) throws CustomException;
}
