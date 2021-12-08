package course.by.zhukova.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "season", schema = "shoes_shop", catalog = "")
public class SeasonEntity
{
    private Integer idseason;
    private String seasonName;
    private Collection<MaterialEntity> materialsByIdseason;
    private Collection<ProductEntity> productsByIdseason;

    @Id
    @Column(name = "idseason", nullable = false)
    public Integer getIdseason()
    {
        return idseason;
    }

    public void setIdseason(Integer idseason)
    {
        this.idseason = idseason;
    }

    @Basic
    @Column(name = "seasonName", nullable = false, length = 45)
    public String getSeasonName()
    {
        return seasonName;
    }

    public void setSeasonName(String seasonName)
    {
        this.seasonName = seasonName;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SeasonEntity that = (SeasonEntity) o;

        if (idseason != null ? !idseason.equals(that.idseason) : that.idseason != null) return false;
        if (seasonName != null ? !seasonName.equals(that.seasonName) : that.seasonName != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = idseason != null ? idseason.hashCode() : 0;
        result = 31 * result + (seasonName != null ? seasonName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "seasonByMaterialSeason")
    public Collection<MaterialEntity> getMaterialsByIdseason()
    {
        return materialsByIdseason;
    }

    public void setMaterialsByIdseason(Collection<MaterialEntity> materialsByIdseason)
    {
        this.materialsByIdseason = materialsByIdseason;
    }

    @OneToMany(mappedBy = "seasonByProductSeasonId")
    public Collection<ProductEntity> getProductsByIdseason()
    {
        return productsByIdseason;
    }

    public void setProductsByIdseason(Collection<ProductEntity> productsByIdseason)
    {
        this.productsByIdseason = productsByIdseason;
    }
}
