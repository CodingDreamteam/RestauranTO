package RestauranTO.Database.Datamodel;

import java.io.Serializable;

public class TblGallery implements Serializable {

    private static final long serialVersionUID = 4422910648446096593L;

    protected String strRestaurantID;
    
    protected String strImageID;

    protected String strDirection;

    public TblGallery( String strRestaurantID, String strImageID, String strDirection ) {
        super();
        this.strRestaurantID = strRestaurantID;
        this.strImageID = strImageID;
        this.strDirection = strDirection;
    }

    public TblGallery() {
        super();
       
    }

    
    public String getStrRestaurantID() {
        
        return strRestaurantID;
    }

    
    public void setStrRestaurantID( String strRestaurantID ) {
        
        this.strRestaurantID = strRestaurantID;
    }

    
    public String getStrImageID() {
        
        return strImageID;
    }

    
    public void setStrImageID( String strImageID ) {
        
        this.strImageID = strImageID;
    }

    
    public String getStrDirection() {
        
        return strDirection;
    }

    
    public void setStrDirection( String strDirection ) {
        
        this.strDirection = strDirection;
    }
    
    
}
