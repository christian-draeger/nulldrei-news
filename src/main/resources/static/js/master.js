$(document).ready(function() {

    var feedback_dialog = $('#kontakt');

    var display_success_message = function () {
        var success_message = feedback_dialog.data('success');

        feedback_dialog.find('.modal-body').slideUp(500);
        feedback_dialog.find('.modal-header')
            .append('<br><div class="panel panel-heading panel-success">' + success_message + '</div>').fadeIn(400);
    };

    var display_error_dialog = function () {
        feedback_dialog.modal("hide");
        $('#codegenerator-error').modal('show');
    };

    var contact_submit = function (event) {
        event.preventDefault();

        var responseField = feedback_dialog.find("#g-recaptcha-response").val();

        $.ajax({
            url: "/feedback",
            method: "POST",
            data: {
                from: feedback_dialog.find('[name="email-adresse"]').val(),
                message: feedback_dialog.find('textarea').val(),
                'g-recaptcha-response': responseField
            }
        })
            .done(display_success_message)
            .fail(display_error_dialog);
    };

    feedback_dialog.find('form').submit(contact_submit);
});
