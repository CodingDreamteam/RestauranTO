package RestauranTO.Database.Datamodel;

import java.io.Serializable;

public class TblRating implements Serializable{

    private static final long serialVersionUID = 3184056212740975921L;
    
    protected String strID;
    
    protected String strComment;
    
    protected int intRating;

    protected String UserName;
    
    

    public TblRating( String strID, String strComment, int intRating ) {
        super();
        this.strID = strID;
        this.strComment = strComment;
        this.intRating = intRating;
    }

    public TblRating(  ) {
        super();

    }
    public String getStrID() {
        
        return strID;
    }

    
    public void setStrID( String strID ) {
        
        this.strID = strID;
    }

    
    public String getStrComment() {
        
        return strComment;
    }

    
    public void setStrComment( String strComment ) {
        
        this.strComment = strComment;
    }

    
    public int getIntRating() {
        
        return intRating;
    }

    
    public void setIntRating( int intRating ) {
        
        this.intRating = intRating;
    }
    
    public String getUserName() {
        
        return UserName;
    }

    
    public void setUserName( String userName ) {
        
        UserName = userName;
    }

}
