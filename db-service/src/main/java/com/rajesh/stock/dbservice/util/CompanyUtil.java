package com.rajesh.stock.dbservice.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.rajesh.stock.dbservice.model.Company;

@Service
public class CompanyUtil {
	
	private static String[] HEADERS = { "Symbol", "Name", "Sector"};
	
	public List<Company> companyList() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("companylist.csv").getFile());
		Reader in = new FileReader(file);
	    Iterable<CSVRecord> records = CSVFormat.DEFAULT
	      .withHeader(HEADERS)
	      .withFirstRecordAsHeader()
	      .parse(in);
	    List<Company> companyList=new ArrayList<Company>();
	    for (CSVRecord record : records) {
			companyList.add(new Company(record.get("Symbol"), record.get("Name"), record.get("Sector")));
	    }
	    return companyList;
	}
}
