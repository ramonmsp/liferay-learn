package com.acme.c1n4.web.internal.frontend.taglib.servlet.taglib;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationCategory;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.io.IOException;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"screen.navigation.category.order:Integer=11",
		"screen.navigation.entry.order:Integer=11"
	},
	service = {ScreenNavigationCategory.class, ScreenNavigationEntry.class}
)
public class C1N4ScreenNavigationEntry
	implements ScreenNavigationCategory, ScreenNavigationEntry<CPDefinition> {

	@Override
	public String getCategoryKey() {
		return "c1n4";
	}

	@Override
	public String getEntryKey() {
		return "c1n4";
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "c1n4-screen-navigation-entry");
	}

	@Override
	public String getScreenNavigationKey() {
		return "cp.definition.general";
	}

	@Override
	public boolean isVisible(User user, CPDefinition cpDefinition) {
		if (cpDefinition == null) {
			return false;
		}

		return Objects.equals(
			cpDefinition.getProductTypeName(), getCategoryKey());
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		_jspRenderer.renderJSP(
			_servletContext, httpServletRequest, httpServletResponse,
			"/c1n4.jsp");
	}

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference(target = "(osgi.web.symbolicname=com.acme.c1n4.web)")
	private ServletContext _servletContext;

}