function validateLogin() {
    var email = document.forms["loginForm"]["email"].value;

    if (!(email.includes("@")) || !(email.includes("."))) {
        alert("Please provide a valid email address")
        email.focus();
        return false;
    }
}