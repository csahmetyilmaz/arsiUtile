package testPack.vue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import javafx.scene.image.Image;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import testPack.MainApp;
import testPack.model.Stagiaire;
import testPack.model.DbConnecteur;

public class GeneralListVueController {

	// GORUNEN ELEMANLAR--------------------------------------
	@FXML
	private TableView<Stagiaire> stagiereTable;

	@FXML
	private TableColumn<Stagiaire, Integer> idColumn;
	@FXML
	private TableColumn<Stagiaire, String> nomColumn;
	@FXML
	private TableColumn<Stagiaire, String> prenomColumn;
	@FXML
	private TableColumn<Stagiaire, String> mdpColumn;

	@FXML
	private ImageView userImage;

	@FXML
	private Button addImgButton;
	
	@FXML
	private TextField idText;
	@FXML
	private TextField nomText;
	@FXML
	private TextField prenomText;
	@FXML
	private TextField mdpText;
	@FXML
    private Button btn_delete;
	@FXML
    private Button btn_update;

    @FXML
    private Button btn_add;

	ObservableList<Stagiaire> oblist = FXCollections.observableArrayList();
	private Stagiaire selectedPerson;
	File selectedFile;
	public static String srcFolder="D:/AFIP/JAVA/worksjava-jfx-luna/TestBdd/src/testPack/vue/";
	String localUrl;
	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public GeneralListVueController() {

	}

	// METOTLAR--------------------------------------------------------------------------
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		
		loadDataFromDatabase();

		setCellTable();

		stagiereTable.setItems(oblist);
		showPersonDetails(null);

		// Listen for selection changes and show the person details when
		// changed.
		stagiereTable
				.getSelectionModel()
				.selectedItemProperty()
				.addListener(
						(observable, oldValue, newValue) -> showPersonDetails(newValue));
	}

	private void showPersonDetails(Stagiaire object) {
		
		int selectedIndex = stagiereTable.getSelectionModel()
				.getSelectedIndex();
		if (selectedIndex >= 0) {
			selectedPerson = object;
			
		}

		if (selectedPerson != null) {
			
			idText.setText(Integer.toString(selectedPerson.getId()));
			nomText.setText(selectedPerson.getNom());
			prenomText.setText(selectedPerson.getPrenom());
			mdpText.setText(selectedPerson.getMdp());
			
			
			File file = new File(
					srcFolder+"USER_IMG/"
							+ selectedPerson.getImgPath());
			localUrl = srcFolder+"USER_IMG/imgDefault.png";
			try {
				localUrl = file.toURI().toURL().toString();
			} catch (MalformedURLException e) {
				
				e.printStackTrace();
			}

			Image localImage = new Image(localUrl, false);
			userImage.setImage(localImage);

			

		} else {
			// Person is null, remove all the text.
			idText.setText("");
			nomText.setText("");
			prenomText.setText("");
			mdpText.setText("");
			
		}
	}

	private void loadDataFromDatabase() {
		
		try {
			Connection conx1 = DbConnecteur.connectionALaBdd();
			ResultSet rs = conx1.createStatement().executeQuery(
					"SELECT * FROM stagiaires");
			oblist.clear();
			while (rs.next()) {
				oblist.add(new Stagiaire(rs.getInt("stagiaires_id"), rs
						.getString("nom"), rs.getString("prenom"), rs
						.getString("motdepasse"), rs.getString("imgPath")));
			}
			

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	private void setCellTable() {
		
		idColumn.setCellValueFactory( 
										
		cellData -> cellData.getValue().idProperty().asObject());// Bu Lambda yazilis sekli asagidaki normali
		// idColumn.setCellValueFactory(new
		// PropertyValueFactory<Stagiere,Integer>("id")); // sağdaki objenin degiskenlerini soldaki araca ekliyor
		
		nomColumn.setCellValueFactory(cellData -> cellData.getValue()
				.nomProperty());
		prenomColumn.setCellValueFactory(cellData -> cellData.getValue()
				.prenomProperty());
		mdpColumn.setCellValueFactory(cellData -> cellData.getValue()
				.mdpProperty());

	}

	@FXML
	public void handleAddImgButton(ActionEvent e) throws IOException {

		Stage stage = new Stage();

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose an image ..");
		fileChooser.getExtensionFilters().addAll(
				
				new FileChooser.ExtensionFilter("JPEG", "*.jpg"),
				new FileChooser.ExtensionFilter("PNG", "*.png"));

		// File yukarda tanimlanmısti
		selectedFile = fileChooser.showOpenDialog(stage);

		Image OriginalPhoto = new Image(selectedFile.toURI().toString());
		userImage.setImage(OriginalPhoto); 
			
		//Image img2 = new Image(selectedFile.toURI().toString(), 600, 600,false, false);

		int selectedIndex = stagiereTable.getSelectionModel()
				.getSelectedIndex();
		int id_edit = idColumn.getCellData(selectedIndex);
		
		saveToFile(OriginalPhoto, "S_" + id_edit);
		DbConnecteur.modifierImagePath(id_edit, "S_"+id_edit+".png");
		
	}

	public void saveToFile(Image image, String name) throws IOException {
		File fileOutput = new File(
				srcFolder+"USER_IMG/"//"D:/AFIP/JAVA/worksjava-jfx-luna/TestBdd/src/testPack/vue/USER_IMG/" //srcFolder+"USER_IMG/"
						+ name + ".png");
		BufferedImage BI = SwingFXUtils.fromFXImage(image, null);

		ImageIO.write(BI, "png", fileOutput);
	}

	@FXML
	private void handleDeletePerson() {

		int selectedIndex = stagiereTable.getSelectionModel()
				.getSelectedIndex();

		if (selectedIndex >= 0) {
			int id_del = idColumn.getCellData(selectedIndex);
			stagiereTable.getItems().remove(selectedIndex);
			
			deleteImageFile(id_del); //supprime l'image file
			DbConnecteur.supprimerParId(id_del);	//Supprime la selection en bdd
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);

			// alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected to delete");
			alert.setContentText("Please select a person in the table.");
			alert.showAndWait();
		}

	}

	private void deleteImageFile(int id_del) {
		String fileUrl=srcFolder+"USER_IMG/S_"+id_del+".png";
		File dFile=new File(fileUrl);
		boolean result;
		try {
			// delete the file specified
			result = dFile.delete();
			// test if successfully deleted the file
			if (result) {
				System.out.println("Successfully deleted: " + dFile.getCanonicalPath());
			} else {
				System.out.println("Failed deleting " + dFile.getCanonicalPath());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleAjoutPerson() {
		int id_edit = 0;
		String nom_ajout = nomText.getText();
		String prenom_ajout = prenomText.getText();
		String mdp_ajout = mdpText.getText();
		DbConnecteur.ajouterEnBdd(id_edit, nom_ajout, prenom_ajout, mdp_ajout);
		loadDataFromDatabase();
	}

	@FXML
	private void handleUpdatePerson() throws IOException {
		this.selectedPerson = stagiereTable.getSelectionModel()
				.getSelectedItem();

		int selectedIndex = stagiereTable.getSelectionModel()
				.getSelectedIndex();
		int id_edit = idColumn.getCellData(selectedIndex);
		String nom_edit = nomText.getText();
		String prenom_edit = prenomText.getText();
		String mdp_edit = mdpText.getText();
		

		DbConnecteur.modifierChampEnBdd(id_edit, nom_edit, prenom_edit,
				mdp_edit, "S_" + id_edit + ".png");
		loadDataFromDatabase();
	}

	/**
	 * MAIN APP  Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		// Add observable list data to the table
		stagiereTable.setItems(mainApp.stagiaireData());
	}
}