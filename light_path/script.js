var waypoints = [];
var points = null;
var config = [];
var path = null;
var ctx;
var width = 1656; //pixels
var height = 823; //pixels
var fieldWidth = 652; // in inches
var fieldHeight = 324; // in inches
var robotWidth = 28; //inches
var robotHeight = 33; //inches
var pointRadius = 5;
var turnRadius = 30;
var kEpsilon = 1E-9;
var image;
var imageFlipped;
var wto;

var maxSpeed = 120;
var maxSpeedColor = [0, 255, 0];
var minSpeed = 0;
var minSpeedColor = [255, 0, 0];
var pathFillColor = "rgba(150, 150, 150, 0.5)";

class Translation2d {
	constructor(x, y) {
		this.x = x;
		this.y = y;
	}

	norm() {
		return Math.sqrt(Translation2d.dot(this, this));
	}

	scale(s) {
		return new Translation2d(this.x * s, this.y * s);
	}

	translate(t) {
		return new Translation2d(this.x + t.x, this.y + t.y);
	}

	invert() {
		return new Translation2d(-this.x, -this.y);
	}

	perp() {
		return new Translation2d(-this.y, this.x);
	}

	draw(color) {
		color = color || "#f72c1c";
		ctx.beginPath();
		ctx.arc(this.drawX, this.drawY, pointRadius, 0, 2 * Math.PI, false);
		ctx.fillStyle = color;
		ctx.strokeStyle = color;
		ctx.fill();
		ctx.lineWidth = 0;
		ctx.stroke();
	}

  point() {
    ctx.fillRect(this.drawX, this.drawY, 2, 2);
  }

	get drawX() {
		return this.x*(width/fieldWidth);
	}

	get drawY() {
		return height - this.y*(height/fieldHeight);
	}

	get angle() {
		return Math.atan2(-this.y, this.x);
	}

	static diff(a, b) {
		return new Translation2d(b.x - a.x, b.y - a.y);
	}

	static cross(a, b) {
		return a.x * b.y - a.y * b.x;
	}

	static dot(a, b) {
		return a.x * b.x + a.y * b.y;
	}

	static angle(a, b) {
		return Math.acos(Translation2d.dot(a,b) / (a.norm() * b.norm()));
	}
}

class Waypoint {
	constructor(position, theta, comment) {
		this.position = position;
		this.theta = theta;
		this.comment = comment;
	}

	draw() {
		this.position.draw("rgba(120,120,120,0.8)");
	}

	toString() {
		var comment = (this.comment.length > 0) ? " //" + this.comment : "";
		return "sWaypoints.add(new WaypointSequence.Waypoint("+this.position.x+","+this.position.y+",Math.toRadians("+this.theta+")));" + comment;
	}
}

class Line {
	constructor(pointA, pointB) {
		this.pointA = pointA;
		this.pointB = pointB;
		this.slope = Translation2d.diff(pointA.position, pointB.position);
		this.start = pointA.position.translate( this.slope.scale( pointA.radius/this.slope.norm() ) );
		this.end = pointB.position.translate( this.slope.scale( pointB.radius/this.slope.norm() ).invert() );
	}

	draw() {
		ctx.beginPath();
        ctx.moveTo(this.start.drawX, this.start.drawY);
        ctx.lineTo(this.end.drawX, this.end.drawY);
        
		try {
        	var grad = ctx.createLinearGradient(this.start.drawX, this.start.drawY, this.end.drawX, this.end.drawY);
	grad.addColorStop(0, getColorForSpeed(this.pointB.speed));
		grad.addColorStop(1, getColorForSpeed(getNextSpeed(this.pointB)));
		ctx.strokeStyle = grad;
		} catch (e) {
			ctx.strokeStyle = "#00ff00"
		}

        ctx.lineWidth = pointRadius * 2;
        ctx.stroke();
        this.pointA.draw();
        this.pointB.draw();
	}

	fill() {
		var start = this.start;
		var deltaEnd = Translation2d.diff(this.start,this.end);
		var angle = deltaEnd.angle;
		var length = deltaEnd.norm();
		for(var i=0; i<length; i++) {
		drawRotatedRect(start.translate(deltaEnd.scale(i/length)), robotHeight, robotWidth, angle, null, pathFillColor, true);
		}
	}

	translation() {
		return new Translation2d(this.pointB.position.y - this.pointA.position.y, this.pointB.position.x - this.pointA.position.x)
	}

	slope() {
		if(this.pointB.position.x - this.pointA.position.x > kEpsilon)
			return (this.pointB.position.y - this.pointA.position.y) / (this.pointB.position.x - this.pointA.position.x);
		else
			return (this.pointB.position.y - this.pointA.position.y) / kEpsilon;
	}

	b() {
		return this.pointA.y - this.slope() * this.pointA.x;
	}

	static intersect(a, b, c, d) {
		var i = ((a.x-b.x)*(c.y-d.y) - (a.y-b.y)*(c.x-d.x));
		i = (Math.abs(i) < kEpsilon) ? kEpsilon : i;
		var x = (Translation2d.cross(a, b) * (c.x - d.x) - Translation2d.cross(c, d)*(a.x - b.x)) / i;
		var y = (Translation2d.cross(a, b) * (c.y - d.y) - Translation2d.cross(c, d)*(a.y - b.y)) / i;
		return new Translation2d(x, y);
	}

	static pointSlope(p, s) {
		return new Line(p, p.translate(s));
	}
}

class Arc {
	constructor(lineA, lineB) {
		this.lineA = lineA;
		this.lineB = lineB;
		this.center = Line.intersect(lineA.end, lineA.end.translate(lineA.slope.perp()), lineB.start, lineB.start.translate(lineB.slope.perp()));
		this.center.draw;
		this.radius = Translation2d.diff(lineA.end, this.center).norm();
	}

	draw() {
		var sTrans = Translation2d.diff(this.center, this.lineA.end);
		var eTrans = Translation2d.diff(this.center, this.lineB.start);
		console.log(sTrans);
		console.log(eTrans);
		var sAngle, eAngle;
		if(Translation2d.cross(sTrans, eTrans) > 0) {
			eAngle = -Math.atan2(sTrans.y, sTrans.x);
			sAngle = -Math.atan2(eTrans.y, eTrans.x);
		} else {
			sAngle = -Math.atan2(sTrans.y, sTrans.x);
			eAngle = -Math.atan2(eTrans.y, eTrans.x);
		}
		this.lineA.draw();
		this.lineB.draw();
		ctx.beginPath();
		ctx.arc(this.center.drawX,this.center.drawY,this.radius*(width/fieldWidth),sAngle,eAngle);
		ctx.strokeStyle=getColorForSpeed(this.lineB.pointB.speed);
		ctx.stroke();
	}

	fill() {
		this.lineA.fill();
		this.lineB.fill();
		var sTrans = Translation2d.diff(this.center, this.lineA.end);
		var eTrans = Translation2d.diff(this.center, this.lineB.start);
		var sAngle = (Translation2d.cross(sTrans, eTrans) > 0) ? sTrans.angle : eTrans.angle;
		var angle = Translation2d.angle(sTrans, eTrans);
		var length = angle * this.radius;
		for(var i=0; i<length; i+=this.radius/100) {
		drawRotatedRect(this.center.translate(new Translation2d(this.radius*Math.cos(sAngle-i/length*angle),-this.radius*Math.sin(sAngle-i/length*angle))), robotHeight, robotWidth, sAngle-i/length*angle+Math.PI/2, null, pathFillColor, true);
		}

		

	}

	static fromPoints(a, b, c) {
		return new Arc( new Line(a, b), new Line(b, c));
	}
}


function init() { 
	$("#field").css("width", (width / 1.5) + "px");
	$("#field").css("height", (height / 1.5) + "px");
	ctx = document.getElementById('field').getContext('2d');
    ctx.canvas.width = width;
    ctx.canvas.height = height;
    ctx.clearRect(0, 0, width, height);
    ctx.fillStyle="#FF0000";
    image = new Image();
    image.src = 'field.png';
    image.onload = function(){
        ctx.drawImage(image, 0, 0, width, height);
        update();
    };
    imageFlipped = new Image();
    imageFlipped.src = 'fieldflipped.png';
    $('input').bind("change paste keyup", function() {
		console.log("change");
		clearTimeout(wto);
			wto = setTimeout(function() {
			update();
		}, 500);
	});

  $('#field').click(function(e) {
    var imgHeight = $(this).height();
    var imgWidth = $(this).width();
    var offset = $(this).offset();

    var x = e.pageX - offset.left;
    var y = imgHeight - (e.pageY - offset.top);
    x = x / imgWidth;
    y = y / imgHeight;
    x = Math.floor(x * fieldWidth);
    y = Math.floor(y * fieldHeight);

    $($('table tbody#points tr:last td input')[0]).prop("value",x);
    $($('table tbody#points tr:last td input')[1]).prop("value",y);
    update();
  });
}

function clear() {
    ctx.clearRect(0, 0, width, height);
    ctx.fillStyle="#FF0000";
    if(flipped)
    	ctx.drawImage(imageFlipped, 0, 0, width, height);
    else
    	ctx.drawImage(image, 0, 0, width, height);
}

var f;
function create() {
	var a = new Waypoint(new Translation2d(30,30), 0,0);
	var b = new Waypoint(new Translation2d(230,30), 0,0);
	var c = new Waypoint(new Translation2d(230,230), 0,0);
	var d = new Line(a, b);
	var e = new Line(b, c);
	f = new Arc(d, e);
}

function addPoint() {
	var prev;
	if(waypoints.length > 0)
		prev = waypoints[waypoints.length - 1].position;
	else 
		prev = new Translation2d(50, 50);
  //$("tbody#points tr:last td:nth-child(3) input").prop("value", "30");
	$("tbody#points").append("<tr>"
		+"<td><input value='"+(prev.x+40)+"'></td>"
		+"<td><input value='"+(prev.y+40)+"'></td>"
		+"<td><input value='0'></td>"
		+"<td class='comments'><input placeholder='Comments'></td>"
		+"<td><button onclick='$(this).parent().parent().remove();update()'>Delete</button></td></tr>"
	);
	update();
	$('input').unbind("change paste keyup");
	$('input').bind("change paste keyup", function() {
		console.log("change");
		clearTimeout(wto);
			wto = setTimeout(function() {
			update();
		}, 500);
	});
}

function degrees2radians(deg) {
  return deg / 57.297469362;
}

function update() {
  clear();
	waypoints = [];
  points = new WaypointSequence($("tbody#points tr").length);
  eachPoint(function(x,y,theta,comment) {
    var pos = new Translation2d(x,y);
    waypoints.push(new Waypoint(pos, theta, comment));
		drawRotatedRect(pos, robotHeight, robotWidth, -theta, getColorForSpeed(10));
    points.addWaypoint(new WaypointSequence.Waypoint(x, y, theta));
  });
  config = new TrajectoryGenerator.Config();
  config.dt = parseFloat($("td.dt input").val());
  config.max_vel = parseFloat($("td.max_vel input").val());
  config.max_acc = parseFloat($("td.max_acc input").val());
  config.max_jerk = parseFloat($("td.max_jerk input").val());

  if (points.getNumWaypoints() > 1) {
    path = PathGenerator.makePath(points, config, 18.0, "Curve");
    drawPath();
    $("td.time").text("" + path.getLeftWheelTrajectory().getNumSegments() * config.dt);
  } else {
    $("td.time").text("0");
  }
}

function drawPath() {
	color = "#000";
	ctx.beginPath();
	ctx.fillStyle = color;
	ctx.strokeStyle = color;

  eachTimeSlice(function(left, right) {
    ctx.fillStyle = getColorForSpeed(left.vel);
    ctx.strokeStyle = getColorForSpeed(left.vel);
    (new Translation2d(left.x, left.y)).point();
    ctx.fillStyle = getColorForSpeed(right.vel);
    ctx.strokeStyle = getColorForSpeed(right.vel);
    (new Translation2d(right.x, right.y)).point();
  });

	ctx.fill();
	ctx.lineWidth = 0;
	ctx.stroke();
}

function eachTimeSlice(func) {
  var pair = path.getPair();
  var lpoints = pair.left.segments_;
  var rpoints = pair.right.segments_;
  var count = lpoints.length;

  for (var i = 0; i < count; ++i) {
    func(lpoints[i], rpoints[i], i);
  }
}

function eachPoint(func) {
	$('tbody#points').children('tr').each(function () {
    var x = parseInt( $($($(this).children()).children()[0]).val() );
    if(isNaN(x)) {
      x = 0;
    }
    var y = parseInt( $($($(this).children()).children()[1]).val() );
    if(isNaN(y)) {
      y = 0;
    }
    var theta = parseFloat( $($($(this).children()).children()[2]).val() );
    if(isNaN(theta)) {
      theta = 0.0;
    }
    theta = degrees2radians(theta);
    var comment = ( $($($(this).children()).children()[3]).val() );
    func(x,y,theta,comment);
  });
}

function drawRobot() {
	if(waypoints.length > 1) {
		var deltaStart = Translation2d.diff(waypoints[0].position, waypoints[1].position);
		drawRotatedRect(waypoints[0].position, robotHeight, robotWidth, deltaStart.angle, getColorForSpeed(waypoints[1].speed));

		var deltaEnd = Translation2d.diff(waypoints[waypoints.length-2].position, waypoints[waypoints.length-1].position);
		drawRotatedRect(waypoints[waypoints.length-1].position, robotHeight, robotWidth, deltaEnd.angle, getColorForSpeed(0));
	}
}

function drawRotatedRect(pos,w,h,angle,strokeColor,fillColor,noFill){
	w = w*(width/fieldWidth);
	h = h*(height/fieldHeight);
	fillColor = fillColor || "rgba(0,0,0,0)";
	//ctx.save();
	if(noFill == null || !noFill)
		ctx.beginPath();
	ctx.translate(pos.drawX, pos.drawY);
	ctx.rotate(angle);
    	ctx.rect(-w/2, -h/2, w,h);
	ctx.fillStyle = fillColor;
	if(noFill == null || !noFill)
		ctx.fill();
	if(strokeColor != null) {
		ctx.strokeStyle = strokeColor;
		ctx.lineWidth = 4;
		ctx.stroke();
	}
	ctx.rotate(-angle);
	ctx.translate(-pos.drawX, -pos.drawY);
	//ctx.restore();

}

function drawPoints() {
	clear();
	var i = 0;
	ctx.beginPath();
	do {
		var a = Arc.fromPoints(getPoint(i), getPoint(i+1), getPoint(i+2));
		a.fill();
		i++;
	} while(i < waypoints.length - 2);
	ctx.fill();
	i=0;
	do {
		var a = Arc.fromPoints(getPoint(i), getPoint(i+1), getPoint(i+2));
		a.draw();
		i++;
	} while(i < waypoints.length - 2);

}

function getPoint(i) {
	if(i >= waypoints.length)
		return waypoints[waypoints.length - 1];
	else
		return waypoints[i];
}

function importData() {
	$('#upl').click();
	let u = $('#upl')[0];
	$('#upl').change(() => {
		var file =  u.files[0];
		var fr = new FileReader();
		fr.onload = function(e) {
			var c = fr.result;
			let re = /(?:\/\/\sWAYPOINT_DATA:\s)(.*)/gm;
			let reversed = /(?:\/\/\sIS_REVERSED:\s)(.*)/gm;
			let title = /(?:\/\/\sFILE_NAME:\s)(.*)/gm;
			console.log();
			$("#title").val(title.exec(c)[1]);
			$("#isReversed").prop('checked', reversed.exec(c)[1].includes("true"));
			let jde = re.exec(c)[1];
			let jd = JSON.parse(jde);
			// console.log(jd);
			waypoints = [];
			$("tbody#points").empty();
			jd.forEach((wpd) => {
				let wp = new Waypoint(new Translation2d(wpd.position.x, wpd.position.y), wpd.speed, wpd.radius, wpd.comment);
				// console.log(wp);
				$("tbody#points").append("<tr>"
					+"<td><input value='" + wp.position.x + "'></td>"
					+"<td><input value='" + wp.position.y + "'></td>"
					+"<td><input value='" + wp.radius + "'></td>"
					+"<td><input value='" + wp.speed + "'></td>"
					+"<td class='comments'><input placeholder='Comments' value='" + wp.comment + "'></td>"
					+"<td><button onclick='$(this).parent().parent().remove();''>Delete</button></td></tr>"
				);
			})
			update();
			$('input').unbind("change paste keyup");
			$('input').bind("change paste keyup", function() {
				console.log("change");
				clearTimeout(wto);
					wto = setTimeout(function() {
					update();
				}, 500);
			});
		};
		fr.readAsText(file);
})
}

function getDataString() {
	var title = ($("#title").val().length > 0) ? $("#title").val() : "UntitledPath";
	var pathInit = "";
	for(var i=0; i<waypoints.length; i++) {
		pathInit += "        " + waypoints[i].toString() + "\n";
	}
	var startPoint = "new Translation2d(" + waypoints[0].position.x + ", " + waypoints[0].position.y + ")";
	var importStr = "WAYPOINT_DATA: " + JSON.stringify(waypoints);
	var isReversed = $("#isReversed").is(':checked');
  var num_elements = path.getLeftWheelTrajectory().getNumSegments();

  var set_segments = "Trajectory.Segment segment;";
  
  eachTimeSlice(function(left, right, i) {
  var segment = `
        segment = new Trajectory.Segment(${left.pos}, ${left.vel}, ${left.acc}, ${left.jerk}, ${left.heading}, ${left.dt}, ${left.x}, ${left.y});
        left.setSegment(${i}, segment);

        segment = new Trajectory.Segment(${right.pos}, ${right.vel}, ${right.acc}, ${right.jerk}, ${right.heading}, ${right.dt}, ${right.x}, ${right.y});
        right.setSegment(${i}, segment);

`;
    set_segments += segment;
  });

  
	var str = `package org.usfirst.frc862.glitch.paths;
 
import org.usfirst.frc862.util.DynamicPathCommand;
import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.Trajectory;

public class ${title} extends DynamicPathCommand {
    
    @Override
    protected boolean loadPath() {
        int num_elements = ${num_elements};
        Trajectory left = new Trajectory(num_elements);
        Trajectory right = new Trajectory(num_elements);

        ${set_segments}
        
        path = new Path("${title}", new Trajectory.Pair(left, right));
        return true;
    }

    @Override
    public boolean isReversed() {
        return ${isReversed}; 
    }
  
	// ${importStr}
	// IS_REVERSED: ${isReversed}
	// FILE_NAME: ${title}
}`;
	return str;
}

function exportData() { 
	update();
	var title = ($("#title").val().length > 0) ? $("#title").val() : "UntitledPath";
	var blob = new Blob([getDataString()], {type: "text/plain;charset=utf-8"});
	saveAs(blob, title+".java", {type: "text/plain;charset=utf-8"});
}

function showData() {
	update();
	var title = ($("#title").val().length > 0) ? $("#title").val() : "UntitledPath";
	$("#modalTitle").html(title + ".java");
	$(".modal > pre").text(getDataString());
	showModal();
}

function showModal() {
	$(".modal, .shade").removeClass("behind");
	$(".modal, .shade").removeClass("hide");
}

function closeModal() {
	$(".modal, .shade").addClass("hide");
	setTimeout(function() {
		$(".modal, .shade").addClass("behind");
	}, 500);
}

var flipped = false;
function flipField() {
	flipped = !flipped;
	if(flipped)
		ctx.drawImage(imageFlipped, 0, 0, width, height);
	else
		ctx.drawImage(image, 0, 0, width, height);
	update();
}

function lerpColor(color1, color2, factor) {
	var result = color1.slice();
	for (var i=0;i<3;i++) {
	result[i] = Math.round(result[i] + factor*(color2[i]-color1[i]));
	}
	return result;
}

function getColorForSpeed(speed) {
	var u = Math.max(0, Math.min(1, speed/maxSpeed));
	if(u<0.5)
		return RGBToHex(lerpColor(minSpeedColor, [255,255,0], u*2));
	return RGBToHex(lerpColor([255,255,0], maxSpeedColor, u*2-1));

}

function hexToRGB(hex) {
    var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
    return result ? [
        parseInt(result[1], 16),
        parseInt(result[2], 16),
        parseInt(result[3], 16)
    ] : null;
}

function RGBToHex(rgb) {
    return "#" + ((1 << 24) + (rgb[0] << 16) + (rgb[1] << 8) + rgb[2]).toString(16).slice(1);
}

function getNextSpeed(prev) {
	for(var i=0; i<waypoints.length-1; i++) {
		if(waypoints[i] == prev)
			return waypoints[i+1].speed;
	}
	return 0;
}
