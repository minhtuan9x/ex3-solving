package com.trongit.dto;

import java.util.ArrayList;
import java.util.List;

public class SpecialQuery {
    private List<String> joins = new ArrayList<>();
    private List<String> wheres = new ArrayList<>();

    public List<String> getJoins() {
        return joins;
    }

    public void setJoins(List<String> joins) {
        this.joins = joins;
    }

    public List<String> getWheres() {
        return wheres;
    }

    public void setWheres(List<String> wheres) {
        this.wheres = wheres;
    }
}
