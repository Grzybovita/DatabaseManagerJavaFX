package model;

public class Address {
  private String address;
  private String city;
  private String postalcode;

  public Address() {
  }

  public Address(String address, String city, String postalcode) {
    this.address = address;
    this.city = city;
    this.postalcode = postalcode;
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

  @Override
  public String toString() {
    return "Address{" +
            "address='" + address + '\'' +
            ", city='" + city + '\'' +
            ", postalcode='" + postalcode + '\'' +
            '}';
  }
}
