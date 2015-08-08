/**
 * 
 */
/**
 * @author hadong
 *
 */
package modules.blog.entity;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

import frame.plugin.sqlinxml.SqlKit;
import frame.plugin.tablebind.TableBind;

@SuppressWarnings("serial")
@TableBind(tableName = "blog_tags", pkName = "tag_id")
public class Tag extends Model<Tag> {
	public static final Tag dao = new Tag();

	public List<Record> getTags() {
		return Db.find(SqlKit.sql("blog.getTags"));
	}

	public Record getTagByTagId(Integer tagId) {
		return Db.findFirst(SqlKit.sql("blog.getTagByTagId"), tagId);
	}

}