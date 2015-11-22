package com.hrd.article.services;

import java.util.List;

import com.hrd.article.entities.ArticleDTO;

public interface ArtitcleServices {
	
	public List<ArticleDTO> listArticles(int page);
	public ArticleDTO listArticle(int id);
	public int updateArticle(ArticleDTO article);
	public int insertArticle(ArticleDTO article);
	public int deleteArticle(int id);
	public List<ArticleDTO> searchArticle(String key);

	public int getArticleRow(String key);

	public int enableArticle(int id);
	public int disableArticle(int id);


	public List<ArticleDTO> listArticles(String key,int pages, int uid, int cid);
	

	
}
