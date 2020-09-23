package de.nae;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class LogoutBacking {

    @Inject
    private FacesContext facesContext;

    public String submit() {
        facesContext.getExternalContext().invalidateSession();

        return "/login.xhtml?faces-redirect=true";
    }
}
