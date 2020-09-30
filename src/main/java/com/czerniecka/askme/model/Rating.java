package com.czerniecka.askme.model;

import lombok.Getter;

@Getter
public enum Rating {
    VERYPOOR(1),
    POOR(2),
    OK(3),
    GOOD(4),
    EXCELLENT(5);
    private final int rate;

    Rating(int rate) {
        this.rate = rate;
    }
}
