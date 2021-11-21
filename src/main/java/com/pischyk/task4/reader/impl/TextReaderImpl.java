package com.pischyk.task4.reader.impl;

import com.pischyk.task4.exception.CustomException;
import com.pischyk.task4.reader.TextReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;

public class TextReaderImpl implements TextReader {
    static Logger logger = LogManager.getLogger();

    @Override
    public String readText(String pathToFile) throws CustomException {
        String text;

        try {
            text = Files.readString(Paths.get(pathToFile));
            logger.info("Text: " + "\n" + text);

        } catch (FileNotFoundException ex) {
            logger.error(pathToFile + " - the file was not found, ", ex);
            throw new CustomException(pathToFile + " - the file was not found, ", ex);
        } catch (IOException ex) {
            logger.error(pathToFile + " I/O error ", ex);
            throw new CustomException(pathToFile + " I/O error ", ex);
        }
        return text;
    }
}
