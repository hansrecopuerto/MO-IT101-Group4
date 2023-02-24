package utils;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;
import java.util.Enumeration;
import model.*;
import utils.csvWriter;

public class DataSetCsv {

    

    public EmployeeObject[] generate_payroll(String employee_no) throws Exception {
        EmployeeObject[] employeeObjects = new EmployeeObject[50];
        employeeObjects = read_employee_record(employeeObjects, employee_no);
        return employeeObjects;
        // return read_attendance(employeeObjects);
    }

    public EmployeeObject[] read_employee_record(EmployeeObject[] employeeObjects, String employee_no) throws Exception {
        
        try {
            Scanner sc = new Scanner(new File("MO-IT101-Group4/src/utils/employee_details.csv"));
            Integer index = 0;
            sc.useDelimiter(",");
            sc.nextLine();
            String[] employee_records = new String[30];

            while (sc.hasNext()) {
                    employee_records = sc.nextLine().split(",");
                    employeeObjects[index] = new EmployeeObject();
                    
                    employeeObjects[index].employee_no = employee_records[0];
                    employeeObjects[index].setData(
                        Double.parseDouble(employee_records[18]),
                        Double.parseDouble(employee_records[14]),
                        Double.parseDouble(employee_records[15]),
                        Double.parseDouble(employee_records[16]),
                        Double.parseDouble(employee_records[13]));
                    employeeObjects[index].last_name = employee_records[1];
                    employeeObjects[index].first_name = employee_records[2];
                    employeeObjects[index].birthdate = employee_records[3];
                    
                    Scanner sc_attance_record = new Scanner(new File("MO-IT101-Group4/src/utils/attendance_record.csv"));
                    int month = 8;
                    int day = 5;

                    //parsing a CSV file into the constructor of Scanner class 
                    sc_attance_record.useDelimiter(",");
                    String[] employee_attendance = new String[2300];
                    Double salary = 0.00;
                    sc_attance_record.nextLine();
                    
                    while (sc_attance_record.hasNext()) {

                        employee_attendance = sc_attance_record.nextLine().split(",");
                        // employee_attendance_object.setProperty(employee_attendance[3], employee_attendance[23]);
                        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder()
                        .parseCaseInsensitive().parseLenient()
                        .appendPattern("[d-M-yyyy]")
                        .appendPattern("[d-MM-yyyy]")
                        .appendPattern("[dd-M-yyyy]")
                        .appendPattern("[dd-MM-yyyy]");
                        DateTimeFormatter formatter = builder.toFormatter(Locale.ENGLISH);
                        LocalDate date = LocalDate.parse(employee_attendance[3], formatter);

                        // String dayWeekText = new SimpleDateFormat("EEEE").format(date);
                        String[] am_time = employee_attendance[4].split(":");

                        Integer am_time_int = Integer.parseInt(am_time[1]);
                        
                        if (employee_attendance[0].equals(employee_records[0])) {
                            if (employee_attendance[4].equals("8:00") && employee_attendance[5].equals("17:00")) {
                                
                                salary = salary + (employeeObjects[index].hourly_rate * 8);

                            } else if(am_time_int > 0 ) { // condition to check if late time in
                                salary += (employeeObjects[index].hourly_rate / 60) * am_time_int;
                            }
                            
                            if (String.valueOf(date.getDayOfWeek()).equals("FRIDAY")) {
                                employeeObjects[index].gross_salary_object.setProperty(String.valueOf(date).replace("0022", "2022"), String.valueOf(salary));
                                salary = 0.00;
                                
                                if (month != date.getMonthValue() && day < date.getDayOfMonth()) {
                                            SssBracket sss_obj = new SssBracket();
                                            Properties props = sss_obj.init_prop();
                                            Enumeration e = props.propertyNames();
                                            String[] sss_bracket = new String[1];
                                            while (e.hasMoreElements()) {
                                                String key = (String) e.nextElement();
                                                sss_bracket = key.split("-");
                                                if (Double.parseDouble(sss_bracket[0]) <= employeeObjects[index].basic_salary && Double.parseDouble(sss_bracket[1]) >= employeeObjects[index].basic_salary) {
                                                    employeeObjects[index].sss_deduction_object.setProperty(String.valueOf(date).replace("0022", "2022"), String.valueOf(Double.parseDouble(props.getProperty(key))));
                                                    break;
                                                }
                                            }
                                            employeeObjects[index].philhealth_deduction_object.setProperty(String.valueOf(date).replace("0022", "2022"), String.valueOf((employeeObjects[index].basic_salary * 3 / 100)));
                                            employeeObjects[index].pag_ibig_deduction_object.setProperty(String.valueOf(date).replace("0022", "2022"), String.valueOf((employeeObjects[index].basic_salary * 2 / 100)));
                                            
                                            
                                            WithholdingTax withholding_tax_obj = new WithholdingTax();
                                            Properties withholding_tax_obj_props = withholding_tax_obj.init_prop();
                                            Enumeration withholding_tax_enum = withholding_tax_obj_props.propertyNames();
                                            String[] withholding_tax_arr = new String[1];
                                            String[] withholding_tax_value = new String[1];
                                            while (withholding_tax_enum.hasMoreElements()) {
                                                String key2 = (String) withholding_tax_enum.nextElement();
                                                withholding_tax_arr = key2.split("-");
                                                // System.out.println(Double.parseDouble(withholding_tax_arr[0]) <= employeeObjects[index].basic_salary);
                                                // System.out.println(Double.parseDouble(withholding_tax_arr[1]) >= employeeObjects[index].basic_salary);
                                                // System.out.println(Double.parseDouble(withholding_tax_arr[0]));
                                                // System.out.println(Double.parseDouble(withholding_tax_arr[1]));
                                                // System.out.println(employeeObjects[index].basic_salary);
                                                
                                                if (Double.parseDouble(withholding_tax_arr[0]) <= employeeObjects[index].basic_salary && Double.parseDouble(withholding_tax_arr[1]) >= employeeObjects[index].basic_salary) {
                                                    
                                                    String tax_rate_and_amount_percentage = withholding_tax_obj_props.getProperty(key2);
                                                    withholding_tax_value = tax_rate_and_amount_percentage.split("-");
                                                    Double tax_amount = 0.00;
                                                    Double tax_amount2 = 0.00;
                                                    if (Integer.parseInt(withholding_tax_value[1]) != 0) {
                                                        tax_amount = Double.parseDouble(withholding_tax_value[1]);
                                                    }

                                                    if (Integer.parseInt(withholding_tax_value[0]) != 0) {
                                                        
                                                        tax_amount2 = employeeObjects[index].basic_salary - Double.parseDouble(withholding_tax_arr[0]);
                                                        
                                                        tax_amount += (tax_amount2 * Integer.parseInt(withholding_tax_value[0]) / 100);
                                                    }
                                                    employeeObjects[index].withholding_tax_deduction_object.setProperty(String.valueOf(date).replace("0022", "2022"), String.valueOf(tax_amount));
                                                    
                                                }
                                            }
                                month = date.getMonthValue();
                                day = date.getDayOfMonth();
                                // System.out.println(employeeObjects[index].sss_deduction_object.getProperty(String.valueOf(date).replace("0022", "2022")));
                                // System.out.println(employeeObjects[index].gross_salary_object.getProperty(String.valueOf(date).replace("0022", "2022")));
                                // System.out.println(String.valueOf(date).replace("0022", "2022"));
                                }
                            }
                        }
                    }
                    sc_attance_record.close();
                    
                    index++;
            }
            sc.close();
             CsvWriter csv_write = new CsvWriter();
            csv_write.writeDataLineByLine(employeeObjects, employee_no);
            // System.out.print(props.getProperty("10002"));
        
        } catch(Exception e) {
              System.out.println(e);
              e.printStackTrace();  // This will give line number
        }
        return employeeObjects;
    }
}
