<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>

<#macro hack_form actionUrl btnValue>
    <@sf.form role="form" action=actionUrl method="post" modelAttribute="hack">
        <fieldset>
            <div class="field">
                <@sf.label path="title">Title</@sf.label>
                <@sf.input path="title" cssClass="form-control" type="text"/>
                <@sf.errors path="title" cssClass="help-block"/>
            </div>
            <div class="field">
                <@sf.label path="city">City</@sf.label>
                <@sf.input path="city" cssClass="form-control" type="text"/>
                <@sf.errors path="city" cssClass="help-block"/>
            </div>
            <div class="field">
                <@sf.label path="address">Address</@sf.label>
                <@sf.input path="address" cssClass="form-control" type="text"/>
                <@sf.errors path="address" cssClass="help-block"/>
            </div>
            <div class="field">
                <@sf.label path="date">Date</@sf.label>
                <@sf.input path="date" cssClass="form-control" type="datetime-local"/>
                <@sf.errors path="date" cssClass="help-block"/>
            </div>
            <div class="field">
                <@sf.label path="description">Description</@sf.label>
                <@sf.textarea path="description" cssClass="form-control" rows="15"/>
                <@sf.errors path="description" cssClass="help-block"/>
            </div>
            <div class="form-group">
                <input class="btn btn-info btn-outline" type="submit" value=${btnValue}>
            </div>
        </fieldset>
    </@sf.form>
</#macro>