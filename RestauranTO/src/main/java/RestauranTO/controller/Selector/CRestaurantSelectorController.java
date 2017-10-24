package RestauranTO.controller.Selector;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import RestauranTO.Constants.SystemConstants;
import RestauranTO.Database.CDatabaseConnection;
import RestauranTO.Database.CDatabaseConnectionConfig;
import RestauranTO.Database.Dao.RestaurantDAO;
import RestauranTO.Database.Datamodel.TblRestaurant;
import RestauranTO.Database.Datamodel.TblUser;


public class CRestaurantSelectorController extends SelectorComposer<Component> {


    private static final long serialVersionUID = -2878542862853962669L;
    
    @Wire
    Label labelName;
    @Wire
    Label labelDirection;
    @Wire
    Label labelDescription;
    @Wire
    Label labelEmail;
    @Wire
    Label labelZona;
    @Wire
    Window windowRestaurantSelector;
    @Wire
    Button buttonAddRating;
    
    protected TblUser tblUser = null;
    
    protected Session sesion = Sessions.getCurrent();
    
    protected final Execution execution = Executions.getCurrent();
    
    protected TblRestaurant SelectedRestaurant = null;
    
    public void doAfterCompose( Component comp ) {
        
        try {
            super.doAfterCompose( comp );
          
            TblRestaurant tblrestaurant = ( TblRestaurant ) execution.getArg().get( "tblRestaurant" );     
            
            tblUser = ( TblUser ) sesion.getAttribute( SystemConstants._Operator_Credential_Session_Key );
            
            if ( tblUser != null)
             buttonAddRating.setVisible( true );   
            
            if (tblrestaurant != null) {
                
                CDatabaseConnection databaseConnection = new CDatabaseConnection();
                
                CDatabaseConnectionConfig databaseConnectionConfig = new CDatabaseConnectionConfig();
                            
                String strRunningPath = Sessions.getCurrent().getWebApp().getRealPath( SystemConstants._Web_Inf_Dir ) + File.separator;
                
                if ( databaseConnectionConfig.loadConfig( strRunningPath + SystemConstants._Config_Dir + SystemConstants._Database_Config_File ) ) {
                    
                    if ( databaseConnection.makeConnectionToDatabase( databaseConnectionConfig ) ) {
                
                       SelectedRestaurant = ( TblRestaurant ) RestaurantDAO.SearchOneRestaurant( databaseConnection, tblrestaurant.getStrID() ); 
                       
                       databaseConnection.closeConnectionToDB();
                    }
                    
                }   
                    
            }
            
            if ( SelectedRestaurant != null ) {
                
               labelName.setValue( SelectedRestaurant.getStrName() ); 
                
               labelDescription.setValue( SelectedRestaurant.getStrDescription() );
               
               labelDirection.setValue( SelectedRestaurant.getStrDirection() );
               
               labelEmail.setValue( SelectedRestaurant.getStrEmail() );
               
               labelZona.setValue( SelectedRestaurant.getZone() );
               
            }
            
        }
        
        catch ( Exception Ex ) {
              
            Ex.printStackTrace();
                
            }
            
        }
    
    @Listen( "onClick=#buttonAddRating" )
    public void onClickbuttonAddRating( Event event ) {
        
        Map<String, Object> arg = new HashMap<String, Object>();
        
        arg.put("RestaurantID",  SelectedRestaurant.getStrID());
                   
        Window win = ( Window ) Executions.createComponents( "/views/rating/rating.zul", null, arg );
        
        win.doModal();    
        
        
    }  
    
    @Listen( "onClick=#buttonRatingList" )
    public void onClickbuttonRatingList( Event event ) {
        
        Map<String, Object> arg = new HashMap<String, Object>();
        
        arg.put("RestaurantID",  SelectedRestaurant.getStrID());
                   
        Window win = ( Window ) Executions.createComponents( "/views/rating/rating.zul", null, arg );
        
        win.doModal();    
        
        
    }
}
