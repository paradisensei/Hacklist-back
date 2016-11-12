<#include "../main_template.ftl"/>

<#macro content>
    <h1>Hacks</h1>

    <table>
        <thead>
            <tr>
                <th>Title</th>
                <th>City</th>
                <th>Address</th>
                <th>Date</th>
                <th>Description</th>
            </tr>
        </thead>
        <tbody>
            <#list hacks as hack>
                <tr>
                    <td>
                        <a href="/admin/hacks/${hack.id}">${hack.title}</a>
                    </td>
                    <td>${hack.city}</td>
                    <td>${hack.address}</td>
                    <td>${hack.date}</td>
                    <td>${hack.description}</td>
                </tr>
            </#list>
        </tbody>
    </table>
</#macro>

<@main title="Hacks"/>