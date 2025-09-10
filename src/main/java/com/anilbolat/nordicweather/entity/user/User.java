package com.anilbolat.nordicweather.entity.user;

import com.anilbolat.nordicweather.entity.userlocation.UserLocation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "users")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserLocation> userLocations;

    @Override
    public String toString() {
        return "User{email=" + email + "}";
    }
}
