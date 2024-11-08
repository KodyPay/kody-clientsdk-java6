package com.kodypay.api.model;

public class Objects {
    /**
     * Helper method to compare two objects for equality.
     */
    static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
}
