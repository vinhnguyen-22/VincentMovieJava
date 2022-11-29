<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <%@include file="partials/header.jsp" %>
  </head>
  <body>
    <div class="wrapper">
      <%@include file="partials/sidebar.jsp" %>

      <section class="home-section p-4">
        <div class="layer">
          <div class="wrapper-loader">
            <div class="loader"></div>
            <div class="loader"></div>
          </div>
        </div>
      </section>
    </div>
    <%@include file="partials/footer.jsp" %>
  </body>
</html>
