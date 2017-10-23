package RestauranTO.Database.Datamodel;

import java.io.Serializable;
import java.time.LocalDate;

public class TblRestaurant implements Serializable {

  
    private static final long serialVersionUID = 6470631631146971306L;
  
    protected String strID;
    
    protected String strName;
    
    protected String strDescription; 
    
    protected String strEmail;
    
    protected String strPicture; 
    
    protected int intCapacity;
    
    protected String strDirection;
    
    protected LocalDate CreatedAtDate;
    
    protected LocalDate DisabledAtDate;
    
    protected String Zone;

    public TblRestaurant( String strID, String strName, String strDescription, String strEmail, String strPicture, int intCapacity, String strDirection, LocalDate createdAtDate, LocalDate disabledAtDate, String Zone ) {
        super();
        this.strID = strID;
        this.strName = strName;
        this.strDescription = strDescription;
        this.strEmail = strEmail;
        this.strPicture = strPicture;
        this.intCapacity = intCapacity;
        this.strDirection = strDirection;
        this.CreatedAtDate = createdAtDate;
        this.DisabledAtDate = disabledAtDate;
        this.Zone = Zone;
    }

    public TblRestaurant() {
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

    
    public String getStrDescription() {
        
        return strDescription;
    }

    
    public void setStrDescription( String strDescription ) {
        
        this.strDescription = strDescription;
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

    
    public int getIntCapacity() {
        
        return intCapacity;
    }

    
    public void setIntCapacity( int intCapacity ) {
        
        this.intCapacity = intCapacity;
    }

    
    public String getStrDirection() {
        
        return strDirection;
    }

    
    public void setStrDirection( String strDirection ) {
        
        this.strDirection = strDirection;
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
    
    public String getZone() {
        
        return Zone;
    }

    
    public void setZone( String zone ) {
        
        Zone = zone;
    }
    
}
