package by.menko.xmlparsing.controller.command.impl;

import by.menko.xmlparsing.bean.Candy;
import by.menko.xmlparsing.controller.command.Command;

import by.menko.xmlparsing.service.GetListCandy;
import by.menko.xmlparsing.service.file.FileService;
import by.menko.xmlparsing.service.validator.ValidatorXML;
import org.xml.sax.SAXException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

import java.io.IOException;
import java.util.List;


@MultipartConfig
public class ResultParseCommand implements Command {
    /**
     * Parses the request and takes out the file.
     * Sent for validation and parsing. returns the result.
     *
     * @param req  request.
     * @param resp response.
     *
     * @throws ServletException .
     * @throws IOException      .
     */
    @Override
    public void execute(final HttpServletRequest req,
                        final HttpServletResponse resp)
            throws ServletException, IOException {
        Part filePart = null;
        try {
            filePart = req.getPart("file");
            String parse = req.getParameter("parse");
            String fileName = filePart.getSubmittedFileName();
            String path = req.getServletContext().getResource("")
                    .getPath();
            String pathTemp = path + req.getServletContext()
                    .getInitParameter("tempfile.dir");
            String pathXML = pathTemp + "/" + fileName;
            FileService fileService = new FileService();
            fileService.createDirAndWriteToFile(pathTemp, filePart);
            ValidatorXML v = new ValidatorXML();
            if (v.isValid(pathXML, path)) {
                GetListCandy getListCandy = new GetListCandy();
                List<Candy> listCandy = null;

                listCandy = getListCandy.getListCandy(parse, pathXML);

                req.setAttribute("list", listCandy);
                RequestDispatcher result = req
                        .getRequestDispatcher("/Result.jsp");
                result.forward(req, resp);
            } else {
                String error = "File failed validation,"
                        + " please go to the home page!";
                req.setAttribute("result", error);
                RequestDispatcher errorPage = req
                        .getRequestDispatcher("/Error.jsp");
                errorPage.forward(req, resp);
                log.info("File failed validation: " + fileName + ".");
            }
        } catch (XMLStreamException | SAXException
                | ParserConfigurationException | IOException e) {
            log.error("Parser error: ", e);
            String error = "A parser error has occurred,"
                    + " please go to the home page!";
            req.setAttribute("result", error);
            RequestDispatcher errorPage = req
                    .getRequestDispatcher("/Error.jsp");
            errorPage.forward(req, resp);
        }

    }


}

