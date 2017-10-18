package RestauranTO.Database.Datamodel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class TblReservation implements Serializable {


    private static final long serialVersionUID = 6118355009360334069L;

    protected String strUserID;
    
    protected String strRestaurantID;
    
    protected int intCapacity; 
    
    protected String strEmail;
    
    protected LocalDate SelectedDate;
    
    protected LocalTime SelectedTime;

    public TblReservation( String strUserID, String strRestaurantID, int intCapacity, String strEmail, LocalDate selectedDate, LocalTime selectedTime ) {
        super();
        this.strUserID = strUserID;
        this.strRestaurantID = strRestaurantID;
        this.intCapacity = intCapacity;
        this.strEmail = strEmail;
        this.SelectedDate = selectedDate;
        this.SelectedTime = selectedTime;
    }

    public TblReservation() {
        super();
    }

    
    public String getStrUserID() {
        
        return strUserID;
    }

    
    public void setStrUserID( String strUserID ) {
        
        this.strUserID = strUserID;
    }

    
    public String getStrRestaurantID() {
        
        return strRestaurantID;
    }

    
    public void setStrRestaurantID( String strRestaurantID ) {
        
        this.strRestaurantID = strRestaurantID;
    }

    
    public int getIntCapacity() {
        
        return intCapacity;
    }

    
    public void setIntCapacity( int intCapacity ) {
        
        this.intCapacity = intCapacity;
    }

    
    public String getStrEmail() {
        
        return strEmail;
    }

    
    public void setStrEmail( String strEmail ) {
        
        this.strEmail = strEmail;
    }

    
    public LocalDate getSelectedDate() {
        
        return SelectedDate;
    }

    
    public void setSelectedDate( LocalDate selectedDate ) {
        
        this.SelectedDate = selectedDate;
    }

    
    public LocalTime getSelectedTime() {
        
        return SelectedTime;
    }

    
    public void setSelectedTime( LocalTime selectedTime ) {
        
        this.SelectedTime = selectedTime;
    }

    
}
