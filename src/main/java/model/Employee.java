package model;

import pokermanagerapp.EmployeeDAO;

public class Employee extends Person {

  private String position;

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public Employee(int id, String name, String lastname, String telnumber, String email,
                Address address, String position) {
    this.setId(id);
    this.setName(name);
    this.setLastname(lastname);
    this.setTelnumber(telnumber);
    this.setEmail(email);
    this.setAddress(address);
    this.setPosition(position);
  }

  public static int getCount()
  {
    return EmployeeDAO.getAllEmployees().size();
  }
}
