/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package note.app.pkg2;

import java.util.*;
import javafx.geometry.Insets;
import javafx.scene.control.Label;

/**
 *
 * @author ranje
 */
public class Notes {
    String title;
    String context;
    Date date;
    Label label;

    public Notes(String title, String context, Date date) {
        this.title = title;
        this.context = context;
        this.date = date;
        label = new Label(title + "\n" + context + "\n" + date.toString());
        label.setStyle("-fx-font-size: 14");
        label.setPadding(new Insets(0,0,20,20));
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
