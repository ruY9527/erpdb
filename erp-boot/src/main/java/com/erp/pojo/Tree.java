package com.erp.pojo;

/**
 * 树形结构
 *
 * @author baoyang
 */
public class Tree {
    private String id;
    private String text;
    private boolean checked;
    private java.util.List<Tree> children = new java.util.ArrayList<>();

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public boolean isChecked() { return checked; }
    public void setChecked(boolean checked) { this.checked = checked; }
    public java.util.List<Tree> getChildren() { return children; }
    public void setChildren(java.util.List<Tree> children) { this.children = children; }
}