package com.tutorial.springTutorial.model.member;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member_api_account")
public class MemberAccountEntity implements Serializable {
	private static final long serialVersionUID = 4128678005965718956L;
	
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
  @Column(name="EMAIL")
  private String email;
  @Column(name="CELLPHONE")
  private String cellphone;
  @Column(name="PASSWORD")
  private String password;
  @Column(name="ADDRESS")
  private String address;
  
  

  public int getId() {
	return id;
  }
  public void setId(int id) {
	this.id = id;
  }
  public String getEmail() {
	return email;
  }
  public void setEmail(String email) {
	this.email = email;
  }
  public String getCellphone() {
	return cellphone;
  }
  public void setCellphone(String cellphone) {
	this.cellphone = cellphone;
  }
  public String getPassword() {
	return password;
  }
  public void setPassword(String password) {
	this.password = password;
  }
  public String getAddress() {
	return address;
  }
  public void setAddress(String address) {
	this.address = address;
  }
 
  
  
  
}