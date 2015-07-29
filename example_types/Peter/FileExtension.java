package org.codecop.ki.model.values;

import javax.annotation.Nullable;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import org.codecop.ki.model.descriptor.ValueObject;

@ValueObject
@FieldDefaults(level = AccessLevel.PUBLIC, makeFinal = true)
@EqualsAndHashCode
public class FileExtension {

    private static final String EXTENSION_PATTERN = "[a-zA-Z0-9]{1,10}";

    String extension;

    @SuppressWarnings("null")
    public FileExtension(String extension) {
        if (!extension.matches(EXTENSION_PATTERN)) {
            throw new IllegalArgumentException(extension);
        }
        this.extension = extension.toLowerCase().intern();
    }

    @Override
    public String toString() {
        return extension;
    }

    public static boolean hasExtension(String fileName) {
        return fileName.matches(".*" + "\\." + EXTENSION_PATTERN);
    }

    @SuppressWarnings("null")
    public static FileExtension from(String fileName) {
        int pos = fileName.lastIndexOf('.');
        String ext = fileName.substring(pos + 1);
        return new FileExtension(ext);
    }

    @SuppressWarnings("null")
    public static FileExtension fromPath(@Nullable String path) {
        if (path != null && FileExtension.hasExtension(path)) {
            return FileExtension.from(path);
        }
        return null;
    }

}
