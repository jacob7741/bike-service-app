package bike.service.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Mechanic")
public class Mechanic {
    @Column
    private int mechanicId;
    @Column
    private String first_name;
    @Column
    private String last_name;
    @Column
    private String user_name;
    @Column
    private String password;

    @Override
    public String toString() {
        return "Mechanic{" +
                "mechanicId=" + mechanicId +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
