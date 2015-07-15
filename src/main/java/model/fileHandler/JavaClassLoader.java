package model.fileHandler;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaClassLoader {
        
    private final static String CLASS_FILE_EXTENSION = ".class";
    
    public List<File> getClassFiles(String rootSourceDirectory) throws MalformedURLException, ClassNotFoundException {
        List<File> filePaths = new ArrayList<>();
        this.findClasses(rootSourceDirectory, filePaths);
        
        return filePaths;
    }
    
    private void findClasses(String rootSourceDirectory, List<File> filePaths) throws MalformedURLException, ClassNotFoundException {
        File root = new File(rootSourceDirectory);
        List<File> files = new ArrayList<>(Arrays.asList(root.listFiles()));
        for (File file : files) {
            if(file.isDirectory())
                findClasses(file.getAbsolutePath(), filePaths);
            else if(file.getAbsolutePath().endsWith(CLASS_FILE_EXTENSION))
                filePaths.add(new File(file.getAbsolutePath()));
        }        
    }    
}
