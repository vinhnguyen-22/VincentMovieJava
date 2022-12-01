package com.ray.controller.admin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ray.entity.Category;
import com.ray.entity.Movie;
import com.ray.service.CategoryService;
import com.ray.service.MovieService;

@WebServlet("/admin/manage_movie")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // if file > 1MB, store disk instead RAM
		maxFileSize = 1024 * 1024 * 10, // maximum file size = 10MB
		maxRequestSize = 1024 * 1024 * 100 // maximum request size = 100MB
)
public class MovieController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MovieService movieService;
	private CategoryService categoryService;

	public MovieController() {
		super();
		movieService = new MovieService();
		categoryService = new CategoryService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String theCommand = request.getParameter("command");

		if (theCommand == null) {
			theCommand = "LIST";
		}

		switch (theCommand) {
		case "LIST":
			getList(request, response);
			break;
		case "LOAD":
			showEditForm(request, response);
			break;
		case "DELETE":
			delete(request, response);
			break;
		default:
			getList(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String theCommand = request.getParameter("command");

		if (theCommand == null) {
			theCommand = "LIST";
		}

		switch (theCommand) {
		case "INSERT":
			insert(request, response);
			break;
		case "UPDATE":
			update(request, response);
			break;
		default:
			getList(request, response);
		}
	}

	private void getList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get data
		List<Movie> movieList = movieService.listMovie();
		List<Category> categoryList = categoryService.listCategory();

		/// pass data to JSP
		request.setAttribute("movieList", movieList);
		request.setAttribute("categoryList", categoryList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("movie.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Integer theMovieId = Integer.valueOf(request.getParameter("movieId"));

		Movie movieToUpdate = movieService.getById(theMovieId);
		session.setAttribute("theMovie", movieToUpdate);

		List<Category> categoryList = categoryService.listCategory();
		request.getSession().setAttribute("categoryList", categoryList);

		response.sendRedirect("movie.jsp");
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Movie newMovie = new Movie();

		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		Category category = categoryService.getById(categoryId);

		newMovie.setName(request.getParameter("name"));
		newMovie.setAuthor(request.getParameter("author"));
		newMovie.setActor(request.getParameter("actor"));
		newMovie.setCountry(request.getParameter("country"));
		newMovie.setAuthor(request.getParameter("author"));
		newMovie.setDesc(request.getParameter("desc"));
		newMovie.setCatdesc(request.getParameter("catDesc"));
		newMovie.setStatus(new Byte(request.getParameter("status")));
//		newMovie.setImg(request.getParameter("img"));
		newMovie.setEpisode(new Integer(request.getParameter("episode")));
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date publishDate = dateFormat.parse(request.getParameter("publishDate"));
			newMovie.setPublishDate(publishDate);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new ServletException("The format date is yyyy-MM-dd");
		}
		newMovie.setCategory(category);

//		Part filePart = request.getPart("image");
//
//		if (filePart != null & filePart.getSize() > 0) {
//			long size = filePart.getSize();
//			byte[] imageBytes = new byte[(int) size];
//
//			InputStream inputStream = filePart.getInputStream();
//			inputStream.read(imageBytes);
//			inputStream.close();
//
//			newMovie.setImg(imageBytes);
//		}

		String errorMessage = this.movieService.create(newMovie);
		if (errorMessage != null) {
			request.setAttribute("message", errorMessage);
			request.setAttribute("theMovie", newMovie);
			RequestDispatcher rd = request.getRequestDispatcher("movie.jsp");
			rd.forward(request, response);
			return;
		}

		response.sendRedirect(request.getContextPath() + "/admin/");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		Integer movieId = Integer.valueOf(request.getParameter("movieId"));
//		String name = request.getParameter("name");
//		
//		Movie MovieToUpdate = new Movie(movieId, name);
//		String errorMessage = movieService.update(MovieToUpdate);
//		
//		if (errorMessage != null) {
//			request.setAttribute("message", errorMessage);
//			RequestDispatcher rd = request.getRequestDispatcher("Movie_form.jsp");
//			rd.forward(request, response);
//			return;
//		}
//		
//		response.sendRedirect("manage_movie?command=LIST");
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer movieId = Integer.valueOf(request.getParameter("movieId"));
		movieService.delete(movieId);

		response.sendRedirect("manage_movie?command=LIST");
	}
}
