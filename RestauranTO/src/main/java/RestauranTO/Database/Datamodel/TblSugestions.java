package RestauranTO.Database.Datamodel;

import java.io.Serializable;

public class TblSugestions implements Serializable {

    private static final long serialVersionUID = 4437712241408431567L;

    protected String strID;
    
    protected String strName;
    
    protected String strDirection; 
    
    protected String strEmail;
    
    protected String strPhoneNumber;
    
    protected String strImage;

    
    
    public TblSugestions( String strID, String strName, String strDirection, String strEmail, String strPhoneNumber ,String strImage ) {
        super();
        this.strID = strID;
        this.strName = strName;
        this.strDirection = strDirection;
        this.strEmail = strEmail;
        this.strPhoneNumber = strPhoneNumber;
        this.strImage = strImage;
    }



    public TblSugestions() {
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



    
    public String getStrDirection() {
        
        return strDirection;
    }



    
    public void setStrDirection( String strDirection ) {
        
        this.strDirection = strDirection;
    }



    
    public String getStrEmail() {
        
        return strEmail;
    }



    
    public void setStrEmail( String strEmail ) {
        
        this.strEmail = strEmail;
    }


    public String getStrPhoneNumber() {
        
        return strPhoneNumber;
    }



    
    public void setStrPhoneNumber( String strPhoneNumber ) {
        
        this.strPhoneNumber = strPhoneNumber;
    }
    
    public String getStrImage() {
        
        return strImage;
    }



    
    public void setStrImage( String strImage ) {
        
        this.strImage = strImage;
    } 
    
    
    
}
