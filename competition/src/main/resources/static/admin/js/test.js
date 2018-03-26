/**
 * Created by Chan on 17/5/15.
 */

$(function () {
    var i = -1;
    var toastCount = 0;
    var $toastlast;
    var getMessage = function () {
        var msg = "Hi, welcome to Inspinia. This is example of Toastr notification box.";
        return msg
    };
    $("#showsimple").click(function () {
        toastr.success("Without any options", "Simple notification!")
    });
    $("#showtoast").click(function () {
        var shortCutFunction = $("#toastTypeGroup input:radio:checked").val();
        var msg = $("#message").val();
        var title = $("#title").val() || "";
        var $showDuration = $("#showDuration");
        var $hideDuration = $("#hideDuration");
        var $timeOut = $("#timeOut");
        var $extendedTimeOut = $("#extendedTimeOut");
        var $showEasing = $("#showEasing");
        var $hideEasing = $("#hideEasing");
        var $showMethod = $("#showMethod");
        var $hideMethod = $("#hideMethod");
        var toastIndex = toastCount++;
        toastr.options = {
            closeButton: $("#closeButton").prop("checked"),
            debug: $("#debugInfo").prop("checked"),
            progressBar: $("#progressBar").prop("checked"),
            positionClass: $("#positionGroup input:radio:checked").val() || "toast-top-right",
            onclick: null
        };
        if ($("#addBehaviorOnToastClick").prop("checked")) {
            toastr.options.onclick = function () {
                alert("You can perform some custom action after a toast goes away")
            }
        }
        if ($showDuration.val().length) {
            toastr.options.showDuration = $showDuration.val()
        }
        if ($hideDuration.val().length) {
            toastr.options.hideDuration = $hideDuration.val()
        }
        if ($timeOut.val().length) {
            toastr.options.timeOut = $timeOut.val()
        }
        if ($extendedTimeOut.val().length) {
            toastr.options.extendedTimeOut = $extendedTimeOut.val()
        }
        if ($showEasing.val().length) {
            toastr.options.showEasing = $showEasing.val()
        }
        if ($hideEasing.val().length) {
            toastr.options.hideEasing = $hideEasing.val()
        }
        if ($showMethod.val().length) {
            toastr.options.showMethod = $showMethod.val()
        }
        if ($hideMethod.val().length) {
            toastr.options.hideMethod = $hideMethod.val()
        }
        if (!msg) {
            msg = getMessage()
        }
        $("#toastrOptions").text("Command: toastr[" + shortCutFunction + ']("' + msg + (title ? '", "' + title : "") + '")\n\ntoastr.options = ' + JSON.stringify(toastr.options, null, 2));
        var $toast = toastr[shortCutFunction](msg, title);
        $toastlast = $toast;
        if ($toast.find("#okBtn").length) {
            $toast.delegate("#okBtn", "click", function () {
                alert("you clicked me. i was toast #" + toastIndex + ". goodbye!");
                $toast.remove()
            })
        }
        if ($toast.find("#surpriseBtn").length) {
            $toast.delegate("#surpriseBtn", "click", function () {
                alert("Surprise! you clicked me. i was toast #" + toastIndex + ". You could perform an action here.")
            })
        }
    });
    function getLastToast() {
        return $toastlast
    }

    $("#clearlasttoast").click(function () {
        toastr.clear(getLastToast())
    });
    $("#cleartoasts").click(function () {
        toastr.clear()
    })
});
