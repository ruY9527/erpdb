package com.yang.erp.pojo;

import java.util.ArrayList;
import java.util.List;

public class Tree
{
    private String id;  //菜单id
    private String text;  //菜单名称
    private boolean checked;  //是否被选中
    private List<Tree> children;  //下级菜单

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public boolean isChecked()
    {
        return checked;
    }

    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }

    public List<Tree> getChildren()
    {
        if(children == null){
            children = new ArrayList<Tree>();
        }
        return children;
    }

    public void setChildren(List<Tree> children)
    {
        this.children = children;
    }

    @Override
    public String toString()
    {
        return "Tree{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", checked=" + checked +
                ", children=" + children +
                '}';
    }
}
