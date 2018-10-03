<?php
 
$dataPoints2 = array(
	array("x" => 13, "y" => 23, "indexLabel" => ""),
array("x" => 6, "y" => 9, "indexLabel" => ""),
array("x" => 7, "y" => 15, "indexLabel" => ""),
array("x" => 1, "y" => 13, "indexLabel" => ""),
array("x" => 23, "y" => 2, "indexLabel" => ""),
array("x" => 25, "y" => 9, "indexLabel" => ""),
array("x" => 2, "y" => 19, "indexLabel" => ""),
array("x" => 19, "y" => 2, "indexLabel" => "")

);

$dataPoints = array(
	array("x" => 7, "y" => 12, "indexLabel" => "Cidade 0", "color" => "green"),
array("x" => 1, "y" => 3, "indexLabel" => "Cidade 1", "color" => "blue"),
array("x" => 13, "y" => 3, "indexLabel" => "Cidade 2", "color" => "blue"),
array("x" => 15, "y" => 20, "indexLabel" => "Cidade 7", "color" => "blue"),
array("x" => 17, "y" => 24, "indexLabel" => "Cidade 4", "color" => "blue"),
array("x" => 22, "y" => 29, "indexLabel" => "Cidade 5", "color" => "blue"),
array("x" => 15, "y" => 29, "indexLabel" => "Cidade 6", "color" => "blue"),
array("x" => 3, "y" => 21, "indexLabel" => "Cidade 3", "color" => "yellow"),
array("x" => 7, "y" => 12, "indexLabel" => "Cidade 0", "color" => "green"),
);
 
?>
<!DOCTYPE HTML>
<html>
<head>
<script>
window.onload = function () {
 
var chart = new CanvasJS.Chart("chartContainer", {
	title: {
		text: "Caixeiro Viajante - Melhor Solução"
	},
	axisY: {
		title: ""
	},
	data: [{
		type: "line",
		lineColor:"red",
        lineThickness: 1,
		lineDashType: "dashDot",
		dataPoints: <?php echo json_encode($dataPoints, JSON_NUMERIC_CHECK); ?>
	}]
});
chart.render();
 
}
</script>
</head>
<body>
<div style="width: 100%; height: auto;">
	<div id="chartContainer" style="height: 500px; width: 80%; float: left;"></div>
	<div id="chartContainer2" style="height: 500px; width: 45%; float:left;"></div>
</div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>
</html>  