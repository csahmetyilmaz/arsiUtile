package testPack.model;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Ahmet
 * @since 19-06-2019
 * @version 1.0
 * 
 * BDD : bdd1
 * table : stagiaires
 * l/p : root/ 
 */
public class DbConnecteur {
 static Connection cnxBdd1;
 static Statement statemntBdd1;
 static ResultSet resultReqBdd1; 
    
public static Connection connectionALaBdd(){
		// System.out.println("-----------------> connectionALaBdd");
		//Veri tabanına baglanıyor
        try{
            Class.forName("com.mysql.jdbc.Driver");
        //    System.out.println("INFO ---> Driver Chargé");
            String url="jdbc:mysql://localhost:3306/bdd1";
            String user="root";
            String password="";
           Connection cnx=DriverManager.getConnection(url,user,password);
         //   System.out.println("Connexion à la BDD bien établie ! ");
         //   System.out.println("connectionALaBdd <-----------------");
            return cnx;
        }catch(Exception e){
            e.printStackTrace();
        }        
        
        //System.out.println("connectionALaBdd <-----------------");
        return null;
    }

public static void supprimerParId(int id){ //DELETE ISLEMLERI
		System.out.println("--------------> supprimerParId");
     try{
        String query="DELETE FROM stagiaires WHERE stagiaires_id = "+id; 
        cnxBdd1=connectionALaBdd();
        statemntBdd1=cnxBdd1.createStatement();
        statemntBdd1.executeUpdate(query);
        System.out.println("Champ avec id "+ id + " supprimé");
         
     }catch(SQLException e){
         System.out.println(e.getMessage());
     }
		System.out.println("supprimerParId <--------------");
 }

public static void modifierChampEnBdd(int id,String n_nom,String n_prenom,String n_motdepasse,String n_imgPath ){ //UPDATE
	//	System.out.println("--------------> modifierChampEnBdd");
    try{
        String query="UPDATE stagiaires SET nom='"+n_nom
                +"', prenom='"+n_prenom
                +"', motdepasse='"+n_motdepasse
                +"', imgPath='"+n_imgPath
                +"' WHERE stagiaires_id="+id;
        cnxBdd1=connectionALaBdd();
        statemntBdd1=cnxBdd1.createStatement();
        statemntBdd1.executeUpdate(query);
    //    System.out.println("Champ en BDD bien modifié");
    }catch(SQLException e){
        System.out.println(e.getMessage());
    } 
	
}
public static void modifierImagePath(int id , String n_imgPath){
	try{
        String query="UPDATE stagiaires SET imgPath='"+n_imgPath
                +"' WHERE stagiaires_id="+id;
        cnxBdd1=connectionALaBdd();
        statemntBdd1=cnxBdd1.createStatement();
        statemntBdd1.executeUpdate(query);
    //    System.out.println("Champ en BDD bien modifié");
    }catch(SQLException e){
        System.out.println(e.getMessage());
    } 
}

public static void ajouterEnBdd(int id,String n_nom,String n_prenom,String n_motdepasse ){
	
	//System.out.println("--------------> ajouterEnBdd");//VT Ekleme İşlemleri
	if (id==0) {
		try{
			//Bu tarih kısmını yapınca burayı duzeltecez
			// String query="INSERT INTO stagiaires (nom,prenom,date_entree,motdepasse) VALUES('"+s_x.getNom()+"','"+s_x.getPrenom()+"','"+s_x.getDate_entree()+"','"+s_x.getMdp()+"')";
            String query="INSERT INTO stagiaires (nom,prenom,motdepasse) VALUES('"+n_nom+"','"+n_prenom+"','"+n_motdepasse+"')";
            cnxBdd1=connectionALaBdd();
            statemntBdd1=cnxBdd1.createStatement();
            statemntBdd1.executeUpdate(query);
          // System.out.println("Stagiaire bien ajouté avec Id en Auto incrément");
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }	
	} else {
		try{
            String query="INSERT INTO stagiaires (stagiaires_id,nom,prenom,motdepasse,imgPath) VALUES("+id+",'"+n_nom+"','"+n_prenom+"','"+n_motdepasse+"')";
            cnxBdd1=connectionALaBdd(); 
            statemntBdd1=cnxBdd1.createStatement();
            statemntBdd1.executeUpdate(query);
            // System.out.println("Stagiere bien ajouté avec Id spécifié");
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
	}
    
    
   // System.out.println("ajouterEnBdd <--------------");
	
}

}