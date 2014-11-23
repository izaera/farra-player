<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="lib/imports.jspf"%>
<%@include file="lib/functions.jspf"%>

<%@include file="lib/services.jspf"%>
<%@include file="lib/parameters.jspf"%>

<%
    File file = new File(settings.rootDir, pfile);
%>

<%
    if (pcmd.equals("play")) {
        playlist.play();
    }
    else if (pcmd.equals("pause")) {
        playlist.pause();
    }
    else if (pcmd.equals("stop")) {
        playlist.stop();
    }
    else if (pcmd.equals("prev")) {
        playlist.prev();
    }
    else if (pcmd.equals("next")) {
        playlist.next();
    }
    else if (pcmd.equals("voldown")) {
        playlist.voldown();
    }
    else if (pcmd.equals("volup")) {
        playlist.volup();
    }
    else if (pcmd.equals("add")) {
        playlist.add(file);
    }
    else if (pcmd.equals("play_next")) {
        playlist.playNext(file);
    }
    else if (pcmd.equals("play_now")) {
        playlist.playNow(file);
    }
    else if (pcmd.equals("up")) {
        playlist.up(pindex);
    }
    else if (pcmd.equals("down")) {
        playlist.down(pindex);
    }
    else if (pcmd.equals("delete")) {
        playlist.delete(pindex);
    }
%>

<%
    response.sendRedirect(request.getHeader("referer"));
%>

