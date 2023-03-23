package com.ntneik15.selflearning.retailerapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "orders", schema = "public", indexes = {
        @Index(name = "customernumber", columnList = "customernumber")
})
public class Order {
    @Id
    @Column(name = "ordernumber", nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordernumber;

    @Column(name = "orderdate", nullable = false)
    private LocalDate orderdate;

    @Column(name = "requireddate", nullable = false)
    private LocalDate requireddate;

    @Column(name = "shippeddate")
    private LocalDate shippeddate;


    @Column(name = "status", nullable = false, length = 15)
    private String status;

    @Column(name = "comments", length = Integer.MAX_VALUE)
    private String comments;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customernumber", nullable = false)
    private Customer customernumber;

    @OneToMany(mappedBy = "ordernumber")
    private Set<Orderdetail> orderdetails = new LinkedHashSet<>();

    public Long getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(Long ordernumber) {
        this.ordernumber = ordernumber;
    }

    public LocalDate getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(LocalDate orderdate) {
        this.orderdate = orderdate;
    }

    public LocalDate getRequireddate() {
        return requireddate;
    }

    public void setRequireddate(LocalDate requireddate) {
        this.requireddate = requireddate;
    }

    public LocalDate getShippeddate() {
        return shippeddate;
    }

    public void setShippeddate(LocalDate shippeddate) {
        this.shippeddate = shippeddate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Customer getCustomernumber() {
        return customernumber;
    }

    public void setCustomernumber(Customer customernumber) {
        this.customernumber = customernumber;
    }

    public Set<Orderdetail> getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(Set<Orderdetail> orderdetails) {
        this.orderdetails = orderdetails;
    }

}