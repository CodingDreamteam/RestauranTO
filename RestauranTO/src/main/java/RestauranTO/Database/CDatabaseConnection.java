package RestauranTO.Database;

import java.sql.Connection;
import java.sql.DriverManager;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;


public class CDatabaseConnection extends SelectorComposer<Component> {

    private static final long serialVersionUID = 4225343907390582232L;
    
   protected Connection DatabaseConnection;
    
    protected CDatabaseConnectionConfig DatabaseConnectionConfig;
    
    public CDatabaseConnection() {
        
        DatabaseConnection = null;
        
        DatabaseConnectionConfig = null;
        
    }
    
    public Connection getDatabaseConnection() {
        
        return DatabaseConnection;
        
    }
    
    public CDatabaseConnectionConfig getDatabaseConnectionConfig() {
        
        return DatabaseConnectionConfig;
        
    }
    
    public void setDatabaseConnection( Connection DatabaseConnection ) {
                
        this.DatabaseConnection = DatabaseConnection;
        
    }
    
    public void setDatabaseConnectionConfig( CDatabaseConnectionConfig DatabaseConnectionConfig ) {
                
        this.DatabaseConnectionConfig = DatabaseConnectionConfig;
        
    }
    
    public boolean makeConnectionToDatabase( CDatabaseConnectionConfig localDBConnectionConfig ) {
        
        boolean bResult = false;
        
        try {
            
            if ( this.DatabaseConnection == null ) {
                
                Class.forName( localDBConnectionConfig.Driver );
                               
                String strDatabaseURL = localDBConnectionConfig.Prefix + localDBConnectionConfig.Host + ":" + localDBConnectionConfig.Port + "/" + localDBConnectionConfig.Database;
                                
                Connection localDBConnection = DriverManager.getConnection( strDatabaseURL, localDBConnectionConfig.User, localDBConnectionConfig.Password );
                
                localDBConnection.setTransactionIsolation( Connection.TRANSACTION_READ_COMMITTED );
                                
                bResult = localDBConnection != null && localDBConnection.isValid( 5 );
                
                if ( bResult ) {
                    
                    localDBConnection.setAutoCommit( false );
                                      
                    this.DatabaseConnection = localDBConnection; 
                    
                    this.DatabaseConnectionConfig = localDBConnectionConfig; 
                    
                    
                }
                else {
                    
                    localDBConnection.close();
                    
                    localDBConnection = null;
                                     
                }
                
            }
            
        }
        catch ( Exception Ex ) {
            
            Ex.printStackTrace();
            
        }
        
        return bResult;
        
    }
    
    public boolean closeConnectionToDB( ) {
        
        boolean bResult = false;
        
        try {
                     
            if ( DatabaseConnection != null ) {
                
                DatabaseConnection.close();//cerramos
                               
            }
            
            DatabaseConnection = null;
            
            DatabaseConnectionConfig = null;
            
            bResult = true;
            
        }
        catch ( Exception Ex ) {
            
            Ex.printStackTrace();
            
        }
        
        return bResult;
        
    }
    
    public boolean isValid(  ) {
        
        boolean bResult = false;
        
        try {
            
    
            bResult = DatabaseConnection.isValid( 5 );
            
        }
        catch ( Exception Ex ) {
            
            Ex.printStackTrace();
         
        }
        
        return bResult;
        
    }

    
    
    
}

    
    
