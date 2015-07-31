package org.codecop.ki.model.descriptor;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * Value object identifying documents uniquely.
 */
@ValueObject
@RequiredArgsConstructor
@EqualsAndHashCode
public class Id {

    private static final OnlyAlphaNum ENCODING = new OnlyAlphaNum();

    public final String value;

    @Override
    public String toString() {
        return value;
    }

    public String readableValue() {
        return ENCODING.makeReadable(value);
    }

    @SuppressWarnings("null")
    public static Id forPath(String path) {
        String value = ENCODING.escape(path);
        return new Id(value.intern());
    }

}
