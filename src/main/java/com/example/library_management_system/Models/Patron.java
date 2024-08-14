package com.example.library_management_system.Models;

import jakarta.persistence.*;

public class Patron {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(nullable = false, name = "patron_id")
        private Long patronId;

        @Column(nullable = false)
        private String name;

        private String email;

        private String phoneNumber;

        public Patron(Long patronId, String name, String email, String phoneNumber) {
            this.patronId = patronId;
            this.name = name;
            this.email = email;
            this.phoneNumber = phoneNumber;
        }

        public Patron() {
        }

        //Getters and Setters

        public void setPatronId(Long patronId) {
            this.patronId = patronId;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public Long getPatronId() {
            return patronId;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getContactInfo() {
            return name + " " + email + " " + phoneNumber;
        }


}
