package org.flyingsparrow.bean;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 表信息
 *
 * @author Siborne
 */
public class TableInfo {

    /**
     * 表名
     */
    private String tableName;

    /**
     * bean名称
     */
    private String beanName;

    /**
     * 参数名称
     */
    private String beanQueryName;

    /**
     * 表注释
     */
    private String comment;


    /**
     * 字段信息
     */
    private List<FieldInfo> fieldList;

    /**
     * 拓展字段信息
     */
    private List<FieldInfo> extendFieldList;

    /**
     * 唯一索引集合
     */
    private Map<String, List<FieldInfo>> keyIndexMap = new LinkedHashMap<>();

    /**
     * 是否有date类型
     */
    private Boolean haveDate;

    /**
     * 是否有时间类型
     */
    private Boolean haveDateTime;

    /**
     * 是否有 BigDecimal 类型
     */
    private Boolean haveBigDecimal;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanQueryName() {
        return beanQueryName;
    }

    public void setBeanQueryName(String beanQueryName) {
        this.beanQueryName = beanQueryName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<FieldInfo> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<FieldInfo> fieldList) {
        this.fieldList = fieldList;
    }

    public Map<String, List<FieldInfo>> getKeyIndexMap() {
        return keyIndexMap;
    }

    public void setKeyIndexMap(Map<String, List<FieldInfo>> keyIndexMap) {
        this.keyIndexMap = keyIndexMap;
    }

    public Boolean isHaveDate() {
        return haveDate;
    }

    public void setHaveDate(boolean haveDate) {
        this.haveDate = haveDate;
    }

    public Boolean isHaveDateTime() {
        return haveDateTime;
    }

    public void setHaveDateTime(boolean haveDateTime) {
        this.haveDateTime = haveDateTime;
    }

    public Boolean isHaveBigDecimal() {
        return haveBigDecimal;
    }

    public void setHaveBigDecimal(boolean haveBigDecimal) {
        this.haveBigDecimal = haveBigDecimal;
    }

    public List<FieldInfo> getExtendFieldList() {
        return extendFieldList;
    }

    public void setExtendFieldList(List<FieldInfo> extendFieldList) {
        this.extendFieldList = extendFieldList;
    }
}
