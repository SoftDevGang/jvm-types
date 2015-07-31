package org.codecop.ki.model.descriptor;

import lombok.Getter;
import lombok.Setter;

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
