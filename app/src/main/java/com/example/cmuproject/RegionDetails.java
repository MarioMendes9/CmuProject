package com.example.cmuproject;

public class RegionDetails {
    private Address address;

    public RegionDetails(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "RegionDetails{" +
                "address=" + address.toString() +
                '}';
    }
}
