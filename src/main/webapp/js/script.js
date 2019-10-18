function validateLogin() {
    var email = document.forms["loginForm"]["email"].value;
    var password = document.forms["loginForm"]["password"].value;
    if (email === "") {
        alert("Please enter an email");
        email.focus();
        return false;
    }
    if (password === "") {
        alert("Please provide a password");
        password.focus();
        return false;
    }
    if (!(email.includes("@")) || !(email.includes("."))) {
        alert("Please provide a valid email address")
        email.focus();
        return false;
    }
}