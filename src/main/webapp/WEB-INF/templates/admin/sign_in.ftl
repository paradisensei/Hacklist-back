<#include "../main_template.ftl"/>

<#import "../macro/auth_form.ftl" as af_mac/>

<#macro content>
    <h1>Sign in</h1>
    <@af_mac.auth_form actionUrl="/login/process" btnValue="Sign_in"/>

    <#if error??>
        <p>Bad credentials</p>
    </#if>
</#macro>

<@main title="Sign in"/>