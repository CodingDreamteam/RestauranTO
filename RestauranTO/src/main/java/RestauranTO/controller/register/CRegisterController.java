package RestauranTO.controller.register;

import java.io.File;

import org.zkoss.image.AImage;
import org.zkoss.image.Image;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import RestauranTO.Constants.SystemConstants;
import RestauranTO.Database.CDatabaseConnection;
import RestauranTO.Database.CDatabaseConnectionConfig;
import RestauranTO.Database.Dao.UserDAO;
import RestauranTO.Database.Datamodel.TblUser;


public class CRegisterController extends SelectorComposer<Component> {

    private static final long serialVersionUID = 1677662886424670594L;
    
    @Wire
    Button buttonPicture;
    @Wire
    Button buttonOperatorSave;
    @Wire
    Textbox textboxEmail;
    @Wire
    Textbox textboxName;
    @Wire
    Textbox textboxPassword;
    @Wire
    Textbox textboxRepeatPassword;
    @Wire
    Window windowRegister;
    
    protected Image Picture = null;
    
    protected Label labelregister = null;
    
    protected final Execution execution = Executions.getCurrent();
    
    public void doAfterCompose( Component comp ) {
        
        try {
            super.doAfterCompose( comp );
                               
            buttonPicture.setUpload( "true" );
            
            labelregister = ( Label ) execution.getArg().get( "labelregister" );
            
        }
        
        catch ( Exception Ex ) {
              
            Ex.printStackTrace();
                
            }
            
        }
        
    
    
    
    @Listen ("onClick=#buttonPicture")
    public void onClickbuttonimportGroupContact ( Event event ) {
        
        buttonPicture.addEventListener( "onUpload", new EventListener<UploadEvent> ()  {
            public void onEvent(UploadEvent event) throws Exception {
                
                try {
                    
                    Media media = event.getMedia();
                    
                    if ( media.getName().contains( ".png" ) || media.getName().contains( ".jpg" )) {
                    
                        AImage image = ( AImage ) media;
                        
                        //Image image2 = ( Image ) media;
                        
                        
                        //File file = new File( media.getName());
                        
                        
                        //file.renameTo( new File (SystemConstants._Web_Inf_Dir + File.separator + SystemConstants._Picture_Folder + File.separator + file.getName() ) );
                       
                        Messagebox.show( "the file was succesufully submited" );
                                        
                    }
                    else {
                        
                        Messagebox.show( "you must import a image file (.png or .jpg)" );
                        
                    }
                    
                } 
                catch (Exception e) {
                    
                    e.printStackTrace();
                    
                    Messagebox.show("Upload failed");
                    
                }
            }

        });

    }
    
    
    @Listen( "onClick=#buttonCancel" )
    public void onClickbuttonCancel( Event event ) { 
        
       windowRegister.detach(); 
        
        
    }
    
    @Listen( "onClick=#buttonSave" )
    public void onClickbuttonAddcontact( Event event ) { 
        
       if ( textboxEmail.getValue().isEmpty() || textboxName.getValue().isEmpty() || textboxPassword.getValue().isEmpty() || textboxRepeatPassword.getValue().isEmpty() ) {
           
          Messagebox.show( "Debe llenar almenos el correo, la contraseña y el nombre de usuario" ); 
           
       }
       else {
           
          if ( textboxPassword.getValue() != textboxRepeatPassword.getValue() ) {
              
              Messagebox.show( "las contraseñas no concuerdan" );
              
          }
          else {
              
             TblUser tbluser = new TblUser(); 
             tbluser.setStrName( textboxName.getValue() );
             tbluser.setStrPassword( textboxPassword.getValue() );
             tbluser.setStrEmail( textboxEmail.getValue() ); 
             if ( Picture != null )
             tbluser.setStrPicture( Picture.getName() );
             
             CDatabaseConnection databaseConnection = new CDatabaseConnection();
             
             CDatabaseConnectionConfig databaseConnectionConfig = new CDatabaseConnectionConfig();
                         
             String strRunningPath = Sessions.getCurrent().getWebApp().getRealPath( SystemConstants._Web_Inf_Dir ) + File.separator;
             
             if ( databaseConnectionConfig.loadConfig( strRunningPath + SystemConstants._Config_Dir + SystemConstants._Database_Config_File ) ) {
                 
                 if ( databaseConnection.makeConnectionToDatabase( databaseConnectionConfig ) ) {
                     
                   if (  UserDAO.AddUser( databaseConnection, tbluser ) ) {
                       
                      Messagebox.show( "Registro exitoso" );
                       
                   }
                   else {
                       
                       Messagebox.show( "Error con el registro" );
                       
                   }
                     
                 }
                 
                 databaseConnection.closeConnectionToDB();
                 
             }             
             
          }
           
           
       }
        
    }
    
    
}
 
        
