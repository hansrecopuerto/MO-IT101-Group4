package controller;

import utils.*;
public class EmployeeController {


    public void generate_payroll(String employee_no) {
        try {
            DataSetCsv db_connection = new DataSetCsv();
            db_connection.generate_payroll(employee_no);
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
}
