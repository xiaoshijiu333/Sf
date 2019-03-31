function test(Names){

	
	if(Names=="C")
	{
		document.getElementById("C_module").style.display='';
		document.getElementById("Java_module").style.display='none';
		document.getElementById("Python_module").style.display='none';
	}
	else if(Names=="Java")
	{
		document.getElementById("C_module").style.display='none';
		document.getElementById("Java_module").style.display='';
		document.getElementById("Python_module").style.display='none';
	}
	else if(Names=="Python")
	{
		document.getElementById("C_module").style.display='none';
		document.getElementById("Java_module").style.display='none';
		document.getElementById("Python_module").style.display='';
	}
}
