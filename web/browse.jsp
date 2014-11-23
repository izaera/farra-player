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
    File[] dirs = basedir.listFiles(new FileFilter() {
        @Override
        public boolean accept(File file) {
            if (file.getName().startsWith(".")) {
                return false;
            }

            if (!file.isDirectory()) {
                return false;
            }

            return true;
        }
    });

    File[] files = basedir.listFiles(new FileFilter() {
        @Override
        public boolean accept(File file) {
            if (file.isDirectory()) {
                return false;
            }

            if (!settings.mediaExtensions.contains(util.extension(file))) {
                return false;
            }

            return true;
        }
    });
%>

<div class="browser">

    <%for(File item : dirs) {
        String title = item.getName();%>
        <%@include file="html/item.jspf"%>
    <%}%>

    <%for(File item : files) {
        String title = item.getName();%>
        <%@include file="html/item.jspf"%>
    <%}%>

</div>


<%@include file="html/close.jspf"%>

