package RestauranTO.Database.Datamodel;

import java.io.Serializable;

public class TblUserPhoneNumber implements Serializable {

    private static final long serialVersionUID = -1812536481636459513L;
    
    protected String strUserID;
    
    protected String strNumber;

    public TblUserPhoneNumber( String strUserID, String strNumber ) {
        super();
        this.strUserID = strUserID;
        this.strNumber = strNumber;
    }

    public TblUserPhoneNumber() {
        super();
    }

    
    public String getStrUserID() {
        
        return strUserID;
    }

    
    public void setStrUserID( String strUserID ) {
        
        this.strUserID = strUserID;
    }

    
    public String getStrNumber() {
        
        return strNumber;
    }

    
    public void setStrNumber( String strNumber ) {
        
        this.strNumber = strNumber;
    }
    
    

}
