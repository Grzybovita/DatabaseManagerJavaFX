package model;

import pokermanagerapp.EmployeeDAO;
import pokermanagerapp.PlayerDAO;

public abstract class Person {

  private int id;
  private String name;
  private String lastname;
  private String telnumber;
  private String email;
  private String address;
  private String city;
  private String postalcode;
  private ClubStatus clubStatus;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getTelnumber() {
    return telnumber;
  }

  public void setTelnumber(String telnumber) {
    this.telnumber = telnumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getPostalcode() {
    return postalcode;
  }

  public void setPostalcode(String postalcode) {
    this.postalcode = postalcode;
  }

  public static int getCount()
  {
    return PlayerDAO.getAllPlayers().size() + EmployeeDAO.getAllEmployees().size();
  }

  @Override
  public String toString() {
    return "Person{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", lastname='" + lastname + '\'' +
            ", email='" + email + '\'' +
            '}';
  }
}
