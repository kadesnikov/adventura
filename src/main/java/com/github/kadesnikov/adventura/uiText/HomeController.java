package com.github.kadesnikov.adventura.uiText;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.github.kadesnikov.adventura.logika.IHra;
import com.github.kadesnikov.adventura.logika.Prostor;
import com.github.kadesnikov.adventura.logika.Vec;
import com.github.kadesnikov.adventura.logika.Hra;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury
 * 
 * @author Filip Vencovsky
 *
 */
@SuppressWarnings("restriction")
public class HomeController extends GridPane implements Observer {
	
	@FXML private TextField textVstup;
	@FXML private TextArea textVypis;
	@FXML private Button odesli;
	@FXML private ListView<Prostor> seznamMistnosti;
	@FXML private ListView<Vec> seznamVeci;
	@FXML private ImageView uzivatel;
	@FXML private ListView<Vec> seznamVeci1;
	@FXML private MenuItem NovaHra;
	@FXML private MenuItem prirucka;
	private IHra hra;
	
	private URL napoveda;
	
	@FXML private ListView<ImageView> veci;
	@FXML private ListView<ImageView> veci1;
	private ObservableList<ImageView> observableList;
	private ObservableList<ImageView> observableList1;

	/**
	 * Metoda čte příkaz ze vstupního textového pole
	 * a zpracuje ho...
	 */
	public void odesliPrikaz() {
		
		String vypis = hra.zpracujPrikaz(textVstup.getText());
		textVypis.appendText("\n--------\n"+textVstup.getText()+"\n--------\n");
		textVypis.appendText(vypis);
		textVstup.setText("");
		
		if(hra.konecHry()) {
			textVypis.appendText("\n\n Konec hry \n");
			textVstup.setDisable(true);
			odesli.setDisable(true);
		}
		
	}
	
	/**
	 * metoda zahaji novu hru
	 */
	
	@FXML public void setNovaHra() {
		seznamMistnosti.getItems().clear();
		veci.getItems().clear();
		veci1.getItems().clear();
		inicializuj(new Hra());
		textVypis.setText(hra.vratUvitani());
		textVstup.setDisable(false);
                hra.getHerniPlan().addObserver(this);
                hra.getHerniPlan().notifyObservers();
	}
	
	/**
	 * metoda ukončí hru
	 */
	
	@FXML public void setKonecHry() {
		Platform.exit();
	}

	/**
	 * metoda zobrazi prirucku do webview
	 */
	@FXML public void displayHelp(ActionEvent event){
		napoveda = this.getClass().getResource("../img/prirucka.htm");
		WebView webView = new WebView();
		WebEngine engine = webView.getEngine();
		engine.load(napoveda.toString());
		Scene scene = new Scene(webView);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		stage.setWidth (1200);
		stage.setHeight(720);
		}
	
	/**
	 * Metoda bude soužit pro předání objektu se spuštěnou hrou
	 * kontroleru a zobrazí stav hry v grafice.
	 * @param objekt spuštěné hry
	 */
	public void inicializuj(IHra hra) {
		this.hra = hra;
		textVypis.setText(hra.vratUvitani());
		List<ImageView> list1 = new ArrayList<ImageView>();
		observableList = FXCollections.observableList(list1);
		List<ImageView> list2 = new ArrayList<ImageView>();
		observableList1 = FXCollections.observableList(list2);
		seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		upravInventar();
		upravVeciMistnost();
		uzivatel.setX(hra.getHerniPlan().getAktualniProstor().getX());
		uzivatel.setY(hra.getHerniPlan().getAktualniProstor().getY());
		hra.getHerniPlan().addObserver(this);
		hra.getHerniPlan().getKapsa().addObserver(this);
		hra.getHerniPlan().notifyObservers();

	}
	
	/**
	 * Metoda pro zpracování kliku na listu místností
	 * 
	 * @param	arg0 akce kliknutí na prvek listu
	 */
	@FXML public void klikMistnosti(MouseEvent arg0) {
		vypis(hra.zpracujPrikaz("jdi " + seznamMistnosti.getSelectionModel().getSelectedItem()));
		upravInventar();
		upravVeciMistnost();
	}

	/**
	 * Metoda pro zpracování kliku na veci v místností
	 * 
	 * @param	arg0 akce kliknutí na prvek listu
	 */
	@FXML public void klikVeci2(MouseEvent arg0) {
		vypis(hra.zpracujPrikaz("seber " + veci1.getSelectionModel().getSelectedItem().getId()));
		upravInventar();
		upravVeciMistnost();
	}
	/**
	 * Metoda pro zpracování kliku na veci v kapse
	 * 
	 * @param	arg0 akce kliknutí na prvek listu
	 */
	
	@FXML public void klikVeciPoloz(MouseEvent arg0) {
		vypis(hra.zpracujPrikaz("poloz " + veci.getSelectionModel().getSelectedItem().getId()));
		upravInventar();
		upravVeciMistnost();
	}
	
	
	/**
	 * update akce
	 */
	@Override
	public void update(Observable o, Object arg) {
		seznamMistnosti.getItems().clear();
		seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		upravInventar();
		upravVeciMistnost();
		uzivatel.setX(hra.getHerniPlan().getAktualniProstor().getX());
		uzivatel.setY(hra.getHerniPlan().getAktualniProstor().getY());
		hra.getHerniPlan().getKapsa().addObserver(this);
		hra.getHerniPlan().addObserver(this);
		
		
	}
	
	/**
	 * Metoda pro zpracování změn v Kapse
	 * 
	 * 
	 */
	public void upravInventar() {
		observableList.removeAll(observableList);		
		for (String nazev : hra.getHerniPlan().getKapsa().getPredmety().keySet()) {
        	String URI = hra.getHerniPlan().getKapsa().getVec(nazev).getObrazek();
        	Image pic = new Image(getClass().getResourceAsStream(URI));
        	ImageView image = new ImageView(pic);
        	image.setId(nazev);
        	observableList.add(image);	
    
		}
		veci.setItems(observableList);
	}
	
	/**
	 * Metoda pro zpracování změn vecí v místností
	 * 
	 * 
	 */
	public void upravVeciMistnost() {
		observableList1.removeAll(observableList1);		
		for (String nazev : hra.getHerniPlan().getAktualniProstor().getVeciMap().keySet()) {
        	String URI = hra.getHerniPlan().getAktualniProstor().getVec(nazev).getObrazek();
        	Image pic = new Image(getClass().getResourceAsStream(URI));
        	ImageView image = new ImageView(pic);
            image.setId(nazev);
        	observableList1.add(image);	
    
		}
		veci1.setItems(observableList1);
	}

	/**
	 * Metoda pro vypis na obrazovku.
	 * Používá se v případě, kdy je potřeba vypsat akci prováděnou grafickými prvky.
	 * 
	 * @param	text text, který se vypisuje na obrazovku
	 */
	public void vypis(String text){
		String odpoved = "\n" + "----------------------------------------------------" + "\n";
		odpoved = odpoved + text;
		textVypis.appendText(odpoved);
		textVstup.setText("");
	}

}
