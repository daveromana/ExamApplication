
<?php
	$user = "sarjak29";
	$password = "86909996088";
	$db = "sarjak29_marks2";

	$connect = mysql_connect("64.62.211.131",$user,$password) or die("Please, Check Your Connection...:::");
	
	
	//	echo "hurray";
		mysql_select_db($db,$connect) or die("No database");
	$uid = $_POST['studid'];
	$upas = $_POST['password'];
	
	$sql = "SELECT * from student where stuname='$uid' and stupassword='$upas'";
	$res = mysql_query($sql);

	$count=mysql_num_rows($res);
	if($count==1)
	{
		die("Loged in Successfully.... Hurray...");
	}
	else
	{
		die("Username or Password is Invalid....");
	}


	echo "<table border=\"1\">\n";
	echo "<th>StudentID</th><th>Student Name</th>\n";
	while ($row = mysql_fetch_array($res))
	{
		extract($row);
		echo "<tr>";
		echo "<td>";
		echo "$stuid";
		echo "</td>";
		echo "<td>";
		echo "$stuname";
		echo "</td>";
		echo "</tr>";
		echo "<tr>\n";
	}
	echo "</table>\n";

	
?>