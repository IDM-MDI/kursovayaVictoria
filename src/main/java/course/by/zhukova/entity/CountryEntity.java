package course.by.zhukova.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "country", schema = "shoes_shop", catalog = "")
public class CountryEntity
{
    private Integer idcountry;
    private String countryName;
    private Collection<ManufacturerEntity> manufacturersByIdcountry;

    @Id
    @Column(name = "idcountry", nullable = false)
    public Integer getIdcountry()
    {
        return idcountry;
    }

    public void setIdcountry(Integer idcountry)
    {
        this.idcountry = idcountry;
    }

    @Basic
    @Column(name = "countryName", nullable = false, length = 45)
    public String getCountryName()
    {
        return countryName;
    }

    public void setCountryName(String countryName)
    {
        this.countryName = countryName;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountryEntity that = (CountryEntity) o;

        if (idcountry != null ? !idcountry.equals(that.idcountry) : that.idcountry != null) return false;
        if (countryName != null ? !countryName.equals(that.countryName) : that.countryName != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = idcountry != null ? idcountry.hashCode() : 0;
        result = 31 * result + (countryName != null ? countryName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "countryByManufacturerCountry")
    public Collection<ManufacturerEntity> getManufacturersByIdcountry()
    {
        return manufacturersByIdcountry;
    }

    public void setManufacturersByIdcountry(Collection<ManufacturerEntity> manufacturersByIdcountry)
    {
        this.manufacturersByIdcountry = manufacturersByIdcountry;
    }
}
