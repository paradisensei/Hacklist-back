<#include "../main_template.ftl"/>

<#import "../macro/hack_form.ftl" as hf_mac/>

<#macro content>
    <h1>New hack</h1>
    <@hf_mac.hack_form actionUrl="/admin/hacks/create" btnValue="Add"/>
</#macro>

<@main title="New hack"/>