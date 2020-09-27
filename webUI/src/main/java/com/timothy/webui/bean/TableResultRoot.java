/**
 * Copyright 2020 bejson.com
 */
package com.timothy.webui.bean;

import lombok.ToString;

import java.util.List;

/**
 * Auto-generated: 2020-09-18 12:26:54
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@ToString
public class TableResultRoot<T> {

    private String cols;
    private String fileName;
    private int from;
    private List<T> gridModel;
    private String oper;
    private int page;
    private int records;
    private int rows;
    private String searchField;
    private String searchOper;
    private String searchString;
    private String sidx;
    private String sord;
    private String titles;
    private int to;
    private int total;
    private String totalrows;

    public void setCols(String cols) {
        this.cols = cols;
    }

    public String getCols() {
        return cols;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getFrom() {
        return from;
    }

    public void setGridModel(List<T> gridModel) {
        this.gridModel = gridModel;
    }

    public List<T> getGridModel() {
        return gridModel;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public String getOper() {
        return oper;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public int getRecords() {
        return records;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getRows() {
        return rows;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchOper(String searchOper) {
        this.searchOper = searchOper;
    }

    public String getSearchOper() {
        return searchOper;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public String getSord() {
        return sord;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getTitles() {
        return titles;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getTo() {
        return to;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotalrows(String totalrows) {
        this.totalrows = totalrows;
    }

    public String getTotalrows() {
        return totalrows;
    }

}