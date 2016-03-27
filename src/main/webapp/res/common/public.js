/**
 * Created by lemon on 16/2/8.
 */

var P = {
    version: 1.0,
    frame_left_click: function () {

    },
    modal: function (id) {
        var modal = document.getElementById(id);
        modal.style.display = 'block';
        document.getElementById('shade').style.display = 'block';
    },
    closeModal: function (id) {
        var modal = document.getElementById(id);
        modal.style.display = 'none';
        document.getElementById('shade').style.display = 'none';
    }
}