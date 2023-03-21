package com.ntneik15.selflearning.retailerapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "customers", schema = "public", indexes = {
        @Index(name = "salesrepemployeenumber", columnList = "salesrepemployeenumber")
})
public class Customer {
    @Id
    @Column(name = "customernumber", nullable = false)
    private Long customernumber;

    @Size(max = 50)
    @NotNull
    @Column(name = "customername", nullable = false, length = 50)
    private String customername;

    @Size(max = 50)
    @NotNull
    @Column(name = "contactlastname", nullable = false, length = 50)
    private String contactlastname;

    @Size(max = 50)
    @NotNull
    @Column(name = "contactfirstname", nullable = false, length = 50)
    private String contactfirstname;

    @Size(max = 50)
    @NotNull
    @Column(name = "phone", nullable = false, length = 50)
    private String phone;

    @Size(max = 50)
    @NotNull
    @Column(name = "addressline1", nullable = false, length = 50)
    private String addressline1;

    @Size(max = 50)
    @Column(name = "addressline2", length = 50)
    private String addressline2;

    @Size(max = 50)
    @NotNull
    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Size(max = 50)
    @Column(name = "state", length = 50)
    private String state;

    @Size(max = 15)
    @Column(name = "postalcode", length = 15)
    private String postalcode;

    @Size(max = 50)
    @NotNull
    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salesrepemployeenumber")
    private Employee salesrepemployeenumber;

    @Column(name = "creditlimit", precision = 10, scale = 2)
    private BigDecimal creditlimit;

    @OneToMany(mappedBy = "customernumber")
    private Set<Payment> payments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "customernumber")
    private Set<Order> orders = new LinkedHashSet<>();

    public Long getCustomernumber() {
        return customernumber;
    }

    public void setCustomernumber(Long customernumber) {
        this.customernumber = customernumber;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getContactlastname() {
        return contactlastname;
    }

    public void setContactlastname(String contactlastname) {
        this.contactlastname = contactlastname;
    }

    public String getContactfirstname() {
        return contactfirstname;
    }

    public void setContactfirstname(String contactfirstname) {
        this.contactfirstname = contactfirstname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Employee getSalesrepemployeenumber() {
        return salesrepemployeenumber;
    }

    public void setSalesrepemployeenumber(Employee salesrepemployeenumber) {
        this.salesrepemployeenumber = salesrepemployeenumber;
    }

    public BigDecimal getCreditlimit() {
        return creditlimit;
    }

    public void setCreditlimit(BigDecimal creditlimit) {
        this.creditlimit = creditlimit;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

}