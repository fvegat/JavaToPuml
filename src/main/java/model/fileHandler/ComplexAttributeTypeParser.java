/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.fileHandler;

import constants.ComplexAttributeNameTypes;

/**
 *
 * @author fvegat
 */
public class ComplexAttributeTypeParser {
    public static String getComplexAttributeType(String signature) {
        if(signature.contains("List<")){
            return ComplexAttributeNameTypes.LIST;
        }
        else
            return ComplexAttributeNameTypes.MAP;
    }
}
