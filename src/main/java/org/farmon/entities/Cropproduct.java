/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.farmon.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author sb
 */
@Entity
@Table(name = "cropproduct")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cropproduct.findAll", query = "SELECT c FROM Cropproduct c"),
    @NamedQuery(name = "Cropproduct.findById", query = "SELECT c FROM Cropproduct c WHERE c.id = :id"),
    @NamedQuery(name = "Cropproduct.findByCropid", query = "SELECT c FROM Cropproduct c WHERE c.cropid = :cropid"),
    @NamedQuery(name = "Cropproduct.findByProductid", query = "SELECT c FROM Cropproduct c WHERE c.productid = :productid"),
    @NamedQuery(name = "Cropproduct.findByProductname", query = "SELECT c FROM Cropproduct c WHERE c.productname = :productname"),
    @NamedQuery(name = "Cropproduct.findByTotalstock", query = "SELECT c FROM Cropproduct c WHERE c.totalstock = :totalstock"),
    @NamedQuery(name = "Cropproduct.findByUnit", query = "SELECT c FROM Cropproduct c WHERE c.unit = :unit")})
public class Cropproduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "cropid")
    private Integer cropid;
    @Column(name = "productid")
    private Integer productid;
    @Size(max = 100)
    @Column(name = "productname")
    private String productname;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalstock")
    private BigDecimal totalstock;
    @Size(max = 50)
    @Column(name = "unit")
    private String unit;

    public Cropproduct() {
    }

    public Cropproduct(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCropid() {
        return cropid;
    }

    public void setCropid(Integer cropid) {
        this.cropid = cropid;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public BigDecimal getTotalstock() {
        return totalstock;
    }

    public void setTotalstock(BigDecimal totalstock) {
        this.totalstock = totalstock;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cropproduct)) {
            return false;
        }
        Cropproduct other = (Cropproduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.farmon.entities.Cropproduct[ id=" + id + " ]";
    }
    
}
