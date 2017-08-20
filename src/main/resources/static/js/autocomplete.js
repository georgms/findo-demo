$(document).ready(function () {
    var xhr;
    var timeout;
    var delay = 330;

    $('#query').keyup(function () {
        if (timeout) {
            clearTimeout(timeout);
        }

        timeout = setTimeout(function () {
            if (xhr) {
                xhr.abort();
            }

            var query = $('#query').val();
            console.log(query);

            xhr = $.ajax({
                url: "/ac",
                type: "GET",
                data: {query: query}
            }).done(function (data, textStatus, jqXHR) {
                if (jqXHR.status === 200) {
                    $('#suggestions').empty();
                    console.log(data);
                    $.each(data, function (index, value) {
                        $('#suggestions').append("<a class='suggestion dropdown-item' href='#'>" + value + "</a>");
                    });
                    registerClick();
                }
            });
        }, delay);
    });
});

function registerClick() {
    $('.suggestion').click(function () {
        var value = $(this).text();
        $('#query').val(value);
        $('#suggestions').empty();
    });
}