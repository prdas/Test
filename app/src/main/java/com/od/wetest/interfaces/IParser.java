package com.od.wetest.interfaces;

import java.io.Reader;
import java.lang.reflect.Type;

/**
 * The Parser interface contains methods to Parse Json data
 */
public interface IParser {

    /**
     * Abstract method to parse Json data
     *
     * @param <T>             the type parameter
     * @param reader          Reader instance
     * @param classOfResponse java.lang.Class<T> instance
     * @return T t
     */
    <T> T fromJson(Reader reader, Class<T> classOfResponse);

    /**
     * Abstract method to parse Json data
     *
     * @param <T>             the type parameter
     * @param string          String value
     * @param classOfResponse java.lang.Class<T> instance
     * @return T t
     */
    <T> T fromJson(String string, Class<T> classOfResponse);

    /**
     * Abstract method to parse Json data
     *
     * @param <T>            the type parameter
     * @param string         String value
     * @param collectionType java.lang.reflect.Type instance
     * @return T t
     */
    <T> T fromJson(String string, Type collectionType);

    /**
     * To json string.
     *
     * @param src Object
     * @return String value
     */
    String toJson(Object src);

    /**
     * To json with expose annotation string.
     *
     * @param src Object
     * @return String value
     */
    String toJsonWithExposeAnnotation(Object src);
}
