package com.evangel.chart.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A basic servlet that generates an HTML page that displays a chart generated
 * by JFreeChart. &lt;P&gt; This servlet uses another servlet
 * (ServletDemo2ChartGenerator) to create a PNG image for the embedded chart.
 * &lt;P&gt; This class is described in the JFreeChart Developer Guide.
 */
public class ServletDemo2 extends HttpServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = 9024040467697909853L;

	/**
	 * Creates a new servlet demo.
	 */
	public ServletDemo2() {
		// nothing required
	}

	/**
	 * Processes a POST request. &lt;P&gt; The chart.html page contains a form
	 * for generating the first request, after that the HTML returned by this
	 * servlet contains the same form for generating subsequent requests.
	 *
	 * @param request
	 *            the request.
	 * @param response
	 *            the response.
	 *
	 * @throws ServletException
	 *             if there is a servlet related problem.
	 * @throws IOException
	 *             if there is an I/O problem.
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = new PrintWriter(response.getWriter());
		try {
			String param = request.getParameter("chart");
			response.setContentType("text/html");
			out.println("<HTML>");
			out.println("<HEAD>");
			out.println("<TITLE>JFreeChart Servlet Demo 2</TITLE>");
			out.println("</HEAD>");
			out.println("<BODY>");
			out.println("<H2>JFreeChart Servlet Demo</H2>");
			out.println("<P>");
			out.println("Please choose a chart type:");
			out.println("<FORM ACTION=\"ServletDemo2\" METHOD=POST>");
			String pieChecked = (param.equals("pie") ? " CHECKED" : "");
			String barChecked = (param.equals("bar") ? " CHECKED" : "");
			String timeChecked = (param.equals("time") ? " CHECKED" : "");
			out.println("<INPUT TYPE=\"radio\" NAME=\"chart\" VALUE=\"pie\""
					+ pieChecked + "> Pie Chart");
			out.println("<INPUT TYPE=\"radio\" NAME=\"chart\" VALUE=\"bar\""
					+ barChecked + "> Bar Chart");
			out.println("<INPUT TYPE=\"radio\" NAME=\"chart\" VALUE=\"time\""
					+ timeChecked + "> Time Series Chart");
			out.println("<P>");
			out.println("<INPUT TYPE=\"submit\" VALUE=\"Generate Chart\">");
			out.println("</FORM>");
			out.println("<P>");
			out.println("<IMG SRC=\"ServletDemo2ChartGenerator?type=" + param
					+ "\" BORDER=1 WIDTH=400 HEIGHT=300/>");
			out.println("</BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
		} catch (Exception e) {
			System.err.println(e.toString());
		} finally {
			out.close();
		}
	}
}