<#include "../main_template.ftl"/>

<#macro content>
    <a href="/logout">Logout</a>
    <div>
        <h1 style="margin-right: 35px; display: inline-block">Hacks</h1>
        <button type="button" id="flush" style="display: inline-block">
            Flush cache
        </button>
    </div>

    <table>
        <thead>
            <tr>
                <th>Title</th>
                <th>City</th>
                <th>Category</th>
                <th>Address</th>
                <th>Organizer</th>
                <th>Date</th>
                <th>Description</th>
                <th>Image</th>
                <th>Url</th>
            </tr>
        </thead>
        <tbody>
            <#list hacks as hack>
                <tr>
                    <td>
                        <a href="/admin/hacks/${hack.id}">${hack.title}</a>
                    </td>
                    <td>${hack.city.getName()}</td>
                    <td>${hack.category}</td>
                    <td>${hack.address}</td>
                    <td>${hack.organizer}</td>
                    <td>${hack.date}</td>
                    <td>${hack.description}</td>
                    <td><a href="${hack.imageUrl}" target="_blank">Image</a></td>
                    <td><a href="${hack.url}" target="_blank">Url</a></td>
                </tr>
            </#list>
        </tbody>
    </table>
</#macro>

<@main title="Hacks" scripts=["/js/hacks.js"]/>