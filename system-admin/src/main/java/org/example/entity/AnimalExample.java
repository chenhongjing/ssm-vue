package org.example.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnimalExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AnimalExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andAnimalIdIsNull() {
            addCriterion("animal_id is null");
            return (Criteria) this;
        }

        public Criteria andAnimalIdIsNotNull() {
            addCriterion("animal_id is not null");
            return (Criteria) this;
        }

        public Criteria andAnimalIdEqualTo(Integer value) {
            addCriterion("animal_id =", value, "animalId");
            return (Criteria) this;
        }

        public Criteria andAnimalIdNotEqualTo(Integer value) {
            addCriterion("animal_id <>", value, "animalId");
            return (Criteria) this;
        }

        public Criteria andAnimalIdGreaterThan(Integer value) {
            addCriterion("animal_id >", value, "animalId");
            return (Criteria) this;
        }

        public Criteria andAnimalIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("animal_id >=", value, "animalId");
            return (Criteria) this;
        }

        public Criteria andAnimalIdLessThan(Integer value) {
            addCriterion("animal_id <", value, "animalId");
            return (Criteria) this;
        }

        public Criteria andAnimalIdLessThanOrEqualTo(Integer value) {
            addCriterion("animal_id <=", value, "animalId");
            return (Criteria) this;
        }

        public Criteria andAnimalIdIn(List<Integer> values) {
            addCriterion("animal_id in", values, "animalId");
            return (Criteria) this;
        }

        public Criteria andAnimalIdNotIn(List<Integer> values) {
            addCriterion("animal_id not in", values, "animalId");
            return (Criteria) this;
        }

        public Criteria andAnimalIdBetween(Integer value1, Integer value2) {
            addCriterion("animal_id between", value1, value2, "animalId");
            return (Criteria) this;
        }

        public Criteria andAnimalIdNotBetween(Integer value1, Integer value2) {
            addCriterion("animal_id not between", value1, value2, "animalId");
            return (Criteria) this;
        }

        public Criteria andAnimalNameIsNull() {
            addCriterion("animal_name is null");
            return (Criteria) this;
        }

        public Criteria andAnimalNameIsNotNull() {
            addCriterion("animal_name is not null");
            return (Criteria) this;
        }

        public Criteria andAnimalNameEqualTo(String value) {
            addCriterion("animal_name =", value, "animalName");
            return (Criteria) this;
        }

        public Criteria andAnimalNameNotEqualTo(String value) {
            addCriterion("animal_name <>", value, "animalName");
            return (Criteria) this;
        }

        public Criteria andAnimalNameGreaterThan(String value) {
            addCriterion("animal_name >", value, "animalName");
            return (Criteria) this;
        }

        public Criteria andAnimalNameGreaterThanOrEqualTo(String value) {
            addCriterion("animal_name >=", value, "animalName");
            return (Criteria) this;
        }

        public Criteria andAnimalNameLessThan(String value) {
            addCriterion("animal_name <", value, "animalName");
            return (Criteria) this;
        }

        public Criteria andAnimalNameLessThanOrEqualTo(String value) {
            addCriterion("animal_name <=", value, "animalName");
            return (Criteria) this;
        }

        public Criteria andAnimalNameLike(String value) {
            addCriterion("animal_name like", value, "animalName");
            return (Criteria) this;
        }

        public Criteria andAnimalNameNotLike(String value) {
            addCriterion("animal_name not like", value, "animalName");
            return (Criteria) this;
        }

        public Criteria andAnimalNameIn(List<String> values) {
            addCriterion("animal_name in", values, "animalName");
            return (Criteria) this;
        }

        public Criteria andAnimalNameNotIn(List<String> values) {
            addCriterion("animal_name not in", values, "animalName");
            return (Criteria) this;
        }

        public Criteria andAnimalNameBetween(String value1, String value2) {
            addCriterion("animal_name between", value1, value2, "animalName");
            return (Criteria) this;
        }

        public Criteria andAnimalNameNotBetween(String value1, String value2) {
            addCriterion("animal_name not between", value1, value2, "animalName");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNull() {
            addCriterion("updated_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNotNull() {
            addCriterion("updated_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeEqualTo(Date value) {
            addCriterion("updated_time =", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotEqualTo(Date value) {
            addCriterion("updated_time <>", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThan(Date value) {
            addCriterion("updated_time >", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_time >=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThan(Date value) {
            addCriterion("updated_time <", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("updated_time <=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIn(List<Date> values) {
            addCriterion("updated_time in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotIn(List<Date> values) {
            addCriterion("updated_time not in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeBetween(Date value1, Date value2) {
            addCriterion("updated_time between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("updated_time not between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andSciNameIsNull() {
            addCriterion("sci_name is null");
            return (Criteria) this;
        }

        public Criteria andSciNameIsNotNull() {
            addCriterion("sci_name is not null");
            return (Criteria) this;
        }

        public Criteria andSciNameEqualTo(String value) {
            addCriterion("sci_name =", value, "sciName");
            return (Criteria) this;
        }

        public Criteria andSciNameNotEqualTo(String value) {
            addCriterion("sci_name <>", value, "sciName");
            return (Criteria) this;
        }

        public Criteria andSciNameGreaterThan(String value) {
            addCriterion("sci_name >", value, "sciName");
            return (Criteria) this;
        }

        public Criteria andSciNameGreaterThanOrEqualTo(String value) {
            addCriterion("sci_name >=", value, "sciName");
            return (Criteria) this;
        }

        public Criteria andSciNameLessThan(String value) {
            addCriterion("sci_name <", value, "sciName");
            return (Criteria) this;
        }

        public Criteria andSciNameLessThanOrEqualTo(String value) {
            addCriterion("sci_name <=", value, "sciName");
            return (Criteria) this;
        }

        public Criteria andSciNameLike(String value) {
            addCriterion("sci_name like", value, "sciName");
            return (Criteria) this;
        }

        public Criteria andSciNameNotLike(String value) {
            addCriterion("sci_name not like", value, "sciName");
            return (Criteria) this;
        }

        public Criteria andSciNameIn(List<String> values) {
            addCriterion("sci_name in", values, "sciName");
            return (Criteria) this;
        }

        public Criteria andSciNameNotIn(List<String> values) {
            addCriterion("sci_name not in", values, "sciName");
            return (Criteria) this;
        }

        public Criteria andSciNameBetween(String value1, String value2) {
            addCriterion("sci_name between", value1, value2, "sciName");
            return (Criteria) this;
        }

        public Criteria andSciNameNotBetween(String value1, String value2) {
            addCriterion("sci_name not between", value1, value2, "sciName");
            return (Criteria) this;
        }

        public Criteria andInfoIsNull() {
            addCriterion("info is null");
            return (Criteria) this;
        }

        public Criteria andInfoIsNotNull() {
            addCriterion("info is not null");
            return (Criteria) this;
        }

        public Criteria andInfoEqualTo(String value) {
            addCriterion("info =", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotEqualTo(String value) {
            addCriterion("info <>", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoGreaterThan(String value) {
            addCriterion("info >", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoGreaterThanOrEqualTo(String value) {
            addCriterion("info >=", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLessThan(String value) {
            addCriterion("info <", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLessThanOrEqualTo(String value) {
            addCriterion("info <=", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLike(String value) {
            addCriterion("info like", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotLike(String value) {
            addCriterion("info not like", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoIn(List<String> values) {
            addCriterion("info in", values, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotIn(List<String> values) {
            addCriterion("info not in", values, "info");
            return (Criteria) this;
        }

        public Criteria andInfoBetween(String value1, String value2) {
            addCriterion("info between", value1, value2, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotBetween(String value1, String value2) {
            addCriterion("info not between", value1, value2, "info");
            return (Criteria) this;
        }

        public Criteria andPictureUrlIsNull() {
            addCriterion("picture_url is null");
            return (Criteria) this;
        }

        public Criteria andPictureUrlIsNotNull() {
            addCriterion("picture_url is not null");
            return (Criteria) this;
        }

        public Criteria andPictureUrlEqualTo(String value) {
            addCriterion("picture_url =", value, "pictureUrl");
            return (Criteria) this;
        }

        public Criteria andPictureUrlNotEqualTo(String value) {
            addCriterion("picture_url <>", value, "pictureUrl");
            return (Criteria) this;
        }

        public Criteria andPictureUrlGreaterThan(String value) {
            addCriterion("picture_url >", value, "pictureUrl");
            return (Criteria) this;
        }

        public Criteria andPictureUrlGreaterThanOrEqualTo(String value) {
            addCriterion("picture_url >=", value, "pictureUrl");
            return (Criteria) this;
        }

        public Criteria andPictureUrlLessThan(String value) {
            addCriterion("picture_url <", value, "pictureUrl");
            return (Criteria) this;
        }

        public Criteria andPictureUrlLessThanOrEqualTo(String value) {
            addCriterion("picture_url <=", value, "pictureUrl");
            return (Criteria) this;
        }

        public Criteria andPictureUrlLike(String value) {
            addCriterion("picture_url like", value, "pictureUrl");
            return (Criteria) this;
        }

        public Criteria andPictureUrlNotLike(String value) {
            addCriterion("picture_url not like", value, "pictureUrl");
            return (Criteria) this;
        }

        public Criteria andPictureUrlIn(List<String> values) {
            addCriterion("picture_url in", values, "pictureUrl");
            return (Criteria) this;
        }

        public Criteria andPictureUrlNotIn(List<String> values) {
            addCriterion("picture_url not in", values, "pictureUrl");
            return (Criteria) this;
        }

        public Criteria andPictureUrlBetween(String value1, String value2) {
            addCriterion("picture_url between", value1, value2, "pictureUrl");
            return (Criteria) this;
        }

        public Criteria andPictureUrlNotBetween(String value1, String value2) {
            addCriterion("picture_url not between", value1, value2, "pictureUrl");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}