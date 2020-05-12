package com.yicheng.tourism.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class commodityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public commodityExample() {
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

        public Criteria andCommodityNumberIsNull() {
            addCriterion("commodity_number is null");
            return (Criteria) this;
        }

        public Criteria andCommodityNumberIsNotNull() {
            addCriterion("commodity_number is not null");
            return (Criteria) this;
        }

        public Criteria andCommodityNumberEqualTo(String value) {
            addCriterion("commodity_number =", value, "commodityNumber");
            return (Criteria) this;
        }

        public Criteria andCommodityNumberNotEqualTo(String value) {
            addCriterion("commodity_number <>", value, "commodityNumber");
            return (Criteria) this;
        }

        public Criteria andCommodityNumberGreaterThan(String value) {
            addCriterion("commodity_number >", value, "commodityNumber");
            return (Criteria) this;
        }

        public Criteria andCommodityNumberGreaterThanOrEqualTo(String value) {
            addCriterion("commodity_number >=", value, "commodityNumber");
            return (Criteria) this;
        }

        public Criteria andCommodityNumberLessThan(String value) {
            addCriterion("commodity_number <", value, "commodityNumber");
            return (Criteria) this;
        }

        public Criteria andCommodityNumberLessThanOrEqualTo(String value) {
            addCriterion("commodity_number <=", value, "commodityNumber");
            return (Criteria) this;
        }

        public Criteria andCommodityNumberLike(String value) {
            addCriterion("commodity_number like", value, "commodityNumber");
            return (Criteria) this;
        }

        public Criteria andCommodityNumberNotLike(String value) {
            addCriterion("commodity_number not like", value, "commodityNumber");
            return (Criteria) this;
        }

        public Criteria andCommodityNumberIn(List<String> values) {
            addCriterion("commodity_number in", values, "commodityNumber");
            return (Criteria) this;
        }

        public Criteria andCommodityNumberNotIn(List<String> values) {
            addCriterion("commodity_number not in", values, "commodityNumber");
            return (Criteria) this;
        }

        public Criteria andCommodityNumberBetween(String value1, String value2) {
            addCriterion("commodity_number between", value1, value2, "commodityNumber");
            return (Criteria) this;
        }

        public Criteria andCommodityNumberNotBetween(String value1, String value2) {
            addCriterion("commodity_number not between", value1, value2, "commodityNumber");
            return (Criteria) this;
        }

        public Criteria andCommodityNameIsNull() {
            addCriterion("commodity_name is null");
            return (Criteria) this;
        }

        public Criteria andCommodityNameIsNotNull() {
            addCriterion("commodity_name is not null");
            return (Criteria) this;
        }

        public Criteria andCommodityNameEqualTo(String value) {
            addCriterion("commodity_name =", value, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameNotEqualTo(String value) {
            addCriterion("commodity_name <>", value, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameGreaterThan(String value) {
            addCriterion("commodity_name >", value, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameGreaterThanOrEqualTo(String value) {
            addCriterion("commodity_name >=", value, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameLessThan(String value) {
            addCriterion("commodity_name <", value, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameLessThanOrEqualTo(String value) {
            addCriterion("commodity_name <=", value, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameLike(String value) {
            addCriterion("commodity_name like", value, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameNotLike(String value) {
            addCriterion("commodity_name not like", value, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameIn(List<String> values) {
            addCriterion("commodity_name in", values, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameNotIn(List<String> values) {
            addCriterion("commodity_name not in", values, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameBetween(String value1, String value2) {
            addCriterion("commodity_name between", value1, value2, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityNameNotBetween(String value1, String value2) {
            addCriterion("commodity_name not between", value1, value2, "commodityName");
            return (Criteria) this;
        }

        public Criteria andCommodityScriptIsNull() {
            addCriterion("commodity_script is null");
            return (Criteria) this;
        }

        public Criteria andCommodityScriptIsNotNull() {
            addCriterion("commodity_script is not null");
            return (Criteria) this;
        }

        public Criteria andCommodityScriptEqualTo(String value) {
            addCriterion("commodity_script =", value, "commodityScript");
            return (Criteria) this;
        }

        public Criteria andCommodityScriptNotEqualTo(String value) {
            addCriterion("commodity_script <>", value, "commodityScript");
            return (Criteria) this;
        }

        public Criteria andCommodityScriptGreaterThan(String value) {
            addCriterion("commodity_script >", value, "commodityScript");
            return (Criteria) this;
        }

        public Criteria andCommodityScriptGreaterThanOrEqualTo(String value) {
            addCriterion("commodity_script >=", value, "commodityScript");
            return (Criteria) this;
        }

        public Criteria andCommodityScriptLessThan(String value) {
            addCriterion("commodity_script <", value, "commodityScript");
            return (Criteria) this;
        }

        public Criteria andCommodityScriptLessThanOrEqualTo(String value) {
            addCriterion("commodity_script <=", value, "commodityScript");
            return (Criteria) this;
        }

        public Criteria andCommodityScriptLike(String value) {
            addCriterion("commodity_script like", value, "commodityScript");
            return (Criteria) this;
        }

        public Criteria andCommodityScriptNotLike(String value) {
            addCriterion("commodity_script not like", value, "commodityScript");
            return (Criteria) this;
        }

        public Criteria andCommodityScriptIn(List<String> values) {
            addCriterion("commodity_script in", values, "commodityScript");
            return (Criteria) this;
        }

        public Criteria andCommodityScriptNotIn(List<String> values) {
            addCriterion("commodity_script not in", values, "commodityScript");
            return (Criteria) this;
        }

        public Criteria andCommodityScriptBetween(String value1, String value2) {
            addCriterion("commodity_script between", value1, value2, "commodityScript");
            return (Criteria) this;
        }

        public Criteria andCommodityScriptNotBetween(String value1, String value2) {
            addCriterion("commodity_script not between", value1, value2, "commodityScript");
            return (Criteria) this;
        }

        public Criteria andCommodityPriceIsNull() {
            addCriterion("commodity_price is null");
            return (Criteria) this;
        }

        public Criteria andCommodityPriceIsNotNull() {
            addCriterion("commodity_price is not null");
            return (Criteria) this;
        }

        public Criteria andCommodityPriceEqualTo(Double value) {
            addCriterion("commodity_price =", value, "commodityPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityPriceNotEqualTo(Double value) {
            addCriterion("commodity_price <>", value, "commodityPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityPriceGreaterThan(Double value) {
            addCriterion("commodity_price >", value, "commodityPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("commodity_price >=", value, "commodityPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityPriceLessThan(Double value) {
            addCriterion("commodity_price <", value, "commodityPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityPriceLessThanOrEqualTo(Double value) {
            addCriterion("commodity_price <=", value, "commodityPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityPriceIn(List<Double> values) {
            addCriterion("commodity_price in", values, "commodityPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityPriceNotIn(List<Double> values) {
            addCriterion("commodity_price not in", values, "commodityPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityPriceBetween(Double value1, Double value2) {
            addCriterion("commodity_price between", value1, value2, "commodityPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityPriceNotBetween(Double value1, Double value2) {
            addCriterion("commodity_price not between", value1, value2, "commodityPrice");
            return (Criteria) this;
        }

        public Criteria andCommodityStateIsNull() {
            addCriterion("commodity_state is null");
            return (Criteria) this;
        }

        public Criteria andCommodityStateIsNotNull() {
            addCriterion("commodity_state is not null");
            return (Criteria) this;
        }

        public Criteria andCommodityStateEqualTo(String value) {
            addCriterion("commodity_state =", value, "commodityState");
            return (Criteria) this;
        }

        public Criteria andCommodityStateNotEqualTo(String value) {
            addCriterion("commodity_state <>", value, "commodityState");
            return (Criteria) this;
        }

        public Criteria andCommodityStateGreaterThan(String value) {
            addCriterion("commodity_state >", value, "commodityState");
            return (Criteria) this;
        }

        public Criteria andCommodityStateGreaterThanOrEqualTo(String value) {
            addCriterion("commodity_state >=", value, "commodityState");
            return (Criteria) this;
        }

        public Criteria andCommodityStateLessThan(String value) {
            addCriterion("commodity_state <", value, "commodityState");
            return (Criteria) this;
        }

        public Criteria andCommodityStateLessThanOrEqualTo(String value) {
            addCriterion("commodity_state <=", value, "commodityState");
            return (Criteria) this;
        }

        public Criteria andCommodityStateLike(String value) {
            addCriterion("commodity_state like", value, "commodityState");
            return (Criteria) this;
        }

        public Criteria andCommodityStateNotLike(String value) {
            addCriterion("commodity_state not like", value, "commodityState");
            return (Criteria) this;
        }

        public Criteria andCommodityStateIn(List<String> values) {
            addCriterion("commodity_state in", values, "commodityState");
            return (Criteria) this;
        }

        public Criteria andCommodityStateNotIn(List<String> values) {
            addCriterion("commodity_state not in", values, "commodityState");
            return (Criteria) this;
        }

        public Criteria andCommodityStateBetween(String value1, String value2) {
            addCriterion("commodity_state between", value1, value2, "commodityState");
            return (Criteria) this;
        }

        public Criteria andCommodityStateNotBetween(String value1, String value2) {
            addCriterion("commodity_state not between", value1, value2, "commodityState");
            return (Criteria) this;
        }

        public Criteria andCommodityAuthorNumberIsNull() {
            addCriterion("commodity_author_number is null");
            return (Criteria) this;
        }

        public Criteria andCommodityAuthorNumberIsNotNull() {
            addCriterion("commodity_author_number is not null");
            return (Criteria) this;
        }

        public Criteria andCommodityAuthorNumberEqualTo(String value) {
            addCriterion("commodity_author_number =", value, "commodityAuthorNumber");
            return (Criteria) this;
        }

        public Criteria andCommodityAuthorNumberNotEqualTo(String value) {
            addCriterion("commodity_author_number <>", value, "commodityAuthorNumber");
            return (Criteria) this;
        }

        public Criteria andCommodityAuthorNumberGreaterThan(String value) {
            addCriterion("commodity_author_number >", value, "commodityAuthorNumber");
            return (Criteria) this;
        }

        public Criteria andCommodityAuthorNumberGreaterThanOrEqualTo(String value) {
            addCriterion("commodity_author_number >=", value, "commodityAuthorNumber");
            return (Criteria) this;
        }

        public Criteria andCommodityAuthorNumberLessThan(String value) {
            addCriterion("commodity_author_number <", value, "commodityAuthorNumber");
            return (Criteria) this;
        }

        public Criteria andCommodityAuthorNumberLessThanOrEqualTo(String value) {
            addCriterion("commodity_author_number <=", value, "commodityAuthorNumber");
            return (Criteria) this;
        }

        public Criteria andCommodityAuthorNumberLike(String value) {
            addCriterion("commodity_author_number like", value, "commodityAuthorNumber");
            return (Criteria) this;
        }

        public Criteria andCommodityAuthorNumberNotLike(String value) {
            addCriterion("commodity_author_number not like", value, "commodityAuthorNumber");
            return (Criteria) this;
        }

        public Criteria andCommodityAuthorNumberIn(List<String> values) {
            addCriterion("commodity_author_number in", values, "commodityAuthorNumber");
            return (Criteria) this;
        }

        public Criteria andCommodityAuthorNumberNotIn(List<String> values) {
            addCriterion("commodity_author_number not in", values, "commodityAuthorNumber");
            return (Criteria) this;
        }

        public Criteria andCommodityAuthorNumberBetween(String value1, String value2) {
            addCriterion("commodity_author_number between", value1, value2, "commodityAuthorNumber");
            return (Criteria) this;
        }

        public Criteria andCommodityAuthorNumberNotBetween(String value1, String value2) {
            addCriterion("commodity_author_number not between", value1, value2, "commodityAuthorNumber");
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