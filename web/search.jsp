<%@ page import="java.util.Iterator" %>
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

<%!
    private FileFilter filter = new FileFilter() {
        @Override
        public boolean accept(File file) {
            if (file.getName().startsWith(".")) {
                return false;
            }

            if (!file.isDirectory() && !settings.mediaExtensions.contains(util.extension(file))) {
                return false;
            }

            return true;
        }
    };

    void addFiles(List<File> files, File dir) {
        for(File file : dir.listFiles(filter)) {
            files.add(file);
            if (file.isDirectory()) {
                addFiles(files, file);
            }
        }
    }
%>

<%
    List<File> files = new ArrayList<File>();

    addFiles(files, basedir);

    String lowerCasePsearch = psearch.toLowerCase();
    Iterator<File> iterator = files.iterator();
    while (iterator.hasNext()) {
        File file = iterator.next();

        if (!file.getName().toLowerCase().contains(lowerCasePsearch)) {
            iterator.remove();
        }
    }
%>

<div class="hits">
    <%for(File item : files) {
        String title = prettyPath(relPath(item));%>
        <%@include file="html/item.jspf"%>
    <%}%>
</div>

<%@include file="html/close.jspf"%>

