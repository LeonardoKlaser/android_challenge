package br.com.kunden.movies_catalog.utils;

public class StringUtils {
    public static boolean isNullOrBlank(String param) {
        return param == null || param.trim().length() == 0;
    }
}
