package com.ba.builder;

public abstract class IdBuilder<T> {
    public abstract T build();
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

