/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceplan.prog3.hibernate;

import br.udesc.ceplan.prog3.hibernate.domain.Employee;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author uktech
 */
public class ExemploHibernate {

    private static final Logger logger = LoggerFactory.getLogger(ExemploHibernate.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        SessionFactory sessFact = NewHibernateUtil.getSessionFactory();
        Session session = sessFact.getCurrentSession();
        org.hibernate.Transaction tr = session.beginTransaction();
        Employee emp;
        
        emp = new Employee();
        emp.setEmpName("TESTE 01");
        emp.setEmpMobileNos("11111111111");
        emp.setEmpAddress("ENDEREÇO TESTE 01");
        session.save(emp);
        
        emp = new Employee();
        emp.setEmpName("TESTE 02");
        emp.setEmpMobileNos("22222222222");
        emp.setEmpAddress("ENDEREÇO TESTE 02");
        session.save(emp);
        
        emp = new Employee();
        emp.setEmpName("TESTE 03");
        emp.setEmpMobileNos("333333333333");
        emp.setEmpAddress("ENDEREÇO TESTE 03");
        session.save(emp);
        
        tr.commit();
        System.out.println("Successfully inserted");

        printData();

        sessFact.close();
    }

    public static void printData() throws Exception {

        SessionFactory sessFact = NewHibernateUtil.getSessionFactory();
        Session session = sessFact.getCurrentSession();
        org.hibernate.Transaction tr = session.beginTransaction();

        CriteriaQuery cq = session.getCriteriaBuilder().createQuery(Employee.class);
        cq.from(Employee.class);
        List<Employee> employeeList = session.createQuery(cq).getResultList();

        for (Employee employee : employeeList) {
            logger.info("ID: " + employee.getId());
            logger.info("Name: " + employee.getEmpName());
        }

        tr.commit();
        System.out.println("Data printed");

    }

}
