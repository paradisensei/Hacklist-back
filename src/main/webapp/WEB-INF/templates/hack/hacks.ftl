<#include "../main_template.ftl"/>

<#macro content>
    <h1>Hacks</h1>

    <table>
        <thead>
            <tr>
                <th>Title</th>
                <th>City</th>
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

<@main title="Hacks"/>