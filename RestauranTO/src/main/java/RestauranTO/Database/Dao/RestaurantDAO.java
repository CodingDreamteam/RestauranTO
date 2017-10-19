package RestauranTO.Database.Dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import RestauranTO.Database.CDatabaseConnection;
import RestauranTO.Database.Datamodel.TblRestaurant;



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
    
}
