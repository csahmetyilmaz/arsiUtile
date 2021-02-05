package testPack.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class pour un Stagiere.
 *
 * @author Ahmet YILMAZ
 */

public class Stagiaire {
		
	private LocalDate date_entree;
	
	//Property ozelligini kullanabilmek icin type leri stringproperty olarak seceriz
	private final IntegerProperty id;
    private final StringProperty nom;
    private final StringProperty prenom;
    private final StringProperty mdp;
    private final StringProperty imgPath;
    //private final ObjectProperty<LocalDate> date_entree; //simdilik kafa karistirmasin diye yapmadim
    
	public Stagiaire(){
		this(0, null,null,null,null);
		System.out.println("Eksik parametre var Bu objest db kaydedilemez");
	}
	public Stagiaire(int id_x, String nom_x, String prenom_x, String mdp,String imgPath) {
		
		//this(int id,null,null,0);
		//this.date_entree=new SimpleObjectProperty(LocalDateTime.of());eksik
		this.id=new SimpleIntegerProperty(id_x);
		this.nom = new SimpleStringProperty(nom_x);;
		this.prenom =new SimpleStringProperty(prenom_x);
		this.mdp = new SimpleStringProperty(mdp);
		
		this.imgPath=new SimpleStringProperty(imgPath);
		//System.out.println("INFO--> Nouveau Stagiere crée!"); 
	}

	public String getImgPath() {
		return imgPath.get();
	}
	
	public IntegerProperty idProperty() {
	//	System.out.println("INFO--> idProperty worked!");
		return id;
	}
	public int getId() {
	//	System.out.println("INFO--> getId worked!");

		return id.get();
	}

	public void setImgPathProperty(String imgPath){
	//	System.out.println("INFO--> setImgPathProperty worked!");
		this.imgPath.set(imgPath);
	}
	public void setId(int id) {
		
		this.id.set(id);
	}

	public StringProperty nomProperty() {
		return nom;
	}

	public String getNom() {
		return nom.get();
	}
	public void setNom(String nom) {
		this.nom.set(nom);
	}

	public String getPrenom() {
		return prenom.get();
	}

	public StringProperty prenomProperty() {
		return prenom;
	}
	public void setPrenom(String prenom_x) {
		this.prenom.set(prenom_x);
	}

	public LocalDate getDate_entree() {
		//System.out.println("INFO--> getDate_entree worked!");
		return date_entree;
	}

	public void setDate_entree(LocalDate date_entree) {
		this.date_entree = date_entree;
	}

	public StringProperty mdpProperty() {
		return mdp;
	}
	public String getMdp() {
		return mdp.get();
	}
	
	public void setMdp(String mdp) {
		this.mdp.set(mdp);
	}

	// Fonctions Special
	public void affiche() {

		System.out
				.println("--------------Details Stagiere----------------------");
		System.out.println(this.nom);
		System.out.println(this.prenom);
		System.out.println(this.date_entree);
		System.out
				.println("----------------------------------------------------");
	}

}
