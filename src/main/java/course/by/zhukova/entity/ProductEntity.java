package course.by.zhukova.entity;

import javax.persistence.*;

@Entity
@Table(name = "product", schema = "shoes_shop", catalog = "")
public class ProductEntity
{
    private Integer idProduct;
    private String productName;
    private Integer productManufacturerId;
    private Integer productColorId;
    private Integer productSize;
    private Integer productMaterialId;

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
    @Column(name = "productName", nullable = true, length = 45)
    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
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
    @Column(name = "productMaterialID", nullable = false)
    public Integer getProductMaterialId()
    {
        return productMaterialId;
    }

    public void setProductMaterialId(Integer productMaterialId)
    {
        this.productMaterialId = productMaterialId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity entity = (ProductEntity) o;

        if (idProduct != null ? !idProduct.equals(entity.idProduct) : entity.idProduct != null) return false;
        if (productName != null ? !productName.equals(entity.productName) : entity.productName != null) return false;
        if (productManufacturerId != null ? !productManufacturerId.equals(entity.productManufacturerId) : entity.productManufacturerId != null)
            return false;
        if (productColorId != null ? !productColorId.equals(entity.productColorId) : entity.productColorId != null)
            return false;
        if (productSize != null ? !productSize.equals(entity.productSize) : entity.productSize != null) return false;
        if (productMaterialId != null ? !productMaterialId.equals(entity.productMaterialId) : entity.productMaterialId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = idProduct != null ? idProduct.hashCode() : 0;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (productManufacturerId != null ? productManufacturerId.hashCode() : 0);
        result = 31 * result + (productColorId != null ? productColorId.hashCode() : 0);
        result = 31 * result + (productSize != null ? productSize.hashCode() : 0);
        result = 31 * result + (productMaterialId != null ? productMaterialId.hashCode() : 0);
        return result;
    }
}
