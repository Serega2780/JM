$(document).ready(function () {
    $.ajax({
        dataType: "json",
        type: 'GET',
        url: "/user/users",

    }).done(function (users) {
        $.each(users, function (i, user) {

            $('#userBody').append("<tr>")
                .append("<td>" + user.id + "</td>")
                .append("<td>" + user.name + "</td>")
                .append("<td>" + user.email + "</td>")
                .append("<td>" + user.country + "</td>")
                .append("</tr>");

        })
    }).fail(function (jqXHR, textStatus, errorThrown) {
    });

    $.ajax({
        dataType: "json",
        type: 'GET',
        url: "/user/user",

    }).done(function (user) {

        $("h4.hello").text("Hello, " + user.name + " !!!");

    }).fail(function () {

    });
})