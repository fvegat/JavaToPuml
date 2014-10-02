/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.fileHandler;

/**
 *
 * @author
 * fvega
 */
public class ParserFactory {
    private ParserFactory parserFactory = new ParserFactory();
    
    private ParserFactory() {}
    
    public static JavaParser getInstance(String parser) {
        return new JavaClassParser();
    }
}
