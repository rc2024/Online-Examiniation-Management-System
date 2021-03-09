function validPassword()
{
    var pass = document.getElementById("npwd").value;
    var p=new RegExp("[A-Za-z0-9]{8,12}");
    if (pass==null||pass=="")
    {
        document.getElementById("snpwd").style="color:Red";
        document.getElementById("snpwd").innerHTML="Password cannot be empty";
    }
    else if(p.test(pass))
    {
        document.getElementById("snpwd").style="color:green";
        document.getElementById("snpwd").innerHTML="Valid Password";
    }
    
    else
    {
        document.getElementById("snpwd").style="color:Red";
        document.getElementById("snpwd").innerHTML="Invalid Password";
    }
}

function validConfirmPassword()
{
    var pass= document.getElementById("npwd").value;
    var pass123 = document.getElementById("cnfnpwd").value;
    var p=new RegExp("[A-Za-z0-9]{8,12}");
    if (pass==null||pass=="")
    {
        document.getElementById("scnfnpwd").style="color:Red";
        document.getElementById("scnfnpwd").innerHTML="Password cannot be empty";
    }
    else if(!p.test(pass))
    {
        document.getElementById("scnfnpwd").style="color:Red";
        document.getElementById("scnfnpwd").innerHTML="Invalid Password";
    }
    else if(pass==pass123)
    {
        document.getElementById("scnfnpwd").style="color:green";
        document.getElementById("scnfnpwd").innerHTML="Password is same";
    }
    else
    {
        document.getElementById("scnfnpwd").style="color:Red";
        document.getElementById("scnfnpwd").innerHTML="Password is different";
    }
}