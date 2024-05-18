package com.hoperise.staff.dtos;

public class ServiceDTO {
    private int id;

    public ServiceDTO(String name, int price, String departmentName) {
        this.name = name;
        this.price = price;
        this.departmentName = departmentName;
    }

    private String name;
    private int price;
    private String departmentName;

    public ServiceDTO(int id, String name, int price, String departmentName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.departmentName = departmentName;
    }

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
