package com.ntneik15.selflearning.retailerapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "orders", schema = "public", indexes = {
        @Index(name = "customernumber", columnList = "customernumber")
})
public class Order {
    @Id
    @Column(name = "ordernumber", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "orderdate", nullable = false)
    private LocalDate orderdate;

    @NotNull
    @Column(name = "requireddate", nullable = false)
    private LocalDate requireddate;

    @Column(name = "shippeddate")
    private LocalDate shippeddate;

    @Size(max = 15)
    @NotNull
    @Column(name = "status", nullable = false, length = 15)
    private String status;

    @Column(name = "comments", length = Integer.MAX_VALUE)
    private String comments;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customernumber", nullable = false)
    private Customer customernumber;

    @OneToMany(mappedBy = "ordernumber")
    private Set<Orderdetail> orderdetails = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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