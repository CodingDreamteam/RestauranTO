package RestauranTO.Database.Datamodel;

import java.io.Serializable;

public class TblZone implements Serializable {

    private static final long serialVersionUID = -1812536481636459513L;
    
    protected String strID;
    
    protected String strName;

    public TblZone( String strUserID, String strNumber ) {
        super();
        this.strID = strUserID;
        this.strName = strNumber;
    }

    public TblZone() {
        super();
    }

    
    public String getStrID() {
        
        return strID;
    }

    
    public void setStrID( String strUserID ) {
        
        this.strID = strUserID;
    }

    
    public String getStrName() {
        
        return strName;
    }

    
    public void setStrName( String strNumber ) {
        
        this.strName = strNumber;
    }
    
    

}
