$(document).ready(function () {
    $(document).on('click', '#flush', function () {
        $.ajax({
            url: '/admin/flush',
            type: 'post',
            success: function () {
                alert('Cache was successfully flushed');
            }
        });
    })
});

