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
@Table(name = "shoprescrop")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shoprescrop.findAll", query = "SELECT s FROM Shoprescrop s"),
    @NamedQuery(name = "Shoprescrop.findById", query = "SELECT s FROM Shoprescrop s WHERE s.id = :id"),
    @NamedQuery(name = "Shoprescrop.findByShopresid", query = "SELECT s FROM Shoprescrop s WHERE s.shopresid = :shopresid"),
    @NamedQuery(name = "Shoprescrop.findByRecropid", query = "SELECT s FROM Shoprescrop s WHERE s.recropid = :recropid"),
    @NamedQuery(name = "Shoprescrop.findByAppliedamt", query = "SELECT s FROM Shoprescrop s WHERE s.appliedamt = :appliedamt")})
public class Shoprescrop implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "shopresid")
    private int shopresid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "recropid")
    private int recropid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "appliedamt")
    private BigDecimal appliedamt;

    public Shoprescrop() {
    }

    public Shoprescrop(Integer id) {
        this.id = id;
    }

    public Shoprescrop(Integer id, int shopresid, int recropid, BigDecimal appliedamt) {
        this.id = id;
        this.shopresid = shopresid;
        this.recropid = recropid;
        this.appliedamt = appliedamt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getShopresid() {
        return shopresid;
    }

    public void setShopresid(int shopresid) {
        this.shopresid = shopresid;
    }

    public int getRecropid() {
        return recropid;
    }

    public void setRecropid(int recropid) {
        this.recropid = recropid;
    }

    public BigDecimal getAppliedamt() {
        return appliedamt;
    }

    public void setAppliedamt(BigDecimal appliedamt) {
        this.appliedamt = appliedamt;
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
        if (!(object instanceof Shoprescrop)) {
            return false;
        }
        Shoprescrop other = (Shoprescrop) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.farmon.entities.Shoprescrop[ id=" + id + " ]";
    }
    
}
