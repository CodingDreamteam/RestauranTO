package RestauranTO.Database.Dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import RestauranTO.Database.CDatabaseConnection;
import RestauranTO.Database.Datamodel.TblRestaurant;
import RestauranTO.Database.Datamodel.TblSugestions;



public class RestaurantDAO {
    
 public static List<TblRestaurant> SearchRestaurants ( final CDatabaseConnection dbConnection, String strName ) {
        
        List<TblRestaurant> result = new ArrayList<TblRestaurant>();
        
        try {
            
            if ( dbConnection != null && dbConnection.getDatabaseConnection() != null ) {
                
                Statement statement = dbConnection.getDatabaseConnection().createStatement();
                
                String strSQL = "SELECT * FROM tblrestaurant WHERE Name like %strName%";
                
                ResultSet resultSet = statement.executeQuery( strSQL );
                
                while ( resultSet.next() ) {
                    
                   TblRestaurant tblRestaurant = new TblRestaurant();                
                   tblRestaurant.setStrName( resultSet.getString("strName"));
                   tblRestaurant.setStrDescription( resultSet.getString("strDescription"));
                   tblRestaurant.setStrDirection( resultSet.getString("strDirection") );
                   tblRestaurant.setStrEmail(resultSet.getString( "strEmail" ) );
                   tblRestaurant.setStrPicture( resultSet.getString( "strPicture" ) );
                 
                   result.add( tblRestaurant );
                   
                }
                
                resultSet.close();
                
                statement.close();
                
            }
            
        }
        catch (Exception ex) {
            
          ex.printStackTrace();  
                        
        }
        
        return result;
    
    }
 
    public static boolean AddRating( final CDatabaseConnection dbConnection, TblSugestions tblSugestions) {
     
     boolean result = false;
     
     try {
         
         if ( dbConnection != null && dbConnection.getDatabaseConnection() != null ) {
             
             Statement statement = dbConnection.getDatabaseConnection().createStatement();
             
             String ID = UUID.randomUUID().toString();
             
             final String strSQL = "INSERT INTO tblsugestions (ID, Name, Direction, Image, Email) VALUES ('" + ID + "', '" + tblSugestions.getStrName() + "', '" + tblSugestions.getStrDirection() + "', '" + tblSugestions.getStrImage() + "', '" + tblSugestions.getStrEmail() + "')";
             
             statement.executeUpdate( strSQL );
             
             dbConnection.getDatabaseConnection().commit();
             
             if ( tblSugestions.getStrPhoneNumber() != null ) {
                 
                final String strSQL2 = "INSERT INTO tblsugestions_phonenumber Values ('" + ID + "', '" + tblSugestions.getStrPhoneNumber() + "')";
                
                statement.executeUpdate( strSQL2 );
                
                dbConnection.getDatabaseConnection().commit();
                
             }
             
             
             statement.close();
             
             result = true;
         }
         
     }
     
     catch ( Exception ex ) {
         
         ex.getStackTrace();
         
     }
     return result;
 }  
    
}
