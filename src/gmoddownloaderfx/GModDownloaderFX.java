/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gmoddownloaderfx;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * http://stackoverflow.com/questions/13683335/including-an-icon-into-a-self-contained-javafx-application-exe
 * http://stackoverflow.com/questions/13341638/how-to-change-javafx-native-bundle-exe-icon
 * http://stackoverflow.com/questions/10275841/how-to-change-the-icon-on-the-title-bar-of-a-stage-in-java-fx-2-0-of-my-
 * http://stackoverflow.com/questions/10121991/javafx-application-icon << THIS ONE 2ND POST WORKED.
 * https://blogs.oracle.com/jmxetc/entry/connecting_scenebuilder_edited_fxml_to
 * @author Justin Mah @ jmmah98@gmail.com
 */
public class GModDownloaderFX extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                
        Scene scene = new Scene(root);
        stage.setTitle("Garry's Mod Map Downloader");
        stage.getIcons().add(new Image(GModDownloaderFX.class.getResourceAsStream("gmodicon.png")));
        stage.setScene(scene);
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
    
}
