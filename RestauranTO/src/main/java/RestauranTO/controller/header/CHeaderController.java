package RestauranTO.controller.header;

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
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import RestauranTO.Constants.SystemConstants;
import RestauranTO.Database.CDatabaseConnection;
import RestauranTO.Database.CDatabaseConnectionConfig;
import RestauranTO.Database.Dao.RestaurantDAO;
import RestauranTO.Database.Datamodel.TblRestaurant;
import RestauranTO.Database.Datamodel.TblUser;



public class CHeaderController extends SelectorComposer<Component> {

    private static final long serialVersionUID = 411818559727221830L;
    
    protected String strSearch = null;
    
    protected Listbox listboxRestaurantes;
    
    protected TblRestaurant tblRestaurant = null;
    
    protected 
    
    
    @Wire
    Label labelregister;
    @Wire
    Label labelsugestion;
    @Wire
    Label labellogout;
    @Wire
    Label labellogin;
    @Wire
    Label labeluser;
    @Wire
    Textbox search;    
    @Wire
    Button buttonSearch;
    
    protected TblUser tblUser = null;
    
    protected Session sesion = Sessions.getCurrent();
    
    protected final Execution execution = Executions.getCurrent();
    
    public void doAfterCompose( Component comp ) {
        
        try {
            super.doAfterCompose( comp );
          
            tblUser = ( TblUser ) sesion.getAttribute( SystemConstants._Operator_Credential_Session_Key );
            
            if ( tblUser != null ) {
                
               labellogin.setVisible( false ); 
                
               labelregister.setVisible( false );
               
               labellogout.setVisible( true );
               
               labelsugestion.setVisible( true );
               
               labeluser.setValue( tblUser.getStrName() );
               
            }
            
            listboxRestaurantes = ( Listbox ) execution.getArg().get( "listboRestaurantes" );
            
        }
        
        catch ( Exception Ex ) {
              
            Ex.printStackTrace();
                
            }
            
        }
        
        @Listen( "onClick=#labelregister" )
        public void onClicklabelregister( Event event ) {
            
            Map<String, Object> arg = new HashMap<String, Object>();
            
            arg.put( "labelregister", labelregister );
                       
            Window win = ( Window ) Executions.createComponents( "/views/register/register.zul", null, arg );
            
            win.doModal();    
            
            
        }
        
        @Listen( "onClick=#labelsugestion" )
        public void onClicklabelsugestion( Event event ) {
            
            Map<String, Object> arg = new HashMap<String, Object>();
            
            arg.put( "labelsugestion", labelsugestion );
                       
            Window win = ( Window ) Executions.createComponents( "/views/sugestions/sugestions.zul", null, arg );
            
            win.doModal();    
            
            
        }
        
        @Listen( "onClick=#labellogin" )
        public void onClicklabellogin( Event event ) {
            
            Map<String, Object> arg = new HashMap<String, Object>();
            
            arg.put( "labellogin", labellogin );
                       
            Window win = ( Window ) Executions.createComponents( "/views/login/login.zul", null, arg );
            
            win.doModal();    
            
            
        }
    
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
               
               Executions.sendRedirect( "/views/list/list.zul" ); //lugar donde llevara al listbox
               
               
               
           }
                      
        }    
}

