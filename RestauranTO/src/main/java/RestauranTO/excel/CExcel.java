package RestauranTO.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.UUID;

import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.ListModelList;

import RestauranTO.Database.Datamodel.Jsonthing;
import RestauranTO.commonlibs.commonclasses.ConstantsCommonClasses.ConstantsCommonClasses;



public class CExcel implements Serializable {

    private static final long serialVersionUID = -3627120651799801414L;

    public void DriverReport (ListModelList<Jsonthing> datamodel) {

        String strRunningPath = Sessions.getCurrent().getWebApp().getRealPath(ConstantsCommonClasses._WEB_INF_Dir) + File.separator;

        File DirPath = new File(strRunningPath + ConstantsCommonClasses._Temp_Dir + File.separator);

        if (DirPath.exists() == false) {

            DirPath.mkdirs();

        }

       
        String strFileName = "Square-Report" + "-" + UUID.randomUUID().toString() + ".xlsx";

        File reportFile = new File(strRunningPath + ConstantsCommonClasses._Temp_Dir + File.separator + strFileName);

        // PrintWriter reportWriter = null;
        SXSSFWorkbook workBook = null;

        try {

            workBook = new SXSSFWorkbook(); // Se crea el libro en excel

            SXSSFSheet workSheet = (SXSSFSheet) workBook.createSheet("Square Report"); //  Se crea la planilla
            
            SXSSFRow rowHead = (SXSSFRow) workSheet.createRow(0); //Se crea las cabeceras de las columnas
            
            rowHead.createCell(0).setCellValue("authorized_location_ids"); 
            
            rowHead.createCell(1).setCellValue("role_ids");
            
            rowHead.createCell(2).setCellValue("id"); 
            
            rowHead.createCell(3).setCellValue("first_name");
            
            rowHead.createCell(4).setCellValue("last_name");
            
            rowHead.createCell(5).setCellValue("external_id");
            
            rowHead.createCell(6).setCellValue("status");
            
            rowHead.createCell(7).setCellValue("updated_at");
            
            rowHead.createCell(8).setCellValue("created_at");
            
            rowHead.createCell(9).setCellValue("email");

            int intRowCount = 1;//Contador de las filas
            
            int intListIterator = 0;//Contador de la lista
            
            Jsonthing Json = new Jsonthing();
             
            boolean bolAux = false;
            
            while ( bolAux == false ){                                              
            
                if ( intListIterator < datamodel.size()){   
                    
                    System.out.println((intListIterator));
                    
                    rowHead= (SXSSFRow) workSheet.createRow(intRowCount);//Se le asigna la fila nueva                   
            
                    rowHead.createCell(0).setCellValue(datamodel.getElementAt(intListIterator).getAuthorized_location_ids());
            
                    rowHead.createCell(1).setCellValue(datamodel.getElementAt(intListIterator).getRole_ids());
            
                    rowHead.createCell(2).setCellValue(datamodel.getElementAt(intListIterator).getId());
            
                    rowHead.createCell(3).setCellValue(datamodel.getElementAt(intListIterator).getFirst_name());
            
                    rowHead.createCell(4).setCellValue(datamodel.getElementAt(intListIterator).getLast_name());
            
                    rowHead.createCell(5).setCellValue(datamodel.getElementAt(intListIterator).getExternal_id());
            
                    rowHead.createCell(6).setCellValue(datamodel.getElementAt(intListIterator).getStatus());
                    
                    rowHead.createCell(7).setCellValue(datamodel.getElementAt(intListIterator).getUpdated_at().toString());
                    
                    rowHead.createCell(8).setCellValue(datamodel.getElementAt(intListIterator).getCreated_at().toString());
            
                    rowHead.createCell(9).setCellValue(datamodel.getElementAt(intListIterator).getEmail());
            
                    intRowCount ++;
            
                    intListIterator ++;                 
            
                    if(datamodel.iterator().hasNext()){
                        
                        datamodel.iterator().next();
                    
                    }else{
                    
                        bolAux = true;
                    
                    }
            
                }else{
                    bolAux=true;
                }
            
            }
                
            FileOutputStream fileOut = new FileOutputStream(reportFile);

            workBook.write(fileOut);

            workBook.dispose();
            
            Messagebox.show("File created at "+ reportFile.toString());

        } catch (Exception e) {
            
            e.printStackTrace();
        
        }       

    }

}