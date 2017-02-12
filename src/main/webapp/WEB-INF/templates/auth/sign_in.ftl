<#include "../main_template.ftl"/>

<#import "../macro/auth_form.ftl" as af_mac/>

<#macro content>
    <h1>Sign in</h1>

    <#if error??>
        <p class="help-block">Bad credentials or admin is not enabled.</p>
    </#if>

    <@af_mac.auth_form actionUrl="/sign_in/process" btnValue="Sign in"/>

    <h5>Not registered?</h5>
    <a href="/admin/sign_up">Sign up</a>
</#macro>

<@main title="Sign in"/>