package com.ntneik15.selflearning.retailerapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
@Table(name = "productlines", schema = "public")
@NoArgsConstructor
public class Productline {
    @Id
    @Size(max = 50)
    @Column(name = "productline", nullable = false, length = 50)
    private String productline;

    @Size(max = 4000)
    @Column(name = "textdescription", length = 4000)
    private String textdescription;

    @Column(name = "htmldescription", length = Integer.MAX_VALUE)
    private String htmldescription;

    @Column(name = "image")
    private byte[] image;

    @OneToMany(mappedBy = "productline")
    private Set<Product> products = new LinkedHashSet<>();

    public String getProductline() {
        return productline;
    }

    public void setProductline(String productline) {
        this.productline = productline;
    }

    public String getTextdescription() {
        return textdescription;
    }

    public void setTextdescription(String textdescription) {
        this.textdescription = textdescription;
    }

    public String getHtmldescription() {
        return htmldescription;
    }

    public void setHtmldescription(String htmldescription) {
        this.htmldescription = htmldescription;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

}