package org.codecop.ki.model.descriptor;

import java.util.ArrayList;
import java.util.List;

class OnlyAlphaNum {

    private final List<Replacement> replacementsToReadable = new ArrayList<>();

    public OnlyAlphaNum() {
        prepareCommonReplacements();
    }

    private void prepareCommonReplacements() {
        willMakeReadable(' ');
        willMakeReadable('-');
        willMakeReadable('.');
        willMakeReadable('/');
        willMakeReadable('_');
    }

    @SuppressWarnings("null")
    private void willMakeReadable(char c) {
        replacementsToReadable.add(new Replacement(encode(c), String.valueOf(c)));
    }

    @SuppressWarnings("null")
    public String escape(String path) {
        StringBuilder buf = new StringBuilder();
        for (char c : path.toLowerCase().toCharArray()) {
            boolean isAsciiAlphaNum = c >= 'a' && c <= 'z' || c >= '0' && c <= '9';
            if (isAsciiAlphaNum) {
                buf.append(c);
            } else {
                buf.append(encode(c));
            }
        }
        return buf.toString();
    }

    @SuppressWarnings("null")
    private static String encode(char c) {
        return Integer.toHexString(c).toLowerCase();
    }

    public String makeReadable(String escapedValue) {
        String readable = escapedValue;
        for (Replacement replacement : replacementsToReadable) {
            readable = replacement.replace(readable);
        }
        return readable;
    }

    private static class Replacement {

        private final String from;
        private final String to;

        public Replacement(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @SuppressWarnings("null")
        public String replace(String original) {
            return original.replaceAll(from, to);
        }

    }

}
