package org.codecop.ki.model.values;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.codecop.ki.model.descriptor.ValueObject;

@ValueObject
@FieldDefaults(level = AccessLevel.PUBLIC, makeFinal = true)
@RequiredArgsConstructor
@EqualsAndHashCode
public class MimeType {

    String mimeType;

    @Override
    public String toString() {
        return mimeType;
    }

    @SuppressWarnings("null")
    public static MimeType from(String fullQualifiedMimeType) {
        int pos = fullQualifiedMimeType.indexOf(';');
        if (pos != -1) {
            return new MimeType(fullQualifiedMimeType.substring(0, pos).intern());
        }
        return new MimeType(fullQualifiedMimeType.intern());
    }

    public boolean isImage() {
        return mimeType.startsWith("image/");
    }
}
