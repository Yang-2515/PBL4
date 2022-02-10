var uploadField = document.getElementById("file");

uploadField.onchange = function() {
    if(this.files[0].size > 1024*1024*100){
       alert("File is larger than 100MB! Please choose again!");
       this.value = "";
    };
};