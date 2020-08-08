package com.izhengyin.test.java9feature.flow.item1;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-08-08 12:13
 */
import java.util.ArrayList;
import java.util.List;
public class EmpHelper {
    public static List<Employee> getEmps() {
        Employee e1 = new Employee(1, "Pankaj");
        Employee e2 = new Employee(2, "David");
        Employee e3 = new Employee(3, "Lisa");
        Employee e4 = new Employee(4, "Ram");
        Employee e5 = new Employee(5, "Anupam");
        List<Employee> emps = new ArrayList<>();
        emps.add(e1);
        emps.add(e2);
        emps.add(e3);
        emps.add(e4);
        emps.add(e5);
        for (int i=6;i<10;i++){
            emps.add(new Employee(i, "xxx-"+i));
        }

        return emps;
    }

}