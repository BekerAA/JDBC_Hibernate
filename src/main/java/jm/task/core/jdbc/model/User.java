package jm.task.core.jdbc.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastName;

    private Byte age;


    //---------------------------------@NoArgsConstructor/@AllArgsConstructor---------------------------------
    public User() {}

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    //---------------------------------@Getter/@Setter---------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    //---------------------------------@Bulder---------------------------------
    public static UserBulder builder(){
        return new UserBulder();
    }

    public static class UserBulder{

        private String name;
        private String lastname;
        private Byte age;

        public UserBulder(){}

        public UserBulder name(String name){
            this.name = name;
            return this;
        }

        public UserBulder lastname(String lastname){
            this.lastname = lastname;
            return this;
        }

        public UserBulder age(Byte age){
            this.age = age;
            return this;
        }

        public User build(){
            return new User(name, lastname, age);
        }
    }

    //---------------------------------@ToString---------------------------------
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
    //---------------------------------@EqualsAndHashCode---------------------------------
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
    //------------------------------------------------------------------

}

