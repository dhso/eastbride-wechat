package frame.plugin.shiro.freemarker;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

@SuppressWarnings("deprecation")
public class ShiroFreemarkerKit implements TemplateMethodModel {

	@Override
	public Object exec(@SuppressWarnings("rawtypes") List list) throws TemplateModelException {
		if (null == list || 1 != list.size()) {
			throw new TemplateModelException("Arguments wrong:one argument is allowed");
		}
		String permission = (String) list.get(0);
		return permission != null && permission.length() > 0 && getSubject() != null && getSubject().isPermitted(permission);
	}

	private static Subject getSubject() {
		return SecurityUtils.getSubject();
	}
}
