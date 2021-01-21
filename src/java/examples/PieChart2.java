package examples;

import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.ss.util.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPieChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPieSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrRef;


/*
 * compatible to the libreoffice( openoffice )
 */
public class PieChart2 {

    public static void main(String[] args) throws Exception {
        Workbook wb = new XSSFWorkbook();

        Sheet sheet = wb.createSheet("Sheet1");

        pieChart((XSSFSheet)sheet);

        FileOutputStream fileOut = new FileOutputStream("PieChart2.xlsx");
        wb.write(fileOut);
        fileOut.close();
    }
    
    public static void pieChart(XSSFSheet sheet) {
        Row row;
        Cell cell;
        for (int r = 0; r < 3; r++) {
            row = sheet.createRow(r);
            cell = row.createCell(0);
            cell.setCellValue("S" + r);
            cell = row.createCell(1);
            cell.setCellValue(r + 1);
        }

        Drawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 5, 5, 20);

        Chart chart = drawing.createChart(anchor);

        CTChart ctChart = ((XSSFChart) chart).getCTChart();
        CTPlotArea ctPlotArea = ctChart.getPlotArea();
        CTPieChart ctPieChart = ctPlotArea.addNewPieChart();
        CTBoolean ctBoolean = ctPieChart.addNewVaryColors();
        ctBoolean.setVal(true);

        for (int r = 1; r < 4; r++) {
            CTPieSer ctPieSer = ctPieChart.addNewSer();
            CTSerTx ctSerTx = ctPieSer.addNewTx();
            CTStrRef ctStrRef = ctSerTx.addNewStrRef();
            ctStrRef.setF("Sheet1!$A$" + r);
            ctPieSer.addNewIdx().setVal(r - 2);
            CTAxDataSource cttAxDataSource = ctPieSer.addNewCat();
            ctStrRef = cttAxDataSource.addNewStrRef();
            ctStrRef.setF("Sheet1!$A$1:$A$3");
            CTNumDataSource ctNumDataSource = ctPieSer.addNewVal();
            CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
            ctNumRef.setF("Sheet1!$B$1:$B$3");

            // at least the border lines in Libreoffice Calc ;-)

            ctPieSer.addNewSpPr().addNewLn().addNewSolidFill().addNewSrgbClr().setVal(new byte[] { 0, 0, 0 });
        }
    }
}
