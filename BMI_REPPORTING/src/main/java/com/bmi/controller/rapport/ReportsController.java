package com.bmi.controller.rapport;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.bmi.rapport.entity.*;
import com.bmi.rapport.repository.*;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.*;
import net.sf.jasperreports.export.*;

@Controller
public class ReportsController {
	@Autowired
	BmiequRepository bmiequRepository;
	@Autowired
	ApplicationContext context;

	/*
	 * // @GetMapping(path = "pdf/{jrxml}")
	 * 
	 * @GetMapping(path = "/rapports/pdf")
	 * 
	 * @ResponseBody // public void getPdf(@PathVariable String jrxml,
	 * HttpServletResponse response) throws Exception { public void
	 * getPdf(HttpServletResponse response) throws Exception { //Get JRXML template
	 * from resources folder // Resource resource =
	 * context.getResource("classpath:reports/" + jrxml + ".jrxml"); Resource
	 * resource = context.getResource("classpath:reports/EQU.jrxml"); //Compile to
	 * jasperReport InputStream inputStream = resource.getInputStream();
	 * JasperReport report = JasperCompileManager.compileReport(inputStream);
	 * 
	 * //Parameters Set Map<String, Object> params = new HashMap<>();
	 * 
	 * List<Bmiequ> cars = (List<Bmiequ>) bmiequRepository.findAll();
	 * 
	 * //Data source Set JRDataSource dataSource = new
	 * JRBeanCollectionDataSource(cars,true); params.put("datasource", dataSource);
	 * 
	 * //Make jasperPrint JasperPrint jasperPrint =
	 * JasperFillManager.fillReport(report, null, dataSource); //Media Type
	 * response.setContentType(MediaType.APPLICATION_PDF_VALUE); //Export PDF Stream
	 * JasperExportManager.exportReportToPdfStream(jasperPrint,
	 * response.getOutputStream()); }
	 */

//    @GetMapping(path = "pdf/{jrxml}")
	@GetMapping(path = "/rapports/pdf")
	@ResponseBody
//	    public void getPdf(@PathVariable String jrxml, HttpServletResponse response) throws Exception {
	public void getPdf(HttpServletResponse response) throws Exception {
		// Get JRXML template from resources folder
//	        Resource resource = context.getResource("classpath:reports/" + jrxml + ".jrxml");
		Resource resource = context.getResource("classpath:reports/EQU.jrxml");
		// Compile to jasperReport
		InputStream inputStream = resource.getInputStream();
		JasperReport report = JasperCompileManager.compileReport(inputStream);
		double total = bmiequRepository.findbyTotal();
		// Parameters Set
		Map<String, Object> params = new HashMap<>();
		System.out.println(total);
		params.put("Total", total);
		List<Bmiequ> cars = (List<Bmiequ>) bmiequRepository.findAll();
		// Data source Set

		JRDataSource dataSource = new JRBeanCollectionDataSource(cars);
		// System.out.println(dataSource.));

		// Make jasperPrint
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
		// Media Type
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		// Export PDF Stream
		JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
	}

	Statement stmt = null;
	ResultSet resultset = null;
	ResultSet resultset1 = null;
	Connection con = null;
	Connection con1 = null;
	int unitId = 1;
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String connectionurl = "jdbc:sqlserver://pfe2019.hopto.org;databaseName=MAINTA_TEST?user=sa&password=Admin123";
	String connection = "jdbc:sqlserver://pfe2019.hopto.org:1433;"
			+ "databaseName=MAINTA_TEST;user=sa;password=Admin123;";
	List<Object> shortnamelist = new ArrayList<Object>();

	@GetMapping(path = "/rapports/rpdf")
	@ResponseBody

	public void getrPdf(HttpServletResponse response) throws Exception {

		try {
			String selectstatement = "select * from VPER_BMIEQU ";
			Class.forName(driver);
			con1 = DriverManager.getConnection(connection);
			stmt = con1.createStatement();
			resultset = stmt.executeQuery(selectstatement);
			JRResultSetDataSource resultsetdatasource = new JRResultSetDataSource(resultset);

			String selectstatement1 = "select dbo.totale()";
			Class.forName(driver);
			con = DriverManager.getConnection(connection);
			stmt = con.createStatement();
			resultset1 = stmt.executeQuery(selectstatement1);
			resultset1.next();
			double total = resultset1.getDouble(1);

			/*
			 * while (resultset.next()) { double shortname =
			 * resultset.getDouble("Total_cout"); System.out.println("Total_cout" +
			 * shortname); double workamount =s; System.out.println("Total" + workamount);
			 * shortnamelist.add(shortname); shortnamelist.add(workamount); }
			 */

			// System.out.println("shortnamelist" + shortnamelist.size());

			Map<String, Object> hashmap = new HashMap<String, Object>();
			hashmap.put("Total", total);
			// String realpath =
			// FacesContext.getCurrentInstance().getExternalContext().getRealPath("common/reports/registerofwages.jasper");
			Resource resource = context.getResource("classpath:reports/EQU2.jrxml");
			InputStream inputStream = resource.getInputStream();
			JasperReport report = JasperCompileManager.compileReport(inputStream);
			JasperPrint jasperprint = JasperFillManager.fillReport(report, hashmap, con1);

			try {
				/*
				 * final Exporter exporter;//begin html export exporter = new HtmlExporter();
				 * exporter.setExporterOutput(new
				 * SimpleHtmlExporterOutput(response.getOutputStream()));
				 * exporter.setExporterInput(new SimpleExporterInput(jasperprint));
				 * exporter.exportReport();//end hml export
				 */
				JasperExportManager.exportReportToPdfStream(jasperprint, response.getOutputStream());
			} catch (JRException ex) {
				ex.getMessage();
			}
		} catch (net.sf.jasperreports.engine.JRException JRexception) {
			System.out.println("JRException Exception" + JRexception.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close(stmt);
			// close(resultset);
			// close(con);
		}
	}

}
