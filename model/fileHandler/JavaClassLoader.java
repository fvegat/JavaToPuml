package model.fileHandler;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaClassLoader {
    
    private List<Class> classFilesToDraw;
    private String rootSourceDirectory;
    private final static String CLASS_FILE_EXTENSION = ".class";
    
    public JavaClassLoader(String rootSourceDirectory) {
        classFilesToDraw = new ArrayList<>();
        this.rootSourceDirectory = rootSourceDirectory;
    }
    
    public List<Class> getClassFiles() throws MalformedURLException, ClassNotFoundException {
        List<File> filePaths = new ArrayList<>();
        this.findClasses(this.rootSourceDirectory, filePaths);
        this.fileToClass(filePaths);
        return classFilesToDraw;
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
    
    private void fileToClass(List<File> filePaths) throws MalformedURLException, ClassNotFoundException {        
        for (File file : filePaths) {                                                                      
            URL loadPath = file.getParentFile().toURI().toURL();
            URL[] classUrl = new URL[]{loadPath};
            ClassLoader cl = new URLClassLoader(classUrl);
            
            Class loadedClass = cl.loadClass(prepareClassName(file));
            classFilesToDraw.add(loadedClass);
        }        
    }
    
    private String prepareClassName(File fileToLoad) {
        String preparedClassName = fileToLoad.toString();
        preparedClassName = preparedClassName.replace(CLASS_FILE_EXTENSION, "");
        preparedClassName = preparedClassName.replace(rootSourceDirectory, "").replace(".", "");  
        preparedClassName = preparedClassName.replace("/", ".");
        
        return preparedClassName;
    }
}
