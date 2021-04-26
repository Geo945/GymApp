package proiect.fis.gym.aplication.controllers;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import proiect.fis.gym.aplication.exceptions.FieldsAreNotEmptyException;
import proiect.fis.gym.aplication.model.GymManager;


public class CommonFunctionality {
    
    //checks if the text fields from a pane are empty
    public static boolean checkTextFieldsInAPaneAreNotEmpty(Pane pane) {
        for(Node node: pane.getChildren()){
            if(node instanceof TextField){
                if(((TextField)node).getText().isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkTextAreaInAPaneAreNotEmpty(Pane pane) {
        for(Node node: pane.getChildren()){
            if(node instanceof TextArea){
                if(((TextArea)node).getText().isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }

}
