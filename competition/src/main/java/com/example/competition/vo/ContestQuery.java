package com.example.competition.vo;

public class ContestQuery {

    private String title;
    private Long typeId;
//    private boolean maintype;

    public ContestQuery() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId)
    {
        this.typeId = typeId;
    }

//    public boolean isMaintype() {
//        return maintype;
//    }
//
//    public void setMaintype(boolean maintype) {
//        this.maintype = maintype;
//    }
}
