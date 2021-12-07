package course.by.zhukova.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "color", schema = "shoes_shop", catalog = "")
public class ColorEntity
{
    private Integer idcolor;
    private String colorName;
    private Collection<ProductEntity> productsByIdcolor;

    @Id
    @Column(name = "idcolor", nullable = false)
    public Integer getIdcolor()
    {
        return idcolor;
    }

    public void setIdcolor(Integer idcolor)
    {
        this.idcolor = idcolor;
    }

    @Basic
    @Column(name = "colorName", nullable = false, length = 20)
    public String getColorName()
    {
        return colorName;
    }

    public void setColorName(String colorName)
    {
        this.colorName = colorName;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ColorEntity that = (ColorEntity) o;

        if (idcolor != null ? !idcolor.equals(that.idcolor) : that.idcolor != null) return false;
        if (colorName != null ? !colorName.equals(that.colorName) : that.colorName != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = idcolor != null ? idcolor.hashCode() : 0;
        result = 31 * result + (colorName != null ? colorName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "colorByProductColorId")
    public Collection<ProductEntity> getProductsByIdcolor()
    {
        return productsByIdcolor;
    }

    public void setProductsByIdcolor(Collection<ProductEntity> productsByIdcolor)
    {
        this.productsByIdcolor = productsByIdcolor;
    }
}
