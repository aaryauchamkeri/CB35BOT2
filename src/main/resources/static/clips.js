let loggedIn = false;

(function(){
    console.log("executed");
    let cookies = document.cookie;
    let asArray = cookies.split(';');
    asArray.forEach(function (v, i){
        let cookieValue = v.split('=');
        if(cookieValue[0].trim() === "sessionId"){
            let x = new XMLHttpRequest();
            x.open("GET", "/main/checkSession", true);
            x.onload = function(){
                if(this.status === 200){
                    loggedIn = true;
                } else {
                    // do nothing
                }
            }
            console.log(cookieValue[1].trim());
            x.setRequestHeader("sessionId", cookieValue[1].trim());
            x.send();
        }
    });
})();

document.getElementById("file-input-button").addEventListener("click", function(e){
    if(loggedIn) {
        let file = document.getElementById("clipInput");
        file.click();
    } else {
        window.location.replace("https://discord.com/api/oauth2/authorize?client_id=795488321974304791&redirect_uri=http%3A%2F%2Flocalhost%3A5000%2Fauthorization%2FdiscordOAuthCode&response_type=code&scope=identify%20email");
    }
});

document.getElementById("clipInput").addEventListener("change", function (e){
    if(loggedIn) {
        let file = document.getElementById("clipInput");
        if (file.files.length === 0) {
            // do nothing
            console.log("pressed");
        } else {
            document.getElementById("file-name").innerText = file.files[0].name;
        }
    } else {
        window.location.replace("https://discord.com/api/oauth2/authorize?client_id=795488321974304791&redirect_uri=http%3A%2F%2Flocalhost%3A5000%2Fauthorization%2FdiscordOAuthCode&response_type=code&scope=identify%20email");
    }
});