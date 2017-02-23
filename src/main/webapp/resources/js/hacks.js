$(document).ready(function () {
    $("#flush").on('click', function () {
        $.ajax({
            url: '/admin/flush',
            type: 'POST',
            success: function () {
                alert('Cache was successfully flushed');
            }
        });
    })
});

