package cn.edu.cqu.kb.model;

import java.util.ArrayList;
import java.util.List;

public class BRuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BRuleExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andChessidIsNull() {
            addCriterion("chessId is null");
            return (Criteria) this;
        }

        public Criteria andChessidIsNotNull() {
            addCriterion("chessId is not null");
            return (Criteria) this;
        }

        public Criteria andChessidEqualTo(Integer value) {
            addCriterion("chessId =", value, "chessid");
            return (Criteria) this;
        }

        public Criteria andChessidNotEqualTo(Integer value) {
            addCriterion("chessId <>", value, "chessid");
            return (Criteria) this;
        }

        public Criteria andChessidGreaterThan(Integer value) {
            addCriterion("chessId >", value, "chessid");
            return (Criteria) this;
        }

        public Criteria andChessidGreaterThanOrEqualTo(Integer value) {
            addCriterion("chessId >=", value, "chessid");
            return (Criteria) this;
        }

        public Criteria andChessidLessThan(Integer value) {
            addCriterion("chessId <", value, "chessid");
            return (Criteria) this;
        }

        public Criteria andChessidLessThanOrEqualTo(Integer value) {
            addCriterion("chessId <=", value, "chessid");
            return (Criteria) this;
        }

        public Criteria andChessidIn(List<Integer> values) {
            addCriterion("chessId in", values, "chessid");
            return (Criteria) this;
        }

        public Criteria andChessidNotIn(List<Integer> values) {
            addCriterion("chessId not in", values, "chessid");
            return (Criteria) this;
        }

        public Criteria andChessidBetween(Integer value1, Integer value2) {
            addCriterion("chessId between", value1, value2, "chessid");
            return (Criteria) this;
        }

        public Criteria andChessidNotBetween(Integer value1, Integer value2) {
            addCriterion("chessId not between", value1, value2, "chessid");
            return (Criteria) this;
        }

        public Criteria andInblackXIsNull() {
            addCriterion("inBlack_X is null");
            return (Criteria) this;
        }

        public Criteria andInblackXIsNotNull() {
            addCriterion("inBlack_X is not null");
            return (Criteria) this;
        }

        public Criteria andInblackXEqualTo(Integer value) {
            addCriterion("inBlack_X =", value, "inblackX");
            return (Criteria) this;
        }

        public Criteria andInblackXNotEqualTo(Integer value) {
            addCriterion("inBlack_X <>", value, "inblackX");
            return (Criteria) this;
        }

        public Criteria andInblackXGreaterThan(Integer value) {
            addCriterion("inBlack_X >", value, "inblackX");
            return (Criteria) this;
        }

        public Criteria andInblackXGreaterThanOrEqualTo(Integer value) {
            addCriterion("inBlack_X >=", value, "inblackX");
            return (Criteria) this;
        }

        public Criteria andInblackXLessThan(Integer value) {
            addCriterion("inBlack_X <", value, "inblackX");
            return (Criteria) this;
        }

        public Criteria andInblackXLessThanOrEqualTo(Integer value) {
            addCriterion("inBlack_X <=", value, "inblackX");
            return (Criteria) this;
        }

        public Criteria andInblackXIn(List<Integer> values) {
            addCriterion("inBlack_X in", values, "inblackX");
            return (Criteria) this;
        }

        public Criteria andInblackXNotIn(List<Integer> values) {
            addCriterion("inBlack_X not in", values, "inblackX");
            return (Criteria) this;
        }

        public Criteria andInblackXBetween(Integer value1, Integer value2) {
            addCriterion("inBlack_X between", value1, value2, "inblackX");
            return (Criteria) this;
        }

        public Criteria andInblackXNotBetween(Integer value1, Integer value2) {
            addCriterion("inBlack_X not between", value1, value2, "inblackX");
            return (Criteria) this;
        }

        public Criteria andInblackYIsNull() {
            addCriterion("inBlack_Y is null");
            return (Criteria) this;
        }

        public Criteria andInblackYIsNotNull() {
            addCriterion("inBlack_Y is not null");
            return (Criteria) this;
        }

        public Criteria andInblackYEqualTo(Integer value) {
            addCriterion("inBlack_Y =", value, "inblackY");
            return (Criteria) this;
        }

        public Criteria andInblackYNotEqualTo(Integer value) {
            addCriterion("inBlack_Y <>", value, "inblackY");
            return (Criteria) this;
        }

        public Criteria andInblackYGreaterThan(Integer value) {
            addCriterion("inBlack_Y >", value, "inblackY");
            return (Criteria) this;
        }

        public Criteria andInblackYGreaterThanOrEqualTo(Integer value) {
            addCriterion("inBlack_Y >=", value, "inblackY");
            return (Criteria) this;
        }

        public Criteria andInblackYLessThan(Integer value) {
            addCriterion("inBlack_Y <", value, "inblackY");
            return (Criteria) this;
        }

        public Criteria andInblackYLessThanOrEqualTo(Integer value) {
            addCriterion("inBlack_Y <=", value, "inblackY");
            return (Criteria) this;
        }

        public Criteria andInblackYIn(List<Integer> values) {
            addCriterion("inBlack_Y in", values, "inblackY");
            return (Criteria) this;
        }

        public Criteria andInblackYNotIn(List<Integer> values) {
            addCriterion("inBlack_Y not in", values, "inblackY");
            return (Criteria) this;
        }

        public Criteria andInblackYBetween(Integer value1, Integer value2) {
            addCriterion("inBlack_Y between", value1, value2, "inblackY");
            return (Criteria) this;
        }

        public Criteria andInblackYNotBetween(Integer value1, Integer value2) {
            addCriterion("inBlack_Y not between", value1, value2, "inblackY");
            return (Criteria) this;
        }

        public Criteria andInredXIsNull() {
            addCriterion("inRed_X is null");
            return (Criteria) this;
        }

        public Criteria andInredXIsNotNull() {
            addCriterion("inRed_X is not null");
            return (Criteria) this;
        }

        public Criteria andInredXEqualTo(Integer value) {
            addCriterion("inRed_X =", value, "inredX");
            return (Criteria) this;
        }

        public Criteria andInredXNotEqualTo(Integer value) {
            addCriterion("inRed_X <>", value, "inredX");
            return (Criteria) this;
        }

        public Criteria andInredXGreaterThan(Integer value) {
            addCriterion("inRed_X >", value, "inredX");
            return (Criteria) this;
        }

        public Criteria andInredXGreaterThanOrEqualTo(Integer value) {
            addCriterion("inRed_X >=", value, "inredX");
            return (Criteria) this;
        }

        public Criteria andInredXLessThan(Integer value) {
            addCriterion("inRed_X <", value, "inredX");
            return (Criteria) this;
        }

        public Criteria andInredXLessThanOrEqualTo(Integer value) {
            addCriterion("inRed_X <=", value, "inredX");
            return (Criteria) this;
        }

        public Criteria andInredXIn(List<Integer> values) {
            addCriterion("inRed_X in", values, "inredX");
            return (Criteria) this;
        }

        public Criteria andInredXNotIn(List<Integer> values) {
            addCriterion("inRed_X not in", values, "inredX");
            return (Criteria) this;
        }

        public Criteria andInredXBetween(Integer value1, Integer value2) {
            addCriterion("inRed_X between", value1, value2, "inredX");
            return (Criteria) this;
        }

        public Criteria andInredXNotBetween(Integer value1, Integer value2) {
            addCriterion("inRed_X not between", value1, value2, "inredX");
            return (Criteria) this;
        }

        public Criteria andInredYIsNull() {
            addCriterion("inRed_Y is null");
            return (Criteria) this;
        }

        public Criteria andInredYIsNotNull() {
            addCriterion("inRed_Y is not null");
            return (Criteria) this;
        }

        public Criteria andInredYEqualTo(Integer value) {
            addCriterion("inRed_Y =", value, "inredY");
            return (Criteria) this;
        }

        public Criteria andInredYNotEqualTo(Integer value) {
            addCriterion("inRed_Y <>", value, "inredY");
            return (Criteria) this;
        }

        public Criteria andInredYGreaterThan(Integer value) {
            addCriterion("inRed_Y >", value, "inredY");
            return (Criteria) this;
        }

        public Criteria andInredYGreaterThanOrEqualTo(Integer value) {
            addCriterion("inRed_Y >=", value, "inredY");
            return (Criteria) this;
        }

        public Criteria andInredYLessThan(Integer value) {
            addCriterion("inRed_Y <", value, "inredY");
            return (Criteria) this;
        }

        public Criteria andInredYLessThanOrEqualTo(Integer value) {
            addCriterion("inRed_Y <=", value, "inredY");
            return (Criteria) this;
        }

        public Criteria andInredYIn(List<Integer> values) {
            addCriterion("inRed_Y in", values, "inredY");
            return (Criteria) this;
        }

        public Criteria andInredYNotIn(List<Integer> values) {
            addCriterion("inRed_Y not in", values, "inredY");
            return (Criteria) this;
        }

        public Criteria andInredYBetween(Integer value1, Integer value2) {
            addCriterion("inRed_Y between", value1, value2, "inredY");
            return (Criteria) this;
        }

        public Criteria andInredYNotBetween(Integer value1, Integer value2) {
            addCriterion("inRed_Y not between", value1, value2, "inredY");
            return (Criteria) this;
        }

        public Criteria andExistblackXIsNull() {
            addCriterion("existBlack_X is null");
            return (Criteria) this;
        }

        public Criteria andExistblackXIsNotNull() {
            addCriterion("existBlack_X is not null");
            return (Criteria) this;
        }

        public Criteria andExistblackXEqualTo(Integer value) {
            addCriterion("existBlack_X =", value, "existblackX");
            return (Criteria) this;
        }

        public Criteria andExistblackXNotEqualTo(Integer value) {
            addCriterion("existBlack_X <>", value, "existblackX");
            return (Criteria) this;
        }

        public Criteria andExistblackXGreaterThan(Integer value) {
            addCriterion("existBlack_X >", value, "existblackX");
            return (Criteria) this;
        }

        public Criteria andExistblackXGreaterThanOrEqualTo(Integer value) {
            addCriterion("existBlack_X >=", value, "existblackX");
            return (Criteria) this;
        }

        public Criteria andExistblackXLessThan(Integer value) {
            addCriterion("existBlack_X <", value, "existblackX");
            return (Criteria) this;
        }

        public Criteria andExistblackXLessThanOrEqualTo(Integer value) {
            addCriterion("existBlack_X <=", value, "existblackX");
            return (Criteria) this;
        }

        public Criteria andExistblackXIn(List<Integer> values) {
            addCriterion("existBlack_X in", values, "existblackX");
            return (Criteria) this;
        }

        public Criteria andExistblackXNotIn(List<Integer> values) {
            addCriterion("existBlack_X not in", values, "existblackX");
            return (Criteria) this;
        }

        public Criteria andExistblackXBetween(Integer value1, Integer value2) {
            addCriterion("existBlack_X between", value1, value2, "existblackX");
            return (Criteria) this;
        }

        public Criteria andExistblackXNotBetween(Integer value1, Integer value2) {
            addCriterion("existBlack_X not between", value1, value2, "existblackX");
            return (Criteria) this;
        }

        public Criteria andExistblackYIsNull() {
            addCriterion("existBlack_Y is null");
            return (Criteria) this;
        }

        public Criteria andExistblackYIsNotNull() {
            addCriterion("existBlack_Y is not null");
            return (Criteria) this;
        }

        public Criteria andExistblackYEqualTo(Integer value) {
            addCriterion("existBlack_Y =", value, "existblackY");
            return (Criteria) this;
        }

        public Criteria andExistblackYNotEqualTo(Integer value) {
            addCriterion("existBlack_Y <>", value, "existblackY");
            return (Criteria) this;
        }

        public Criteria andExistblackYGreaterThan(Integer value) {
            addCriterion("existBlack_Y >", value, "existblackY");
            return (Criteria) this;
        }

        public Criteria andExistblackYGreaterThanOrEqualTo(Integer value) {
            addCriterion("existBlack_Y >=", value, "existblackY");
            return (Criteria) this;
        }

        public Criteria andExistblackYLessThan(Integer value) {
            addCriterion("existBlack_Y <", value, "existblackY");
            return (Criteria) this;
        }

        public Criteria andExistblackYLessThanOrEqualTo(Integer value) {
            addCriterion("existBlack_Y <=", value, "existblackY");
            return (Criteria) this;
        }

        public Criteria andExistblackYIn(List<Integer> values) {
            addCriterion("existBlack_Y in", values, "existblackY");
            return (Criteria) this;
        }

        public Criteria andExistblackYNotIn(List<Integer> values) {
            addCriterion("existBlack_Y not in", values, "existblackY");
            return (Criteria) this;
        }

        public Criteria andExistblackYBetween(Integer value1, Integer value2) {
            addCriterion("existBlack_Y between", value1, value2, "existblackY");
            return (Criteria) this;
        }

        public Criteria andExistblackYNotBetween(Integer value1, Integer value2) {
            addCriterion("existBlack_Y not between", value1, value2, "existblackY");
            return (Criteria) this;
        }

        public Criteria andExistredXIsNull() {
            addCriterion("existRed_X is null");
            return (Criteria) this;
        }

        public Criteria andExistredXIsNotNull() {
            addCriterion("existRed_X is not null");
            return (Criteria) this;
        }

        public Criteria andExistredXEqualTo(Integer value) {
            addCriterion("existRed_X =", value, "existredX");
            return (Criteria) this;
        }

        public Criteria andExistredXNotEqualTo(Integer value) {
            addCriterion("existRed_X <>", value, "existredX");
            return (Criteria) this;
        }

        public Criteria andExistredXGreaterThan(Integer value) {
            addCriterion("existRed_X >", value, "existredX");
            return (Criteria) this;
        }

        public Criteria andExistredXGreaterThanOrEqualTo(Integer value) {
            addCriterion("existRed_X >=", value, "existredX");
            return (Criteria) this;
        }

        public Criteria andExistredXLessThan(Integer value) {
            addCriterion("existRed_X <", value, "existredX");
            return (Criteria) this;
        }

        public Criteria andExistredXLessThanOrEqualTo(Integer value) {
            addCriterion("existRed_X <=", value, "existredX");
            return (Criteria) this;
        }

        public Criteria andExistredXIn(List<Integer> values) {
            addCriterion("existRed_X in", values, "existredX");
            return (Criteria) this;
        }

        public Criteria andExistredXNotIn(List<Integer> values) {
            addCriterion("existRed_X not in", values, "existredX");
            return (Criteria) this;
        }

        public Criteria andExistredXBetween(Integer value1, Integer value2) {
            addCriterion("existRed_X between", value1, value2, "existredX");
            return (Criteria) this;
        }

        public Criteria andExistredXNotBetween(Integer value1, Integer value2) {
            addCriterion("existRed_X not between", value1, value2, "existredX");
            return (Criteria) this;
        }

        public Criteria andExistredYIsNull() {
            addCriterion("existRed_Y is null");
            return (Criteria) this;
        }

        public Criteria andExistredYIsNotNull() {
            addCriterion("existRed_Y is not null");
            return (Criteria) this;
        }

        public Criteria andExistredYEqualTo(Integer value) {
            addCriterion("existRed_Y =", value, "existredY");
            return (Criteria) this;
        }

        public Criteria andExistredYNotEqualTo(Integer value) {
            addCriterion("existRed_Y <>", value, "existredY");
            return (Criteria) this;
        }

        public Criteria andExistredYGreaterThan(Integer value) {
            addCriterion("existRed_Y >", value, "existredY");
            return (Criteria) this;
        }

        public Criteria andExistredYGreaterThanOrEqualTo(Integer value) {
            addCriterion("existRed_Y >=", value, "existredY");
            return (Criteria) this;
        }

        public Criteria andExistredYLessThan(Integer value) {
            addCriterion("existRed_Y <", value, "existredY");
            return (Criteria) this;
        }

        public Criteria andExistredYLessThanOrEqualTo(Integer value) {
            addCriterion("existRed_Y <=", value, "existredY");
            return (Criteria) this;
        }

        public Criteria andExistredYIn(List<Integer> values) {
            addCriterion("existRed_Y in", values, "existredY");
            return (Criteria) this;
        }

        public Criteria andExistredYNotIn(List<Integer> values) {
            addCriterion("existRed_Y not in", values, "existredY");
            return (Criteria) this;
        }

        public Criteria andExistredYBetween(Integer value1, Integer value2) {
            addCriterion("existRed_Y between", value1, value2, "existredY");
            return (Criteria) this;
        }

        public Criteria andExistredYNotBetween(Integer value1, Integer value2) {
            addCriterion("existRed_Y not between", value1, value2, "existredY");
            return (Criteria) this;
        }

        public Criteria andNotexistredXIsNull() {
            addCriterion("notExistRed_X is null");
            return (Criteria) this;
        }

        public Criteria andNotexistredXIsNotNull() {
            addCriterion("notExistRed_X is not null");
            return (Criteria) this;
        }

        public Criteria andNotexistredXEqualTo(Integer value) {
            addCriterion("notExistRed_X =", value, "notexistredX");
            return (Criteria) this;
        }

        public Criteria andNotexistredXNotEqualTo(Integer value) {
            addCriterion("notExistRed_X <>", value, "notexistredX");
            return (Criteria) this;
        }

        public Criteria andNotexistredXGreaterThan(Integer value) {
            addCriterion("notExistRed_X >", value, "notexistredX");
            return (Criteria) this;
        }

        public Criteria andNotexistredXGreaterThanOrEqualTo(Integer value) {
            addCriterion("notExistRed_X >=", value, "notexistredX");
            return (Criteria) this;
        }

        public Criteria andNotexistredXLessThan(Integer value) {
            addCriterion("notExistRed_X <", value, "notexistredX");
            return (Criteria) this;
        }

        public Criteria andNotexistredXLessThanOrEqualTo(Integer value) {
            addCriterion("notExistRed_X <=", value, "notexistredX");
            return (Criteria) this;
        }

        public Criteria andNotexistredXIn(List<Integer> values) {
            addCriterion("notExistRed_X in", values, "notexistredX");
            return (Criteria) this;
        }

        public Criteria andNotexistredXNotIn(List<Integer> values) {
            addCriterion("notExistRed_X not in", values, "notexistredX");
            return (Criteria) this;
        }

        public Criteria andNotexistredXBetween(Integer value1, Integer value2) {
            addCriterion("notExistRed_X between", value1, value2, "notexistredX");
            return (Criteria) this;
        }

        public Criteria andNotexistredXNotBetween(Integer value1, Integer value2) {
            addCriterion("notExistRed_X not between", value1, value2, "notexistredX");
            return (Criteria) this;
        }

        public Criteria andNotexistredYIsNull() {
            addCriterion("notExistRed_Y is null");
            return (Criteria) this;
        }

        public Criteria andNotexistredYIsNotNull() {
            addCriterion("notExistRed_Y is not null");
            return (Criteria) this;
        }

        public Criteria andNotexistredYEqualTo(Integer value) {
            addCriterion("notExistRed_Y =", value, "notexistredY");
            return (Criteria) this;
        }

        public Criteria andNotexistredYNotEqualTo(Integer value) {
            addCriterion("notExistRed_Y <>", value, "notexistredY");
            return (Criteria) this;
        }

        public Criteria andNotexistredYGreaterThan(Integer value) {
            addCriterion("notExistRed_Y >", value, "notexistredY");
            return (Criteria) this;
        }

        public Criteria andNotexistredYGreaterThanOrEqualTo(Integer value) {
            addCriterion("notExistRed_Y >=", value, "notexistredY");
            return (Criteria) this;
        }

        public Criteria andNotexistredYLessThan(Integer value) {
            addCriterion("notExistRed_Y <", value, "notexistredY");
            return (Criteria) this;
        }

        public Criteria andNotexistredYLessThanOrEqualTo(Integer value) {
            addCriterion("notExistRed_Y <=", value, "notexistredY");
            return (Criteria) this;
        }

        public Criteria andNotexistredYIn(List<Integer> values) {
            addCriterion("notExistRed_Y in", values, "notexistredY");
            return (Criteria) this;
        }

        public Criteria andNotexistredYNotIn(List<Integer> values) {
            addCriterion("notExistRed_Y not in", values, "notexistredY");
            return (Criteria) this;
        }

        public Criteria andNotexistredYBetween(Integer value1, Integer value2) {
            addCriterion("notExistRed_Y between", value1, value2, "notexistredY");
            return (Criteria) this;
        }

        public Criteria andNotexistredYNotBetween(Integer value1, Integer value2) {
            addCriterion("notExistRed_Y not between", value1, value2, "notexistredY");
            return (Criteria) this;
        }

        public Criteria andNotexistblackXIsNull() {
            addCriterion("notExistBlack_X is null");
            return (Criteria) this;
        }

        public Criteria andNotexistblackXIsNotNull() {
            addCriterion("notExistBlack_X is not null");
            return (Criteria) this;
        }

        public Criteria andNotexistblackXEqualTo(Integer value) {
            addCriterion("notExistBlack_X =", value, "notexistblackX");
            return (Criteria) this;
        }

        public Criteria andNotexistblackXNotEqualTo(Integer value) {
            addCriterion("notExistBlack_X <>", value, "notexistblackX");
            return (Criteria) this;
        }

        public Criteria andNotexistblackXGreaterThan(Integer value) {
            addCriterion("notExistBlack_X >", value, "notexistblackX");
            return (Criteria) this;
        }

        public Criteria andNotexistblackXGreaterThanOrEqualTo(Integer value) {
            addCriterion("notExistBlack_X >=", value, "notexistblackX");
            return (Criteria) this;
        }

        public Criteria andNotexistblackXLessThan(Integer value) {
            addCriterion("notExistBlack_X <", value, "notexistblackX");
            return (Criteria) this;
        }

        public Criteria andNotexistblackXLessThanOrEqualTo(Integer value) {
            addCriterion("notExistBlack_X <=", value, "notexistblackX");
            return (Criteria) this;
        }

        public Criteria andNotexistblackXIn(List<Integer> values) {
            addCriterion("notExistBlack_X in", values, "notexistblackX");
            return (Criteria) this;
        }

        public Criteria andNotexistblackXNotIn(List<Integer> values) {
            addCriterion("notExistBlack_X not in", values, "notexistblackX");
            return (Criteria) this;
        }

        public Criteria andNotexistblackXBetween(Integer value1, Integer value2) {
            addCriterion("notExistBlack_X between", value1, value2, "notexistblackX");
            return (Criteria) this;
        }

        public Criteria andNotexistblackXNotBetween(Integer value1, Integer value2) {
            addCriterion("notExistBlack_X not between", value1, value2, "notexistblackX");
            return (Criteria) this;
        }

        public Criteria andNotexistblackYIsNull() {
            addCriterion("notExistBlack_Y is null");
            return (Criteria) this;
        }

        public Criteria andNotexistblackYIsNotNull() {
            addCriterion("notExistBlack_Y is not null");
            return (Criteria) this;
        }

        public Criteria andNotexistblackYEqualTo(Integer value) {
            addCriterion("notExistBlack_Y =", value, "notexistblackY");
            return (Criteria) this;
        }

        public Criteria andNotexistblackYNotEqualTo(Integer value) {
            addCriterion("notExistBlack_Y <>", value, "notexistblackY");
            return (Criteria) this;
        }

        public Criteria andNotexistblackYGreaterThan(Integer value) {
            addCriterion("notExistBlack_Y >", value, "notexistblackY");
            return (Criteria) this;
        }

        public Criteria andNotexistblackYGreaterThanOrEqualTo(Integer value) {
            addCriterion("notExistBlack_Y >=", value, "notexistblackY");
            return (Criteria) this;
        }

        public Criteria andNotexistblackYLessThan(Integer value) {
            addCriterion("notExistBlack_Y <", value, "notexistblackY");
            return (Criteria) this;
        }

        public Criteria andNotexistblackYLessThanOrEqualTo(Integer value) {
            addCriterion("notExistBlack_Y <=", value, "notexistblackY");
            return (Criteria) this;
        }

        public Criteria andNotexistblackYIn(List<Integer> values) {
            addCriterion("notExistBlack_Y in", values, "notexistblackY");
            return (Criteria) this;
        }

        public Criteria andNotexistblackYNotIn(List<Integer> values) {
            addCriterion("notExistBlack_Y not in", values, "notexistblackY");
            return (Criteria) this;
        }

        public Criteria andNotexistblackYBetween(Integer value1, Integer value2) {
            addCriterion("notExistBlack_Y between", value1, value2, "notexistblackY");
            return (Criteria) this;
        }

        public Criteria andNotexistblackYNotBetween(Integer value1, Integer value2) {
            addCriterion("notExistBlack_Y not between", value1, value2, "notexistblackY");
            return (Criteria) this;
        }

        public Criteria andMovetoXIsNull() {
            addCriterion("moveTo_X is null");
            return (Criteria) this;
        }

        public Criteria andMovetoXIsNotNull() {
            addCriterion("moveTo_X is not null");
            return (Criteria) this;
        }

        public Criteria andMovetoXEqualTo(Integer value) {
            addCriterion("moveTo_X =", value, "movetoX");
            return (Criteria) this;
        }

        public Criteria andMovetoXNotEqualTo(Integer value) {
            addCriterion("moveTo_X <>", value, "movetoX");
            return (Criteria) this;
        }

        public Criteria andMovetoXGreaterThan(Integer value) {
            addCriterion("moveTo_X >", value, "movetoX");
            return (Criteria) this;
        }

        public Criteria andMovetoXGreaterThanOrEqualTo(Integer value) {
            addCriterion("moveTo_X >=", value, "movetoX");
            return (Criteria) this;
        }

        public Criteria andMovetoXLessThan(Integer value) {
            addCriterion("moveTo_X <", value, "movetoX");
            return (Criteria) this;
        }

        public Criteria andMovetoXLessThanOrEqualTo(Integer value) {
            addCriterion("moveTo_X <=", value, "movetoX");
            return (Criteria) this;
        }

        public Criteria andMovetoXIn(List<Integer> values) {
            addCriterion("moveTo_X in", values, "movetoX");
            return (Criteria) this;
        }

        public Criteria andMovetoXNotIn(List<Integer> values) {
            addCriterion("moveTo_X not in", values, "movetoX");
            return (Criteria) this;
        }

        public Criteria andMovetoXBetween(Integer value1, Integer value2) {
            addCriterion("moveTo_X between", value1, value2, "movetoX");
            return (Criteria) this;
        }

        public Criteria andMovetoXNotBetween(Integer value1, Integer value2) {
            addCriterion("moveTo_X not between", value1, value2, "movetoX");
            return (Criteria) this;
        }

        public Criteria andMovetoYIsNull() {
            addCriterion("moveTo_Y is null");
            return (Criteria) this;
        }

        public Criteria andMovetoYIsNotNull() {
            addCriterion("moveTo_Y is not null");
            return (Criteria) this;
        }

        public Criteria andMovetoYEqualTo(Integer value) {
            addCriterion("moveTo_Y =", value, "movetoY");
            return (Criteria) this;
        }

        public Criteria andMovetoYNotEqualTo(Integer value) {
            addCriterion("moveTo_Y <>", value, "movetoY");
            return (Criteria) this;
        }

        public Criteria andMovetoYGreaterThan(Integer value) {
            addCriterion("moveTo_Y >", value, "movetoY");
            return (Criteria) this;
        }

        public Criteria andMovetoYGreaterThanOrEqualTo(Integer value) {
            addCriterion("moveTo_Y >=", value, "movetoY");
            return (Criteria) this;
        }

        public Criteria andMovetoYLessThan(Integer value) {
            addCriterion("moveTo_Y <", value, "movetoY");
            return (Criteria) this;
        }

        public Criteria andMovetoYLessThanOrEqualTo(Integer value) {
            addCriterion("moveTo_Y <=", value, "movetoY");
            return (Criteria) this;
        }

        public Criteria andMovetoYIn(List<Integer> values) {
            addCriterion("moveTo_Y in", values, "movetoY");
            return (Criteria) this;
        }

        public Criteria andMovetoYNotIn(List<Integer> values) {
            addCriterion("moveTo_Y not in", values, "movetoY");
            return (Criteria) this;
        }

        public Criteria andMovetoYBetween(Integer value1, Integer value2) {
            addCriterion("moveTo_Y between", value1, value2, "movetoY");
            return (Criteria) this;
        }

        public Criteria andMovetoYNotBetween(Integer value1, Integer value2) {
            addCriterion("moveTo_Y not between", value1, value2, "movetoY");
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