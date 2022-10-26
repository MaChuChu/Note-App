/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package note.app.pkg2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author ranje
 */
public class NoteApp2 extends Application {
    
    final public static int SCREEN_WIDTH = 400;
    final public static int SCREEN_HEIGHT = 700;
    
    ScrollPane scrollPane;
    VBox layout, notesLayout, editLayout;
    ArrayList<Label> notes;
    ArrayList<Notes> notesS;
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        notes = new ArrayList<>();
        notesS = new ArrayList<>();
        notesLayout = new VBox();
        editLayout = new VBox();
        
        Button btn2 = new Button();
        btn2.setText("O");
        
        Button btn3 = new Button();
        btn3.setText("X");
        
        btn2.setOnAction((ActionEvent event) -> {
            System.out.println("Add Note ... ");
            Label temp = new Label("New Note!");
            notes.add(temp);
            notesLayout.setSpacing(20);
            notesLayout.getChildren().add(temp);
        });
        
        btn3.setOnAction((ActionEvent event) -> {
            if (notes.size() - 1 >= 0) {
                System.out.println("Remove Note ... ");
                int lastIndex = notes.size() - 1;
                notesLayout.getChildren().remove(lastIndex);
                notes.remove(lastIndex);
            }
            
        });

        //Passing FileInputStream object as a parameter 
        Image editImage = new Image(new FileInputStream("src/img/edit.png"));
        Image addImage = new Image(new FileInputStream("src/img/plus.png"));
        Image removeImage = new Image(new FileInputStream("src/img/x.png"));
        
        ImageView editImageView = new ImageView(editImage);
        ImageView addImageView = new ImageView(addImage);
        ImageView removeImageView = new ImageView(removeImage);

        //setting the fit height and width of the image view 
        editImageView.setFitHeight(50);
        editImageView.setFitWidth(50);

        //Setting the preserve ratio of the image view 
        editImageView.setPreserveRatio(true);
        
        editImageView.setOnMousePressed(e -> {
            if (scrollPane.getContent() != editLayout) {
                System.out.println("Edit Note");
                editNotePressed();
            }
        });

        //setting the fit height and width of the image view 
        addImageView.setFitHeight(50);
        addImageView.setFitWidth(50);

        //Setting the preserve ratio of the image view 
        addImageView.setPreserveRatio(true);
        
        addImageView.setOnMousePressed(e -> {
            System.out.println("Add Note ... ");
            createNote();
        });

        //setting the fit height and width of the image view 
        removeImageView.setFitHeight(45);
        removeImageView.setFitWidth(45);

        //Setting the preserve ratio of the image view 
        removeImageView.setPreserveRatio(true);
        
        removeImageView.setOnMousePressed(e -> {
            if (notesS.size() - 1 >= 0) {
                System.out.println("Remove Note ... ");
//                int lastIndex = notes.size() - 1;
//                notesLayout.getChildren().remove(lastIndex);
//                notes.remove(lastIndex);
                removeNote();
            } else {
                System.out.println("List is Empty!");
            }
        });

        //StackPane root = new StackPane();
        scrollPane = new ScrollPane(notesLayout);
        
        layout = new VBox();
        HBox menuBar = new HBox();
        
        menuBar.setPadding(new Insets(20, 20, 20, 20));
        menuBar.setMargin(removeImageView, new Insets(2.5, 0, 0, 0));
        
        menuBar.setSpacing(20);
        menuBar.getChildren().addAll(editImageView, addImageView, removeImageView);
        
        layout.getChildren().addAll(menuBar, scrollPane);
        
        Scene scene = new Scene(layout, SCREEN_WIDTH, SCREEN_HEIGHT);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void createNote() {
        Notes temp = new Notes("First Note", "Hello World.", new Date());
        notesS.add(temp);
        
        editLayout.getChildren().clear();
        scrollPane.setContent(notesLayout);
        
        notesLayout.getChildren().add(temp.getLabel());
    }
    
    public void createNote(String title, String content) {
        Notes temp = new Notes(title, content, new Date());
        notesS.add(temp);
        
        editLayout.getChildren().clear();
        scrollPane.setContent(notesLayout);
        
        notesLayout.getChildren().add(temp.getLabel());
    }
    
    public void removeNote() {
        notesLayout.getChildren().remove(notesS.size() - 1);
        notesS.remove(notesS.size() - 1);
    }
    
    public void editNotePressed() {
        System.out.println("Called Function");
        
        TextField title = new TextField("Title");
        TextArea context = new TextArea("Information");
        context.setPrefSize(SCREEN_WIDTH-40, SCREEN_HEIGHT-500);
        context.setWrapText(true);
        Button editNoteDone = new Button("Finish");
        
        editLayout.getChildren().addAll(title, context, editNoteDone);
        editLayout.setPadding(new Insets(10, 0, 10, 20));
        scrollPane.setContent(editLayout);
        
        editNoteDone.setOnMousePressed(e -> {
            createNote(title.getText(), context.getText());
            editLayout.getChildren().clear();
            scrollPane.setContent(notesLayout);
        });
    }
    
}
