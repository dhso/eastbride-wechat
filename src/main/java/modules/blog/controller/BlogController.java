package modules.blog.controller;

import java.io.IOException;

import modules.blog.entity.Article;
import modules.blog.entity.Tag;
import modules.system.entity.Message;
import modules.system.model.SysConfigModel;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONException;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;

import frame.kit.ServletKit;
import frame.kit.StringKit;

public class BlogController extends Controller {
	public void index() {
		Integer pageNumber = getParaToInt("pageNumber", 1);
		Integer pageSize = getParaToInt("pageSize", 10);
		setAttr("articlePage", Article.dao.getArticles(pageNumber, pageSize));
		setAttr("tagList", Tag.dao.getTags());
		setAttr("popularArticleList", Article.dao.getPopularArticles(5));
		setAttr("blogTitle", SysConfigModel.dao.getCfgValue("blog.title"));
		setAttr("request", getRequest());
		setAttr("fullUrl", ServletKit.getUrl(getRequest()));
		render("front/articleList.htm");
	}

	public void tag() {
		Integer pageNumber = getParaToInt("pageNumber", 1);
		Integer pageSize = getParaToInt("pageSize", 10);
		Integer tagId = getParaToInt("tagId", -1);
		if (tagId != -1) {
			Page<Record> articlePage = Article.dao.getArticlesByTagId(pageNumber, pageSize, tagId);
			if (articlePage.getList().size() == 0) {
				setAttr("message", new Message("200", "warning", "没有找到标签ID为 " + tagId + " 的文章！"));
				render("front/message.htm");
				return;
			}
			setAttr("articlePage", articlePage);
		}
		setAttr("breadcrumbTag", Tag.dao.getTagByTagId(tagId));
		setAttr("tagList", Tag.dao.getTags());
		setAttr("popularArticleList", Article.dao.getPopularArticles(5));
		setAttr("blogTitle",  SysConfigModel.dao.getCfgValue("blog.title"));
		setAttr("request", getRequest());
		setAttr("fullUrl", ServletKit.getUrl(getRequest()));
		render("front/articleList.htm");
	}

	public void search() {
		Integer pageNumber = getParaToInt("pageNumber", 1);
		Integer pageSize = getParaToInt("pageSize", 10);
		String searchKey = getPara("searchKey", "");
		if (StringKit.isNotBlank(searchKey)) {
			Page<Record> articlePage = Article.dao.getArticlesBySearch(pageNumber, pageSize, "%".concat(searchKey).concat("%"));
			if (articlePage.getList().size() == 0) {
				setAttr("message", new Message("200", "warning", "没有搜索到与  " + searchKey + " 相关的文章！"));
				render("front/message.htm");
				return;
			}
			setAttr("articlePage", articlePage);
		}
		setAttr("breadcrumbSearch", searchKey);
		setAttr("tagList", Tag.dao.getTags());
		setAttr("popularArticleList", Article.dao.getPopularArticles(5));
		setAttr("blogTitle",  SysConfigModel.dao.getCfgValue("blog.title"));
		setAttr("request", getRequest());
		setAttr("fullUrl", ServletKit.getUrl(getRequest()));
		render("front/articleList.htm");
	}

	public void articlePage() {
		Integer articleId = getParaToInt("articleId", -1);
		Record article = null;
		if (articleId != -1) {
			Article.dao.updateArticleView(articleId);
			article = Article.dao.getArticleByArticleId(articleId);
		}
		if (null == article) {
			renderError(404, "error404");
			return;
		}
		setAttr("article", article);
		setAttr("tagList", Tag.dao.getTags());
		setAttr("popularArticleList", Article.dao.getPopularArticles(5));
		setAttr("blogTitle", SysConfigModel.dao.getCfgValue("blog.title"));
		setAttr("request", getRequest());
		setAttr("fullUrl", ServletKit.getUrl(getRequest()));
		render("front/articlePage.htm");
	}

	public void tagsWall() {
		setAttr("tagList", Tag.dao.getTags());
		setAttr("blogTitle",SysConfigModel.dao.getCfgValue("blog.title"));
		setAttr("request", getRequest());
		setAttr("fullUrl", ServletKit.getUrl(getRequest()));
		render("front/tagsWall.htm");
	}

	@ActionKey("back/ueditor")
	public void ueditor() throws JSONException, FileUploadException, IOException {
		/*String rootPath = StringKit.getClassRealPath(UeditorKit.class);
		String result = new UeditorKit(getRequest(), rootPath).exec();
		renderText(result);*/
	}

	@ActionKey("back/article/add")
	@RequiresPermissions("cms:article:add")
	public void addArticle() {
		setAttr("tagList", Tag.dao.getTags());
		setAttr("blogTitle", SysConfigModel.dao.getCfgValue("blog.title"));
		setAttr("request", getRequest());
		setAttr("fullUrl", ServletKit.getUrl(getRequest()));
		render("back/addArticle.htm");
	}

	@ActionKey("back/article/submit")
	@RequiresPermissions(value = { "cms:article:add", "cms:article:edit" }, logical = Logical.OR)
	@Before(Tx.class)
	public void submitArticle() {
		Integer articleId = getParaToInt("articleId", -1);
		String articleTitle = getPara("articleTitle");
		String editorValue = getPara("editorValue").replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		String articleTag = getPara("articleTag");
		if (articleId == -1) {
			Record article = Article.dao.addArticle(articleTitle, editorValue, articleTag);
			articleId = article.getInt("articleId");
			redirect("/back/article/edit?articleId=" + String.valueOf(articleId) + "&type=add");
		} else {
			Article.dao.updateArticle(articleId, articleTitle, editorValue, articleTag);
			redirect("/back/article/edit?articleId=" + String.valueOf(articleId) + "&type=update");
		}

	}

	@ActionKey("back/article/edit")
	@RequiresPermissions("cms:article:edit")
	public void editArticle() {
		Integer articleId = getParaToInt("articleId", -1);
		String type = getPara("type", "");
		if (articleId != -1) {
			setAttr("article", Article.dao.getArticleByArticleId(articleId));
		}
		if ("add".equalsIgnoreCase(type)) {
			setAttr("message", new Message("200", "success", "添加文章成功！"));
		} else if ("update".equalsIgnoreCase(type)) {
			setAttr("message", new Message("200", "success", "修改文章成功！"));
		}
		setAttr("tagList", Tag.dao.getTags());
		setAttr("blogTitle",SysConfigModel.dao.getCfgValue("blog.title"));
		setAttr("request", getRequest());
		setAttr("fullUrl", ServletKit.getUrl(getRequest()));
		render("back/editArticle.htm");
	}

	@ActionKey("back/article/delete")
	@RequiresPermissions("cms:article:delete")
	@Before(Tx.class)
	public void deleteArticle() {
		Integer articleId = getParaToInt("articleId", -1);
		if (articleId != -1) {
			Article.dao.deleteArticle(articleId);
			setAttr("message", new Message("200", "success", "删除文章成功！"));
		} else {
			setAttr("message", new Message("200", "error", "错误的文章ID！"));
		}
		render("front/message.htm");
	}

	@ActionKey("back/settingsPage")
	@RequiresPermissions("cms:setting:edit")
	public void settingsPage() {
		setAttr("blogTitle",SysConfigModel.dao.getCfgValue("blog.title"));
		setAttr("request", getRequest());
		setAttr("fullUrl", ServletKit.getUrl(getRequest()));
		render("back/settingsPage.htm");
	}


}
