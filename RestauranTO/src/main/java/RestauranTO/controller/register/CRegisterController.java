package RestauranTO.controller.register;

import java.io.File;

import org.zkoss.image.AImage;
import org.zkoss.image.Image;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import RestauranTO.Constants.SystemConstants;


public class CRegisterController extends SelectorComposer<Component> {

    private static final long serialVersionUID = 1677662886424670594L;
    
    @Wire
    Button buttonPicture;
    @Wire
    Button buttonOperatorSave;
    
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
}
