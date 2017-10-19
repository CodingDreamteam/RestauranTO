package RestauranTO.controller.header;

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
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

import RestauranTO.Constants.SystemConstants;
import RestauranTO.Database.Datamodel.TblUser;


public class CHeaderController extends SelectorComposer<Component> {

    private static final long serialVersionUID = 411818559727221830L;
    
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
    
}
