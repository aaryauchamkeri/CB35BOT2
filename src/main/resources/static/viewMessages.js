
let scrolling = true;
let id = "";
let userInfo;
let sessionId;
let authorized = false;
let loggedIn = false;

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
                    if(userInfo.id === "530168928123617300" || userInfo.id === "557706668486557736"){
                        authorized = true;
                        document.getElementById("sign-in-button").innerText = "Sign Out " + userInfo.username;
                        let viewMessagesLink = document.createElement("li");
                        viewMessagesLink.className = "nav-item active";
                        let link = document.createElement("a");
                        link.className = "nav-link";
                        link.href = "viewMessages.html";
                        link.innerText = "Mod Page";
                        viewMessagesLink.appendChild(link);
                        document.getElementById("navbar").appendChild(viewMessagesLink);
                    } else{
                        window.location.replace("/unauthorized.html");
                    }
                });
            } else {
                // do nothing
            }
        });
    }
});

(function getUsers() {
    let request = new XMLHttpRequest;
    request.open("GET", "/main/getUsers", true);
    request.setRequestHeader("sessionId", sessionId);
    request.onload = function () {
        console.log(this.status);
        let response = this.responseText;
        let another = JSON.parse(response);
        another.forEach(function (element) {
            let op = document.createElement("option")
            op.value = element.id;
            op.innerHTML = element.name;
            document.getElementById("ban").appendChild(op);
            let row = document.createElement("tr");
            let td1 = document.createElement("td");
            let td2 = document.createElement("td");
            td1.appendChild(document.createTextNode(element.id));
            td2.appendChild(document.createTextNode(element.name));
            row.appendChild(td1);
            row.appendChild(td2);
            document.getElementById("users").appendChild(row);
            row.id = element.id;
        });

    }
    request.send();
})();

document.getElementById("sign-in-button").addEventListener("click", function (e){
    if(loggedIn){
        document.cookie = "sessionId=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
        window.location.reload();
    } else{
        window.location.replace("https://discord.com/api/oauth2/authorize?client_id=795488321974304791&redirect_uri=http%3A%2F%2Fcb35botfinal-env.eba-urda3nhc.us-west-1.elasticbeanstalk.com%2Fauthorization%2FdiscordOAuthCode&response_type=code&scope=identify%20email");
    }
});

let changeScroll = () => {
    scrolling = !scrolling;
    document.getElementById("messageDisplay").contentWindow.postMessage('hello', '*');
    if (scrolling) {
        document.getElementById("scrollChange").innerHTML = "Turn Off Auto Scroll";
    } else {
        document.getElementById("scrollChange").innerHTML = "Turn On Auto Scroll";
    }
}

let viewOlder = () => {
    window.location.replace("/main/liveMessages.html");
}

let sendUpdate = () => {
    let xhr = new XMLHttpRequest;
    xhr.open("GET", "/main/sendLiveUpdate");
    xhr.setRequestHeader("sessionId", sessionId);
    xhr.send();
}

let banUser = () => {
    let val = document.getElementById("ban").value;
    let xhr = new XMLHttpRequest;
    xhr.open("POST", "/main/banUser", true);
    xhr.setRequestHeader("sessionId", sessionId);
    xhr.send(val);
};

let sendMessage = () => {
    let valueToSend = document.getElementById("messageInput").value;
    let x = new XMLHttpRequest();
    x.open("POST", "/main/sendMessage");
    x.setRequestHeader("sessionId", sessionId);
    x.onload = () => {
        if (!(this.status === 200)) {
            let error = document.createElement("a");
            error.innerHTML = "\nAn error occurred sending your message"
            error.className = "errorMessage";
            document.body.appendChild(error);
            setTimeout(function () {
                error.remove();
            }, 3000);
        }
    }
    x.setRequestHeader("server", id);
    x.send(valueToSend);
    document.getElementById("messageInput").value = "";
};

window.onmessage = function (e) {
    id = e.data;
}
