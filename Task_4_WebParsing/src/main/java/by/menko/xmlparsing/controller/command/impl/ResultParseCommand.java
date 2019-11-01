package by.menko.xmlparsing.controller.command.impl;

import by.menko.xmlparsing.bean.Candy;
import by.menko.xmlparsing.controller.command.Command;
import by.menko.xmlparsing.dal.Spetification;
import by.menko.xmlparsing.dal.factory.CandyBuilderFactory;
import by.menko.xmlparsing.service.GetListCandy;
import by.menko.xmlparsing.service.file.FileService;
import by.menko.xmlparsing.service.validator.ValidatorXML;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.ws.Service;
import java.io.*;
import java.util.List;


import static java.nio.charset.StandardCharsets.*;

@MultipartConfig
public class ResultParseCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Part filePart = req.getPart("file");
        String parse = req.getParameter("parse");
        String fileName = filePart.getSubmittedFileName();
        String path = req.getServletContext().getResource("").getPath();
        String pathTemp = path + req.getServletContext().getInitParameter("tempfile.dir");
        String pathXML = pathTemp + "/" + fileName;
        FileService fileService = new FileService();
        fileService.createDirAndWriteToFile(pathTemp, filePart);
        ValidatorXML v = new ValidatorXML();
        if (v.isValid(pathXML, path)) {
            GetListCandy getListCandy = new GetListCandy();
            List<Candy> listCandy = getListCandy.getListCandy(parse, pathXML);
            req.setAttribute("list", listCandy);
            RequestDispatcher result = req.getRequestDispatcher("/Result.jsp");
            result.forward(req, resp);
        }

    }


}

