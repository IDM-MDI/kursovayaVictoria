package course.by.zhukova.entity;

import javax.persistence.*;

@Entity
@Table(name = "orders", schema = "shoes_shop", catalog = "")
public class OrdersEntity
{
    private Integer idorder;
    private Integer orderCartId;
    private Integer orderProductId;

    @Id
    @Column(name = "idorder", nullable = false)
    public Integer getIdorder()
    {
        return idorder;
    }

    public void setIdorder(Integer idorder)
    {
        this.idorder = idorder;
    }

    @Basic
    @Column(name = "orderCartID", nullable = false)
    public Integer getOrderCartId()
    {
        return orderCartId;
    }

    public void setOrderCartId(Integer orderCartId)
    {
        this.orderCartId = orderCartId;
    }

    @Basic
    @Column(name = "orderProductID", nullable = false)
    public Integer getOrderProductId()
    {
        return orderProductId;
    }

    public void setOrderProductId(Integer orderProductId)
    {
        this.orderProductId = orderProductId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersEntity that = (OrdersEntity) o;

        if (idorder != null ? !idorder.equals(that.idorder) : that.idorder != null) return false;
        if (orderCartId != null ? !orderCartId.equals(that.orderCartId) : that.orderCartId != null) return false;
        if (orderProductId != null ? !orderProductId.equals(that.orderProductId) : that.orderProductId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = idorder != null ? idorder.hashCode() : 0;
        result = 31 * result + (orderCartId != null ? orderCartId.hashCode() : 0);
        result = 31 * result + (orderProductId != null ? orderProductId.hashCode() : 0);
        return result;
    }
}
