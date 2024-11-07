package bike.service.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Client")
public class Client {

    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "uniqId")
    private int uniqId;
    @Column(name = "phoneNumber")
    private int phoneNumber;

    @Override
    public String toString() {
        return "Client{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", uniqId=" + uniqId +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
