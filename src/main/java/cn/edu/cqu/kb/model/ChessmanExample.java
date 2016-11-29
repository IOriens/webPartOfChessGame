package cn.edu.cqu.kb.model;

import java.util.ArrayList;
import java.util.List;

public class ChessmanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ChessmanExample() {
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

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Integer value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Integer value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Integer value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Integer value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Integer value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Integer> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Integer> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Integer value1, Integer value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andIsredIsNull() {
            addCriterion("isRed is null");
            return (Criteria) this;
        }

        public Criteria andIsredIsNotNull() {
            addCriterion("isRed is not null");
            return (Criteria) this;
        }

        public Criteria andIsredEqualTo(Boolean value) {
            addCriterion("isRed =", value, "isred");
            return (Criteria) this;
        }

        public Criteria andIsredNotEqualTo(Boolean value) {
            addCriterion("isRed <>", value, "isred");
            return (Criteria) this;
        }

        public Criteria andIsredGreaterThan(Boolean value) {
            addCriterion("isRed >", value, "isred");
            return (Criteria) this;
        }

        public Criteria andIsredGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isRed >=", value, "isred");
            return (Criteria) this;
        }

        public Criteria andIsredLessThan(Boolean value) {
            addCriterion("isRed <", value, "isred");
            return (Criteria) this;
        }

        public Criteria andIsredLessThanOrEqualTo(Boolean value) {
            addCriterion("isRed <=", value, "isred");
            return (Criteria) this;
        }

        public Criteria andIsredIn(List<Boolean> values) {
            addCriterion("isRed in", values, "isred");
            return (Criteria) this;
        }

        public Criteria andIsredNotIn(List<Boolean> values) {
            addCriterion("isRed not in", values, "isred");
            return (Criteria) this;
        }

        public Criteria andIsredBetween(Boolean value1, Boolean value2) {
            addCriterion("isRed between", value1, value2, "isred");
            return (Criteria) this;
        }

        public Criteria andIsredNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isRed not between", value1, value2, "isred");
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