package RestauranTO.controller.advancedsearch;

import java.io.File;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import RestauranTO.Constants.SystemConstants;
import RestauranTO.Database.CDatabaseConnection;
import RestauranTO.Database.CDatabaseConnectionConfig;
import RestauranTO.Database.Dao.RestaurantDAO;


public class CAdvancedSearchController extends SelectorComposer<Component> {

    private static final long serialVersionUID = 4539859646034119338L;
    
    @Wire
    Label labelzonesearch;
    @Wire
    Textbox search;
    
    @Listen( "onClick=#buttonSearch" )
    public void onClickButtonSearch( Event event ) {

       if ( search.getValue().isEmpty() ) {
           
           Messagebox.show( "Debe incluir un valor de busqueda" );
           
       }
       else {
           
           Session currentSession = Sessions.getCurrent();
           
           CDatabaseConnection databaseConnection = new CDatabaseConnection();
           
           CDatabaseConnectionConfig databaseConnectionConfig = new CDatabaseConnectionConfig();
                       
           String strRunningPath = Sessions.getCurrent().getWebApp().getRealPath( SystemConstants._Web_Inf_Dir ) + File.separator;
           
           if ( databaseConnectionConfig.loadConfig( strRunningPath + SystemConstants._Config_Dir + SystemConstants._Database_Config_File ) ) {
               
               if ( databaseConnection.makeConnectionToDatabase( databaseConnectionConfig ) ) {
           
                 currentSession.setAttribute( "listboxRestaurant", RestaurantDAO.SearchRestaurants( databaseConnection, search.getValue() ));
           
               }
               
           }    
           
           Executions.sendRedirect( "/index.zul" ); //lugar donde llevara al listbox
           
           
           
       }
                  
    }    
}
