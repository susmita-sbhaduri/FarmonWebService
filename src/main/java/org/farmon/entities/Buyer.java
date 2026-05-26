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

/**
 *
 * @author sb
 */
@Entity
@Table(name = "buyer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Buyer.findAll", query = "SELECT b FROM Buyer b"),
    @NamedQuery(name = "Buyer.findByBuyerid", query = "SELECT b FROM Buyer b WHERE b.buyerid = :buyerid"),
    @NamedQuery(name = "Buyer.findByBuyername", query = "SELECT b FROM Buyer b WHERE b.buyername = :buyername"),
    @NamedQuery(name = "Buyer.findByLocation", query = "SELECT b FROM Buyer b WHERE b.location = :location"),
    @NamedQuery(name = "Buyer.findByContact", query = "SELECT b FROM Buyer b WHERE b.contact = :contact"),
    @NamedQuery(name = "Buyer.findByAvailabilitytime", query = "SELECT b FROM Buyer b WHERE b.availabilitytime = :availabilitytime")})
public class Buyer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "buyerid")
    private Integer buyerid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "buyername")
    private String buyername;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "location")
    private String location;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "contact")
    private String contact;
    @Size(max = 100)
    @Column(name = "availabilitytime")
    private String availabilitytime;

    public Buyer() {
    }

    public Buyer(Integer buyerid) {
        this.buyerid = buyerid;
    }

    public Buyer(Integer buyerid, String buyername, String location, String contact) {
        this.buyerid = buyerid;
        this.buyername = buyername;
        this.location = location;
        this.contact = contact;
    }

    public Integer getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(Integer buyerid) {
        this.buyerid = buyerid;
    }

    public String getBuyername() {
        return buyername;
    }

    public void setBuyername(String buyername) {
        this.buyername = buyername;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAvailabilitytime() {
        return availabilitytime;
    }

    public void setAvailabilitytime(String availabilitytime) {
        this.availabilitytime = availabilitytime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (buyerid != null ? buyerid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Buyer)) {
            return false;
        }
        Buyer other = (Buyer) object;
        if ((this.buyerid == null && other.buyerid != null) || (this.buyerid != null && !this.buyerid.equals(other.buyerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.farmon.entities.Buyer[ buyerid=" + buyerid + " ]";
    }
    
}
