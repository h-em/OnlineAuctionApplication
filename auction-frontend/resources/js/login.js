$(document).ready(function () {
/*
  var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
  var theUrl = "http://localhost:8080/api/register";
  xmlhttp.open("get", theUrl);
  xmlhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
  xmlhttp.send(JSON.stringify({ "email": "inosdf@yaho.com",
   "password":"$10$Xr7TdOAyrUPSaHJliuwFHeq0q/.dXtBGxUPpeUsX/pNkzHUe4893m"}));
*/

  // $.get("http://localhost:8080/api/test", JSON.stringify({ email: "b@g.c" }),
  //     function (data, status) {
  //       alert("Data: " + data + "\nStatus: " + status);
  //     });

  $("form#contactForm .error").remove();

  $("form#contactForm button").click(function(){

    const email = $("form#contactForm input#email").val();
    const password = $("form#contactForm input#password").val();
    const obj = JSON.stringify({"email": email,"password":password});

    $.ajax({
      url: 'http://localhost:8080/api/register',
      dataType: 'json',
      type: 'post',
      contentType: 'application/json',
      data: obj,
      //     processData: false,
      success: function (data, textStatus, jQxhr) {

        console.log('success: ' + JSON.stringify(data));

      },
      error: function (jqXhr, textStatus, errorThrown) {
        const errorsArray = jqXhr.responseJSON.errors;
        if (errorsArray != undefined) {
          for (var index = 0; index < errorsArray.length; index++) {

            const element = $("#" + errorsArray[index].field);

            if (!$(element).next().hasClass("error")) {
              const errorLine = errorLineStart + errorsArray[index].defaultMessage + errorLineEnd;
              $(errorLine).insertAfter(element);

            }
          }
        } else {
          const errorMessage = jqXhr.responseJSON.message;
          console.log("singura eroare: " + errorMessage);
        }

      }
    });
  });

});

