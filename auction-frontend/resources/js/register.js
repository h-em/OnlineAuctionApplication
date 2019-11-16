const errorLineStart = "<p class='error'>";
const errorLineEnd = "</p>";
const successLineStart = "<p class='success'>";
const successLineEnd = "</p>";

$(document).ready(function(){
  
  register();
  /*
  $("#firstName").focusout(function(){
    validateUserName();
  });
*/

});

function register(){
  $("form#register_form .error").remove();

  $("form#register_form button").click(function(){
    
    const firstName = $("form#register_form input#firstName").val();
    const lastName = $("form#register_form input#lastName").val();
    const email = $("form#register_form input#email").val();
    const password = $("form#register_form input#password").val();
    const confirmPassword = $("form#register_form input#confirmPassword").val();

    const userDto = JSON.stringify({"firstName":firstName,"lastName":lastName,
                                    "email": email,"password":password,
                                    "confirmPassword":confirmPassword });

    console.log(userDto);

    $.ajax({
      url: 'http://localhost:8080/api/register',
      dataType: 'json',
      type: 'post',
      contentType: 'application/json',
      data: userDto,
      //     processData: false,
      success: function (data, textStatus, jQxhr) {

        console.log('success: ' + JSON.stringify(data));
        const successLine = successLineStart +"You have been successfully registered!" +successLineEnd;
        const element = $("form#register_form .registerSuccessful");
        $(element).append(successLine);
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
}




function validateUserName(){
  var firstName = $(this).val();
    if(firstName.length < 6){
      if($(element).next().hasClass()){
        const errorLine = errorLineStart +" First name is not valid!" + errorLineEnd;
        $(errorLine).insertAfter(element);
    }
  } 
};
