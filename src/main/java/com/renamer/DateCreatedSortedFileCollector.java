package com.renamer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DateCreatedSortedFileCollector extends SortedFileCollector {
    public DateCreatedSortedFileCollector(Path directoryPath){
        super(directoryPath);
    }

    @Override
    void sortDirectoryFiles(ArrayList<File> directoryFiles) {
        {
            Comparator<File> creationTimeComparator = Comparator.comparing(file -> {
                try{
                    return Files.readAttributes(Paths.get(file.toURI()), BasicFileAttributes.class).creationTime();
                }catch(IOException ex){
                    return null;
                }
            });

            Collections.sort(directoryFiles, creationTimeComparator);
        }

    }
}
