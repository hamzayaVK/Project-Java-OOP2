package POJO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courier")
public class Courier {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "courier_generator")
    @SequenceGenerator(name = "courier_generator", sequenceName = "courier_sequence", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "familyname", nullable = false)
    private String familyname;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "courier")
    private List<Orders> ordersList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "courierofficeid")
    private CourierOffice courierOffice;

    public Courier(){}

    public Courier(String username, String password, String firstName, String familyName)
    {
        this.username = username;
        this.password = password;
        this.firstname = firstName;
        this.familyname = familyName;
    }

    public CourierOffice getCourieroffice() {
        return courierOffice;
    }

    public void setCourieroffice(CourierOffice courierOffice) {
        this.courierOffice = courierOffice;
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public CourierOffice getCourierOffice() {
        return courierOffice;
    }

    public void setCourierOffice(CourierOffice courierOffice) {
        this.courierOffice = courierOffice;
    }

    @Override
    public String toString() {
        return "Courier{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", familyname='" + familyname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", ordersList=" + ordersList +
                ", courierOffice=" + courierOffice +
                '}';
    }
}
