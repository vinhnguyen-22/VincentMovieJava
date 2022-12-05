<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="<%= request.getParameter("modal") %>" tabindex="-1" aria-labelledby="createModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="createModalLabel"><%= request.getParameter("title") %></h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
		<form id="movieForm" class="m-auto" action="manage_movie?command=<%= request.getParameter("command") %>" method="post" enctype="multipart/form-data">
			<div class="modal-body">
          <div class="d-flex flex-column align-items-center py-2">
              <input type="hidden" id="id" name="movieId" value="">
              <input type="hidden" id="theSelectedCategory" value=""/>
              <div class="row">
                <div class="mb-2 col-md-6">
                  <div class="form-floating mb-2">
                      <input name="name" id="name" type="text" value=""
                              class="form-control"  placeholder="inputMovieName">
                      <label for="inputMovieName">Name</label>
                      <div class="invalid-feedback">Please input a name</div>
                  </div>
                </div>

                <div class="mb-2 col-md-6">
                  <div class="form-floating mb-2">
                      <input name="actor" id="actor" type="text" value=""
                              class="form-control"  placeholder="inputMovieActor">
                      <label for="inputMovieActor">Actor</label>
                      <div class="invalid-feedback">Please input a actor</div>
                  </div>
                </div>

                <div class="mb-2 col-md-6">
                  <div class="form-floating mb-2">
                      <input name="country" id="country" type="text" value=""
                              class="form-control"  placeholder="inputMovieCountry">
                      <label for="inputMovieCountry">Country</label>
                      <div class="invalid-feedback">Please input a country</div>
                  </div>
                </div>

                <div class="mb-2 col-md-6">
                  <div class="form-floating mb-2">
                      <input name="author" id="author" type="text" value=""
                              class="form-control" id="inputMovieAuthor" placeholder="inputMovieAuthor">
                      <label for="inputMovieAuthor">Author</label>
                      <div class="invalid-feedback">Please input a author</div>
                  </div>
                </div>

                <div class="mb-2 col-md-12">
                  <div class="form-floating mb-2">
                      <textarea class="form-control" id="description" name="description" cols="30" rows="10" placeholder="description"></textarea>
                      <label for="inputMovieDescription">Description</label>
                      <div class="invalid-feedback">Please input a actor</div>
                  </div>
                </div>

                <div class="mb-2 col-md-12">
                  <div class="form-floating mb-2">
                      <textarea class="form-control" id="catDesc" name="catDesc" cols="30" rows="10" placeholder="Category Description"></textarea>
                      <label for="inputMovieCatDesc">Category Description</label>
                      <div class="invalid-feedback">Please input a category description</div>
                  </div>
                </div>

                <div class="mb-2 col-md-6">
                  <div class="form-floating mb-2">
                      <input name="episode" id="episode" type="number" value=""
                              class="form-control"  placeholder="inputMovieEpisode">
                      <label for="inputMovieEpisode">Episode</label>
                      <div class="invalid-feedback">Please input a episode</div>
                  </div>
                </div>

                <div class="mb-2 col-md-6">
                  <div class="form-floating mb-3">
                    <input name="publishDate" type="date" 
                        class="form-control" id="publishDate" placeholder="publishDate">
                    <label for="publishDate">Publish date</label>
                    <div class="invalid-feedback">Please input publish date</div>
                  </div>
                </div>
                
                <div class="mb-2 col-md-6">
                  <select class="form-select" id="status" name="status" aria-label="Default select example">
                    <option selected>Status</option>
                    <option value="0">Hide</option>
                    <option value="1">Show</option>
                  </select>
                </div>

                <div class="mb-2 col-md-6">
                  <div class="form-floating mb-3">
                    <select name="category" class="form-select" id="selectCategory">
                      <option value="" selected>Select a category</option>
                      <c:forEach items="${categoryList}" var="category">
                        <option value="${category.categoryId}">${category.name}</option>
                      </c:forEach>
                    </select>
                    <label for="selectCategory">Category</label>
                  </div>
                </div>

                <div class="mb-2 col-md-6">
                    <input name="file" id="file" type="file" value=""
                            class="form-control"  placeholder="inputMovieFile">
                    <div class="invalid-feedback">Please input a file</div>
                </div>
              </div>
          </div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
				<button type="submit" class="btn btn-primary ">Submit</button>
			</div>
		</form>
    </div>
  </div>
</div>