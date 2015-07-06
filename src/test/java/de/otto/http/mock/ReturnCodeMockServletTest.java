package de.otto.http.mock;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReturnCodeMockServletTest {
    private ReturnCodeMockServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ServletOutputStream servletOutputStream;

    @BeforeMethod
    public void setUp() throws IOException {
        servlet = new ReturnCodeMockServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        servletOutputStream = mock(ServletOutputStream.class);
        when(response.getOutputStream()).thenReturn(servletOutputStream);
    }

    @DataProvider
    public Object[][] returnCodes() {
        return new Object[][]{
                {"200"},
                {"204"},
                {"404"},
                {"500"},
        };
    }

    @Test(dataProvider = "returnCodes")
    public void shouldDoGet(final String returnCode) throws Exception {
        // given
        when(request.getParameter(anyString())).thenReturn(returnCode);

        // when
        servlet.doGet(request, response);

        // then
        verify(request).getParameter("rc");
        verify(response).setStatus(Integer.decode(returnCode));
        if (!returnCode.equals("204")) {
            verify(servletOutputStream).write(any(byte[].class));
        } else {
            verify(servletOutputStream, never()).write(any(byte[].class));
        }
    }

    @Test(dataProvider = "returnCodes")
    public void shouldDoHead(final String returnCode) throws Exception {
        // given
        when(request.getParameter(anyString())).thenReturn(returnCode);

        // when
        servlet.doHead(request, response);

        // then
        verify(request).getParameter("rc");
        verify(response).setStatus(Integer.decode(returnCode));
        verify(servletOutputStream, never()).write(any(byte[].class));
    }

    @Test(dataProvider = "returnCodes")
    public void shouldDoPost(final String returnCode) throws Exception {
        // given
        when(request.getParameter(anyString())).thenReturn(returnCode);

        // when
        servlet.doPost(request, response);

        // then
        verify(request).getParameter("rc");
        verify(response).setStatus(Integer.decode(returnCode));
        if (!returnCode.equals("204")) {
            verify(servletOutputStream).write(any(byte[].class));
        } else {
            verify(servletOutputStream, never()).write(any(byte[].class));
        }
    }

    @Test(dataProvider = "returnCodes")
    public void shouldDoPut(final String returnCode) throws Exception {
        // given
        when(request.getParameter(anyString())).thenReturn(returnCode);

        // when
        servlet.doPut(request, response);

        // then
        verify(request).getParameter("rc");
        verify(response).setStatus(Integer.decode(returnCode));
        if (!returnCode.equals("204")) {
            verify(servletOutputStream).write(any(byte[].class));
        } else {
            verify(servletOutputStream, never()).write(any(byte[].class));
        }
    }

    @Test(dataProvider = "returnCodes")
    public void shouldDoDelete(final String returnCode) throws Exception {
        // given
        when(request.getParameter(anyString())).thenReturn(returnCode);

        // when
        servlet.doDelete(request, response);

        // then
        verify(request).getParameter("rc");
        verify(response).setStatus(Integer.decode(returnCode));
        if (!returnCode.equals("204")) {
            verify(servletOutputStream).write(any(byte[].class));
        } else {
            verify(servletOutputStream, never()).write(any(byte[].class));
        }
    }
}