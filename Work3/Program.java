package ru.geekbrains.lesson3;

import java.util.Collections;
import java.util.List;


public class Program {

    public static void main(String[] args) {

        List<Employee> worker = Worker.getEmployees(5);
        List<Employee> freelance = Freelancer.getEmployeesFreelancer(5);
        
        
        System.out.println("Наемные рабочие: ");
        for (Employee employee: worker) {
            System.out.println(employee);
        }

            Collections.sort(worker, new EmployeeNameComparator());
            System.out.println();
                for (Employee employee : worker) {
                    System.out.println(employee);    
            }
            System.out.println();
            
        System.out.println("Фрилансеры: " );
        for (Employee employee: freelance){
            System.out.println(employee);
        }
            Collections.sort(freelance, new EmployeeNameComparator());
            System.out.println();
                for (Employee employee : freelance) {
                    System.out.println(employee);              
                }

        
    }
} 

