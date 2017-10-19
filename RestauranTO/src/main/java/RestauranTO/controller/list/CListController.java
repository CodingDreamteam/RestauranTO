package RestauranTO.controller.list;

import java.io.Serializable;

import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Listbox;

public class CListController implements Serializable{

    private static final long serialVersionUID = -1426709078820259442L;
    
    @Wire
    Listbox listboxRestaurantes;
    
}
