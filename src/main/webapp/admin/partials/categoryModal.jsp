<div class="modal fade" id="<%= request.getParameter("modal") %>" tabindex="-1" aria-labelledby="createModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="createModalLabel"><%= request.getParameter("title") %></h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
		<form id="categoryForm" class="m-auto" action="manage_category?command=<%= request.getParameter("command") %>" method="post" style="width:350px;">
			<div class="modal-body">
                <div class="d-flex flex-column align-items-center py-2">
                    <input type="hidden" name="categoryId" id="id" value="">
                        
                    <div class="form-floating mb-2">
                        <input name="name" id="name" type="text" value=""
                                class="form-control" id="inputCategoryName" placeholder="inputCategoryName">
                        <label for="inputCategoryName">Name</label>
                        <div class="invalid-feedback">Please input a name</div>
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