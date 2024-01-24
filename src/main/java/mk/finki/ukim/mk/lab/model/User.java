package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.finki.ukim.mk.lab.model.converter.UserFullnameConverter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "movie_users")
public class User {
    @Id
    private String username;

    @Convert(converter = UserFullnameConverter.class)
    private UserFullName fullname;


    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;


//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
//    private List<ShoppingCart> carts;

    public User() {
    }

    public User(String username, String name, String surname) {
        this.username = username;
        UserFullName fullname = new UserFullName();
        fullname.setName(name);
        fullname.setSurname(surname);
        this.fullname = fullname;
    }
}
