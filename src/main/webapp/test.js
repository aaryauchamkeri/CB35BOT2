let x,y,z;
[x,y,z, ...rest] = [1,2,3,4,5,6,7];

rest.forEach((element, index) => {
    console.log(`${element} : ${index}`);
});