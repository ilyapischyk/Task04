package com.pischyk.task4.reader;

import com.pischyk.task4.exception.CustomException;

public interface TextReader {

    String readText(String pathToFile) throws CustomException;
}
