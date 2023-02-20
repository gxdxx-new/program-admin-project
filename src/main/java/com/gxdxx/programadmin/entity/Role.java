package com.gxdxx.programadmin.entity;

import lombok.Getter;

@Getter
public enum Role {

    SUPERADMIN("ROLE_SUPERADMIN"),
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    Role(String value) {
        this.value = value;
    }

    private String value;

}
