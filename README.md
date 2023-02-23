## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).




## How to run this app?


You can Generate payroll CSV using admin and invidual credentials.

### Generate Payroll using admin credential

 You can use ```username: admin``` and ```password: admin``` to generate all csv payroll of each employee.
 
 The generated csv file will be found in ```motorph/csv_payroll/admin/```.
 
 Sample Result:
 ```
 Enter Username
admin
Enter Password
admin
Jose
Christian
Brad 
Anthony
Alice
Rosie 
Martha
Leila
Allison 
Cydney 
Josie 
Selena 
Fredrick 
Mark 
Darlene 
Kolby 
Vella 
Tomas
Jacklyn 
Percival 
Garfield 
Lizeth 
Carol 
Emelia 
Delia 
Payroll Generated in csv Format
```
 
 ### Generate Payroll using Individual employee
 
 You can use ```username: employee_no``` and ```password: employee``` to generate csv for specific employee.
 
 Sample Result:
 ```
 Enter Username
10001
Enter Password
employee
Jose
Payroll Generated in csv Format
```