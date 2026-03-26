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
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author sb
 */
@Entity
@Table(name = "sales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sales.findAll", query = "SELECT s FROM Sales s"),
    @NamedQuery(name = "Sales.findById", query = "SELECT s FROM Sales s WHERE s.id = :id"),
    @NamedQuery(name = "Sales.findByCropid", query = "SELECT s FROM Sales s WHERE s.cropid = :cropid"),
    @NamedQuery(name = "Sales.findByHarvestid", query = "SELECT s FROM Sales s WHERE s.harvestid = :harvestid"),
    @NamedQuery(name = "Sales.findByQuantitysold", query = "SELECT s FROM Sales s WHERE s.quantitysold = :quantitysold"),
    @NamedQuery(name = "Sales.findByTotalincome", query = "SELECT s FROM Sales s WHERE s.totalincome = :totalincome")})
public class Sales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "cropid")
    private Integer cropid;
    @Column(name = "harvestid")
    private Integer harvestid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantitysold")
    private BigDecimal quantitysold;
    @Column(name = "totalincome")
    private BigDecimal totalincome;

    public Sales() {
    }

    public Sales(Integer id) {
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

    public Integer getHarvestid() {
        return harvestid;
    }

    public void setHarvestid(Integer harvestid) {
        this.harvestid = harvestid;
    }

    public BigDecimal getQuantitysold() {
        return quantitysold;
    }

    public void setQuantitysold(BigDecimal quantitysold) {
        this.quantitysold = quantitysold;
    }

    public BigDecimal getTotalincome() {
        return totalincome;
    }

    public void setTotalincome(BigDecimal totalincome) {
        this.totalincome = totalincome;
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
        if (!(object instanceof Sales)) {
            return false;
        }
        Sales other = (Sales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.farmon.entities.Sales[ id=" + id + " ]";
    }
    
}
