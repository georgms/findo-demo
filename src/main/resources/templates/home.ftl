<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Demoday Suche</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.js"></script>
    <script>
        $(document).ready(function () {
            $('#query').keyup(function () {
                var query = $('#query').val();
                console.log(query);

                $.ajax({
                    url: "/search/ac/" + query,
                    type: "GET"
                }).done(function (data, textStatus, jqXHR) {
                    if (jqXHR.status === 200) {
                        $('#suggestions').empty();
                        console.log(data);
                        $.each(data, function (index, value) {
                            $('#suggestions').append("<li><a class='suggestion' href='#'>" + value + "</a></li>");
                        });
                        registerClick();
                    }
                });
            });
        });

        function registerClick() {
            $('.suggestion').click(function () {
                var value = $(this).text();
                $('#query').val(value);
                $('#suggestions').empty();
            });
        }
    </script>
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