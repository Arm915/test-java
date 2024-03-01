package com.example.User.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "User_test")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  userid;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "birthday")
    private LocalDate birthday;
    @Column(name = "created_date")
    private LocalDate created_date;
    @Column(name = "gender")
    private String gender;

    public User() {

    }

    public User(Long userid, String firstName, String lastName, String email, LocalDate birthday,
                          LocalDate created_date, String gender) {
        this.userid = userid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.created_date = created_date;
        this.gender = gender;
    }

    public Long getuserid() {
        return userid;
    }

    public void setuserid(Long userid) {
        this.userid = userid;
    }

    public String getfirstName() {
        return firstName;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getlastName() {
        return lastName;
    }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    public String getemail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getbirthday() {
        return birthday;
    }

    public void setbirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getcreated_date() {
        return created_date;
    }

    public void setcreated_date(LocalDate created_date) {
        this.created_date = created_date;
    }

    public String getgender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Tutorial [id=" + userid + ", firstName=" + firstName + ", lastName=" + lastName +
                ", email=" + email + ", birthday=" + birthday + ",created_date=" + created_date +
                ", gender=" + gender + ",]";
    }
}
