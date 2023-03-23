package com.ntneik15.selflearning.retailerapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "employees", schema = "public", indexes = {
        @Index(name = "officecode", columnList = "officecode"),
        @Index(name = "reportsto", columnList = "reportsto")
})
public class Employee {
    @Id
    @Column(name = "employeenumber", nullable = false)
    private Long employeenumber;

    @Size(max = 50)
    @NotNull
    @Column(name = "lastname", nullable = false, length = 50)
    private String lastname;

    @Size(max = 50)
    @NotNull
    @Column(name = "firstname", nullable = false, length = 50)
    private String firstname;

    @Size(max = 10)
    @NotNull
    @Column(name = "extension", nullable = false, length = 10)
    private String extension;

    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "officecode", nullable = false)
    private Office officecode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reportsto")
    private Employee reportsto;

    @Size(max = 50)
    @NotNull
    @Column(name = "jobtitle", nullable = false, length = 50)
    private String jobtitle;

    @OneToMany(mappedBy = "salesrepemployeenumber")
    private Set<Customer> customers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "reportsto")
    private Set<Employee> employees = new LinkedHashSet<>();

    public Long getEmployeenumber() {
        return employeenumber;
    }

    public void setEmployeenumber(Long employeenumber) {
        this.employeenumber = employeenumber;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Office getOfficecode() {
        return officecode;
    }

    public void setOfficecode(Office officecode) {
        this.officecode = officecode;
    }

    public Employee getReportsto() {
        return reportsto;
    }

    public void setReportsto(Employee reportsto) {
        this.reportsto = reportsto;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

}