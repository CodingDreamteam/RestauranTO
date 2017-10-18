package RestauranTO.Database.Dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.UUID;

import RestauranTO.Database.CDatabaseConnection;
import RestauranTO.Database.Datamodel.TblUser;
import RestauranTO.crypt.BCrypt;

public class UserDAO {
    
    public static TblUser checkData( final CDatabaseConnection dbConnection, final String strName, final String strPassword ) {
        
        TblUser result = null;
        
        try {
            if ( dbConnection != null && dbConnection.getDatabaseConnection() != null ) {
                
                Statement statement = dbConnection.getDatabaseConnection().createStatement();
                
                ResultSet resultSet = statement.executeQuery( "SELECT * FROM tblOperator WHERE Name = '" + strName + "' AND DisabledAtDate <=> null AND DisabledAtTime <=> null" );
                
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
                        result.setDisabledAtDate( resultSet.getDate( "DisabledAtDate" ) != null ? resultSet.getDate( "DisabledAtDate" ).toLocalDate() : null );
                     
                    }
                    
                }
                
                resultSet = statement.executeQuery( "SELECT Name FROM tblGroup WHERE Id IN (SELECT GroupId FROM tblOperator WHERE Name = '" + result.getStrName() + "')" );
                
                if ( resultSet.next() ) {
                    
                    result.setStrRole( resultSet.getString( "Name" ) );
                    
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
    
 public static boolean AddOperator( final CDatabaseConnection dbConnection, TblUser tblUser) {
        
        boolean result = false;
        
        try {
            if ( dbConnection != null && dbConnection.getDatabaseConnection() != null ) {
                
                Statement statement = dbConnection.getDatabaseConnection().createStatement();
                
                final String strSQL = "INSERT INTO tbluser (ID, Name, Password, Email, Picture, Role, CreatedAtDate) VALUES ('" + UUID.randomUUID().toString() + "', '" + tblUser.getStrName() + "', '" + tblUser.getStrPassword() + "', '" + tblUser.getStrEmail() + "', '" + tblUser.getStrPicture() + "', 'Regular User', '" + "', '" + LocalDate.now().toString() + "')";
                
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
