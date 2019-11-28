package by.menko.finalproject.controller.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.menko.finalproject.exception.PersonalException;

import java.io.IOException;


public interface CommandManager {
	void execute(Command command, HttpServletRequest request, HttpServletResponse response) throws PersonalException, ServletException, IOException;

	void close();
}
