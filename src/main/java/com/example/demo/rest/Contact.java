package com.example.demo.rest;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Contact implements Serializable {

    private static final long serialVersionUID = 4048798961366546485L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private UUID id;

    private String name;

    private String phone;

    private String email;

    private String address1;

    private String address2;

    private String address3;

    private String postalCode;

    private String note;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(id, contact.id) && Objects.equals(name, contact.name) && Objects.equals(phone, contact.phone) && Objects.equals(email, contact.email) && Objects.equals(address1, contact.address1) && Objects.equals(address2, contact.address2) && Objects.equals(address3, contact.address3) && Objects.equals(postalCode, contact.postalCode) && Objects.equals(note, contact.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, email, address1, address2, address3, postalCode, note);
    }

}
