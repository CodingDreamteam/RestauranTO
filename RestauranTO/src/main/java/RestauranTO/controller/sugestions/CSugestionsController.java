package RestauranTO.controller.sugestions;

import org.zkoss.image.Image;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import RestauranTO.Constants.SystemConstants;
import RestauranTO.Database.CDatabaseConnection;
import RestauranTO.Database.Dao.RestaurantDAO;
import RestauranTO.Database.Datamodel.TblSugestions;
import RestauranTO.Database.Datamodel.TblUser;


public class CSugestionsController extends SelectorComposer<Component> {

    private static final long serialVersionUID = 6107638621463893607L;
    
    @Wire
    Textbox textboxName;
    @Wire
    Textbox textboxDirection;
    @Wire
    Textbox textboxPhoneNumber;
    @Wire
    Textbox textboxEmail;
    @Wire
    Image Picture;
    
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
    
    @Listen( "onClick=#buttonSubmit" )
    public void onClickbuttonSubmit( Event event ) {
    		
     if ( textboxEmail.getValue().isEmpty() || textboxDirection.getValue().isEmpty() || textboxName.getValue().isEmpty() ) {
    	 
    	 
    	Messagebox.show( "debe colocar nombre, direccion y correo para realizar una sugerencia" ); 
    	 
     }
     else {
    	 
    	 TblSugestions tblSugestions = new TblSugestions();
    	 
    	 tblSugestions.setStrDirection( textboxDirection.getValue() );
    	 tblSugestions.setStrName( textboxName.getValue() );
    	 tblSugestions.setStrEmail(textboxEmail.getValue() );
    	 if ( textboxPhoneNumber.getValue().isEmpty() != true )
    		tblSugestions.setStrPhoneNumber( textboxPhoneNumber.getValue() ); 
    	 if ( Picture != null )
    		tblSugestions.setStrImage( Picture.getName() );
    	 
    	 CDatabaseConnection dbConnection = ( CDatabaseConnection ) sesion.getAttribute( SystemConstants._DB_Connection_Session_Key );
    	 
    	 if ( RestaurantDAO.AddSugestion(dbConnection, tblSugestions) ) {
    		 
    		 Messagebox.show( "sugerencia agregada" );
    		 
    	 }
    	 
     }
       
    } 
    
    
}
