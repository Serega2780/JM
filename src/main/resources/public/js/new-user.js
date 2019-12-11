$(document).ready(function () {
    $.ajax({
        type: 'ajax',
        method: 'GET',
        url: "/new-user/countries",
        async: false,
        dataType: 'json',
        success: function (countries) {
            countries.forEach(function (item) {
                $('#newCountryList').append('<option value="' + item + '\">' + item + '</option>');
            });
        },
        error: function () {

        }
    });

    $('#newSignup').submit(function () {
        var name = $('input[id=login]').val();
        var password = $('input[id=password]').val();
        var email = $('input[id=email]').val();
        var country = $('#newCountryList option:selected').val();
        var role = {
            "id": 2,
            "role": "ROLE_USER"
        };
        var user = {
            "id": 0,
            "name": name,
            "password": password,
            "email": email,
            "country": country,
            "grantedAuthorities": [role]
        }
        $.ajax({
            method: 'POST',
            url: "/new-user/insert",
            dataType: 'json',
            contentType: 'application/json',
            async: false,
            data: JSON.stringify(user),
        }).done(function (data) {

        }).fail(function () {

        }).always(function () {

        })

    })
})