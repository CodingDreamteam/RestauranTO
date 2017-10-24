package RestauranTO.controller.Zone;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import RestauranTO.Database.Datamodel.TblUser;
import RestauranTO.Database.Datamodel.TblZone;


public class CZoneController extends SelectorComposer<Component> {

    private static final long serialVersionUID = -6423649102807553745L;
    
    @Wire
    Listbox listboxZone;
    @Wire
    Window windowZoneSelector;
    
    protected TblUser tblUser = null;
    
    protected Session sesion = Sessions.getCurrent();
    
    protected final Execution execution = Executions.getCurrent();
    
    protected ListModelList<TblZone> datamodelZone = null;
    
    protected Listbox listboxRestaurantes = null;
    
 public class MyRenderer implements ListitemRenderer<TblZone> {
        
        
        public void render( Listitem listitem, TblZone Zone, int arg2 ) throws Exception {
            
            try {
                
                Listcell cell = new Listcell();
                cell.setLabel( Zone.getStrName() );
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
         
         
         listboxRestaurantes = ( Listbox ) execution.getArg().get( "listboxRestaurantes" );
         
         CDatabaseConnection databaseConnection = new CDatabaseConnection();
         
         CDatabaseConnectionConfig databaseConnectionConfig = new CDatabaseConnectionConfig();
                     
         String strRunningPath = Sessions.getCurrent().getWebApp().getRealPath( SystemConstants._Web_Inf_Dir ) + File.separator;
         
         if ( databaseConnectionConfig.loadConfig( strRunningPath + SystemConstants._Config_Dir + SystemConstants._Database_Config_File ) ) {
             
             if ( databaseConnection.makeConnectionToDatabase( databaseConnectionConfig ) ) {
         
                 List<TblZone> listData = RestaurantDAO.SearchAllZones( databaseConnection );
         
                 datamodelZone = new ListModelList<TblZone>( listData );
         
                 listboxZone.setModel( datamodelZone );
         
                 listboxZone.setItemRenderer( ( new MyRenderer() ) );
                 
                 databaseConnection.closeConnectionToDB();
                 
             }
          
         }    
         
         
     }
     
     catch ( Exception Ex ) {
           
         Ex.printStackTrace();
             
         }
         
     }
    
    @Listen( "onSelect=#listboxZone" )
    public void onSelect ( Event event ) {
        
        Set<TblZone> selecteditems = datamodelZone.getSelection(); 
        
        if ( listboxZone.getSelectedIndex() >= 0 ) {
            
            if (  selecteditems != null  &&  datamodelZone.getSelection().size() > 0 ) {
                        
                TblZone tblZone = selecteditems.iterator().next();
                 
                Events.echoEvent( new Event( "onZoneClose", listboxRestaurantes, tblZone ) ); 
                    
                windowZoneSelector.detach();
                
            }
            
        } 
        
   }
    
}
