package course.by.zhukova.entity;

import javax.persistence.*;

@Entity
@Table(name = "material", schema = "shoes_shop", catalog = "")
public class MaterialEntity
{
    private Integer idmaterial;
    private Integer materialInside;
    private Integer materialOutside;
    private Integer materialSole;
    private Integer materialUpper;
    private Integer materialSeason;

    @Id
    @Column(name = "idmaterial", nullable = false)
    public Integer getIdmaterial()
    {
        return idmaterial;
    }

    public void setIdmaterial(Integer idmaterial)
    {
        this.idmaterial = idmaterial;
    }

    @Basic
    @Column(name = "materialInside", nullable = false)
    public Integer getMaterialInside()
    {
        return materialInside;
    }

    public void setMaterialInside(Integer materialInside)
    {
        this.materialInside = materialInside;
    }

    @Basic
    @Column(name = "materialOutside", nullable = false)
    public Integer getMaterialOutside()
    {
        return materialOutside;
    }

    public void setMaterialOutside(Integer materialOutside)
    {
        this.materialOutside = materialOutside;
    }

    @Basic
    @Column(name = "materialSole", nullable = false)
    public Integer getMaterialSole()
    {
        return materialSole;
    }

    public void setMaterialSole(Integer materialSole)
    {
        this.materialSole = materialSole;
    }

    @Basic
    @Column(name = "materialUpper", nullable = false)
    public Integer getMaterialUpper()
    {
        return materialUpper;
    }

    public void setMaterialUpper(Integer materialUpper)
    {
        this.materialUpper = materialUpper;
    }

    @Basic
    @Column(name = "materialSeason", nullable = true)
    public Integer getMaterialSeason()
    {
        return materialSeason;
    }

    public void setMaterialSeason(Integer materialSeason)
    {
        this.materialSeason = materialSeason;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MaterialEntity entity = (MaterialEntity) o;

        if (idmaterial != null ? !idmaterial.equals(entity.idmaterial) : entity.idmaterial != null) return false;
        if (materialInside != null ? !materialInside.equals(entity.materialInside) : entity.materialInside != null)
            return false;
        if (materialOutside != null ? !materialOutside.equals(entity.materialOutside) : entity.materialOutside != null)
            return false;
        if (materialSole != null ? !materialSole.equals(entity.materialSole) : entity.materialSole != null)
            return false;
        if (materialUpper != null ? !materialUpper.equals(entity.materialUpper) : entity.materialUpper != null)
            return false;
        if (materialSeason != null ? !materialSeason.equals(entity.materialSeason) : entity.materialSeason != null)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = idmaterial != null ? idmaterial.hashCode() : 0;
        result = 31 * result + (materialInside != null ? materialInside.hashCode() : 0);
        result = 31 * result + (materialOutside != null ? materialOutside.hashCode() : 0);
        result = 31 * result + (materialSole != null ? materialSole.hashCode() : 0);
        result = 31 * result + (materialUpper != null ? materialUpper.hashCode() : 0);
        result = 31 * result + (materialSeason != null ? materialSeason.hashCode() : 0);
        return result;
    }
}
