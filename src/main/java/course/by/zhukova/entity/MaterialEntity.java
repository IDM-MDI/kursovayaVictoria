package course.by.zhukova.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "material", schema = "shoes_shop", catalog = "")
public class MaterialEntity
{
    private Integer idmaterial;
    private Integer materialInside;
    private Integer materialOutside;
    private Integer materialSole;
    private Integer materialUpper;
    private MaterialtypeEntity materialtypeByMaterialInside;
    private MaterialtypeEntity materialtypeByMaterialOutside;
    private MaterialtypeEntity materialtypeByMaterialSole;
    private MaterialtypeEntity materialtypeByMaterialUpper;
    private Collection<SeasonEntity> seasonsByIdmaterial;

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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MaterialEntity that = (MaterialEntity) o;

        if (idmaterial != null ? !idmaterial.equals(that.idmaterial) : that.idmaterial != null) return false;
        if (materialInside != null ? !materialInside.equals(that.materialInside) : that.materialInside != null)
            return false;
        if (materialOutside != null ? !materialOutside.equals(that.materialOutside) : that.materialOutside != null)
            return false;
        if (materialSole != null ? !materialSole.equals(that.materialSole) : that.materialSole != null) return false;
        if (materialUpper != null ? !materialUpper.equals(that.materialUpper) : that.materialUpper != null)
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
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "materialInside", referencedColumnName = "idmaterialtype", nullable = false)
    public MaterialtypeEntity getMaterialtypeByMaterialInside()
    {
        return materialtypeByMaterialInside;
    }

    public void setMaterialtypeByMaterialInside(MaterialtypeEntity materialtypeByMaterialInside)
    {
        this.materialtypeByMaterialInside = materialtypeByMaterialInside;
    }

    @ManyToOne
    @JoinColumn(name = "materialOutside", referencedColumnName = "idmaterialtype", nullable = false)
    public MaterialtypeEntity getMaterialtypeByMaterialOutside()
    {
        return materialtypeByMaterialOutside;
    }

    public void setMaterialtypeByMaterialOutside(MaterialtypeEntity materialtypeByMaterialOutside)
    {
        this.materialtypeByMaterialOutside = materialtypeByMaterialOutside;
    }

    @ManyToOne
    @JoinColumn(name = "materialSole", referencedColumnName = "idmaterialtype", nullable = false)
    public MaterialtypeEntity getMaterialtypeByMaterialSole()
    {
        return materialtypeByMaterialSole;
    }

    public void setMaterialtypeByMaterialSole(MaterialtypeEntity materialtypeByMaterialSole)
    {
        this.materialtypeByMaterialSole = materialtypeByMaterialSole;
    }

    @ManyToOne
    @JoinColumn(name = "materialUpper", referencedColumnName = "idmaterialtype", nullable = false)
    public MaterialtypeEntity getMaterialtypeByMaterialUpper()
    {
        return materialtypeByMaterialUpper;
    }

    public void setMaterialtypeByMaterialUpper(MaterialtypeEntity materialtypeByMaterialUpper)
    {
        this.materialtypeByMaterialUpper = materialtypeByMaterialUpper;
    }

    @OneToMany(mappedBy = "materialBySeasonMaterialId")
    public Collection<SeasonEntity> getSeasonsByIdmaterial()
    {
        return seasonsByIdmaterial;
    }

    public void setSeasonsByIdmaterial(Collection<SeasonEntity> seasonsByIdmaterial)
    {
        this.seasonsByIdmaterial = seasonsByIdmaterial;
    }
}
