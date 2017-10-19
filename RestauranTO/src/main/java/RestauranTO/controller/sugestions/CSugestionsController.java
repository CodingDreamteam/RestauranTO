package RestauranTO.controller.sugestions;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zul.Listbox;

import RestauranTO.Constants.SystemConstants;
import RestauranTO.Database.Datamodel.TblRestaurant;
import RestauranTO.Database.Datamodel.TblUser;


public class CSugestionsController extends SelectorComposer<Component> {

    private static final long serialVersionUID = 6107638621463893607L;
    
    
    
    
    protected TblUser tblUser = null;
    
    protected Session sesion = Sessions.getCurrent();
    
    protected final Execution execution = Executions.getCurrent();
    
    public void doAfterCompose( Component comp ) {
        
        try {
            super.doAfterCompose( comp );
          
            tblUser = ( TblUser ) sesion.getAttribute( SystemConstants._Operator_Credential_Session_Key );
            
            
        }
        
        catch ( Exception Ex ) {
              
            Ex.printStackTrace();
                
        }
            
    }
    
    
    
    
}
