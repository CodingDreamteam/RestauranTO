package RestauraTO.controller.Ratinglist;

import java.io.File;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import RestauranTO.Constants.SystemConstants;
import RestauranTO.Database.CDatabaseConnection;
import RestauranTO.Database.CDatabaseConnectionConfig;
import RestauranTO.Database.Dao.RestaurantDAO;
import RestauranTO.Database.Dao.UserDAO;
import RestauranTO.Database.Datamodel.TblUser;
import RestauranTO.Database.Datamodel.TblZone;
import RestauranTO.Database.Datamodel.TblRating;
import RestauranTO.controller.Zone.CZoneController.MyRenderer;


public class CRatinglistController extends SelectorComposer<Component> {

    private static final long serialVersionUID = 8558890108543087068L;
    
    
    @Wire
    Listbox listboxRating;
    
    protected TblUser tblUser = null;
    
    protected Session sesion = Sessions.getCurrent();
    
    protected final Execution execution = Executions.getCurrent();
    
    protected ListModelList<TblRating> datamodelRating = null;
    
    protected String RestaurantID = null;
    
 public class MyRenderer implements ListitemRenderer<TblRating> {
        
        
        @SuppressWarnings( "static-access" )
        public void render( Listitem listitem, TblRating Rating, int arg2 ) throws Exception {
            
            try {
                Listcell cell = new Listcell();
                cell.setLabel( Rating.getUserName() );
                listitem.appendChild( cell );
                cell = new Listcell();
                cell.setLabel( Rating.getStrComment() );
                listitem.appendChild( cell );
                cell = new Listcell();
                String Ratingnumber = null;
                Ratingnumber.valueOf( Rating.getIntRating() );
                cell.setLabel( Ratingnumber );
                listitem.appendChild( cell );
            }
            catch ( Exception ex ) {
                ex.printStackTrace();
            }
            
        }

        
    }
 
    @SuppressWarnings( "unchecked" )
    public void doAfterCompose( Component comp ) {
     
     try {
         super.doAfterCompose( comp );
         
         
         RestaurantID = ( String ) execution.getArg().get( "RestaurantID" );
         
         CDatabaseConnection databaseConnection = new CDatabaseConnection();
         
         CDatabaseConnectionConfig databaseConnectionConfig = new CDatabaseConnectionConfig();
                     
         String strRunningPath = Sessions.getCurrent().getWebApp().getRealPath( SystemConstants._Web_Inf_Dir ) + File.separator;
         
         if ( databaseConnectionConfig.loadConfig( strRunningPath + SystemConstants._Config_Dir + SystemConstants._Database_Config_File ) ) {
             
             if ( databaseConnection.makeConnectionToDatabase( databaseConnectionConfig ) ) {
         
                 List<TblRating> listData = UserDAO.SearchRatings( databaseConnection, RestaurantID );
         
                 datamodelRating = new ListModelList<TblRating>( listData );
         
                 listboxRating.setModel( datamodelRating );
         
                 listboxRating.setItemRenderer( ( new MyRenderer() ) );
                 
                 databaseConnection.closeConnectionToDB();
                 
             }
          
         }    
         
         
     }
     
     catch ( Exception Ex ) {
           
         Ex.printStackTrace();
             
         }
         
     }
    
    
}
