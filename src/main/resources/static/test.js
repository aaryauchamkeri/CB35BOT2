let x = fetch("/resources/getImages", {
    method: "GET",
});

x.then(r => {
    console.log(r.ok);
    console.log(r.type);
    r.blob().then(file => {
        console.log(typeof file);
        console.log(file.type);
        console.log(file instanceof Blob);
    });
});
