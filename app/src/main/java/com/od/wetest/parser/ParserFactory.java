package com.od.wetest.parser;

import com.od.wetest.interfaces.IParser;

public class ParserFactory {
    public static IParser getParser() {
        return GsonParser.getGsonInstance();
    }
}
