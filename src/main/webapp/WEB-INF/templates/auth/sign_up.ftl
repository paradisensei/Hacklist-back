<#include "../main_template.ftl"/>

<#import "../macro/auth_form.ftl" as af_mac/>

<#macro content>
    <h1>Sign up</h1>
    <@af_mac.auth_form actionUrl="/admin/sign_up" btnValue="Sign up"/>
</#macro>

<@main title="Sign up"/>