package com.example.videolibrarybe.constants;

import lombok.Getter;

public enum UserRoles {
    ADMIN("ADMIN", "admin user");

    private final String role;
    private final String desc;

    UserRoles(String role, String desc){
        this.role = role;
        this.desc = desc;
    }

    public String getRole() {
        return role;
    }

    public String getDesc() {
        return desc;
    }
}
