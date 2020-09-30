package com.czerniecka.askme.model;

import lombok.Getter;

@Getter
public enum Rating {

    /*
    * Rating of answers as USEFUL/NOT USEFUL (+1/-1)
    * To make rankings easier to calculate and present
    * highest scored answer is voted to be most useful one
    * lowest scored (below zero is voted to be less useful or not clear
    * */
    USEFUL(1),
    NOTUSEFUL(-1);
    private final int rate;

    Rating(int rate) {
        this.rate = rate;
    }
}
