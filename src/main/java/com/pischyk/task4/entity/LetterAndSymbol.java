package com.pischyk.task4.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class LetterAndSymbol implements TextComponent {
    static Logger logger = LogManager.getLogger();

    private ComponentType componentType;
    private char character;

    public LetterAndSymbol(char character) {
        this.character = character;
    }

    public LetterAndSymbol(ComponentType componentType, char character) {
        this.componentType = componentType;
        this.character = character;
    }

    @Override
    public void add(TextComponent component) {
        logger.info("Unsupported operation add symbol");
        throw new UnsupportedOperationException("Unsupported operation add symbol");
    }

    @Override
    public void remove(TextComponent component) {
        logger.info("Unsupported operation remove symbol");
        throw new UnsupportedOperationException("Unsupported operation remove symbol");
    }

    @Override
    public List<TextComponent> getChild() {
        logger.warn("Hasn't got list");
        throw new UnsupportedOperationException("Unsupported operation remove symbol");
    }

    @Override
    public ComponentType getType() {
        return ComponentType.SYMBOL;
    }


    @Override
    public int size() {
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LetterAndSymbol)) return false;
        LetterAndSymbol that = (LetterAndSymbol) o;
        return character == that.character && (componentType != null ?
                componentType == that.componentType :
                that.componentType == null);
    }

    @Override
    public int hashCode() {
        int result = 11;
        result = result * Character.hashCode(character);
        result = result + (componentType != null ? componentType.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return Character.toString(character);
    }

    @Override
    public void setComponents(List<TextComponent> components) {
        throw new UnsupportedOperationException();
    }
}
