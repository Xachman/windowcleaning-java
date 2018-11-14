const spawn = require('child_process').spawn;


var jar = spawn('java', ['-jar','../app.jar']);

jar.stdout.on('data', function(data) {
    console.log('stdout: ' + data.toString())
})
jar.stderr.on('data', function(data) {
    console.log('stderr: ' + data.toString())
})
nw.Window.open('index.html', {}, function(win) {});