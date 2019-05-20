package struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LogoutAction extends Action {

	public ActionForward execute(ActionMapping map, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			MySession.getInstance().getSession().removeAttribute(Constantes.USUARIO);
			//MySession.getInstance().getControlador().setUsuario(null);
		} catch (Exception e) {
			return map.findForward(Constantes.INDEX);
		}		
		 
		return map.findForward(Constantes.INDEX);
	}
}
