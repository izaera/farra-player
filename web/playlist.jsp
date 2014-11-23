<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="lib/imports.jspf"%>
<%@include file="lib/functions.jspf"%>

<%@include file="lib/services.jspf"%>
<%@include file="lib/parameters.jspf"%>

<%
    File basedir = new File(settings.rootDir, pfile);
%>

<%@include file="html/open.jspf"%>

<%@include file="html/toolbar.jspf"%>
<%@include file="html/controls.jspf"%>

<%
    List<File> items = playlist.getToPlay();
%>

<div class="playlist">

    <%for(int i=0; i<items.size(); i++) {
        File item = items.get(i);%>

        <div class="item">

            <%if (i==0){%>
                <%=img(request, "play.png", "", "12pt", "12pt")%>
            <%} else {%>
                <span class="actions">

                    <%if (i > 1){%>
                        <a href="<%=url(request, "control.jsp", "cmd", "up", "index", Integer.toString(i))%>">
                            <%=img(request, "up.png", "Up", "12pt", "12pt")%>
                        </a>
                    <%} else {%>
                            <%=img(request, "empty.png", "", "12pt", "12pt")%>
                    <%}%>

                    <%if (i < items.size()-1){%>
                        <a href="<%=url(request, "control.jsp", "cmd", "down", "index", Integer.toString(i))%>">
                            <%=img(request, "down.png", "Down", "12pt", "12pt")%>
                        </a>
                    <%} else {%>
                            <%=img(request, "empty.png", "", "12pt", "12pt")%>
                    <%}%>

                    <a href="<%=url(request, "control.jsp", "cmd", "delete", "index", Integer.toString(i))%>">
                        <%=img(request, "delete.png", "Delete", "12pt", "12pt")%>
                    </a>

                </span>
            <%}%>

            <%=item.getName()%>

            <%if (i==0){%>
                <br><br>
            <%}%>

        </div>
    <%}%>

</div>


<%@include file="html/close.jspf"%>

