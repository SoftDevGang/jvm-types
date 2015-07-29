package org.codecop.ki.model;

import lombok.Getter;
import lombok.Setter;

import org.codecop.ki.model.values.FileExtension;
import org.codecop.ki.model.values.MimeType;

@Getter
@Setter
public class BaseFile {

    // to search
    private String path;

    // to blacklist and detect media types
    private FileExtension extension;

    // to debug and analysis
    private MimeType mimeType;

}
