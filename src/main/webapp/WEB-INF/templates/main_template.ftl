<#macro main title scripts=[] styles=[]>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" href=
                "https://datasciencebe.files.wordpress.com/2015/02/keep-calm-and-hackathon-9.png"/>

        <title>${title}</title>

        <#list styles as style>
            <link rel="stylesheet" type="text/css" href="${style}">
        </#list>
    </head>

    <body>
        <@content/>

        <!-- jQuery -->
        <script src="/js/jquery.min.js"></script>

        <#list scripts as script>
            <script type="application/javascript" src="${script}"></script>
        </#list>
    </body>
</#macro>