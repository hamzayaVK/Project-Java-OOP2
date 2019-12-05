package users;

import couriercompany.CourierCompany;
import couriercompany.CourierOffice;
import javax.persistence.*;

@Entity
@Table(name = "administrator")
public class Administrator {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "administrator_generator")
    @SequenceGenerator(name = "administrator_generator", sequenceName = "administrator_sequence", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "courierofficeid")
    private CourierOffice courierOffice;

    @ManyToOne
    @JoinColumn(name = "couriercompanyid")
    private CourierCompany courierCompany;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public CourierOffice getCourierOffice() {
        return courierOffice;
    }

    public void setCourierOffice(CourierOffice courierOffice) {
        this.courierOffice = courierOffice;
    }

    public CourierCompany getCourierCompany() {
        return courierCompany;
    }

    public void setCourierCompany(CourierCompany courierCompany) {
        this.courierCompany = courierCompany;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", courierOffice=" + courierOffice +
                ", courierCompany=" + courierCompany +
                '}';
    }
}
