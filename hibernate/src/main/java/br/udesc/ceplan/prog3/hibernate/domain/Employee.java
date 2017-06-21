/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceplan.prog3.hibernate.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author uktech
 */
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "emp_name")
    private String empName;

    @Column(name = "emp_address")
    private String empAddress;

    @Column(name = "emp_mobile_nos")
    private String empMobileNos;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the empName
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * @param empName the empName to set
     */
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    /**
     * @return the empAddress
     */
    public String getEmpAddress() {
        return empAddress;
    }

    /**
     * @param empAddress the empAddress to set
     */
    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    /**
     * @return the empMobileNos
     */
    public String getEmpMobileNos() {
        return empMobileNos;
    }

    /**
     * @param empMobileNos the empMobileNos to set
     */
    public void setEmpMobileNos(String empMobileNos) {
        this.empMobileNos = empMobileNos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.empName);
        hash = 67 * hash + Objects.hashCode(this.empAddress);
        hash = 67 * hash + Objects.hashCode(this.empMobileNos);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.empName, other.empName)) {
            return false;
        }
        if (!Objects.equals(this.empAddress, other.empAddress)) {
            return false;
        }
        if (!Objects.equals(this.empMobileNos, other.empMobileNos)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", empName=" + empName + ", empAddress=" + empAddress + ", empMobileNos=" + empMobileNos + '}';
    }
    
}
