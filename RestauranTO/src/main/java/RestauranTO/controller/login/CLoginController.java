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
    
    protected CExtendedLogger controllerLogger = null;
    
    protected CLanguage controllerLanguage = null;
    
    @Override
    public void doAfterCompose( Component comp ) {
        
        try {
         
            super.doAfterCompose( comp );
            
            controllerLogger = (CExtendedLogger) Sessions.getCurrent().getWebApp().getAttribute( SystemConstants._Webapp_Logger_App_Attribute_Key );
    
        }
        catch ( Exception ex ) {
            
            if ( controllerLogger != null )   
                controllerLogger.logException( "-1021", ex.getMessage(), ex );        
            
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
                
                if ( databaseConnectionConfig.loadConfig( strRunningPath + SystemConstants._Config_Dir + SystemConstants._Database_Config_File, controllerLogger, controllerLanguage ) ) {
                    
                    if ( databaseConnection.makeConnectionToDatabase( databaseConnectionConfig, controllerLogger, controllerLanguage ) ) {

                        TblUser tblOperator = UserDAO.checkData( databaseConnection, strOperator, strPassword, controllerLogger, controllerLanguage );
                        
                        if ( tblOperator != null ) {
                                            
                            labelMessage.setSclass( "" ); 
                            
                            Messagebox.show( "Welcome " + tblOperator.getStrName() + "!" );
                            
                            Session currentSession = Sessions.getCurrent();
                                                        
                            currentSession.setAttribute( SystemConstants._DB_Connection_Session_Key, databaseConnection ); 
                                                        
                            currentSession.setAttribute( SystemConstants._Operator_Credential_Session_Key, tblOperator );
                            
                            controllerLogger.logMessage( "1" , CLanguage.translateIf( controllerLanguage, "Saved user credential in session for user [%s]", tblOperator.getStrName() ) );

                            String strDateTime = Utilities.getDateInFormat( ConstantsCommonClasses._Global_Date_Time_Format_File_System_24, null );
                                                        
                            String strLogPath = strRunningPath + SystemConstants._Logs_Dir + strOperator + File.separator + strDateTime + File.separator;
                                                        
                            currentSession.setAttribute( SystemConstants._Log_Path_Session_Key, strLogPath );

                            controllerLogger.logMessage( "1" , CLanguage.translateIf( controllerLanguage, "Saved user log path [%s] in session for user [%s]", strLogPath, strOperator ) );
                                                        
                            currentSession.setAttribute( SystemConstants._Login_Date_Time_Session_Key, strDateTime );
                            
                            controllerLogger.logMessage( "1" , CLanguage.translateIf( controllerLanguage, "Saved user login date time [%s] in session for user [%s]", strDateTime, strOperator ) );
                                                        
                            List<String> loggedSessionLoggers = new LinkedList<String>();
                                                        
                            currentSession.setAttribute( SystemConstants._Logged_Session_Loggers, loggedSessionLoggers );
                                                        
                            UserDAO.updateLogin( databaseConnection, TblUser.getStrID(), controllerLogger, controllerLanguage );                            
                                                        
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
            
            if ( controllerLogger != null )   
                controllerLogger.logException( "-1021", ex.getMessage(), ex );        
            
        }
    
    }    
    
    @Listen("onTimer=#timerKeepAliveSession" )
    public void onTimer( Event event ) {
                        
        Clients.showNotification( "Automatic renewal of the session successful", "info",  null, "before_center", 2000, true );
        
    }
    
    
}
