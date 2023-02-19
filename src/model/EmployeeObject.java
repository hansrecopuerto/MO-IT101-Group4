package model;

import java.util.Properties;

public class EmployeeObject {
    public Double hourly_rate = 0.00;
    public Double rice_subsidy = 0.00;
    public Double phone_allowance = 0.00;
    public Double clothing_allowance = 0.00;
    public Double basic_salary = 0.00;
    public Properties net_salary_object = new Properties();
    public Properties gross_salary_object = new Properties();
    public Properties pag_ibig_deduction_object = new Properties();
    public Properties philhealth_deduction_object = new Properties();
    public Properties sss_deduction_object =new Properties();
    public Properties withholding_tax_deduction_object = new Properties();
    public String employee_no = "0";
    public String last_name = "";
    public String first_name = "";
    public String birthdate = "";

    public void setData(Double hourly_rate, Double rice_subsidy, Double phone_allowance, Double clothing_allowance,Double basic_salary)
    {
        this.hourly_rate = hourly_rate;
        this.rice_subsidy = rice_subsidy;
        this.phone_allowance = phone_allowance;
        this.clothing_allowance = clothing_allowance;
        this.basic_salary = basic_salary;
    }

}