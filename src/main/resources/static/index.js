let loggedIn = false;
let userInfo;
let sessionId;
let loadedMessage = [];
window.onload = function(){
    console.log("executed");
    let cookies = document.cookie;
    let asArray = cookies.split(';');
    asArray.forEach(function (v, i){
        let cookieValue = v.split('=');
        if(cookieValue[0].trim() === "sessionId"){
            sessionId = cookieValue[1].trim();
            let x = fetch("/main/getUserInfo", {
                method : "GET",
                headers : {
                    "sessionId" : `${cookieValue[1].trim()}`
                },
            });
            x.then(r =>{
                if(r.status === 200){
                    loggedIn = true;
                    r.json().then(json =>{
                        userInfo = json;
                        document.getElementById("sign-in-button").innerText = "Sign Out " + userInfo.username;
                        if(userInfo.id === "530168928123617300" || userInfo.id === "557706668486557736"){
                            let viewMessagesLink = document.createElement("li");
                            viewMessagesLink.className = "nav-item active";
                            let link = document.createElement("a");
                            link.className = "nav-link";
                            link.href = "viewMessages.html";
                            link.innerText = "Mod Page";
                            viewMessagesLink.appendChild(link);
                            document.getElementById("navbar").appendChild(viewMessagesLink);
                        }
                    });
                    loadImages();
                } else {
                    // do nothing
                }
            });
        }
    });
}

let loadImages = function(){
    let x = new XMLHttpRequest();
    x.open("GET", "/resources/images", true);
    x.onload = function(){
        let json =  JSON.parse(this.responseText);
        console.log(json.length);
        console.log(loadedMessage.length);
        if(json.length > loadedMessage.length){
            let length = loadedMessage.length;
            for(let i = length; i < json.length; i++) {
                console.log(loadedMessage);
                console.log(json);
                let index = json[i];
                console.log(index.url);
                let card = document.createElement("div");
                let cardHeader = document.createElement("div");
                let body = document.createElement("div");
                cardHeader.innerText = index.author + " : " + index.content;
                let imageCreated = document.createElement("img");
                imageCreated.src = index.url;
                card.className = "card";
                cardHeader.className = "card-header";
                body.className = "card-body";
                body.appendChild(imageCreated);
                // imageCreated.className = "card-img-top";
                card.appendChild(cardHeader);
                card.appendChild(body);
                document.getElementById("images").prepend(card);
                loadedMessage.push(body);
            }
        }
    }
    x.setRequestHeader("sessionId", sessionId);
    x.send();
}

setInterval(loadImages, 3000);

document.getElementById("upload-button").addEventListener("click", function(e){
    let data = new FormData();
    let file = document.getElementById("image-input").files[0];
    data.append("file", file);
    data.append("id", userInfo.id);
    data.append("description", document.getElementById("image-description").value);
    let response = fetch("/resources/uploadFile", {
        method : "POST",
        headers: {
          sessionId : sessionId
        },
        body : data
    });
    response.then(response => {
       console.log(response.status);
        document.getElementById("file-name").innerText = "";
    });
});

document.getElementById("sign-in-button").addEventListener("click", function (e){
    if(loggedIn){
        document.cookie = "sessionId=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
        window.location.reload();
    } else{
        window.location.replace("https://discord.com/api/oauth2/authorize?client_id=795488321974304791&redirect_uri=http%3A%2F%2Fcb35botfinal-env.eba-urda3nhc.us-west-1.elasticbeanstalk.com%2Fauthorization%2FdiscordOAuthCode&response_type=code&scope=identify%20email");
    }
});

document.getElementById("file-input-button").addEventListener("click", function(e){
    if(loggedIn) {
        let file = document.getElementById("image-input");
        file.click();
    } else {
        window.location.replace("https://discord.com/api/oauth2/authorize?client_id=795488321974304791&redirect_uri=http%3A%2F%2Fcb35botfinal-env.eba-urda3nhc.us-west-1.elasticbeanstalk.com%2Fauthorization%2FdiscordOAuthCode&response_type=code&scope=identify%20email");
    }
});

document.getElementById("image-input").addEventListener("change", function (e){
    if(loggedIn) {
        let file = document.getElementById("image-input");
        if (file.files.length === 0) {
            // do nothing
        } else {
            document.getElementById("file-name").innerText = file.files[0].name;
        }
    } else {
        window.location.replace("https://discord.com/api/oauth2/authorize?client_id=795488321974304791&redirect_uri=http%3A%2F%2Fcb35botfinal-env.eba-urda3nhc.us-west-1.elasticbeanstalk.com%2Fauthorization%2FdiscordOAuthCode&response_type=code&scope=identify%20email");
    }
});