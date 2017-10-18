package RestauranTO.Database.Datamodel;

import java.io.Serializable;

public class TblPhoneNumber implements Serializable {

  
    private static final long serialVersionUID = 5776686719368786767L;

     protected String strRestaurantID;
     
     protected String strNumber;

    public TblPhoneNumber( String strRestaurantID, String strNumber ) {
        super();
        this.strRestaurantID = strRestaurantID;
        this.strNumber = strNumber;
    }

    public TblPhoneNumber() {
        super();
        
    }

    
    public String getStrRestaurantID() {
        
        return strRestaurantID;
    }

    
    public void setStrRestaurantID( String strRestaurantID ) {
        
        this.strRestaurantID = strRestaurantID;
    }

    
    public String getStrNumber() {
        
        return strNumber;
    }

    
    public void setStrNumber( String strNumber ) {
        
        this.strNumber = strNumber;
    }
    
     
}
