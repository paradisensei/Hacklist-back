<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>

<#macro auth_form actionUrl btnValue>
    <@sf.form role="form" action=actionUrl method="post" modelAttribute="authForm">
    <fieldset>
        <div class="field">
            <@sf.label path="email">Email</@sf.label>
            <@sf.input path="email" cssClass="form-control" type="email"/>
            <@sf.errors path="email" cssClass="help-block"/>
        </div>

        <div class="field">
            <@sf.label path="password">Password</@sf.label>
            <@sf.input path="password" cssClass="form-control" type="password"/>
            <@sf.errors path="password" cssClass="help-block"/>
        </div>

        <div class="form-group">
            <input class="btn btn-info btn-outline" type="submit" value=${btnValue}>
        </div>
    </fieldset>
    </@sf.form>
</#macro>