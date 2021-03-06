package simpledemo.dao;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import simpledemo.model.Article;

@Repository("provider")
public class MockArticleProvider implements ArticleProvider {
	private AtomicLong sequence = new AtomicLong(0);
	private Map<String, Article> articles = new ConcurrentHashMap<>();
	
	public MockArticleProvider() {
		Arrays.asList(
				new Article("Welcome to Spring 5", "Spring 5 is great because ...", "0000000000001"),
				new Article("Dependency Injection", "Should I use DI or lookup ...", "0000000000001"),
				new Article("Spring Beans and Wireing", "There are several way to wire Spring beans ...", "0000000000001")
			).stream().forEach(this::addArticle);
		
	}
	
	@Override
	public Collection<Article> getArticles() {
		return articles.values();
	}

	@Override
	public Article addArticle(Article article) {
		article.setId("" + sequence.incrementAndGet());
		articles.put(article.getId(), article);
		return article;
	}

	@Override
	public Optional<Article> getArticleById(long id) {
		return Optional.ofNullable(articles.get(id));
	}

	@Override
	public Optional<Article> deleteArticleById(long id) {
		return Optional.ofNullable(articles.remove(id));
	}
}
