package POJO;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "address_generator")
    @SequenceGenerator(name = "address_generator", sequenceName = "address_sequence", allocationSize = 1)
    @Column(name = "id",unique = true)
    private int id;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    public Address(){}

    public Address(String city, String district){
        this.city = city;
        this.district = district;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return "Address - " +
                " City : " + city + '\'' +
                " District : '" + district
                ;
    }
}
