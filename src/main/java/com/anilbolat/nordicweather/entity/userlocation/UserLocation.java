package com.anilbolat.nordicweather.entity.userlocation;

import com.anilbolat.nordicweather.entity.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity(name = "user_location")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLocation {
    
    @Id
    @GeneratedValue
    private Long id;

    private String location;
    
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    private Long getUserId() {
        return this.user != null ? this.user.getId() : null;
    }

    @Override
    public String toString() {
        return "UserLocation{location=" + location + ", date=" + date + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UserLocation)) return false;
        UserLocation other = (UserLocation) obj;
        return Objects.equals(this.location, other.location) &&
                Objects.equals(this.date, other.date) &&
                Objects.equals(this.getUserId(), other.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.location,
                this.date,
                this.getUserId());
    }
}
