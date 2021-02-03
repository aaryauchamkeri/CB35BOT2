
let scrolling = true;
let id = "";


(function(){
    let request = new XMLHttpRequest;
    request.open("GET", "/getUsers", true);
    request.setRequestHeader("", "");
    request.onload = function(){
        console.log(this.status);
        let response = this.responseText;
        let another = JSON.parse(response);
        another.forEach(function(element){
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

let changeScroll = () => {
    scrolling = !scrolling;
    document.getElementById("messageDisplay").contentWindow.postMessage('hello', '*');
    if(scrolling){
        document.getElementById("scrollChange").innerHTML = "Turn Off Auto Scroll";
    } else{
        document.getElementById("scrollChange").innerHTML = "Turn On Auto Scroll";
    }
}

let viewOlder = () => {
    window.location.replace("/liveMessages.html");
}

let sendUpdate = () => {
    let xhr = new XMLHttpRequest;
    xhr.open("GET", "/sendLiveUpdate");
    xhr.send();
}

let banUser = () => {
    let val = document.getElementById("ban").value;
    let xhr = new XMLHttpRequest;
    xhr.open("POST", "/banUser", true);
    xhr.send(val);
};

sendMessage = () => {
    let valueToSend = document.getElementById("messageInput").value;
    let x = new XMLHttpRequest();
    x.open("POST", "/sendMessage");
    x.onload = () => {
        if(!this.status === 200){
            let error = document.createElement("a");
            error.innerHTML = "An error occurred sending your message"
            error.className = "errorMessage";
            document.body.appendChild(error);
            setTimeout(function(){
                error.remove();
            }, 3000);
        }
    }
    x.setRequestHeader("server", id);
    x.send(valueToSend);
    document.getElementById("messageInput").value = "";
};

window.onmessage = function(e){
    id = e.data;
}