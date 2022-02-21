package com.bkbwongo.bookbank.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author bkaaron
 * @created 12/02/2022 - 5:49 PM
 * @project bookbank
 */
public class GFG {

    public static void main(String[] args) {

        String s = "i am going to school?m . alex - even";
        List<String> words = sentenceToListOfWords(s);

        System.out.println(words);
        //LocalDateTime ld = LocalDateTime.now();
        //System.out.println(ld.toString());

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
