/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * This project didnt work because you cannot write to a text file within the jar. (using getResource())
 */
package gmoddownloaderfx;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Justin
 */
public class FXMLDocumentController implements Initializable {
//Writing @FXML before each variable allows it to be private instead of public. See http://stackoverflow.com/questions/13166588/why-must-i-declare-textfield-as-public-but-not-label

    @FXML
    private TextField dirtext;
    @FXML
    private Button dirbutton;
    @FXML
    private Button dlbutton;
    @FXML
    private TextArea dltext;
    @FXML
    private Label dlnotify;

    // READ COMMENT IN FXML FOR dirbutton
//    @FXML
//    private void handleButtonAction(ActionEvent event) {
//        System.out.println("You clicked me!");
//        final JFileChooser fc = new JFileChooser();
//        try {
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//        } catch (Exception ex) {
//            Logger.getLogger(GModDownloaderFX.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        SwingUtilities.updateComponentTreeUI(fc);
//
//        fc.setCurrentDirectory(new java.io.File("."));
//
//        fc.setDialogTitle("Select Garry's Mod maps folder");
//        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        fc.setAcceptAllFileFilterUsed(false);
//
//        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
//            System.out.println("getCurrentDirectory(): "
//                    + fc.getCurrentDirectory());
//            System.out.println("getSelectedFile() : "
//                    + fc.getSelectedFile());
//        } else {
//            System.out.println("No Selection ");
//        }
//
//        dirtext.setText("" + fc.getSelectedFile());
//
//    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        assert dlbutton != null;
        assert dirbutton != null;
        getDir();
        //openDirFile();
        dirbutton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("You clicked me!");

                final JFileChooser fc = new JFileChooser();
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                } catch (Exception ex) {
                    Logger.getLogger(GModDownloaderFX.class.getName()).log(Level.SEVERE, null, ex);
                }
                SwingUtilities.updateComponentTreeUI(fc);

                if (dirtext.getText().isEmpty()) {
                    fc.setCurrentDirectory(new java.io.File("."));
                } else {
                    fc.setCurrentDirectory(new java.io.File(dirtext.getText()));
                }

                fc.setDialogTitle("Select Garry's Mod maps folder");
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fc.setAcceptAllFileFilterUsed(false);

                if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    System.out.println("getCurrentDirectory(): "
                            + fc.getCurrentDirectory());
                    System.out.println("getSelectedFile() : "
                            + fc.getSelectedFile());
                } else {
                    System.out.println("No Selection ");
                }

                dirtext.setText("" + fc.getSelectedFile());
                setDir(dirtext.getText());
            }
        });

        dlbutton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                dlnotify.setText("");
                String maptext = dltext.getText();
                String maps[] = maptext.split("\\r?\\n");
                boolean error;
                boolean owned;
                int dlcounter = 0;

                //Line splitting thanks to http://stackoverflow.com/questions/454908/split-java-string-by-new-line
                for (String s : maps) {
                    error = false;
                    owned = false;
                    URL dl1 = null;
                    URL dl2 = null;
                    File f = null;
                    try {
                        System.out.println("maps length: " + maps.length);

                        String link1 = "http://fastdl.joseph-ttt.com/maps/" + s;
                        String link2 = "http://joseph-ttt.com/maps/mapdl.php?dl=" + s;
                        System.out.println("This map: " + s);
                        //Check if file exists http://stackoverflow.com/questions/1816673/how-do-i-check-if-a-file-exists-in-java
                        f = new File(dirtext.getText() + "\\" + s);
                        if (!new File(dirtext.getText() + "\\" + s).isFile()) {
                            dl1 = new URL(link1);
                            dl2 = new URL(link2);
                            FileUtils.copyURLToFile(dl1, f, 2000, 2000);
                            dlnotify.setText("Downloading...");

                        } else {
                            dlnotify.setText("Map already owned!");
                            owned = true;
                        }
                    } //FileUtils.copyURL
                    //                try {
                    //            String link = "http://fastdl.joseph-ttt.com/maps/";
                    //            link += jTextField2.getText();
                    //            File f = new File(jTextField1.getText() + "\\" + jTextField2.getText());
                    //            System.out.println(jTextField1.getText() + jTextField2.getText());
                    //            URL dl = new URL(link);
                    //            FileUtils.copyURLToFile(dl, f);
                    //        } catch (MalformedURLException ex) {
                    //            Logger.getLogger(MapDownloader.class.getName()).log(Level.SEVERE, null, ex);
                    //        } catch (IOException ex) {
                    //            Logger.getLogger(MapDownloader.class.getName()).log(Level.SEVERE, null, ex);
                    //        }
                    //FileUtils.copyURLToFile(dl2, f);
                    //FileUtils.copyURLToFile(dl2, f, 2000, 2000);
                    //not sure why it says all successfuly dled if i put in 2 good maps and 1 fake map
                    catch (MalformedURLException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        error = true;
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        error = true;
                    } finally {
                        error = false;
                        if (!new File(dirtext.getText() + "\\" + s).isFile()) {
                            try {
                                FileUtils.copyURLToFile(dl2, f, 2000, 2000);
                            } catch (IOException ex) {
                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                                error = true;
                            }
                        }

                        if (!new File(dirtext.getText() + "\\" + s).isFile() || error) {
                            dlnotify.setText("Download failed");
                        } else if (!error && !owned) {
                            dlnotify.setText("Successfully downloaded " + s);
                            dlcounter += 1;
                            if (dlcounter == maps.length) {
                                dlnotify.setText("Successfully downloaded all maps");
                            } else if (dlcounter >= 2) {
                                dlnotify.setText("Successfully downloaded " + dlcounter + " maps");
                            }

                        }
                        if (dlcounter == 1) {
                            dlnotify.setText("Successfully downloaded " + s);//says map already owned if there are 2 or more maps and the first map gets dled but the 2nd one is already owned..
                        }                            // i tihnk i fixed the problem
                    }
                }

            }
        }
        );
        //ALL ABOVE FOR ERROR CATCHING IS EXTREMELY MESSY. Recheck logic if you ever revisit? I don't know why I had to split both exception catches...

    }

    public void getDir() {
        try {
            InputStream is = getClass().getResourceAsStream("directory.txt");
            //URL resourceUrl = GModDownloaderFX.class.getResource("directory.txt");
            //File file = new File(resourceUrl.toURI());
            //InputStream is = new FileInputStream(file);
            /*
             IT WORKS!!!! The problem was using InputStream is = FXMLDocumentController().class.getResourceAsStream("directory.txt");
             or GModDownloaderFX().class.getResourceAsStream("directory.txt");
             Solved thanks to http://stackoverflow.com/questions/8495069/error-error-java-lang-illegalargumentexception-uri-is-not-hierarchical-whil
             */
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            String read = in.readLine();
            if (!read.isEmpty()) {
                dirtext.setText(read);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setDir(String dir) {
        PrintWriter out = null;
        try {
            URL resourceUrl = getClass().getResource("directory.txt"); //Same error.. URI is not hierarchial...?
            File file = new File(resourceUrl.toURI());
            out = new PrintWriter(new FileWriter(file));
            out.write(dir);
            System.out.println("dir is " + dir);
        } catch (URISyntaxException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /*public void openDirFile() {
        Desktop dk = Desktop.getDesktop();
        InputStream is = null;
        OutputStream os = null;
        try {
            is = getClass().getResourceAsStream("directory.txt");
            os = new FileOutputStream(new File("directory.txt"));
            //dk.open(dir);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = is.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }

            System.out.println("Done!");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    // outputStream.flush();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
        dk.open();
    }*/
}
