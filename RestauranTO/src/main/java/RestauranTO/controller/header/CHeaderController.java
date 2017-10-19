package RestauranTO.controller.header;

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
            tblRestaurant = ( TblRestaurant ) sesion.getAttribute( SystemConstants._Operator_Credential_Session_Key );
            
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
    
        @Listen( "onClick=#buttonSearch" )
        public void onClickbuttonSearch( Event event ) {
         
            if ( search.getValue().isEmpty() == false ) {  
                
                strSearch = search.getValue();
                if ( sesion.getAttribute( SystemConstants._DB_Connection_Session_Key ) instanceof CDatabaseConnection ) {
                    
                    CDatabaseConnection dbConnection = ( CDatabaseConnection ) sesion.getAttribute( SystemConstants._DB_Connection_Session_Key );
                
                    List<TblRestaurant> listData = RestaurantDAO.SearchRestaurants( dbConnection, strSearch, localLogger, localLanguage );
                    
                    Session currentSession = Sessions.getCurrent();
                    
                    currentSession.setAttribute("RestaurantList", listData );
                    
                    Executions.sendRedirect( "/views/list/list.zul" ); 
                    
                    
             }
            
        }
}
}
