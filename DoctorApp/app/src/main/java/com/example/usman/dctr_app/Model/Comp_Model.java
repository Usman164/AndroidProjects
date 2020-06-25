package com.example.usman.dctr_app.Model;

public class Comp_Model
{
        private String name;
        private String email;
        private String bloodGrooup;
        private String cnic;
        private String phone;
        private String address;
        private String complaint;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getBloodGrooup() {
            return bloodGrooup;
        }

        public void setBloodGrooup(String bloodGrooup) {
            this.bloodGrooup = bloodGrooup;
        }

        public String getCnic() {
            return cnic;
        }

        public void setCnic(String cnic) {
            this.cnic = cnic;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getComplaint() {
            return complaint;
        }

        public void setComplaint(String complaint) {
            this.complaint = complaint;
        }

        public String getImageURL() {
            return imageURL;
        }

        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
        }

        public Comp_Model(String name, String email, String bloodGrooup, String cnic, String phone, String address, String complaint, String imageURL) {
            this.name = name;
            this.email = email;
            this.bloodGrooup = bloodGrooup;
            this.cnic = cnic;
            this.phone = phone;
            this.address = address;
            this.complaint = complaint;
            this.imageURL = imageURL;
        }

        private String imageURL;

}
