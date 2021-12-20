<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.acme.f2m9.model.Todo" %><%@
page import="com.acme.f2m9.service.TodoLocalServiceUtil" %>

<%@ page import="com.liferay.portal.kernel.model.WorkflowInstanceLink" %><%@
page import="com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.util.HashMapBuilder" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowConstants" %>

<%@ page import="java.util.List" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<h4>F2M9 Portlet</h4>

<portlet:actionURL name="addTodo" var="addTodoURL" />

<p>
	<aui:form action="<%= addTodoURL %>">
		<aui:input name="item" type="text" />

		<aui:button type="submit" value="submit" />
	</aui:form>
</p>

<%
List<Todo> todoList = TodoLocalServiceUtil.getTodos(-1, -1);
%>

<h5>Todos</h5>
<c:choose>
	<c:when test="<%= (todoList != null) && !todoList.isEmpty() %>">
		<table>
			<tr>
				<th>Entry</th>
				<th>Status</th>
			</tr>

			<tbody>
				<%for(Todo todo: todoList) { %>
				<tr>
					<td><%= todo.getName() %></td>
					<td><%= WorkflowConstants.getStatusLabel(todo.getStatus()) %></td>
					<td>

						<%
						WorkflowInstanceLink workflowDefinitionLink = WorkflowInstanceLinkLocalServiceUtil.fetchWorkflowInstanceLink(todo.getCompanyId(), todo.getGroupId(), Todo.class.getName(), todo.getPrimaryKey());

						if (workflowDefinitionLink != null) {
							Long workflowInstanceId = workflowDefinitionLink.getWorkflowInstanceId();
						%>

						<react:component
							module="js/Index.js"
							props='<%=
								HashMapBuilder.<String, Object>put(
									"workflowInstanceId", workflowInstanceId
								).build()
							%>'
						/>

						<%} %>
					</td>
				</tr>
				<%} %>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<em>None</em>
	</c:otherwise>
</c:choose>
