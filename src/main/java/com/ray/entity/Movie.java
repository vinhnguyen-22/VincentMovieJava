package com.ray.entity;

import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "movie")
@NamedQueries({ @NamedQuery(name = "Movie.HQL.getByName", query = "SELECT m FROM Movie m where m.name = :name"),
		@NamedQuery(name = "Movie.HQL.getByNameAndNotMovieId", query = "SELECT m FROM Movie m where m.name = :name and m.movieId != :moieId") })
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_id")
	private Integer movieId;

	@Column(name = "name")
	private String name;

	@Column(name = "actor")
	private String actor;

	@Column(name = "country")
	private String country;

	@Column(name = "author")
	private String author;

	@Column(name = "description")
	private String description;

	@Column(name = "cat_desc")
	private String catdesc;

	@Column(name = "status")
	private byte status;

	@Column(name = "img")
	private byte[] img;

	@Column(name = "episode")
	private int episode;

	@Column(name = "publish_date")
	private Date publishDate;

	@Column(name = "last_update_time")
	@UpdateTimestamp
	private Date lastUpdateTime;

	@ManyToOne()
	@JoinColumn(name = "category_id")
	private Category category;

	@Transient
	private String base64Image;

	public Movie() {
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDesc() {
		return description;
	}

	public void setDesc(String desc) {
		this.description = desc;
	}

	public String getCatdesc() {
		return catdesc;
	}

	public void setCatdesc(String catdesc) {
		this.catdesc = catdesc;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public int getEpisode() {
		return episode;
	}

	public void setEpisode(int episode) {
		this.episode = episode;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getBase64Image() {
		return Base64.getEncoder().encodeToString(img);
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", name=" + name + ", actor=" + actor + ", country=" + country
				+ ", author=" + author + ", description=" + description + ", catdesc=" + catdesc + ", status=" + status
				+ ", img=" + Arrays.toString(img) + ", episode=" + episode + ", publishDate=" + publishDate
				+ ", lastUpdateTime=" + lastUpdateTime + ", category=" + category + ", base64Image=" + base64Image
				+ "]";
	}

}