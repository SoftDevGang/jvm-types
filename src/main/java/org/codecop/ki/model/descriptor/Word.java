package org.codecop.ki.model.descriptor;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import org.codecop.ki.model.descriptor.ValueObject;

/**
 * Generic value object for a single word.
 */
@ValueObject
@FieldDefaults(level = AccessLevel.PUBLIC, makeFinal = true)
@EqualsAndHashCode
public class Word {

    String word;

    public Word(String word) {
        if (!word.matches("[\\w.]+")) {
            throw new IllegalArgumentException(word);
        }
        this.word = word;
    }

    @Override
    public String toString() {
        return word;
    }

}
