<%@ page import="javax.ws.rs.client.Client" %>
<%@ page import="javax.ws.rs.client.ClientBuilder" %>
<%@ page import="javax.ws.rs.core.Response" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Decode Vin</title>
</head>

<body background="img/bmw.jpg" style="background-repeat: no-repeat;">
<form action="index.jsp" method="GET">

    <%
        String vin = "WVWZZZ3BZWE689725";

        if (request != null && request.getParameter("vin") != null) {
            vin = request.getParameter("vin");
        }
    %>

    <table>
        <tr>
            <h1>Enter your VIN</h1>
            <td><input type="text" name="vin" value="<%= vin %>"></td>
        </tr>
    </table>

    <input type="submit" value="Search">
</form>

    <%
    String responseString = "";

    if (request != null && request.getParameter("vin") != null) {
        String url = request.getRequestURL().toString();
        int index = url.indexOf("/index.jsp");
        String baseURL = url.substring(0, index);

        Client client = ClientBuilder.newClient();

        Response clientResponse = client.target(baseURL)
                .path("api/v1/vehicledata").path(vin)
                .request()
                .get();


        Response.StatusType status = clientResponse.getStatusInfo();

        if (status.getStatusCode() == HttpServletResponse.SC_OK) {
            responseString = clientResponse.readEntity(String.class);
        } else {
           responseString = "Error: " + status.getStatusCode() + ", Message: " + status.getReasonPhrase();
        }

    }
    %>

<h1> Result </h1>
<pre>
    <%= responseString %>
</pre>
