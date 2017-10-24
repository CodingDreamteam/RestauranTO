package RestauranTO.Database.Dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import RestauranTO.Database.CDatabaseConnection;
import RestauranTO.Database.Datamodel.TblRating;
import RestauranTO.Database.Datamodel.TblRestaurant;
import RestauranTO.Database.Datamodel.TblUser;
import RestauranTO.crypt.BCrypt;

public class UserDAO {
    
    public static TblUser checkData( final CDatabaseConnection dbConnection, final String strName, final String strPassword ) {
        
        TblUser result = null;
        
        try {
            if ( dbConnection != null && dbConnection.getDatabaseConnection() != null ) {
                
                Statement statement = dbConnection.getDatabaseConnection().createStatement();
                
                ResultSet resultSet = statement.executeQuery( "SELECT * FROM tblUser WHERE Name = '" + strName + "' AND DisabledAtDate <=> null" );
                
                if ( resultSet.next() ) {
                    
                    String strDBPassword = resultSet.getString( "password" );
                    
                    String strDBPasswordKey = strDBPassword;
                    
                    strDBPasswordKey = strDBPasswordKey.substring( 0, 29 );
                    
                    strDBPasswordKey = strDBPasswordKey.replace( "$2y$10$", "$2a$10$" );
                    
                    String strPasswordHashed = BCrypt.hashpw( strPassword, strDBPasswordKey );
                    
                    strPasswordHashed = strPasswordHashed.replace( "$2a$10$", "$2y$10$" );
                    
                    if ( strPasswordHashed.equals( strDBPassword ) ) {
                        
                        result = new TblUser();
                        
                        result.setStrID( resultSet.getString( "ID" ) );
                        result.setStrName( resultSet.getString( "Name" ) );
                        result.setStrEmail( resultSet.getString( "Email" ) );
                        result.setStrRole( resultSet.getString( "Role" ) );
                        result.setStrPicture( resultSet.getString( "Picture" ) );
                        result.setStrPassword( resultSet.getString( "Password" ) );
                        result.setCreatedAtDate( resultSet.getDate( "CreatedAtDate" ).toLocalDate() );
                        result.setStrRole( resultSet.getString( "Role" ) );
                        result.setDisabledAtDate( resultSet.getDate( "DisabledAtDate" ) != null ? resultSet.getDate( "DisabledAtDate" ).toLocalDate() : null );
                     
                    }
                    
                }
                
                
                resultSet.close();
                
                statement.close();
            }
        }
        catch ( Exception ex ) {
            
            ex.printStackTrace();
                
            }
            
        
        return result;
        
    }
    
    public static boolean AddUser( final CDatabaseConnection dbConnection, TblUser tblUser) {
        
        boolean result = false;
        
        try {
            if ( dbConnection != null && dbConnection.getDatabaseConnection() != null ) {
                
                Statement statement = dbConnection.getDatabaseConnection().createStatement();
                
                String ID = UUID.randomUUID().toString();
                
                final String strSQL = "INSERT INTO tbluser (ID, Name, Password, Email, Role, CreatedAtDate) VALUES ('" + ID + "', '" + tblUser.getStrName() + "', '" + tblUser.getStrPassword() + "', '" + tblUser.getStrEmail()  + "', 'Regular User', '"  + LocalDate.now().toString() + "')";
                
                statement.executeUpdate( strSQL );
                
                dbConnection.getDatabaseConnection().commit();
                
                if ( tblUser.getStrPhoneNumber() != null) {
                            
                  String strSQL2 = "INSERT INTO tbluser_phonenumber VALUES ( '" + ID + "', '" + tblUser.getStrPhoneNumber() + "')"; 
                    
                  statement.executeUpdate( strSQL2 );  
                   
                  dbConnection.getDatabaseConnection().commit(); 
                  
                }
                                
                statement.close();
                
                result = true;
            }
            
        }
        
        catch ( Exception ex ) {
            
            ex.printStackTrace();
            
        }
        return result;
    }
   public static List<TblRating> SearchRatings ( final CDatabaseConnection dbConnection, String RestaurantID ) {
        
        List<TblRating> result = new ArrayList<TblRating>();
        
        try {
            
            if ( dbConnection != null && dbConnection.getDatabaseConnection() != null ) {
                
                Statement statement = dbConnection.getDatabaseConnection().createStatement();
                
                String strSQL = "SELECT * FROM tblratings JOIN tbluser ON tblratings.User_ID = tbluser.ID WHERE tblratings.Restaurant_ID = '" + RestaurantID + "'";
                
                ResultSet resultSet = statement.executeQuery( strSQL );
                
                while ( resultSet.next() ) {
                    
                   TblRating tblRating = new TblRating();  
                   tblRating.setStrID( resultSet.getString( "tblratings.ID" ) );
                   tblRating.setStrComment( resultSet.getString("tblratings.Comment"));
                   tblRating.setIntRating( resultSet.getInt("tblratings.Rating"));
                   tblRating.setUserName( resultSet.getString( "tbluser.Name" ) );    
                   
                   result.add( tblRating );
                   
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
    public static boolean AddRating( final CDatabaseConnection dbConnection, String Comment, int Rating, String UserID, String RestaurantID) {
        
        boolean result = false;
        
        try {
            
            if ( dbConnection != null && dbConnection.getDatabaseConnection() != null ) {
                
                Statement statement = dbConnection.getDatabaseConnection().createStatement();
                                  
                final String strSQL = "INSERT INTO tbluser (ID, User_ID, Restaurant_ID, Comment, Rating) VALUES ('" + UUID.randomUUID().toString() + "', '" + UserID + "', '" + RestaurantID + "', '" + Comment + "', '" + Rating + "')";
                
                statement.executeUpdate( strSQL );
                
                dbConnection.getDatabaseConnection().commit();
                
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
