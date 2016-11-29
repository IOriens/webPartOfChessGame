package cn.edu.cqu.kb.model;

import java.util.ArrayList;
import java.util.List;

public class PRuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PRuleExample() {
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

        public Criteria andNotexistinxX1IsNull() {
            addCriterion("notExistInX_X1 is null");
            return (Criteria) this;
        }

        public Criteria andNotexistinxX1IsNotNull() {
            addCriterion("notExistInX_X1 is not null");
            return (Criteria) this;
        }

        public Criteria andNotexistinxX1EqualTo(Integer value) {
            addCriterion("notExistInX_X1 =", value, "notexistinxX1");
            return (Criteria) this;
        }

        public Criteria andNotexistinxX1NotEqualTo(Integer value) {
            addCriterion("notExistInX_X1 <>", value, "notexistinxX1");
            return (Criteria) this;
        }

        public Criteria andNotexistinxX1GreaterThan(Integer value) {
            addCriterion("notExistInX_X1 >", value, "notexistinxX1");
            return (Criteria) this;
        }

        public Criteria andNotexistinxX1GreaterThanOrEqualTo(Integer value) {
            addCriterion("notExistInX_X1 >=", value, "notexistinxX1");
            return (Criteria) this;
        }

        public Criteria andNotexistinxX1LessThan(Integer value) {
            addCriterion("notExistInX_X1 <", value, "notexistinxX1");
            return (Criteria) this;
        }

        public Criteria andNotexistinxX1LessThanOrEqualTo(Integer value) {
            addCriterion("notExistInX_X1 <=", value, "notexistinxX1");
            return (Criteria) this;
        }

        public Criteria andNotexistinxX1In(List<Integer> values) {
            addCriterion("notExistInX_X1 in", values, "notexistinxX1");
            return (Criteria) this;
        }

        public Criteria andNotexistinxX1NotIn(List<Integer> values) {
            addCriterion("notExistInX_X1 not in", values, "notexistinxX1");
            return (Criteria) this;
        }

        public Criteria andNotexistinxX1Between(Integer value1, Integer value2) {
            addCriterion("notExistInX_X1 between", value1, value2, "notexistinxX1");
            return (Criteria) this;
        }

        public Criteria andNotexistinxX1NotBetween(Integer value1, Integer value2) {
            addCriterion("notExistInX_X1 not between", value1, value2, "notexistinxX1");
            return (Criteria) this;
        }

        public Criteria andNotexistinxX2IsNull() {
            addCriterion("notExistInX_X2 is null");
            return (Criteria) this;
        }

        public Criteria andNotexistinxX2IsNotNull() {
            addCriterion("notExistInX_X2 is not null");
            return (Criteria) this;
        }

        public Criteria andNotexistinxX2EqualTo(Integer value) {
            addCriterion("notExistInX_X2 =", value, "notexistinxX2");
            return (Criteria) this;
        }

        public Criteria andNotexistinxX2NotEqualTo(Integer value) {
            addCriterion("notExistInX_X2 <>", value, "notexistinxX2");
            return (Criteria) this;
        }

        public Criteria andNotexistinxX2GreaterThan(Integer value) {
            addCriterion("notExistInX_X2 >", value, "notexistinxX2");
            return (Criteria) this;
        }

        public Criteria andNotexistinxX2GreaterThanOrEqualTo(Integer value) {
            addCriterion("notExistInX_X2 >=", value, "notexistinxX2");
            return (Criteria) this;
        }

        public Criteria andNotexistinxX2LessThan(Integer value) {
            addCriterion("notExistInX_X2 <", value, "notexistinxX2");
            return (Criteria) this;
        }

        public Criteria andNotexistinxX2LessThanOrEqualTo(Integer value) {
            addCriterion("notExistInX_X2 <=", value, "notexistinxX2");
            return (Criteria) this;
        }

        public Criteria andNotexistinxX2In(List<Integer> values) {
            addCriterion("notExistInX_X2 in", values, "notexistinxX2");
            return (Criteria) this;
        }

        public Criteria andNotexistinxX2NotIn(List<Integer> values) {
            addCriterion("notExistInX_X2 not in", values, "notexistinxX2");
            return (Criteria) this;
        }

        public Criteria andNotexistinxX2Between(Integer value1, Integer value2) {
            addCriterion("notExistInX_X2 between", value1, value2, "notexistinxX2");
            return (Criteria) this;
        }

        public Criteria andNotexistinxX2NotBetween(Integer value1, Integer value2) {
            addCriterion("notExistInX_X2 not between", value1, value2, "notexistinxX2");
            return (Criteria) this;
        }

        public Criteria andNotexistinyY1IsNull() {
            addCriterion("notExistInY_Y1 is null");
            return (Criteria) this;
        }

        public Criteria andNotexistinyY1IsNotNull() {
            addCriterion("notExistInY_Y1 is not null");
            return (Criteria) this;
        }

        public Criteria andNotexistinyY1EqualTo(Integer value) {
            addCriterion("notExistInY_Y1 =", value, "notexistinyY1");
            return (Criteria) this;
        }

        public Criteria andNotexistinyY1NotEqualTo(Integer value) {
            addCriterion("notExistInY_Y1 <>", value, "notexistinyY1");
            return (Criteria) this;
        }

        public Criteria andNotexistinyY1GreaterThan(Integer value) {
            addCriterion("notExistInY_Y1 >", value, "notexistinyY1");
            return (Criteria) this;
        }

        public Criteria andNotexistinyY1GreaterThanOrEqualTo(Integer value) {
            addCriterion("notExistInY_Y1 >=", value, "notexistinyY1");
            return (Criteria) this;
        }

        public Criteria andNotexistinyY1LessThan(Integer value) {
            addCriterion("notExistInY_Y1 <", value, "notexistinyY1");
            return (Criteria) this;
        }

        public Criteria andNotexistinyY1LessThanOrEqualTo(Integer value) {
            addCriterion("notExistInY_Y1 <=", value, "notexistinyY1");
            return (Criteria) this;
        }

        public Criteria andNotexistinyY1In(List<Integer> values) {
            addCriterion("notExistInY_Y1 in", values, "notexistinyY1");
            return (Criteria) this;
        }

        public Criteria andNotexistinyY1NotIn(List<Integer> values) {
            addCriterion("notExistInY_Y1 not in", values, "notexistinyY1");
            return (Criteria) this;
        }

        public Criteria andNotexistinyY1Between(Integer value1, Integer value2) {
            addCriterion("notExistInY_Y1 between", value1, value2, "notexistinyY1");
            return (Criteria) this;
        }

        public Criteria andNotexistinyY1NotBetween(Integer value1, Integer value2) {
            addCriterion("notExistInY_Y1 not between", value1, value2, "notexistinyY1");
            return (Criteria) this;
        }

        public Criteria andNotexistinyY2IsNull() {
            addCriterion("notExistInY_Y2 is null");
            return (Criteria) this;
        }

        public Criteria andNotexistinyY2IsNotNull() {
            addCriterion("notExistInY_Y2 is not null");
            return (Criteria) this;
        }

        public Criteria andNotexistinyY2EqualTo(Integer value) {
            addCriterion("notExistInY_Y2 =", value, "notexistinyY2");
            return (Criteria) this;
        }

        public Criteria andNotexistinyY2NotEqualTo(Integer value) {
            addCriterion("notExistInY_Y2 <>", value, "notexistinyY2");
            return (Criteria) this;
        }

        public Criteria andNotexistinyY2GreaterThan(Integer value) {
            addCriterion("notExistInY_Y2 >", value, "notexistinyY2");
            return (Criteria) this;
        }

        public Criteria andNotexistinyY2GreaterThanOrEqualTo(Integer value) {
            addCriterion("notExistInY_Y2 >=", value, "notexistinyY2");
            return (Criteria) this;
        }

        public Criteria andNotexistinyY2LessThan(Integer value) {
            addCriterion("notExistInY_Y2 <", value, "notexistinyY2");
            return (Criteria) this;
        }

        public Criteria andNotexistinyY2LessThanOrEqualTo(Integer value) {
            addCriterion("notExistInY_Y2 <=", value, "notexistinyY2");
            return (Criteria) this;
        }

        public Criteria andNotexistinyY2In(List<Integer> values) {
            addCriterion("notExistInY_Y2 in", values, "notexistinyY2");
            return (Criteria) this;
        }

        public Criteria andNotexistinyY2NotIn(List<Integer> values) {
            addCriterion("notExistInY_Y2 not in", values, "notexistinyY2");
            return (Criteria) this;
        }

        public Criteria andNotexistinyY2Between(Integer value1, Integer value2) {
            addCriterion("notExistInY_Y2 between", value1, value2, "notexistinyY2");
            return (Criteria) this;
        }

        public Criteria andNotexistinyY2NotBetween(Integer value1, Integer value2) {
            addCriterion("notExistInY_Y2 not between", value1, value2, "notexistinyY2");
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

        public Criteria andExistoneinyY1IsNull() {
            addCriterion("existOneInY_Y1 is null");
            return (Criteria) this;
        }

        public Criteria andExistoneinyY1IsNotNull() {
            addCriterion("existOneInY_Y1 is not null");
            return (Criteria) this;
        }

        public Criteria andExistoneinyY1EqualTo(Integer value) {
            addCriterion("existOneInY_Y1 =", value, "existoneinyY1");
            return (Criteria) this;
        }

        public Criteria andExistoneinyY1NotEqualTo(Integer value) {
            addCriterion("existOneInY_Y1 <>", value, "existoneinyY1");
            return (Criteria) this;
        }

        public Criteria andExistoneinyY1GreaterThan(Integer value) {
            addCriterion("existOneInY_Y1 >", value, "existoneinyY1");
            return (Criteria) this;
        }

        public Criteria andExistoneinyY1GreaterThanOrEqualTo(Integer value) {
            addCriterion("existOneInY_Y1 >=", value, "existoneinyY1");
            return (Criteria) this;
        }

        public Criteria andExistoneinyY1LessThan(Integer value) {
            addCriterion("existOneInY_Y1 <", value, "existoneinyY1");
            return (Criteria) this;
        }

        public Criteria andExistoneinyY1LessThanOrEqualTo(Integer value) {
            addCriterion("existOneInY_Y1 <=", value, "existoneinyY1");
            return (Criteria) this;
        }

        public Criteria andExistoneinyY1In(List<Integer> values) {
            addCriterion("existOneInY_Y1 in", values, "existoneinyY1");
            return (Criteria) this;
        }

        public Criteria andExistoneinyY1NotIn(List<Integer> values) {
            addCriterion("existOneInY_Y1 not in", values, "existoneinyY1");
            return (Criteria) this;
        }

        public Criteria andExistoneinyY1Between(Integer value1, Integer value2) {
            addCriterion("existOneInY_Y1 between", value1, value2, "existoneinyY1");
            return (Criteria) this;
        }

        public Criteria andExistoneinyY1NotBetween(Integer value1, Integer value2) {
            addCriterion("existOneInY_Y1 not between", value1, value2, "existoneinyY1");
            return (Criteria) this;
        }

        public Criteria andExistoneinyY2IsNull() {
            addCriterion("existOneInY_Y2 is null");
            return (Criteria) this;
        }

        public Criteria andExistoneinyY2IsNotNull() {
            addCriterion("existOneInY_Y2 is not null");
            return (Criteria) this;
        }

        public Criteria andExistoneinyY2EqualTo(Integer value) {
            addCriterion("existOneInY_Y2 =", value, "existoneinyY2");
            return (Criteria) this;
        }

        public Criteria andExistoneinyY2NotEqualTo(Integer value) {
            addCriterion("existOneInY_Y2 <>", value, "existoneinyY2");
            return (Criteria) this;
        }

        public Criteria andExistoneinyY2GreaterThan(Integer value) {
            addCriterion("existOneInY_Y2 >", value, "existoneinyY2");
            return (Criteria) this;
        }

        public Criteria andExistoneinyY2GreaterThanOrEqualTo(Integer value) {
            addCriterion("existOneInY_Y2 >=", value, "existoneinyY2");
            return (Criteria) this;
        }

        public Criteria andExistoneinyY2LessThan(Integer value) {
            addCriterion("existOneInY_Y2 <", value, "existoneinyY2");
            return (Criteria) this;
        }

        public Criteria andExistoneinyY2LessThanOrEqualTo(Integer value) {
            addCriterion("existOneInY_Y2 <=", value, "existoneinyY2");
            return (Criteria) this;
        }

        public Criteria andExistoneinyY2In(List<Integer> values) {
            addCriterion("existOneInY_Y2 in", values, "existoneinyY2");
            return (Criteria) this;
        }

        public Criteria andExistoneinyY2NotIn(List<Integer> values) {
            addCriterion("existOneInY_Y2 not in", values, "existoneinyY2");
            return (Criteria) this;
        }

        public Criteria andExistoneinyY2Between(Integer value1, Integer value2) {
            addCriterion("existOneInY_Y2 between", value1, value2, "existoneinyY2");
            return (Criteria) this;
        }

        public Criteria andExistoneinyY2NotBetween(Integer value1, Integer value2) {
            addCriterion("existOneInY_Y2 not between", value1, value2, "existoneinyY2");
            return (Criteria) this;
        }

        public Criteria andExistoneinxX1IsNull() {
            addCriterion("existOneInX_X1 is null");
            return (Criteria) this;
        }

        public Criteria andExistoneinxX1IsNotNull() {
            addCriterion("existOneInX_X1 is not null");
            return (Criteria) this;
        }

        public Criteria andExistoneinxX1EqualTo(Integer value) {
            addCriterion("existOneInX_X1 =", value, "existoneinxX1");
            return (Criteria) this;
        }

        public Criteria andExistoneinxX1NotEqualTo(Integer value) {
            addCriterion("existOneInX_X1 <>", value, "existoneinxX1");
            return (Criteria) this;
        }

        public Criteria andExistoneinxX1GreaterThan(Integer value) {
            addCriterion("existOneInX_X1 >", value, "existoneinxX1");
            return (Criteria) this;
        }

        public Criteria andExistoneinxX1GreaterThanOrEqualTo(Integer value) {
            addCriterion("existOneInX_X1 >=", value, "existoneinxX1");
            return (Criteria) this;
        }

        public Criteria andExistoneinxX1LessThan(Integer value) {
            addCriterion("existOneInX_X1 <", value, "existoneinxX1");
            return (Criteria) this;
        }

        public Criteria andExistoneinxX1LessThanOrEqualTo(Integer value) {
            addCriterion("existOneInX_X1 <=", value, "existoneinxX1");
            return (Criteria) this;
        }

        public Criteria andExistoneinxX1In(List<Integer> values) {
            addCriterion("existOneInX_X1 in", values, "existoneinxX1");
            return (Criteria) this;
        }

        public Criteria andExistoneinxX1NotIn(List<Integer> values) {
            addCriterion("existOneInX_X1 not in", values, "existoneinxX1");
            return (Criteria) this;
        }

        public Criteria andExistoneinxX1Between(Integer value1, Integer value2) {
            addCriterion("existOneInX_X1 between", value1, value2, "existoneinxX1");
            return (Criteria) this;
        }

        public Criteria andExistoneinxX1NotBetween(Integer value1, Integer value2) {
            addCriterion("existOneInX_X1 not between", value1, value2, "existoneinxX1");
            return (Criteria) this;
        }

        public Criteria andExistoneinxX2IsNull() {
            addCriterion("existOneInX_X2 is null");
            return (Criteria) this;
        }

        public Criteria andExistoneinxX2IsNotNull() {
            addCriterion("existOneInX_X2 is not null");
            return (Criteria) this;
        }

        public Criteria andExistoneinxX2EqualTo(Integer value) {
            addCriterion("existOneInX_X2 =", value, "existoneinxX2");
            return (Criteria) this;
        }

        public Criteria andExistoneinxX2NotEqualTo(Integer value) {
            addCriterion("existOneInX_X2 <>", value, "existoneinxX2");
            return (Criteria) this;
        }

        public Criteria andExistoneinxX2GreaterThan(Integer value) {
            addCriterion("existOneInX_X2 >", value, "existoneinxX2");
            return (Criteria) this;
        }

        public Criteria andExistoneinxX2GreaterThanOrEqualTo(Integer value) {
            addCriterion("existOneInX_X2 >=", value, "existoneinxX2");
            return (Criteria) this;
        }

        public Criteria andExistoneinxX2LessThan(Integer value) {
            addCriterion("existOneInX_X2 <", value, "existoneinxX2");
            return (Criteria) this;
        }

        public Criteria andExistoneinxX2LessThanOrEqualTo(Integer value) {
            addCriterion("existOneInX_X2 <=", value, "existoneinxX2");
            return (Criteria) this;
        }

        public Criteria andExistoneinxX2In(List<Integer> values) {
            addCriterion("existOneInX_X2 in", values, "existoneinxX2");
            return (Criteria) this;
        }

        public Criteria andExistoneinxX2NotIn(List<Integer> values) {
            addCriterion("existOneInX_X2 not in", values, "existoneinxX2");
            return (Criteria) this;
        }

        public Criteria andExistoneinxX2Between(Integer value1, Integer value2) {
            addCriterion("existOneInX_X2 between", value1, value2, "existoneinxX2");
            return (Criteria) this;
        }

        public Criteria andExistoneinxX2NotBetween(Integer value1, Integer value2) {
            addCriterion("existOneInX_X2 not between", value1, value2, "existoneinxX2");
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