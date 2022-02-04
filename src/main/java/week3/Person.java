package week3;

import week3.enums.Roles;


public abstract class Person {

    protected String name;
    protected Roles role;


    public Person(String name, Roles role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }



}
