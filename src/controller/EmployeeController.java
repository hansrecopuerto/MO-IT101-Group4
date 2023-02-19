package controller;

import utils.*;
public class EmployeeController {


    public void login() {
        try {
            DataSetCsv db_connection = new DataSetCsv();
            db_connection.read_employee_record();
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }

    public void generate_payroll() {
        try {
            DataSetCsv db_connection = new DataSetCsv();
            db_connection.generate_payroll();
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
}
