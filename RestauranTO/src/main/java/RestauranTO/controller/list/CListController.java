package RestauranTO.controller.list;

import java.io.Serializable;

import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import RestauranTO.Database.Datamodel.TblRestaurant;

public class CListController implements Serializable{

    private static final long serialVersionUID = -1426709078820259442L;
    
    @Wire
    Listbox listboxRestaurantes;
    
    
 public class MyRenderer implements ListitemRenderer<TblRestaurant> {
        
        
        public void render( Listitem listitem, TblRestaurant Restaurant, int arg2 ) throws Exception {
            
            try {
                
                Listcell cell = new Listcell();
                cell.setLabel( Restaurant.getStrName() );
                listitem.appendChild( cell );
                cell = new Listcell();
                cell.setLabel( Restaurant.getStrDescription() );
                listitem.appendChild( cell );
                cell = new Listcell();
                cell.setLabel( Restaurant.getStrDirection());
                listitem.appendChild( cell ); 
                cell = new Listcell();
                cell.setLabel( Restaurant.getStrEmail());
                listitem.appendChild( cell );
                cell = new Listcell();
                cell.setLabel( Restaurant.getStrPicture());
                listitem.appendChild( cell );
                
            }
            catch ( Exception ex ) {
                ex.printStackTrace();
            }
            
        }

        
    }
}
