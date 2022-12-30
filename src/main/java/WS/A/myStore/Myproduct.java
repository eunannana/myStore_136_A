/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WS.A.myStore;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author eunannana
 */
@Entity
@Table(name = "myproduct")
@NamedQueries({
    @NamedQuery(name = "Myproduct.findAll", query = "SELECT m FROM Myproduct m"),
    @NamedQuery(name = "Myproduct.findByProductID", query = "SELECT m FROM Myproduct m WHERE m.productID = :productID"),
    @NamedQuery(name = "Myproduct.findByProductName", query = "SELECT m FROM Myproduct m WHERE m.productName = :productName"),
    @NamedQuery(name = "Myproduct.findByQty", query = "SELECT m FROM Myproduct m WHERE m.qty = :qty")})
public class Myproduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ProductID")
    private Integer productID;
    @Column(name = "ProductName")
    private String productName;
    @Column(name = "Qty")
    private Integer qty;

    public Myproduct() {
    }

    public Myproduct(Integer productID) {
        this.productID = productID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productID != null ? productID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Myproduct)) {
            return false;
        }
        Myproduct other = (Myproduct) object;
        if ((this.productID == null && other.productID != null) || (this.productID != null && !this.productID.equals(other.productID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WS.A.myStore.Myproduct[ productID=" + productID + " ]";
    }
    
}
