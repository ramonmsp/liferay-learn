package com.acme.f2m9.web.internal.portlet;

import com.acme.f2m9.service.TodoLocalService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"javax.portlet.display-name=F2M9 Portlet",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language"
	},
	service = Portlet.class
)
public class F2M9Portlet extends MVCPortlet {

	public void addTodo(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String item = ParamUtil.getString(actionRequest, "item");

		User user = _portal.getUser(actionRequest);

		_todoLocalService.addTodo(
			user.getCompanyId(), themeDisplay.getSiteGroupId(),
			user.getUserId(), user.getFullName(), item);
	}

	@Reference
	private Portal _portal;

	@Reference
	private TodoLocalService _todoLocalService;

}