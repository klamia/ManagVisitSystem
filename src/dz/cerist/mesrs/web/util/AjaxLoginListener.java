package dz.cerist.mesrs.web.util;

import java.io.IOException;







import javax.faces.FacesException;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;


import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;









public class AjaxLoginListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8999133756326352935L;
	
	
	
	@Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        // NOOP.
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext context = event.getFacesContext();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String originalURL = (String) request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);
        String loginURL = request.getContextPath() + "/public/login.jsf";
        if (context.getPartialViewContext().isAjaxRequest()
            && originalURL != null
            && loginURL.equals(request.getRequestURI()))
        {
            try {
                context.getExternalContext().redirect(originalURL);
            } catch (IOException e) {
                throw new FacesException(e);
            }
        }
    }


}
