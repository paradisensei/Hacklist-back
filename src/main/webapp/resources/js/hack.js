$(document).ready(function () {
    $(document).on('click', '#delete_hack', function () {
        var hack_id = $('#hack_id').val();

        $.ajax({
            url: '/admin/hacks/' + hack_id + '/delete',
            type: 'post',
            success: function () {
                window.location = '/admin/hacks';
            }
        });
    })
});
