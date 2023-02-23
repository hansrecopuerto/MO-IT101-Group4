package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.stream.IntStream;

import com.opencsv.CSVWriter;

import model.EmployeeObject;



public class CsvWriter {
    
    public static void writeDataLineByLine(EmployeeObject[] employeeObjects, String employee_no)
    {
        // first create file object for file placed at location
        // specified by filepath
        String filePath = "";
        try {
            for (EmployeeObject  employeeObject: employeeObjects) {
                    
                if (employeeObject == null) {
                    break;
                }
                if (employee_no.equals(employeeObject.employee_no)) {
                    filePath = "motorph/csv_payroll/admin/";
                    generate_csv(filePath, employeeObject, employee_no);
                    break;
                } else if(employee_no.equals("admin")) {
                    filePath = "motorph/csv_payroll/employee/";
                    generate_csv(filePath, employeeObject, employee_no);
                }
            }
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void generate_csv(String filePath, EmployeeObject employeeObject, String employee_no)throws IOException {

        System.out.println(employeeObject.first_name);
        File file = new File(filePath + employeeObject.first_name + ".csv");
        // create FileWriter object with file as parameter
        FileWriter outputfile = new FileWriter(file);
        // create CSVWriter object filewriter object as parameter
        CSVWriter writer = new CSVWriter(outputfile);
        // adding header to csv
        String[] header = { "employee_no", "First name", "Last name", "Birthdate", "Basic Salary", "Clothing Allowance", "Phone Allowance", "Rice Subsidy", "Hourly Rate"};
        writer.writeNext(header);

        String[] next_line = {};
        String[] Data = {employeeObject.employee_no, employeeObject.first_name, employeeObject.last_name, employeeObject.birthdate, String.valueOf(employeeObject.basic_salary), String.valueOf(employeeObject.clothing_allowance), String.valueOf(employeeObject.phone_allowance), String.valueOf(employeeObject.rice_subsidy), String.valueOf(employeeObject.hourly_rate)};
        writer.writeNext(Data);
        IntStream.range(0, 3).forEachOrdered(n -> {
            writer.writeNext(next_line);
        });
        String[] header1 = { "Weekly Date", "Pagibig Deduction", "SSS Deduction", "Philhealth Deduction", "Withholding Tax", "Gross Salary", "Net Salary", };
        writer.writeNext(header1);
        Double net_salary = 0.00;
        Double gross_salary_with_allowance = 0.00;
        Double total_deduction = 0.00;
        Enumeration e = employeeObject.gross_salary_object.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            
            if (employeeObject.sss_deduction_object.getProperty(key) != null) {
                gross_salary_with_allowance = Double.parseDouble(employeeObject.gross_salary_object.getProperty(key))  + employeeObject.clothing_allowance + employeeObject.rice_subsidy + employeeObject.phone_allowance;
                total_deduction = Double.parseDouble(employeeObject.pag_ibig_deduction_object.getProperty(key)) + Double.parseDouble(employeeObject.sss_deduction_object.getProperty(key)) + Double.parseDouble(employeeObject.philhealth_deduction_object.getProperty(key)) + Double.parseDouble(employeeObject.withholding_tax_deduction_object.getProperty(key));
                net_salary =  gross_salary_with_allowance - total_deduction;
            } else {
                gross_salary_with_allowance = Double.parseDouble(employeeObject.gross_salary_object.getProperty(key));
                net_salary = Double.parseDouble(employeeObject.gross_salary_object.getProperty(key));
            }
            
            String[] Data1 = {key, employeeObject.pag_ibig_deduction_object.getProperty(key), employeeObject.sss_deduction_object.getProperty(key), employeeObject.philhealth_deduction_object.getProperty(key), employeeObject.withholding_tax_deduction_object.getProperty(key), String.valueOf(gross_salary_with_allowance), String.valueOf(net_salary)};
            writer.writeNext(Data1);
        }
        writer.close();
        // break;
    }
}
