

let messages = [];
let temp = 0;
let channelId = "";


let request = new XMLHttpRequest;
request.open("GET", "/textChannels", true);
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
    x.open("GET", "/getMessages", true);
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

    }
    x.setRequestHeader("channelId", channelId);
    x.send();
}

setChannel = () => {
    channelId = document.getElementById("channels").value;
    document.getElementById("channels").style.visibility = "collapse";
    document.getElementById("sendChannel").style.visibility = "collapse";
    setInterval(constantlyUpdate, 5000);
    if ( window.location !== window.parent.location ){
        setInterval(() => {
            document.getElementById('bottom').scrollIntoView();
        }, 50);    
    }   
}






