package com.trongit.builder;

import com.trongit.annotation.searchbuilding.LikeField;
import com.trongit.annotation.searchbuilding.OperatorField;
import com.trongit.annotation.searchbuilding.SearchObject;
import com.trongit.dto.SpecialQuery;
import com.trongit.service.CustomQuery;
import com.trongit.utils.ValidateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SearchObject(value = "building", alias = "bd", groupBy = "id")
public class BuildingSearchBuilder implements CustomQuery {
    @LikeField
    private final String name;
    @OperatorField
    private final Integer floorArea;
    @OperatorField
    private final String district;
    @LikeField
    private final String ward;
    @LikeField
    private final String street;
    @OperatorField
    private final Integer numberOfBasement;
    @OperatorField
    private final String direction;
    @OperatorField
    private final String level;
    private final Integer rentAreaFrom;
    private final Integer rentAreaTo;
    private final Integer rentPriceFrom;
    private final Integer rentPriceTo;
    @LikeField
    private final String managerName;
    @LikeField
    private final String managerPhone;
    private final Integer staffID;
    private final List<String> rentTypes;

    public String getName() {
        return name;
    }

    public Integer getFloorArea() {
        return floorArea;
    }

    public String getDistrict() {
        return district;
    }

    public String getWard() {
        return ward;
    }

    public String getStreet() {
        return street;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public String getDirection() {
        return direction;
    }

    public String getLevel() {
        return level;
    }

    public Integer getRentAreaFrom() {
        return rentAreaFrom;
    }

    public Integer getRentAreaTo() {
        return rentAreaTo;
    }

    public Integer getRentPriceFrom() {
        return rentPriceFrom;
    }

    public Integer getRentPriceTo() {
        return rentPriceTo;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public Integer getStaffID() {
        return staffID;
    }

    public List<String> getRentTypes() {
        return rentTypes;
    }

    private BuildingSearchBuilder(Builder builder) {
        name = builder.name;
        floorArea = builder.floorArea;
        district = builder.district;
        ward = builder.ward;
        street = builder.street;
        numberOfBasement = builder.numberOfBasement;
        direction = builder.direction;
        level = builder.level;
        rentAreaFrom = builder.rentAreaFrom;
        rentAreaTo = builder.rentAreaTo;
        rentPriceFrom = builder.rentPriceFrom;
        rentPriceTo = builder.rentPriceTo;
        managerName = builder.managerName;
        managerPhone = builder.managerPhone;
        staffID = builder.staffID;
        rentTypes = builder.rentTypes;
    }

    @Override
    public SpecialQuery buildQuery() {
        SpecialQuery specialQueryBuilder = new SpecialQuery();
        specialQueryBuilder.setJoins(buildJoinQuery());
        specialQueryBuilder.setWheres(buildWhereQuery());
        return specialQueryBuilder;
    }

    private List<String> buildJoinQuery() {
        List<String> joins = new ArrayList<>();
        if (ValidateUtil.isValid(this.getRentAreaTo())
                || ValidateUtil.isValid(this.getRentAreaFrom()))
            joins.add("\ninner join rentarea as ra on bd.id = ra.buildingid ");
        if (ValidateUtil.isValid(this.getStaffID()))
            joins.add("\ninner join assignmentbuilding as ab on bd.id = ab.buildingid inner join user as u on ab.staffid = u.id ");
        return joins;
    }

    private List<String> buildWhereQuery() {
        List<String> wheres = new ArrayList<>();
        StringBuilder where = new StringBuilder();
        if (this.getRentTypes() != null && this.getRentTypes().size() > 0) {
            where.append("\nand (");
            String renttypes = this.getRentTypes().stream()
                    .map(item -> ("bd.type like '%" + item + "%'")).collect(Collectors.joining(" or "));
            where.append(renttypes);
            where.append(" )");
        }

        if (ValidateUtil.isValid(this.getStaffID())) {
            where.append("\nand u.id = " + this.getStaffID());
        }

        if (ValidateUtil.isValid(this.getRentAreaFrom())) {
            where.append("\nand EXISTS (select * from rentarea as ra where bd.id=ra.buildingid and ra.value >= "
                    + this.getRentAreaFrom() + ")");
        }
        if (ValidateUtil.isValid(this.getRentAreaTo())) {
            where.append("\nand EXISTS (select * from rentarea as ra where bd.id=ra.buildingid and ra.value <= "
                    + this.getRentAreaTo() + ")");
        }

        if (ValidateUtil.isValid(this.getRentPriceFrom())) {
            where.append("\nand bd.rentprice >= " + this.getRentPriceFrom());
        }
        if (ValidateUtil.isValid(this.getRentPriceTo())) {
            where.append("\nand bd.rentprice <= " + this.getRentPriceTo());
        }
        wheres.add(where.toString());
        return wheres;
    }

    public static final class Builder {
        private String name;
        private Integer floorArea;
        private String district;
        private String ward;
        private String street;
        private Integer numberOfBasement;
        private String direction;
        private String level;
        private Integer rentAreaFrom;
        private Integer rentAreaTo;
        private Integer rentPriceFrom;
        private Integer rentPriceTo;
        private String managerName;
        private String managerPhone;
        private Integer staffID;
        private List<String> rentTypes;

        public Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder floorArea(Integer val) {
            floorArea = val;
            return this;
        }

        public Builder district(String val) {
            district = val;
            return this;
        }

        public Builder ward(String val) {
            ward = val;
            return this;
        }

        public Builder street(String val) {
            street = val;
            return this;
        }

        public Builder numberOfBasement(Integer val) {
            numberOfBasement = val;
            return this;
        }

        public Builder direction(String val) {
            direction = val;
            return this;
        }

        public Builder level(String val) {
            level = val;
            return this;
        }

        public Builder rentAreaFrom(Integer val) {
            rentAreaFrom = val;
            return this;
        }

        public Builder rentAreaTo(Integer val) {
            rentAreaTo = val;
            return this;
        }

        public Builder rentPriceFrom(Integer val) {
            rentPriceFrom = val;
            return this;
        }

        public Builder rentPriceTo(Integer val) {
            rentPriceTo = val;
            return this;
        }

        public Builder managerName(String val) {
            managerName = val;
            return this;
        }

        public Builder managerPhone(String val) {
            managerPhone = val;
            return this;
        }

        public Builder staffID(Integer val) {
            staffID = val;
            return this;
        }

        public Builder rentTypes(List<String> val) {
            rentTypes = val;
            return this;
        }

        public BuildingSearchBuilder build() {
            return new BuildingSearchBuilder(this);
        }
    }
}
