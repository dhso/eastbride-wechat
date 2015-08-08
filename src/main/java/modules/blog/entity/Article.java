/**
 * 
 */
/**
 * @author hadong
 *
 */
package modules.blog.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.shiro.SecurityUtils;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import frame.plugin.sqlinxml.SqlKit;
import frame.plugin.tablebind.TableBind;

@SuppressWarnings("serial")
@TableBind(tableName = "blog_articles", pkName = "article_id")
public class Article extends Model<Article> {
	public static final Article dao = new Article();

	public Page<Record> getArticles(int pageNumber, int pageSize) {
		return Db.paginate(pageNumber, pageSize, SqlKit.sql("blog.getArticles"), SqlKit.sql("blog.getArticlesEx"));
	}

	public Page<Record> getArticlesByTagId(Integer pageNumber, Integer pageSize, Integer tagId) {
		return Db.paginate(pageNumber, pageSize, SqlKit.sql("blog.getArticlesByTagId"), SqlKit.sql("blog.getArticlesByTagIdEx"), tagId);
	}

	public Record getArticleByArticleId(Integer articleId) {
		return Db.findFirst(SqlKit.sql("blog.getArticleByArticleId"), articleId);
	}

	public int updateArticleView(Integer articleId) {
		return Db.update("update cms_articles set article_view = article_view + 1 where article_id= ?", articleId);
	}

	public List<Record> getPopularArticles(int number) {
		return Db.find(SqlKit.sql("blog.getPopularArticles"), number);
	}

	public Record addArticle(String articleTitle, String editorValue, String articleTag) {
		List<String> tags = Arrays.asList(articleTag.split(","));
		List<Integer> tagsId = new ArrayList<Integer>();
		Iterator<String> it = tags.iterator();
		while (it.hasNext()) {
			String tagName = it.next();
			Record record = Db.findFirst("select * from cms_tags where tag_name = ?", tagName);
			if (null == record) {
				Db.update("insert into cms_tags (tag_name) values (?)", tagName);
				record = Db.findFirst("select * from cms_tags where tag_name = ?", tagName);
				Integer tagId = record.getInt("tag_id");
				tagsId.add(tagId);
			} else {
				Integer tagId = record.getInt("tag_id");
				tagsId.add(tagId);
			}
		}
		Db.update(SqlKit.sql("blog.addArticle"), articleTitle, editorValue, SecurityUtils.getSubject().getPrincipal().toString(), new Date(), new Date());
		Integer articleId = Db.findFirst("select article_id from cms_articles where article_title = ? and article_author = ? order by create_dt desc", articleTitle, SecurityUtils.getSubject().getPrincipal().toString()).getInt("article_id");
		Iterator<Integer> tagsIt = tagsId.iterator();
		while (tagsIt.hasNext()) {
			Db.update("insert into cms_article_tag (article_id,tag_id) values(?,?)", articleId, tagsIt.next());
		}
		Record article = Db.findFirst(SqlKit.sql("blog.getArticleByArticleId"), articleId);
		return article;
	}

	public void updateArticle(Integer articleId, String articleTitle, String editorValue, String articleTag) {
		List<String> tags = Arrays.asList(articleTag.split(","));
		List<Integer> tagsId = new ArrayList<Integer>();
		Db.update(SqlKit.sql("blog.updateArticle"), articleTitle, editorValue, new Date(), articleId);
		Db.update("delete from cms_article_tag where article_id = ?", articleId);
		Iterator<String> it = tags.iterator();
		while (it.hasNext()) {
			String tagName = it.next();
			Record record = Db.findFirst("select * from cms_tags where tag_name = ?", tagName);
			if (null == record) {
				Db.update("insert into cms_tags (tag_name) values (?)", tagName);
				record = Db.findFirst("select * from cms_tags where tag_name = ?", tagName);
				Integer tagId = record.getInt("tag_id");
				tagsId.add(tagId);
			} else {
				Integer tagId = record.getInt("tag_id");
				tagsId.add(tagId);
			}
		}
		Iterator<Integer> tagsIt = tagsId.iterator();
		while (tagsIt.hasNext()) {
			Db.update("insert into cms_article_tag (article_id,tag_id) values(?,?)", articleId, tagsIt.next());
		}
	}

	public void deleteArticle(Integer articleId) {
		Db.update("delete from cms_articles where article_id = ?", articleId);
		Db.update("delete from cms_article_tag where article_id = ?", articleId);
	}

	public Page<Record> getArticlesBySearch(Integer pageNumber, Integer pageSize, String searchKey) {
		return Db.paginate(pageNumber, pageSize, SqlKit.sql("blog.getArticlesBySearch"), SqlKit.sql("blog.getArticlesBySearchEx"), searchKey, searchKey);
	}

}