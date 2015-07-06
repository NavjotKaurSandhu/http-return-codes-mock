package de.otto.http.mock;

import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReturnCodeMockServlet extends HttpServlet {

    public static final String PARAMETER_RC = "rc";
    public static final int DEFAULT_RETURN_CODE = 200;
    public static final int NO_CONTENT_RETURN_CODE = 200;

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        setResponseStatusAndBody(req, resp);
    }

    @Override
    protected void doHead(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        setResponseStatus(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        setResponseStatusAndBody(req, resp);
    }

    @Override
    protected void doPut(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        setResponseStatusAndBody(req, resp);
    }

    @Override
    protected void doDelete(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        setResponseStatusAndBody(req, resp);
    }

    private int setResponseStatus(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        final String rc = req.getParameter(PARAMETER_RC);
        if (NumberUtils.isNumber(rc)) {
            final int status = Integer.decode(rc);
            resp.setStatus(status);
            return status;
        } else {
            return HttpServletResponse.SC_OK;
        }
    }

    private void setResponseStatusAndBody(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        final Integer status = setResponseStatus(req, resp);
        if (!status.equals(HttpServletResponse.SC_NO_CONTENT)) {
            resp.getOutputStream().write("We mock the World.".getBytes("UTF-8"));
        }
    }
}
