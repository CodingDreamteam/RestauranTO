package RestauranTO.controller.list;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Window;

import RestauranTO.Constants.SystemConstants;
import RestauranTO.Database.CDatabaseConnection;
import RestauranTO.Database.CDatabaseConnectionConfig;
import RestauranTO.Database.Dao.RestaurantDAO;
import RestauranTO.Database.Datamodel.TblRestaurant;
import RestauranTO.Database.Datamodel.TblUser;
import RestauranTO.Database.Datamodel.TblZone;


  public class CListController extends SelectorComposer<Component>{

    private static final long serialVersionUID = -1426709078820259442L;
    
    @Wire
    Listbox listboxRestaurantes;
    @Wire
    Button buttonShowAll;
    
    protected TblUser tblUser = null;
    
    protected Session sesion = Sessions.getCurrent();
    
    protected final Execution execution = Executions.getCurrent();
    
    protected ListModelList<TblRestaurant> datamodelRestaurant = null;
    
    public class MyRenderer implements ListitemRenderer<TblRestaurant> {
        
        
        public void render( Listitem listitem, TblRestaurant Restaurant, int arg2 ) throws Exception {
            
            try {
                
                Listcell cell = new Listcell();
                cell.setLabel( Restaurant.getStrName() );
                listitem.appendChild( cell );
                cell = new Listcell();
                cell.setLabel( Restaurant.getStrDescription() );
                listitem.appendChild( cell );
                cell = new Listcell();
                cell.setLabel( Restaurant.getStrDirection());
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
         
         if ( sesion.getAttribute( "listboxRestaurant" ) != null ) {
         
         List<TblRestaurant> listData = ( List<TblRestaurant> ) sesion.getAttribute( "listboxRestaurant" );
         
         datamodelRestaurant = new ListModelList<TblRestaurant>( listData );
         
         listboxRestaurantes.setModel( datamodelRestaurant );
         
         listboxRestaurantes.setItemRenderer( ( new MyRenderer() ) );
         
         }
         else {
             
             Events.echoEvent("onClick", buttonShowAll, null );  
             
         }
         
     }
     
     catch ( Exception Ex ) {
           
         Ex.printStackTrace();
             
         }
         
     }
     
    @Listen( "onClick=#buttonShowAll" )
    public void onClickbuttonShowAll( Event event ) {
        
        CDatabaseConnection databaseConnection = new CDatabaseConnection();
        
        CDatabaseConnectionConfig databaseConnectionConfig = new CDatabaseConnectionConfig();
                    
        String strRunningPath = Sessions.getCurrent().getWebApp().getRealPath( SystemConstants._Web_Inf_Dir ) + File.separator;
        
        if ( databaseConnectionConfig.loadConfig( strRunningPath + SystemConstants._Config_Dir + SystemConstants._Database_Config_File ) ) {
            
            if ( databaseConnection.makeConnectionToDatabase( databaseConnectionConfig ) ) {
        
                List<TblRestaurant> listData = RestaurantDAO.SearchAllRestaurants( databaseConnection );
        
                datamodelRestaurant = new ListModelList<TblRestaurant>( listData );
        
                listboxRestaurantes.setModel( datamodelRestaurant );
        
                listboxRestaurantes.setItemRenderer( ( new MyRenderer() ) );
                
                databaseConnection.closeConnectionToDB();
            }
       }
    }
    
    @Listen( "onClick=#buttonZoneSearch" )
    public void onClicklabellogin( Event event ) {
        
        Map<String, Object> arg = new HashMap<String, Object>();
        
        arg.put( "listboxRestaurantes", listboxRestaurantes );
                
        Window win = ( Window ) Executions.createComponents( "/views/zoneselector/zoneselector.zul", null, arg );
        
        win.doModal();
        
    }
    
    @Listen( "onZoneClose=#listboxRestaurantes" )
    public void onZoneClose( Event event ) {
        
        TblZone tblzone = ( TblZone ) event.getData();
        
        CDatabaseConnection databaseConnection = new CDatabaseConnection();
        
        CDatabaseConnectionConfig databaseConnectionConfig = new CDatabaseConnectionConfig();
                    
        String strRunningPath = Sessions.getCurrent().getWebApp().getRealPath( SystemConstants._Web_Inf_Dir ) + File.separator;
        
        if ( databaseConnectionConfig.loadConfig( strRunningPath + SystemConstants._Config_Dir + SystemConstants._Database_Config_File ) ) {
            
            if ( databaseConnection.makeConnectionToDatabase( databaseConnectionConfig ) ) {
        
                List<TblRestaurant> listData = RestaurantDAO.SearchRestaurantsZones( databaseConnection, tblzone.getStrName() );
        
                datamodelRestaurant = new ListModelList<TblRestaurant>( listData );
        
                listboxRestaurantes.setModel( datamodelRestaurant );
        
                listboxRestaurantes.setItemRenderer( ( new MyRenderer() ) );
                
                databaseConnection.closeConnectionToDB();
        
            }
       }
        
    }
    
}
