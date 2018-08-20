

let messages = [];
let temp = 0;
let channelId = "";
let scroll = true;
let setImage = false;
let userInfo;
let sessionId;

(function(){
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
                        // document.getElementById("sign-in-button").innerText = "Sign Out " + userInfo.username;
                    });
                } else {
                    // do nothing
                }
            });
        }
    });
})();

let request = new XMLHttpRequest;
request.open("GET", "/main/textChannels", true);
request.setRequestHeader("sessionId", sessionId)
request.onload = function(){
    console.log(this.responseText);
    let results = JSON.parse(this.responseText);
    results.forEach(element => {
        let op = document.createElement("option");
        op.value = element.id;
        op.innerHTML = element.name;
        document.getElementById("channels").appendChild(op);
    });
}
request.send();



constantlyUpdate = () => {
    let response;
    let x = new XMLHttpRequest();
    x.open("GET", "/main/getMessages", true);
    x.setRequestHeader("sessionId", sessionId);
    x.onload = function(){
        // console.log(this.status);
        response = this.responseText;
        // console.log(response);
    
        let arr = JSON.parse(response);
        if(arr.length > messages.length){
            let temp = messages.length;

            for(let i = messages.length; i < arr.length; i++){
                messages.push(arr[i]);
            }

            for(let i = temp; i < messages.length; i++){
                let created = document.createElement("li");
                let obj = messages[i];
                let content = document.createTextNode(` ${obj.author} : ${obj.content}`);
                let image = document.createElement("img");
                image.src = obj.url;
                image.style.width="3%";
                image.style.height="3%";
                created.appendChild(image);
                created.appendChild(content);
                created.style.fontSize = "16 px";
                created.style.color = "white";
                document.getElementById("messages").appendChild(created);
            }
        }

        if(!setImage){
            // document.body.style.backgroundColor = "linear-gradient(to bottom, rgb(96, 96, 96), rgb(174, 55, 160))";
            document.body.style.backgroundColor = "rgb(174, 55, 160)";
            setImage = true;
        }

    }
    x.setRequestHeader("channelId", channelId);
    x.send();
}

window.onmessage = (e) => {
    if (e.data === 'hello') {
        scroll = !scroll;
    }
}

setChannel = () => {
    channelId = document.getElementById("channels").value;
    console.log(channelId);
    window.top.postMessage(channelId, '*')
    document.getElementById("channels").style.visibility = "collapse";
    document.getElementById("sendChannel").style.visibility = "collapse";
    setInterval(constantlyUpdate, 5000);
    if ( window.location !== window.parent.location ){
        setInterval(() => {
            if(scroll){
                document.getElementById('bottom').scrollIntoView();
            }
        }, 50);    
    }
}






