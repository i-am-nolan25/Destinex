package com.team08.csci205_final_project.model.Auth;

/**
 * Role for the account
 */
public enum Role {
    ROLE_USER(1), // User role represented by 1
    ROLE_PROVIDER(2), // Provider role by 2
    ROLE_ADMIN(3); // Admin role by 3

    private final int value;

    Role(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Role fromInt(int role) {
        for (Role r : Role.values()) {
            if (r.getValue() == role) {
                return r;
            }
        }
        throw new IllegalArgumentException("Unknown role integer: " + role);
    }
}
