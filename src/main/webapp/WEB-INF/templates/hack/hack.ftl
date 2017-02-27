<#include "../main_template.ftl"/>

<#macro content>
    <h1>Hack</h1>

    <#if hack??>
        <p>${hack.title}</p>
        <p>${hack.city.getName()}</p>
        <p>${hack.category}</p>
        <p>${hack.address}</p>
        <p>${hack.organizer}</p>
        <p>${hack.date}</p>
        <p>${hack.description}</p>
        <p><a href="${hack.imageUrl}" target="_blank">Image</a></p>
        <p><a href="${hack.url}" target="_blank">Url</a></p>
        <a href="/admin/hacks/${hack.id}/update">Update</a>
        <a href="/admin/hacks/">Back</a>
        <button type="button" id="delete_hack" data-hack-id="${hack.id}">Delete</button>
    </#if>
</#macro>

<@main title="Hack" scripts=["/js/hack.js"]/>