$(document).ready(function () {
    $('#delete_hack').on('click', function () {
        if(confirm("Точно?")) {
            var hack_id = $(this).data("hack-id");

            $.ajax({
                url: '/admin/hacks/' + hack_id + '/delete',
                type: 'POST',
                success: function () {
                    window.location = '/admin/hacks';
                }
            });
        }
    })
});
