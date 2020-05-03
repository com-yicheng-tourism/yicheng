package com.yicheng.tourism.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StoreExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StoreExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andStoreNumberIsNull() {
            addCriterion("store_number is null");
            return (Criteria) this;
        }

        public Criteria andStoreNumberIsNotNull() {
            addCriterion("store_number is not null");
            return (Criteria) this;
        }

        public Criteria andStoreNumberEqualTo(String value) {
            addCriterion("store_number =", value, "storeNumber");
            return (Criteria) this;
        }

        public Criteria andStoreNumberNotEqualTo(String value) {
            addCriterion("store_number <>", value, "storeNumber");
            return (Criteria) this;
        }

        public Criteria andStoreNumberGreaterThan(String value) {
            addCriterion("store_number >", value, "storeNumber");
            return (Criteria) this;
        }

        public Criteria andStoreNumberGreaterThanOrEqualTo(String value) {
            addCriterion("store_number >=", value, "storeNumber");
            return (Criteria) this;
        }

        public Criteria andStoreNumberLessThan(String value) {
            addCriterion("store_number <", value, "storeNumber");
            return (Criteria) this;
        }

        public Criteria andStoreNumberLessThanOrEqualTo(String value) {
            addCriterion("store_number <=", value, "storeNumber");
            return (Criteria) this;
        }

        public Criteria andStoreNumberLike(String value) {
            addCriterion("store_number like", value, "storeNumber");
            return (Criteria) this;
        }

        public Criteria andStoreNumberNotLike(String value) {
            addCriterion("store_number not like", value, "storeNumber");
            return (Criteria) this;
        }

        public Criteria andStoreNumberIn(List<String> values) {
            addCriterion("store_number in", values, "storeNumber");
            return (Criteria) this;
        }

        public Criteria andStoreNumberNotIn(List<String> values) {
            addCriterion("store_number not in", values, "storeNumber");
            return (Criteria) this;
        }

        public Criteria andStoreNumberBetween(String value1, String value2) {
            addCriterion("store_number between", value1, value2, "storeNumber");
            return (Criteria) this;
        }

        public Criteria andStoreNumberNotBetween(String value1, String value2) {
            addCriterion("store_number not between", value1, value2, "storeNumber");
            return (Criteria) this;
        }

        public Criteria andStoreNameIsNull() {
            addCriterion("store_name is null");
            return (Criteria) this;
        }

        public Criteria andStoreNameIsNotNull() {
            addCriterion("store_name is not null");
            return (Criteria) this;
        }

        public Criteria andStoreNameEqualTo(String value) {
            addCriterion("store_name =", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotEqualTo(String value) {
            addCriterion("store_name <>", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameGreaterThan(String value) {
            addCriterion("store_name >", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameGreaterThanOrEqualTo(String value) {
            addCriterion("store_name >=", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLessThan(String value) {
            addCriterion("store_name <", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLessThanOrEqualTo(String value) {
            addCriterion("store_name <=", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLike(String value) {
            addCriterion("store_name like", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotLike(String value) {
            addCriterion("store_name not like", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameIn(List<String> values) {
            addCriterion("store_name in", values, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotIn(List<String> values) {
            addCriterion("store_name not in", values, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameBetween(String value1, String value2) {
            addCriterion("store_name between", value1, value2, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotBetween(String value1, String value2) {
            addCriterion("store_name not between", value1, value2, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreScriptIsNull() {
            addCriterion("store_script is null");
            return (Criteria) this;
        }

        public Criteria andStoreScriptIsNotNull() {
            addCriterion("store_script is not null");
            return (Criteria) this;
        }

        public Criteria andStoreScriptEqualTo(String value) {
            addCriterion("store_script =", value, "storeScript");
            return (Criteria) this;
        }

        public Criteria andStoreScriptNotEqualTo(String value) {
            addCriterion("store_script <>", value, "storeScript");
            return (Criteria) this;
        }

        public Criteria andStoreScriptGreaterThan(String value) {
            addCriterion("store_script >", value, "storeScript");
            return (Criteria) this;
        }

        public Criteria andStoreScriptGreaterThanOrEqualTo(String value) {
            addCriterion("store_script >=", value, "storeScript");
            return (Criteria) this;
        }

        public Criteria andStoreScriptLessThan(String value) {
            addCriterion("store_script <", value, "storeScript");
            return (Criteria) this;
        }

        public Criteria andStoreScriptLessThanOrEqualTo(String value) {
            addCriterion("store_script <=", value, "storeScript");
            return (Criteria) this;
        }

        public Criteria andStoreScriptLike(String value) {
            addCriterion("store_script like", value, "storeScript");
            return (Criteria) this;
        }

        public Criteria andStoreScriptNotLike(String value) {
            addCriterion("store_script not like", value, "storeScript");
            return (Criteria) this;
        }

        public Criteria andStoreScriptIn(List<String> values) {
            addCriterion("store_script in", values, "storeScript");
            return (Criteria) this;
        }

        public Criteria andStoreScriptNotIn(List<String> values) {
            addCriterion("store_script not in", values, "storeScript");
            return (Criteria) this;
        }

        public Criteria andStoreScriptBetween(String value1, String value2) {
            addCriterion("store_script between", value1, value2, "storeScript");
            return (Criteria) this;
        }

        public Criteria andStoreScriptNotBetween(String value1, String value2) {
            addCriterion("store_script not between", value1, value2, "storeScript");
            return (Criteria) this;
        }

        public Criteria andAuthorPhoneIsNull() {
            addCriterion("author_phone is null");
            return (Criteria) this;
        }

        public Criteria andAuthorPhoneIsNotNull() {
            addCriterion("author_phone is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorPhoneEqualTo(String value) {
            addCriterion("author_phone =", value, "authorPhone");
            return (Criteria) this;
        }

        public Criteria andAuthorPhoneNotEqualTo(String value) {
            addCriterion("author_phone <>", value, "authorPhone");
            return (Criteria) this;
        }

        public Criteria andAuthorPhoneGreaterThan(String value) {
            addCriterion("author_phone >", value, "authorPhone");
            return (Criteria) this;
        }

        public Criteria andAuthorPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("author_phone >=", value, "authorPhone");
            return (Criteria) this;
        }

        public Criteria andAuthorPhoneLessThan(String value) {
            addCriterion("author_phone <", value, "authorPhone");
            return (Criteria) this;
        }

        public Criteria andAuthorPhoneLessThanOrEqualTo(String value) {
            addCriterion("author_phone <=", value, "authorPhone");
            return (Criteria) this;
        }

        public Criteria andAuthorPhoneLike(String value) {
            addCriterion("author_phone like", value, "authorPhone");
            return (Criteria) this;
        }

        public Criteria andAuthorPhoneNotLike(String value) {
            addCriterion("author_phone not like", value, "authorPhone");
            return (Criteria) this;
        }

        public Criteria andAuthorPhoneIn(List<String> values) {
            addCriterion("author_phone in", values, "authorPhone");
            return (Criteria) this;
        }

        public Criteria andAuthorPhoneNotIn(List<String> values) {
            addCriterion("author_phone not in", values, "authorPhone");
            return (Criteria) this;
        }

        public Criteria andAuthorPhoneBetween(String value1, String value2) {
            addCriterion("author_phone between", value1, value2, "authorPhone");
            return (Criteria) this;
        }

        public Criteria andAuthorPhoneNotBetween(String value1, String value2) {
            addCriterion("author_phone not between", value1, value2, "authorPhone");
            return (Criteria) this;
        }

        public Criteria andStoreStateIsNull() {
            addCriterion("store_state is null");
            return (Criteria) this;
        }

        public Criteria andStoreStateIsNotNull() {
            addCriterion("store_state is not null");
            return (Criteria) this;
        }

        public Criteria andStoreStateEqualTo(String value) {
            addCriterion("store_state =", value, "storeState");
            return (Criteria) this;
        }

        public Criteria andStoreStateNotEqualTo(String value) {
            addCriterion("store_state <>", value, "storeState");
            return (Criteria) this;
        }

        public Criteria andStoreStateGreaterThan(String value) {
            addCriterion("store_state >", value, "storeState");
            return (Criteria) this;
        }

        public Criteria andStoreStateGreaterThanOrEqualTo(String value) {
            addCriterion("store_state >=", value, "storeState");
            return (Criteria) this;
        }

        public Criteria andStoreStateLessThan(String value) {
            addCriterion("store_state <", value, "storeState");
            return (Criteria) this;
        }

        public Criteria andStoreStateLessThanOrEqualTo(String value) {
            addCriterion("store_state <=", value, "storeState");
            return (Criteria) this;
        }

        public Criteria andStoreStateLike(String value) {
            addCriterion("store_state like", value, "storeState");
            return (Criteria) this;
        }

        public Criteria andStoreStateNotLike(String value) {
            addCriterion("store_state not like", value, "storeState");
            return (Criteria) this;
        }

        public Criteria andStoreStateIn(List<String> values) {
            addCriterion("store_state in", values, "storeState");
            return (Criteria) this;
        }

        public Criteria andStoreStateNotIn(List<String> values) {
            addCriterion("store_state not in", values, "storeState");
            return (Criteria) this;
        }

        public Criteria andStoreStateBetween(String value1, String value2) {
            addCriterion("store_state between", value1, value2, "storeState");
            return (Criteria) this;
        }

        public Criteria andStoreStateNotBetween(String value1, String value2) {
            addCriterion("store_state not between", value1, value2, "storeState");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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