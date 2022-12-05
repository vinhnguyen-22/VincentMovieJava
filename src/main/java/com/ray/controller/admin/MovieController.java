package com.ray.controller.admin;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
import javax.servlet.http.Part;

import com.google.gson.Gson;
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

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("application/json");
		Integer theMovieId = Integer.valueOf(request.getParameter("movieId"));

		Movie movieToUpdate = movieService.getById(theMovieId);

		List<Category> categoryList = categoryService.listCategory();
		request.getSession().setAttribute("categoryList", categoryList);
		Integer theCategoryId = Integer.valueOf(request.getParameter("catId"));

		Category categoryToUpdate = categoryService.getById(theCategoryId);
		Movie movie = new Movie();
		movie.setName(movieToUpdate.getName());
		movie.setAuthor(movieToUpdate.getAuthor());
		movie.setActor(movieToUpdate.getActor());
		movie.setCountry(movieToUpdate.getCountry());
		movie.setDesc(movieToUpdate.getDesc());
		movie.setCatdesc(movieToUpdate.getCatdesc());
		movie.setStatus(new Byte(movieToUpdate.getStatus()));
		movie.setEpisode(new Integer(movieToUpdate.getEpisode()));
		PrintWriter writer = response.getWriter();

		movie.setMovieId(theMovieId);
		movie.setPublishDate(movieToUpdate.getPublishDate());

		Gson gson = new Gson();
		writer.print(gson.toJson(movie));
		writer.flush();
		writer.close();
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Movie newMovie = new Movie();

		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		Category category = categoryService.getById(categoryId);

		newMovie.setName(request.getParameter("name"));
		newMovie.setAuthor(request.getParameter("author"));
		newMovie.setActor(request.getParameter("actor"));
		newMovie.setCountry(request.getParameter("country"));
		newMovie.setDesc(request.getParameter("description"));
		newMovie.setCatdesc(request.getParameter("catDesc"));
		newMovie.setStatus(new Byte(request.getParameter("status")));
		newMovie.setEpisode(new Integer(request.getParameter("episode")));
		newMovie.setCategory(category);

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date publishDate = dateFormat.parse(request.getParameter("publishDate"));
			newMovie.setPublishDate(publishDate);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new ServletException("The format date is yyyy-MM-dd");
		}

		Part filePart = request.getPart("file");

		if (filePart != null & filePart.getSize() > 0) {
			long size = filePart.getSize();
			byte[] imageBytes = new byte[(int) size];

			InputStream inputStream = filePart.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();

			newMovie.setImg(imageBytes);
		}

		String errorMessage = this.movieService.create(newMovie);
		if (errorMessage != null) {
			request.setAttribute("message", errorMessage);
			request.setAttribute("theMovie", newMovie);
			RequestDispatcher rd = request.getRequestDispatcher("movie.jsp");
			rd.forward(request, response);
		}

		response.sendRedirect(request.getContextPath() + "/admin/manage_movie");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Integer movieId = Integer.valueOf(request.getParameter("movieId"));
		Movie movieToUpdate = movieService.getById(movieId);

		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		Category category = categoryService.getById(categoryId);

		movieToUpdate.setName(request.getParameter("name"));
		movieToUpdate.setAuthor(request.getParameter("author"));
		movieToUpdate.setActor(request.getParameter("actor"));
		movieToUpdate.setCountry(request.getParameter("country"));
		movieToUpdate.setDesc(request.getParameter("description"));
		movieToUpdate.setCatdesc(request.getParameter("catDesc"));
		movieToUpdate.setStatus(new Byte(request.getParameter("status")));
		movieToUpdate.setEpisode(new Integer(request.getParameter("episode")));
		movieToUpdate.setCategory(category);

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date publishDate = dateFormat.parse(request.getParameter("publishDate"));
			movieToUpdate.setPublishDate(publishDate);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new ServletException("The format date is yyyy-MM-dd");
		}

		Part filePart = request.getPart("file");

		if (filePart != null & filePart.getSize() > 0) {
			long size = filePart.getSize();
			byte[] imageBytes = new byte[(int) size];

			InputStream inputStream = filePart.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();

			movieToUpdate.setImg(imageBytes);
		}

		String errorMessage = this.movieService.update(movieToUpdate);
		if (errorMessage != null) {
			request.setAttribute("message", errorMessage);
			request.setAttribute("theProduct", movieToUpdate);
			RequestDispatcher rd = request.getRequestDispatcher("movie.jsp");
			rd.forward(request, response);
			return;
		}
		response.sendRedirect(request.getContextPath() + "/admin/manage_movie?command=LIST");
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer movieId = Integer.valueOf(request.getParameter("movieId"));
		movieService.delete(movieId);

		response.sendRedirect("manage_movie?command=LIST");
	}
}
