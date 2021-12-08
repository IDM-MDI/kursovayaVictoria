package course.by.zhukova.entity;

import javax.persistence.*;

@Entity
@Table(name = "cart", schema = "shoes_shop", catalog = "")
public class CartEntity
{
    private Integer idcart;
    private Integer cartUserId;

    @Id
    @Column(name = "idcart", nullable = false)
    public Integer getIdcart()
    {
        return idcart;
    }

    public void setIdcart(Integer idcart)
    {
        this.idcart = idcart;
    }

    @Basic
    @Column(name = "cartUserID", nullable = false)
    public Integer getCartUserId()
    {
        return cartUserId;
    }

    public void setCartUserId(Integer cartUserId)
    {
        this.cartUserId = cartUserId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartEntity that = (CartEntity) o;

        if (idcart != null ? !idcart.equals(that.idcart) : that.idcart != null) return false;
        if (cartUserId != null ? !cartUserId.equals(that.cartUserId) : that.cartUserId != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = idcart != null ? idcart.hashCode() : 0;
        result = 31 * result + (cartUserId != null ? cartUserId.hashCode() : 0);
        return result;
    }
}
