package com.example.codefellowship.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class ApplicationUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String bio;
    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinTable(name = "FollowTable",joinColumns = {@JoinColumn(name="followingId")},inverseJoinColumns = {@JoinColumn(name = "followerId")})
    private List<ApplicationUser> followingMe;
    @ManyToMany(cascade = CascadeType.ALL , mappedBy ="followingMe",fetch = FetchType.LAZY)
    private List<ApplicationUser> follower;
    @OneToMany(fetch = FetchType.EAGER,cascade =CascadeType.ALL)
    List<Post> posts;

    public ApplicationUser() {
    }

    public ApplicationUser(String username, String password, String firstName, String lastName, String dateOfBirth, String bio) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    public List<ApplicationUser> getFollowingMe() {
        return followingMe;
    }

    public void setFollowingMe(List<ApplicationUser> followingMe) {
        this.followingMe = followingMe;
    }

    public List<ApplicationUser> getFollower() {
        return follower;
    }

    public void setFollower(List<ApplicationUser> follower) {
        this.follower = follower;
    }
}
