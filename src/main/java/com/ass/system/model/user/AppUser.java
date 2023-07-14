package com.ass.system.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")

public class AppUser implements UserDetails {
    @Id
    private long userId;

    private static final long serialVersionUID = 372851172679601253L;

    private String firstName;
    private String lastName;
    //    private String addressLine1;
//    private String addressLine2;
//    private String city;
//    private String postcode;
    private String email;
    private String password;
    @Enumerated
    private Role role;

    public AppUser(long userId, String firstName, String lastName, String email, String password, Role role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public AppUser() {
    }

    public static AppUserBuilder builder() {
        return new AppUserBuilder();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public long getId() {
        return userId;
    }

    public long getUserId() {
        return this.userId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public Role getRole() {
        return this.role;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AppUser)) return false;
        final AppUser other = (AppUser) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getUserId() != other.getUserId()) return false;
        final Object this$firstName = this.getFirstName();
        final Object other$firstName = other.getFirstName();
        if (this$firstName == null ? other$firstName != null : !this$firstName.equals(other$firstName)) return false;
        final Object this$lastName = this.getLastName();
        final Object other$lastName = other.getLastName();
        if (this$lastName == null ? other$lastName != null : !this$lastName.equals(other$lastName)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$role = this.getRole();
        final Object other$role = other.getRole();
        if (this$role == null ? other$role != null : !this$role.equals(other$role)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AppUser;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $userId = this.getUserId();
        result = result * PRIME + (int) ($userId >>> 32 ^ $userId);
        final Object $firstName = this.getFirstName();
        result = result * PRIME + ($firstName == null ? 43 : $firstName.hashCode());
        final Object $lastName = this.getLastName();
        result = result * PRIME + ($lastName == null ? 43 : $lastName.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $role = this.getRole();
        result = result * PRIME + ($role == null ? 43 : $role.hashCode());
        return result;
    }

    public String toString() {
        return "AppUser(userId=" + this.getUserId() + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ", role=" + this.getRole() + ")";
    }

    public static class AppUserBuilder {
        private long userId;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private Role role;

        AppUserBuilder() {
        }

        public AppUserBuilder userId(long userId) {
            this.userId = userId;
            return this;
        }

        public AppUserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public AppUserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public AppUserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public AppUserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public AppUserBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public AppUser build() {
            return new AppUser(userId, firstName, lastName, email, password, role);
        }

        public String toString() {
            return "AppUser.AppUserBuilder(userId=" + this.userId + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", email=" + this.email + ", password=" + this.password + ", role=" + this.role + ")";
        }
    }
}
