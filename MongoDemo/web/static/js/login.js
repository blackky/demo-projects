function login(){
    $.ajax({
         url: "Login.do",
         data: {email:$('#email').val(),password:$('#password').val()},
         dataType: 'JSON',
         method: 'post',
         success: function (data, textStatus, jqXHR) {
            if(data.flag){
                window.location.href = "welcome.jsp";
            }else{
                alert(data.message);
            }
        },error: function (jqXHR, textStatus, errorThrown) {
            alert("Please try again after some time.");
        }
    });
}

$("#submit").click(login());

