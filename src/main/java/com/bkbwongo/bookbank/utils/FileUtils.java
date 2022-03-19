package com.bkbwongo.bookbank.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author bkaaron
 * @created 19/02/2022 - 6:41 PM
 * @project bookbank
 */
@Slf4j
public class FileUtils {

    private FileUtils() { }

    public static final String folderPath = "/home/bkaaron/Desktop/Live//";

    public static final Path filePath = Paths.get(folderPath);

    public static void storeFile(byte[] bytes, String fileName) throws IOException {
        Files.write(Paths.get(FileUtils.folderPath + fileName), bytes);
        log.info(String.format("file %s stored", fileName));
    }

    public String getFolderPath() {
        return folderPath;
    }
}
