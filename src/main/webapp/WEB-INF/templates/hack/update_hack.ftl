<#include "../main_template.ftl"/>

<#import "../macro/hack_form.ftl" as hf_mac/>

<#macro content>
    <h1>Update hack</h1>
    <@hf_mac.hack_form actionUrl="/admin/hacks/${hack.id}/update" btnValue="Update"/>
</#macro>

<@main title="New hack"/>