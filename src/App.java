import java.util.Scanner;
import java.util.stream.IntStream;

import controller.EmployeeController;

public class App {

    public static void main(String[] args) throws Exception {

        Scanner username = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter Username");

        String employee_no = username.nextLine();
        Scanner passw = new Scanner(System.in);  // Create a Scanner object
        
        System.out.println("Enter Password");
        String password = passw.nextLine();
        int[] employee_no_array = {10001,10002,10003,10004,10005,10006,10007,10008,10009,10010,10011,10012,10013,10014,10015,10016,10017,10018,10019,10020,10021,10022,10023,10024,10025};
        
        if (!employee_no.equals("")) {
            EmployeeController log_in_object = new EmployeeController();
            if (employee_no.equals("admin")) {
                // condition for generating of all payroll
                log_in_object.generate_payroll(employee_no);
                System.out.println("Payroll Generated in csv Format");
            } else {
                // condition for generating of single payroll
                boolean credential = IntStream.of(employee_no_array).anyMatch(x -> x == Integer.parseInt(employee_no));
                if (credential && password.equals("employee")) {
                    log_in_object.generate_payroll(employee_no);
                    System.out.println("Payroll Generated in csv Format");
                } else {
                    System.out.println("<=================>              Wrong username or password              <=================>\n");
                }
            }
        } else {
            System.out.println("<=================>              Wrong username or password              <=================>\n");
        }
    }
}
