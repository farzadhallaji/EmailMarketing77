package email_marketing.azad.hallaji.farzad.emailmarketing.model;

import java.util.List;

/**
 * Created by farzad on 11/21/2016.
 */
public class ReportItem {

    private String time;
    private String list;
    private String template;
    private String t;
    private String s;
    private String r;
    private String c;


    public ReportItem(String time, String list, String template, String s, String r, String c, String t) {
        this.time = time;
        this.list = list;
        this.template = template;
        this.t = t;
        this.s = s;
        this.r = r;
        this.c = c;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }
}
