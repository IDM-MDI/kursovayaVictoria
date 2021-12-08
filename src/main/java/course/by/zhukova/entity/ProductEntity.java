package course.by.zhukova.entity;

import javax.persistence.*;

@Entity
@Table(name = "product", schema = "shoes_shop", catalog = "")
public class ProductEntity
{
    private Integer idProduct;
    private Integer productManufacturerId;
    private Integer productColorId;
    private Integer productSize;
    private Integer productSeasonId;

    @Id
    @Column(name = "idProduct", nullable = false)
    public Integer getIdProduct()
    {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct)
    {
        this.idProduct = idProduct;
    }

    @Basic
    @Column(name = "productManufacturerID", nullable = false)
    public Integer getProductManufacturerId()
    {
        return productManufacturerId;
    }

    public void setProductManufacturerId(Integer productManufacturerId)
    {
        this.productManufacturerId = productManufacturerId;
    }

    @Basic
    @Column(name = "productColorID", nullable = false)
    public Integer getProductColorId()
    {
        return productColorId;
    }

    public void setProductColorId(Integer productColorId)
    {
        this.productColorId = productColorId;
    }

    @Basic
    @Column(name = "productSize", nullable = false)
    public Integer getProductSize()
    {
        return productSize;
    }

    public void setProductSize(Integer productSize)
    {
        this.productSize = productSize;
    }

    @Basic
    @Column(name = "productSeasonID", nullable = false)
    public Integer getProductSeasonId()
    {
        return productSeasonId;
    }

    public void setProductSeasonId(Integer productSeasonId)
    {
        this.productSeasonId = productSeasonId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity entity = (ProductEntity) o;

        if (idProduct != null ? !idProduct.equals(entity.idProduct) : entity.idProduct != null) return false;
        if (productManufacturerId != null ? !productManufacturerId.equals(entity.productManufacturerId) : entity.productManufacturerId != null)
            return false;
        if (productColorId != null ? !productColorId.equals(entity.productColorId) : entity.productColorId != null)
            return false;
        if (productSize != null ? !productSize.equals(entity.productSize) : entity.productSize != null) return false;
        if (productSeasonId != null ? !productSeasonId.equals(entity.productSeasonId) : entity.productSeasonId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = idProduct != null ? idProduct.hashCode() : 0;
        result = 31 * result + (productManufacturerId != null ? productManufacturerId.hashCode() : 0);
        result = 31 * result + (productColorId != null ? productColorId.hashCode() : 0);
        result = 31 * result + (productSize != null ? productSize.hashCode() : 0);
        result = 31 * result + (productSeasonId != null ? productSeasonId.hashCode() : 0);
        return result;
    }
}
