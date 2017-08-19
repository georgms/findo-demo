<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Demoday Suche</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.js"></script>
    <script src="/js/autocomplete.js"></script>
</head>
<body>

<form action="/" method="post">
    <div>
        <label for="query">Suche</label>
        <input id="query" name="query" placeholder="Suchbegriffe eingeben"
               required
               autofocus>
    </div>
    <div>
        <button>Suchen</button>
    </div>

    <ul id="suggestions"></ul>

    <div>
    <#if query?has_content>
        Suche nach: ${query}
    </#if>
    <#list results as result>
        <div>${result}</div>
    </#list>
    </div>
</form>
</body>
</html>