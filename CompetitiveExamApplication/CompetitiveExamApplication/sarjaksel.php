

<?php
  mysql_connect("64.62.211.131","sarjak29","86909996088");
  mysql_select_db("sarjak29_marks2");
  $sql=mysql_query("select * from student");
  while($row=mysql_fetch_assoc($sql)) $output[]=$row;
  print(json_encode($output));
  mysql_close();
?>