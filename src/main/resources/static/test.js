let parameters = "client_id=795488321974304791&client_secret=n5VBeXXISeX9s1RuUB6Bm91l1M40GfMV&code=xFJV5xtYSuqcFU7ylg8bPMbD5mbZpf&redirect_uri=http://localhost:5000/OAuthGrant&scope=identify email&grant_type=authorization_code";
let x = new XMLHttpRequest();
x.open("POST", "https://discord.com/api/oauth2/token" , true);
x.onload = function () {
    console.log(this.responseText);
}
x.send();
