package RestauranTO.controller.login;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timer;

import RestauranTO.Constants.SystemConstants;
import RestauranTO.Database.CDatabaseConnection;
import RestauranTO.Database.CDatabaseConnectionConfig;
import RestauranTO.Database.Dao.UserDAO;
import RestauranTO.Database.Datamodel.TblUser;


public class CLoginController extends SelectorComposer<Component> {

    @Wire
    Textbox textboxOperator;
    
    @Wire
    Textbox textboxPassword;
    
    @Wire
    Button buttonLogin;
    
    @Wire
    Timer timerKeepAliveSession;
    
    @Wire
    Label labelMessage;
    
    private static final long serialVersionUID = -434240304817423997L;
    

    @Override
    public void doAfterCompose( Component comp ) {
        
        try {
         
            super.doAfterCompose( comp );
            
           
        }
        catch ( Exception ex ) {
            
            ex.printStackTrace();
            
        }
    
    
    }    
    
    @Listen( "onChanging=#textboxOperator; onChanging=#textboxPassword" )
    public void onChangeTextbox( Event event ) {

        labelMessage.setValue( "" ); 
    }    
    
    @Listen( "onClick=#buttonLogin; onOK=#windowLogin" )
    public void onClickButtonLogin( Event event ) {

        try {
                        
            final String strOperator = textboxOperator.getValue();
            
            final String strPassword = textboxPassword.getValue();
        
            if ( textboxOperator != null && textboxPassword != null ) {
            
                CDatabaseConnection databaseConnection = new CDatabaseConnection();
                
                CDatabaseConnectionConfig databaseConnectionConfig = new CDatabaseConnectionConfig();
                            
                String strRunningPath = Sessions.getCurrent().getWebApp().getRealPath( SystemConstants._Web_Inf_Dir ) + File.separator;
                
                if ( databaseConnectionConfig.loadConfig( strRunningPath + SystemConstants._Config_Dir + SystemConstants._Database_Config_File ) ) {
                    
                    if ( databaseConnection.makeConnectionToDatabase( databaseConnectionConfig ) ) {

                        TblUser tblOperator = UserDAO.checkData( databaseConnection, strOperator, strPassword );
                        
                        if ( tblOperator != null ) {
                                            
                            labelMessage.setSclass( "" ); 
                            
                            Messagebox.show( "Welcome " + tblOperator.getStrName() + "!" );
                            
                            Session currentSession = Sessions.getCurrent();
                                                        
                            currentSession.setAttribute( SystemConstants._DB_Connection_Session_Key, databaseConnection ); 
                                                        
                            currentSession.setAttribute( SystemConstants._Operator_Credential_Session_Key, tblOperator );
                            
                                                        
                            //String strLogPath = strRunningPath + SystemConstants._Logs_Dir + strOperator + File.separator + strDateTime + File.separator;
                                                        
                           // currentSession.setAttribute( SystemConstants._Log_Path_Session_Key, strLogPath );

                            //currentSession.setAttribute( SystemConstants._Login_Date_Time_Session_Key, strDateTime );
                                                                             
                            List<String> loggedSessionLoggers = new LinkedList<String>();
                                                        
                            currentSession.setAttribute( SystemConstants._Logged_Session_Loggers, loggedSessionLoggers );
                                                                                                                
                            Executions.sendRedirect( "/views/dev027/home/home.zul" ); 
                            
                        }
                        else {
                            
                            labelMessage.setValue( "Invalid operator name and/or password!" );
                                                                                    
                        }
                        
                    }
                    else {
                        
                        Messagebox.show( "Database connection failed" );
                        
                    }
                    
                }
                else {
                    
                    Messagebox.show( "Error when reading the database config file" );
                    
                }
                
            }
            
        }
        catch ( Exception ex ) {
            
         ex.printStackTrace();   
            
        }
    
    }    
    
    @Listen("onTimer=#timerKeepAliveSession" )
    public void onTimer( Event event ) {
                        
        Clients.showNotification( "Automatic renewal of the session successful", "info",  null, "before_center", 2000, true );
        
    }
    
    
}
