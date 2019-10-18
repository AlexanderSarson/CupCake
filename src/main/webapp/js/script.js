function validateLogin() {
    var email = document.forms["loginForm"]["email"];
    var errorContainer = document.getElementById("errorMessage");

    // Validates that email includes @ or .
    if (!(email.value.includes("@")) || !(email.value.includes("."))) {
        email.classList.add("uk-form-danger");
        errorContainer.innerHTML = "Please provide a valid email address with a '@' and at least one '.'";
        return false;
    }

    // Submits form
    document.forms['loginForm'].submit();
}

function validateAccountCreation() {
    var name = document.forms["createAccountForm"]["name"];
    var email = document.forms["createAccountForm"]["email"];
    var pass = document.forms["createAccountForm"]["password"];
    var confirmPass = document.forms["createAccountForm"]["confirmPassword"];
    var errorContainer = document.getElementById("errorMessage");

    name.classList.remove("uk-form-danger");
    email.classList.remove("uk-form-danger");
    pass.classList.remove("uk-form-danger");
    confirmPass.classList.remove("uk-form-danger");

    // Validates that name is not empty
    if(name.value === "") {
        name.classList.add("uk-form-danger");
        errorContainer.innerHTML = "Please fill out the name field";
        return false;
    }

    // Validates that name only contains letters and spaces
    if(!name.value.match(/^[a-zA-Z]+$/)) {
        name.classList.add("uk-form-danger");
        errorContainer.innerHTML = "Name can only contain letters and spaces";
        return false;
    }

    // Validates that email is not empty
    if(email.value === "") {
        email.classList.add("uk-form-danger");
        errorContainer.innerHTML = "Please fill out the email field";
        return false;
    }

    // Validates that email includes @ or .
    if (!(email.value.includes("@")) || !(email.value.includes("."))) {
        email.classList.add("uk-form-danger");
        errorContainer.innerHTML = "Please provide a valid email address with a '@' and at least one '.'";
        return false;
    }

    // Validates that password is not empty
    if(pass.value === "") {
        pass.classList.add("uk-form-danger");
        errorContainer.innerHTML = "Please fill out the password field";
        return false;
    }

    // Validates that password is at least 8 characters long
    if (pass.value.length < 8) {
        pass.classList.add("uk-form-danger");
        confirmPass.classList.add("uk-form-danger");
        errorContainer.innerHTML = "Password has to be at least 8 characters long";
        return false;
    }

    // Validates that confirm password is not empty
    if(confirmPass.value === "") {
        confirmPass.classList.add("uk-form-danger");
        errorContainer.innerHTML = "Please repeat the password";
        return false;
    }

    // Validates that password and confirm password matches
    if (pass.value !== confirmPass.value) {
        pass.classList.add("uk-form-danger");
        confirmPass.classList.add("uk-form-danger");
        errorContainer.innerHTML = "Passwords does not match";
        return false;
    }

    // Submits form
    document.forms['createAccountForm'].submit();
}
