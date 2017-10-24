package RestauranTO.Database.Dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import RestauranTO.Database.CDatabaseConnection;
import RestauranTO.Database.Datamodel.TblRestaurant;
import RestauranTO.Database.Datamodel.TblSugestions;
import RestauranTO.Database.Datamodel.TblZone;



public class RestaurantDAO {
    
 public static List<TblRestaurant> SearchRestaurants ( final CDatabaseConnection dbConnection, String strName ) {
        
        List<TblRestaurant> result = new ArrayList<TblRestaurant>();
        
        try {
            
            if ( dbConnection != null && dbConnection.getDatabaseConnection() != null ) {
                
                Statement statement = dbConnection.getDatabaseConnection().createStatement();
                
                String strSQL = "SELECT * FROM tblrestaurant WHERE Name like '%" + strName +"%'";
                
                ResultSet resultSet = statement.executeQuery( strSQL );
                
                while ( resultSet.next() ) {
                    
                   TblRestaurant tblRestaurant = new TblRestaurant();  
                   tblRestaurant.setStrID( resultSet.getString( "ID" ) );
                   tblRestaurant.setStrName( resultSet.getString("Name"));
                   tblRestaurant.setStrDescription( resultSet.getString("Description"));
                   tblRestaurant.setStrDirection( resultSet.getString("Direction") );
                   tblRestaurant.setStrEmail(resultSet.getString( "Email" ) );
                   tblRestaurant.setStrPicture( resultSet.getString( "Picture" ) );
                   tblRestaurant.setIntCapacity( resultSet.getInt( "Capacity" ) );
                   tblRestaurant.setInActualCapacity( resultSet.getInt( "ActualCapacity" ) );
                   
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
 
 
   public static List<TblRestaurant> SearchAllRestaurants ( final CDatabaseConnection dbConnection ) {
     
     List<TblRestaurant> result = new ArrayList<TblRestaurant>();
     
     try {
         
         if ( dbConnection != null && dbConnection.getDatabaseConnection() != null ) {
             
             Statement statement = dbConnection.getDatabaseConnection().createStatement();
             
             String strSQL = "SELECT * FROM tblrestaurant";
             
             ResultSet resultSet = statement.executeQuery( strSQL );
             
             while ( resultSet.next() ) {
                 
                TblRestaurant tblRestaurant = new TblRestaurant();  
                tblRestaurant.setStrID( resultSet.getString( "ID" ) );
                tblRestaurant.setStrName( resultSet.getString("Name"));
                tblRestaurant.setStrDescription( resultSet.getString("Description"));
                tblRestaurant.setStrDirection( resultSet.getString("Direction") );
                tblRestaurant.setStrEmail(resultSet.getString( "Email" ) );
                tblRestaurant.setStrPicture( resultSet.getString( "Picture" ) );
                tblRestaurant.setIntCapacity( resultSet.getInt( "Capacity" ) );
                tblRestaurant.setInActualCapacity( resultSet.getInt( "ActualCapacity" ) );
                
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
 
  public static List<TblRestaurant> SearchRestaurantsZones ( final CDatabaseConnection dbConnection, String strZone ) {
     
     List<TblRestaurant> result = new ArrayList<TblRestaurant>();
     
     try {
         
         if ( dbConnection != null && dbConnection.getDatabaseConnection() != null ) {
             
             Statement statement = dbConnection.getDatabaseConnection().createStatement();
             
             String strSQL = "SELECT * FROM tblrestaurant JOIN tbllocation ON tblrestaurant.Location_ID = tbllocation.ID WHERE tbllocation.Name LIKE '%" + strZone + "%';";
             
             ResultSet resultSet = statement.executeQuery( strSQL );
             
             while ( resultSet.next() ) {
                 
                TblRestaurant tblRestaurant = new TblRestaurant();  
                tblRestaurant.setStrID( resultSet.getString( "tblrestaurant.ID" ) );
                tblRestaurant.setStrName( resultSet.getString("tblrestaurant.Name"));
                tblRestaurant.setStrDescription( resultSet.getString("tblrestaurant.Description"));
                tblRestaurant.setStrDirection( resultSet.getString("tblrestaurant.Direction") );
                tblRestaurant.setStrEmail(resultSet.getString( "tblrestaurant.Email" ) );
                tblRestaurant.setStrPicture( resultSet.getString( "tblrestaurant.Picture" ) );
                tblRestaurant.setIntCapacity( resultSet.getInt( "tblrestaurant.Capacity" ) );
                tblRestaurant.setInActualCapacity( resultSet.getInt( "tblrestaurant.ActualCapacity" ) );
                tblRestaurant.setZone( resultSet.getString( "tbllocation.Name" ) );
                
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
      
  public static List<TblZone> SearchAllZones ( final CDatabaseConnection dbConnection ) {
      
      List<TblZone> result = new ArrayList<TblZone>();
      
      try {
          
          if ( dbConnection != null && dbConnection.getDatabaseConnection() != null ) {
              
              Statement statement = dbConnection.getDatabaseConnection().createStatement();
              
              String strSQL = "SELECT * FROM tbllocation";
              
              ResultSet resultSet = statement.executeQuery( strSQL );
              
              while ( resultSet.next() ) {
                  
                 TblZone tblZone = new TblZone();  
                 tblZone.setStrID( resultSet.getString( "ID" ) );
                 tblZone.setStrName( resultSet.getString("Name"));      
                 
                 result.add( tblZone );
                 
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
  
    public static boolean AddSugestion( final CDatabaseConnection dbConnection, TblSugestions tblSugestions) {
     
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
