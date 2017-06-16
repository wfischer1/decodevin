<%@ page import="javax.ws.rs.client.Client" %>
<%@ page import="javax.ws.rs.client.ClientBuilder" %>
<%@ page import="javax.ws.rs.core.Response" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Decode Vin</title>
</head>

<body background="bmw.jpg" style="background-repeat: no-repeat;">
<form action="index.jsp" method="GET">

    <%
        String vin = "WVWZZZ3BZWE689725";

        if (request != null && request.getParameter("vin") != null) {
            vin = request.getParameter("vin");
        }
    %>

    <h3>Enter your VIN</h3>
    <div style="background-color: white; border: 1px solid; max-width: 1260px; min-width: 600px;">
        <input type="text" name="vin" value="<%= vin %>" style="width: 100%;background-color: transparent; border: 0px solid;">
    </div>

    <br/>

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

<h3 > Result </h3>
<div style="background-color: white; border: 1px solid; max-width: 1260px; min-width: 600px;">
<pre>
    <%= responseString %>
</pre>

</div>
