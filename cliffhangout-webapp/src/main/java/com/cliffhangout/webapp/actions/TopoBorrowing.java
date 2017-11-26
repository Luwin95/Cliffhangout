package com.cliffhangout.webapp.actions;

import com.cliffhangout.beans.Topo;
import com.cliffhangout.webapp.AbstractAction;
import com.opensymphony.xwork2.conversion.annotations.Conversion;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;
import org.apache.struts2.interceptor.SessionAware;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Conversion()
public class TopoBorrowing extends AbstractAction implements SessionAware {
    private String idTopo;
    private Date startDate;
    private Date endDate;
    private Topo topo;
    private String title = "Emprunter un topo";
    private String page = "/WEB-INF/subscriber/topoBorrowForm.jsp";
    private String stylesheets = "/subscriber/topoBorrow.css";
    private Map<String, Object> session;

    public Date getStartDate() {
        return startDate;
    }

    @TypeConversion(converter = "com.cliffhangout.webapp.converter.StringToDateConverter")
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @TypeConversion(converter = "com.cliffhangout.webapp.converter.StringToDateConverter")
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Topo getTopo() {
        return topo;
    }

    public void setTopo(Topo topo) {
        this.topo = topo;
    }

    public String getIdTopo() {
        return idTopo;
    }

    public void setIdTopo(String idTopo) {
        this.idTopo = idTopo;
    }

    public String getTitle() {
        return title;
    }

    public String getPage() {
        return page;
    }

    public String getStylesheets() {
        return stylesheets;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public String execute() throws Exception {
        if(idTopo !=null)
        {
            setTopo(getManagerFactory().getTopoManager().displayTopo(Integer.parseInt(idTopo)));
            if(topo !=null)
            {
                if(startDate!=null && endDate!=null)
                {
                    getManagerFactory().getTopoManager().borrowTopo(topo, startDate, endDate, session);
                    return SUCCESS;
                }else{
                    return INPUT;
                }
            }else{
                return "notFound";
            }
        }else{
            return "notFound";
        }
    }

    @Override
    public void validate() {
        if(startDate !=null && endDate!=null)
        {
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date now = sdf.parse(sdf.format(new Date()));
                if(!startDate.equals(now))
                {
                    if(startDate.before(now))
                    {
                        addFieldError("startDate", "La date de début de prêt ne peut être inférieur à la date du jour ");
                    }
                }
                if(!endDate.equals(now))
                {
                    if(endDate.before(now) && !endDate.equals(now))
                    {
                        addFieldError("endDate", "La date de fin de prêt ne peut être inférieur à la date du jour ");
                    }
                }
                if(startDate.after(endDate))
                {
                    addFieldError("startDate", "La date de début de prêt ne peut être supérieur à la date de fin ");
                }
                if(endDate.before(startDate))
                {
                    addFieldError("endDate", "La date de fin de prêt ne peut être inférieur à la date de début");
                }
            }catch(ParseException e){
                e.printStackTrace();
            }
        }
    }
}
