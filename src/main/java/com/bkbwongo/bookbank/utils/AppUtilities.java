package com.bkbwongo.bookbank.utils;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author bkaaron
 * @created 07/02/2022 - 11:15 AM
 * @project bookbank
 */
@Slf4j
public class AppUtilities {

    private AppUtilities() { }

    public static String getUUID() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }

    public static Long stringToLong(String value){
        return Long.parseLong(value);
    }

    public static String longToString(Long value){
        return Long.toString(value);
    }

    public static String getFileCheckSum(byte[] bytes, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.update(bytes);
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }

    public static String dateFormatter(){
        LocalDateTime myDateObj = LocalDateTime.now();
        System.out.println("Before formatting: " + myDateObj);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return myDateObj.format(myFormatObj);
    }

    public static List<String> sentenceToListOfWords(String sentence){
        String[] words = sentence.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            // You may want to check for a non-word character before blindly
            // performing a replacement
            // It may also be necessary to adjust the character class
            words[i] = words[i].replaceAll("[^\\w]", "");
        }
        return List.of(words);
    }
}
