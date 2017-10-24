package RestauranTO.controller.advancedsearch;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;


public class CAdvancedSearchController extends SelectorComposer<Component> {

    private static final long serialVersionUID = 4539859646034119338L;
    
    @Wire
    Label labelzonesearch;
    @Wire
    Textbox search;
    
    @Listen( "onClick=#buttonSearch" )
    public void onClickButtonSearch( Event event ) {

       
        
        
        
    }    
}
