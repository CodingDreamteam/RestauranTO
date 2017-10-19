package RestauranTO.controller.rating;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Image;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import RestauranTO.Constants.SystemConstants;
import RestauranTO.Database.CDatabaseConnection;
import RestauranTO.Database.Dao.UserDAO;
import RestauranTO.Database.Datamodel.TblUser;


public class CRatingController extends SelectorComposer<Component> {

    private static final long serialVersionUID = -5900924798947899904L;
    
    @Wire
    Image star1;
    @Wire
    Image star2;
    @Wire
    Image star3;
    @Wire
    Image star4;
    @Wire
    Image star5;
    @Wire
    Textbox textboxComment;
    
    protected TblUser tblUser = null;
    
    protected String RestaurantID = null;
    
    protected Session sesion = Sessions.getCurrent();
    
    protected final Execution execution = Executions.getCurrent();
    
    public void doAfterCompose( Component comp ) {
        
        try {
            super.doAfterCompose( comp );
                               
            tblUser = ( TblUser ) sesion.getAttribute( SystemConstants._Operator_Credential_Session_Key );
            
            RestaurantID = ( String ) execution.getArg().get( "RestaurantID" );    
            
        }
        
        catch ( Exception Ex ) {
              
            Ex.printStackTrace();
                
            }
            
        }
    
    @Listen( "onClick=#buttonSubmit" )
    public void onClickbuttonSubmit( Event event ) {
        
       int Rating = 0;     
       
       String Comment = null;
       
       if ( star1.getSrc() ==  "/resources/yellow star.png" )
        Rating = 1;   
       if ( star2.getSrc() ==  "/resources/yellow star.png" )
        Rating = 2; 
       if ( star3.getSrc() ==  "/resources/yellow star.png" )
        Rating = 3; 
       if ( star4.getSrc() ==  "/resources/yellow star.png" )
        Rating = 4; 
       if ( star5.getSrc() ==  "/resources/yellow star.png" )
        Rating = 5;        
       if ( textboxComment.getValue().isEmpty() == false ) 
        Comment = textboxComment.getValue();
       
       CDatabaseConnection dbConnection = ( CDatabaseConnection ) sesion.getAttribute( SystemConstants._DB_Connection_Session_Key );
             
       UserDAO.AddRating( dbConnection, Comment, Rating, tblUser.getStrID(), RestaurantID );
       
       Messagebox.show( "Rating enviado" );
       
    }
    
    @Listen( "onClick=#star1" )
    public void onClickstar1( Event event ) {
        
        star1.setSrc( "/resources/yellow star.png" );
        
        if ( star2.getSrc() == "/resources/yellow star.png") {
            
            star2.setSrc( "/resources/blank star.png" );
            
            star3.setSrc( "/resources/blank star.png" );
            
            star4.setSrc( "/resources/blank star.png" );
            
            star5.setSrc( "/resources/blank star.png" );
            
        }
            
        
    }
    
    @Listen( "onClick=#star2" )
    public void onClickstar2( Event event ) {
        
        star1.setSrc( "/resources/yellow star.png" );
   
        star2.setSrc( "/resources/yellow star.png" );
        
        if ( star3.getSrc() == "/resources/yellow star.png" ) {
            
            star3.setSrc( "/resources/blank star.png" );
            
            star4.setSrc( "/resources/blank star.png" );
            
            star5.setSrc( "/resources/blank star.png" );
            
        }
        
    }
    
    @Listen( "onClick=#star3" )
    public void onClickstar3( Event event ) {
        
        star1.setSrc( "/resources/yellow star.png" );
   
        star2.setSrc( "/resources/yellow star.png" );
        
        star3.setSrc( "/resources/yellow star.png" );
        
        if ( star4.getSrc() == "/resources/yellow star.png" ) {
                       
            star4.setSrc( "/resources/blank star.png" );
            
            star5.setSrc( "/resources/blank star.png" );
            
        }
        
    }
    
    @Listen( "onClick=#star4" )
    public void onClickstar4( Event event ) {
        
        star1.setSrc( "/resources/yellow star.png" );
   
        star2.setSrc( "/resources/yellow star.png" );
        
        star3.setSrc( "/resources/yellow star.png" );
        
        star4.setSrc( "/resources/yellow star.png" );
        
        if ( star5.getSrc() == "/resources/yellow star.png" ) {
                                   
            star5.setSrc( "/resources/blank star.png" );
            
        }
        
    }
    
    @Listen( "onClick=#star5" )
    public void onClickstar5( Event event ) {
        
        star1.setSrc( "/resources/yellow star.png" );
   
        star2.setSrc( "/resources/yellow star.png" );
        
        star3.setSrc( "/resources/yellow star.png" );
        
        star4.setSrc( "/resources/yellow star.png" );
        
        star5.setSrc( "/resources/yellow star.png" );

    }
}
