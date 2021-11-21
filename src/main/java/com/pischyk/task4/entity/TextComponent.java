package com.pischyk.task4.entity;

import java.util.List;

public interface TextComponent {

    void add(TextComponent component);

    void remove(TextComponent component);

    List<TextComponent> getChild();

    int size();

    void setComponents(List<TextComponent> components);

    ComponentType getType();

    boolean equals(Object o);

    int hashCode();

    String toString();

}
