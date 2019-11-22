package by.menko.finalproject.controller.action.useraction;

import by.menko.finalproject.controller.action.Action;
import by.menko.finalproject.exception.PersonalException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.Logger;

public class LogoutAction extends Action {
	//private static Logger logger = Logger.getLogger(LogoutAction.class);

	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersonalException {

		request.getSession(false).invalidate();
		return new Forward("/index.jsp");
	}
}
