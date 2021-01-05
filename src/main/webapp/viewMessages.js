
(function(){
    console.log("hello world");
    let request = new XMLHttpRequest;
    request.open("GET", "/getUsers", true);
    request.onload = function(){
        console.log(this.status);
        let response = this.responseText;
        let another = JSON.parse(response);
        console.log(response);
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

let banUser = () => {
    let val = document.getElementById("ban").value;
    let xhr = new XMLHttpRequest;
    xhr.open("POST", "/banUser", true);
    xhr.send(val);
};

sendMessage = () => {
    document.getElementById("messageInput").value;
    let x = new XMLHttpRequest();
    x.open("POST", "/sendMessage");
    x.onload = () => {
        if(!this.status === 200){
            let error = document.createElement("a");
            error.innerHTML = "An error occured sending your message"
            error.className = "errorMessage";
            document.body.appendChild(error);
            setTimeout(function(){
                error.remove();
            }, 3000);
        }
    }
    x.send(document.getElementById("messageInput").value);
    console.log("sent");
};