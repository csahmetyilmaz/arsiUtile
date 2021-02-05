package testPack;

import java.io.IOException;

import testPack.model.Stagiaire;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
     /**
     * The data as an observable list of Persons.
     */
    private ObservableList<Stagiaire> oblist = FXCollections.observableArrayList();

    /**
     * Yapılandırıcı
     */
    public MainApp() {

    }
  
    /**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    public ObservableList<Stagiaire> stagiaireData() {
    	return oblist;
    }
    
    @Override
    public void start(Stage primaryStage) { //ilk once bu calisiyor args olarak HERHALDE
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Stagiaire-App");
        
        initRootLayout();

        showGeneralListVue();
        
    }
       
    /**
     * Kök Yerleşimi Başlatır. INFO 03
     */
    public void initRootLayout() {
    	System.out.println("INFO 03");
    	try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vue/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Kök yerleşim içerisinde kişi önizlemeyi gösterir. INFO 04
     */
    public void showGeneralListVue() {
    	System.out.println("INFO 04");
    	try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("vue/GeneralListVue.fxml"));
            AnchorPane GeneralListVue = (AnchorPane)loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(GeneralListVue);
            
          // GeneralListVueController controller = loader.getController();// BU CALISINCA SAPITIYOR
            //controller.setMainApp(this);
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  
   
        /**
     * Main stage i gönderir. 
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
    	System.out.println("INFO 01");
    	launch(args);
    }
}
