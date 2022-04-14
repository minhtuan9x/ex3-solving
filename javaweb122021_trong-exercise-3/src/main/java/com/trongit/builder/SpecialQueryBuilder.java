package com.trongit.builder;

import java.util.ArrayList;
import java.util.List;

public class SpecialQueryBuilder {
    private final List<String> joins;
    private final List<String> wheres;

    public List<String> getJoins() {
        return joins;
    }

    public List<String> getWheres() {
        return wheres;
    }

    private SpecialQueryBuilder(Builder builder) {
        this.joins = builder.joins;
        this.wheres = builder.wheres;
    }

    public static class Builder {
        private final List<String> joins = new ArrayList<>();
        private final List<String> wheres = new ArrayList<>();

        public Builder join(String join) {
            this.joins.add(join);
            return this;
        }

        public Builder where(String where) {
            this.wheres.add(where);
            return this;
        }

        public SpecialQueryBuilder build() {
            return new SpecialQueryBuilder(this);
        }
    }
}
