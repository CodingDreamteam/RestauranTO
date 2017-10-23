package RestauranTO.Database.Datamodel;

import java.io.Serializable;
import java.time.LocalDate;

import RestauranTO.crypt.BCrypt;

public class TblUser implements Serializable {

    private static final long serialVersionUID = -5001151189415958989L;
    
    protected String strID;
    
    protected String strName;
    
    protected String strPassword; 
    
    protected String strEmail;
    
    protected String strPicture; 
    
    protected String strRole;
    
    protected String strPhoneNumber;
    
    protected LocalDate CreatedAtDate;
    
    protected LocalDate DisabledAtDate;
    
    public TblUser( String strID, String strName, String strPassword, String strEmail, String strPicture, String strRole, String strPhoneNumber, LocalDate createdAtDate, LocalDate DisabledAtDate) {
        super();
        this.strID = strID;
        this.strName = strEmail;
        this.strPassword = strPassword;
        this.strEmail = strEmail;
        this.strPicture = strPicture;
        this.strRole = strRole;
        this.strPhoneNumber = strPhoneNumber;
        this.CreatedAtDate = createdAtDate;
        this.DisabledAtDate = DisabledAtDate;
}
    public TblUser( ) {
        super();
        
    }
    
    public String getStrID() {
        
        return strID;
    }
    
    public void setStrID( String strID ) {
        
        this.strID = strID;
    }
    
    public String getStrName() {
        
        return strName;
    }
    
    public void setStrName( String strName ) {
        
        this.strName = strName;
    }
    
    public String getStrPassword() {
        
        return strPassword;
    }
    
    public void setStrPassword( String strPassword ) {
        if (strPassword.startsWith( "$2a$105" ) == false) {
            
            String strPasswordKey = BCrypt.gensalt( 10 ); 
            
            strPassword = BCrypt.hashpw( strPassword, strPasswordKey ); 
            
            strPassword = strPassword.replace( "$2a$10", "$2y$10" );
            
        }
        
        this.strPassword = strPassword;
    }
    
    public String getStrEmail() {
        
        return strEmail;
    }
    
    public void setStrEmail( String strEmail ) {
        
        this.strEmail = strEmail;
    }
    
    public String getStrPicture() {
        
        return strPicture;
    }
    
    public void setStrPicture( String strPicture ) {
        
        this.strPicture = strPicture;
    }
    
    public String getStrRole() {
        
        return strRole;
    }
    
    public void setStrRole( String strRole ) {
        
        this.strRole = strRole;
    }
    
    public String getStrPhoneNumber() {
        
        return strPhoneNumber;
    }
    
    public void setStrPhoneNumber( String strPhoneNumber ) {
        
        this.strPhoneNumber = strPhoneNumber;
    }
    
    public LocalDate getCreatedAtDate() {
        
        return CreatedAtDate;
    }
    
    public void setCreatedAtDate( LocalDate createdAtDate ) {
        
       this.CreatedAtDate = createdAtDate;
    }
    
    public LocalDate getDisabledAtDate() {
        
        return DisabledAtDate;
    }
    
    public void setDisabledAtDate( LocalDate disabledAtDate ) {
        
        this.DisabledAtDate = disabledAtDate;
    }
}
