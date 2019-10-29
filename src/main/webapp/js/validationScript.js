/**
 * Validates user input for the Login Form
 * Not really being used. HTML5 build-in handles validation
 * on login because.... we can...
 * @author Mads
 * @returns {boolean} false if validation fails.
 */
function validateLogin() {
    var email = document.forms["loginForm"]["loginEmail"];
    var errorContainer = document.getElementById("errorMessage");

    /** Validates that email includes @ or . */
    if (!(email.value.includes("@")) || !(email.value.includes("."))) {
        email.classList.add("uk-form-danger");
        email.focus();
        errorContainer.innerHTML = "Please provide a valid email address with a '@' and at least one '.'";
        return false;
    }

    document.forms["loginForm"].submit();
}

/**
 * Validates the user input for the Create Account Form
 * @author Mads
 * @returns {boolean} false if validation fails
 */
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

    /** Validates that name is not empty */
    if(name.value === "") {
        name.classList.add("uk-form-danger");
        name.focus();
        errorContainer.innerHTML = "Please fill out the name field";
        return false;
    }

    /** Validates that name only contains letters and spaces */
    if(!name.value.match(/^([a-zA-Z]+\s)*[a-zA-Z]+$/)) {
        name.classList.add("uk-form-danger");
        name.focus();
        errorContainer.innerHTML = "Name can only contain letters and spaces";
        return false;
    }

    /** Validates that email is not empty */
    if(email.value === "") {
        email.classList.add("uk-form-danger");
        email.focus();
        errorContainer.innerHTML = "Please fill out the email field";
        return false;
    }

    /** Validates that email includes @ or . */
    if (!(email.value.includes("@")) || !(email.value.includes("."))) {
        email.classList.add("uk-form-danger");
        email.focus();
        errorContainer.innerHTML = "Please provide a valid email address with a '@' and at least one '.'";
        return false;
    }

    /** Validates that password is not empty */
    if(pass.value === "") {
        pass.classList.add("uk-form-danger");
        pass.focus();
        errorContainer.innerHTML = "Please fill out the password field";
        return false;
    }

    /** Validates that password is at least 8 characters long */
    if (pass.value.length < 8) {
        pass.classList.add("uk-form-danger");
        pass.focus();
        errorContainer.innerHTML = "Password has to be at least 8 characters long";
        return false;
    }

    /* Validates that confirm password is not empty */
    if(confirmPass.value === "") {
        confirmPass.classList.add("uk-form-danger");
        confirmPass.focus();
        errorContainer.innerHTML = "Please confirm the password";
        return false;
    }

    /** Validates that password and confirm password matches */
    if (pass.value !== confirmPass.value) {
        pass.classList.add("uk-form-danger");
        pass.focus();
        confirmPass.classList.add("uk-form-danger");
        errorContainer.innerHTML = "Passwords do not not match";
        return false;
    }

    document.forms["createAccountForm"].submit();
}

function validateAccountEdit() {
    var name = document.forms["editAccountForm"]["name"];
    var email = document.forms["editAccountForm"]["email"];
    var oldPass = document.forms["editAccountForm"]["oldPassword"];
    var newPass = document.forms["editAccountForm"]["newPassword"];
    var confirmPass = document.forms["editAccountForm"]["confirmPassword"];
    var errorContainer = document.getElementById("errorMessage");

    /** Resets the classes on the fields */
    name.classList.remove("uk-form-danger");
    email.classList.remove("uk-form-danger");
    oldPass.classList.remove("uk-form-danger");
    newPass.classList.remove("uk-form-danger");
    confirmPass.classList.remove("uk-form-danger");

    /** Validates that name is not empty */
    if(name.value === "") {
        name.classList.add("uk-form-danger");
        name.focus();
        errorContainer.innerHTML = "Please fill out the name field";
        return false;
    }

    /** Validates that name only contains letters and spaces */
    if(!name.value.match(/^[a-zA-Z]+$/)) {
        name.classList.add("uk-form-danger");
        name.focus();
        errorContainer.innerHTML = "Name can only contain letters and spaces";
        return false;
    }

    /** Validates that email is not empty */
    if(email.value === "") {
        email.classList.add("uk-form-danger");
        email.focus();
        errorContainer.innerHTML = "Please fill out the email field";
        return false;
    }

    /** Validates that email includes @ or . */
    if (!(email.value.includes("@")) || !(email.value.includes("."))) {
        email.classList.add("uk-form-danger");
        email.focus();
        errorContainer.innerHTML = "Please provide a valid email address with a '@' and at least one '.'";
        return false;
    }

    /** Validates that old password is not empty */
    if(oldPass.value === "") {
        oldPass.classList.add("uk-form-danger");
        oldPass.focus();
        errorContainer.innerHTML = "Please enter your old password";
        return false;
    }

    /** Validates that the old password is at least 8 characters long */
    if (oldPass.value.length < 8) {
        oldPass.classList.add("uk-form-danger");
        oldPass.focus();
        errorContainer.innerHTML = "Password has to be at least 8 characters long";
        return false;
    }

    /* Validates that new password is not empty */
    if (newPass.value === "") {
        newPass.classList.add("uk-form-danger");
        newPass.focus();
        errorContainer.innerHTML = "Please pick a new password";
        return false;
    }

    /** Validates that the new password is at least 8 characters long */
    if (newPass.value.length < 8) {
        newPass.classList.add("uk-form-danger");
        newPass.focus();
        errorContainer.innerHTML = "New Password has to be at least 8 characters long";
        return false;
    }

    /* Validates that confirm password is not empty */
    if (confirmPass.value === "") {
        confirmPass.classList.add("uk-form-danger");
        confirmPass.focus();
        errorContainer.innerHTML = "Please confirm the new password";
        return false;
    }

    /** Validates that password and confirm password matches */
    if (newPass !== confirmPass.value) {
        newPass.classList.add("uk-form-danger");
        confirmPass.classList.add("uk-form-danger");
        newPass.focus();
        errorContainer.innerHTML = "Passwords does not match";
        return false;
    }

    document.forms['createAccountForm'].submit();
}
