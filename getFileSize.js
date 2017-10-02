/*
*  This script is not official MongoDB code, is subject to change 
*  and should be used at your own risk
*/


function getFileSize(files_id) {
    var len = 0;
    var output = {};
    db.fs.files.find({"files_id": files_id},{_id: 0, data: 1}).forEach(function(doc) {
        var b = doc.data;
        if (b instanceof BinData) {
            len += b.length();
        }
    });

    var kbScale = new NumberDecimal("1024"); //1024
    var mbScale = new NumberDecimal("1048576"); //1024*1024
    var gbScale = new NumberDecimal("1073741824"); //1024*1024*1024

    output.bytes = new NumberDecimal(len);
    output.kb = db.fs.files.aggregate([{$project:{_id:0, sum: {$divide:[output.bytes, kbScale]}}}]).next().sum;
    output.mb = db.fs.files.aggregate([{$project:{_id:0, sum: {$divide:[output.bytes, kbScale]}}}]).next().sum;
    output.gb = db.fs.files.aggregate([{$project:{_id:0, sum: {$divide:[output.bytes, kbScale]}}}]).next().sum;

    print(JSON.stringify(output));
};