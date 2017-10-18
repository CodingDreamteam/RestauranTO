package RestauranTO.controller.register;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import RestauranTO.Database.Datamodel.TblUser;
import web.zk.frontend.elysiumst.smsgateway.dev008.database.datamodel.TblContact;
import web.zk.frontend.elysiumst.smsgateway.dev008.utilities.SystemUtilities;


public class CRegisterController extends SelectorComposer<Component> {

    private static final long serialVersionUID = 1677662886424670594L;
    
    @Wire
    Button buttonPicture;
    @Wire
    Button buttonOperatorSave;
    
    @Listen ("onClick=#buttonimportGroupContact")
    public void onClickbuttonimportGroupContact ( Event event ) {
        
        buttonPicture.addEventListener( "onUpload", new EventListener<UploadEvent> ()  {
            public void onEvent(UploadEvent event) throws Exception {
                
                try {
                    
                    Media media = event.getMedia();
                    
                    if ( media.getName().contains( ".png" ) || media.getName().contains( ".jpg" )) {
                    
                        media
                        List<TblUser> ContactList = SystemUtilities.excelFileImport( media.getStreamData() );
                    
                        Messagebox.show( "the file was succesufully imported now enter the new group information" );
                    
                        Map<String, Object> arg = new HashMap<String, Object>();
                
                        arg.put( "listboxGroupContact", listboxGroupContact );
                
                        arg.put( "GroupList", ContactList);
                
                        Window win = ( Window ) Executions.createComponents( "/views/dev008/contact/groupcontacteditor.zul", null, arg );
                
                        win.doModal();
                    
                    }
                    else {
                        
                        Messagebox.show( "you must import a Microsoft Office Excel File (.xlsx)" );
                        
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
