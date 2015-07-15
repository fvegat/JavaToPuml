package util;

public class Util {

	public static String cleanClassName(String className) {
        String[] splittedName = className.replace(".","/").split("/");
        return splittedName[splittedName.length -1].replace(";", "");                
    }
	
}
