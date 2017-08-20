<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Demoday Suche</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<div class="container">
    <div class="row spacer">
        <div class="col-lg-12">
            <form action="/" method="post">
                <div class="input-group">
                    <input id="query" name="query" class="form-control dropdown-toggle" data-toggle="dropdown"
                           placeholder="Search for..."
                           aria-label="Search for...">
                    <span class="input-group-btn">
                        <button class="btn btn-secondary">Go!</button>
                    </span>
                    <div id="suggestions" class="dropdown-menu"></div>
                </div>
            </form>
        </div>
    </div>

<#if query?has_content>
    <div class="row spacer">
        <div class="col-lg-12">
            <h6>Searching for: ${query}</h6>
        </div>
    </div>
</#if>

    <div class="row spacer">
        <div class="col-lg-12">
            <ul class="list-group">
            <#list results as result>
                <li class="list-group-item">${result}</li>
            </#list>
            </ul>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
<script src="/js/autocomplete.js"></script>

</body>
</html>