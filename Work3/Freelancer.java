package ru.geekbrains.lesson3;

import java.util.ArrayList;
import java.util.List;

public class Freelancer extends Employee{

    private Freelancer (String surName, String name, double salary){
        
        super(surName, name, salary);
        
    }

        public static Employee getInstance(){
            return new Freelancer(
                    surNames[random.nextInt(surNames.length)],
                    names[random.nextInt(surNames.length)],
                    random.nextInt(1, 50) * 20.8 * 8);
        }

        public static List<Employee> getEmployeesFreelancer(int count){
            List<Employee> freelance = new ArrayList<>();
            for (int i = 0; i < count; i++)
                freelance.add(getInstance());
            return freelance;
        }

    @Override
    public double calculateSalary() {
        return 2.8 * 8 * salary;
    }
    
    @Override
    public String toString() {
        return String.format("%s %s; Фрилансер. Почасовая оплата: %.2f (руб.)",
                surName, name, calculateSalary());
    }
}
