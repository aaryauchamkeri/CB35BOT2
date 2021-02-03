// let setServer = () => {
//     let server = document.getElementById("server").value;
//     console.log(server);
//     let req = new XMLHttpRequest;
//     req.open("POST", "/authorize", true);
//     console.log(typeof server);
//     req.onload = function() {
//         if(this.status !== 200){
//             console.log("error occured");
//         } else if(this.responseText !== "1"){
//             let x = document.createElement("h3");
//             x.innerHTML = "Server not found or choccy bot isn't and admin of this server";
//             console.log(this.responseText);
//             x.style.color = "red";
//             document.getElementById("main").appendChild(x);
//             setTimeout(() => {
//                 x.remove();
//             }, 2000);
//         } else {
//             window.location.replace("/viewMessages.html");
//         }
//     }
//     req.send(server);
// }

