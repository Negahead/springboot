package com.google.springboot.entity.response;

import java.io.Serializable;
import java.util.List;

public class TeacherInfo implements Serializable {
    public static final long serialVersionUID = 1L;

    private String webViewName;
    private String personTag;
    private List<String> names;

    public String getWebViewName() {
        return webViewName;
    }

    public void setWebViewName(String webViewName) {
        this.webViewName = webViewName;
    }

    public String getPersonTag() {
        return personTag;
    }

    public void setPersonTag(String personTag) {
        this.personTag = personTag;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
