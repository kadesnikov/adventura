package com.github.kadesnikov.adventura.main;

import com.github.kadesnikov.adventura.logika.Hra;
import com.github.kadesnikov.adventura.uiText.HomeController;
import com.github.kadesnikov.adventura.logika.IHra;
import com.github.kadesnikov.adventura.uiText.TextoveRozhrani;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*******************************************************************************
 * Třída  Start je hlavní třídou projektu,
 * který představuje jednoduchou textovou adventuru určenou k dalším úpravám a rozšiřování
 *
 * @author    Jarmila Pavlíčková
 * @version   ZS 2015/2016
 */
public class Start extends Application
{
    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {

    	if (args.length == 0) {
            launch(args);
        } else {
            if (args[0].equals("-text")) {
                IHra hra = new Hra();
                TextoveRozhrani ui = new TextoveRozhrani(hra);
                ui.hraj();
            } else {
                System.out.println("Neplatný parametr");
            }
        }

    }

    /**
   	 * Metoda, ve které se konstruuje okno, kontroler a hra,
   	 * která se předává kontroleru
   	 */
   	@Override
   	public void start(Stage primaryStage) throws Exception {
   		FXMLLoader loader = new FXMLLoader();
       	loader.setLocation(getClass().getResource("/MainWindow.fxml"));    	
       	Parent root = loader.load();

       	HomeController controller = loader.getController();
       	IHra hra = new Hra();
   		controller.inicializuj(hra);
       	
       	primaryStage.setScene(new Scene(root));
       	primaryStage.show();
       	primaryStage.setTitle("Základní adventura");
   		
   	}
}
